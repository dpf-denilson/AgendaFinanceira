package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@WebMvcTest
public class AgendaServiceIntegrationTest {

    @TestConfiguration
    static class AgendaServiceTestContextConfiguration {

        @Bean
        public IAgendaService geraAgenda() {
            return new AgendaService();
        }

        @Bean
        public ITaxaLogic geraTaxaLogic() {
            return new TaxaLogic();
        }

    }

    @Autowired
    private IAgendaService agenda;

    @MockBean
    private AgendamentoRepository repo;

    private Agendamento agendamento;

    public Agendamento geraAgendamentoDefault() {
        return new Agendamento(
                "000001",
                "000002",
                BigDecimal.valueOf(200000),
                BigDecimal.ZERO,
                LocalDate.now().plusDays(1));
    }

    @BeforeEach
    public void inicia() {
        agendamento = geraAgendamentoDefault();
    }

    @AfterEach
    public void finaliza() {
        agendamento = null;
    }

    private BigDecimal estimativaRegraA(@NotNull Agendamento agendamento) {
        return BigDecimal.valueOf(3).add(agendamento.getvTransf().multiply(BigDecimal.valueOf(0.03)));
    }

    private BigDecimal estimativaRegraB(@NotNull Agendamento agendamento, int diferencaDias) {
        return BigDecimal.valueOf(12 * diferencaDias);
    }

    private BigDecimal estimativaRegraC1(@NotNull Agendamento agendamento) {
        return agendamento.getvTransf().multiply(BigDecimal.valueOf(0.08));
    }

    private BigDecimal estimativaRegraC2(@NotNull Agendamento agendamento) {
        return agendamento.getvTransf().multiply(BigDecimal.valueOf(0.06));
    }

    private BigDecimal estimativaRegraC3(@NotNull Agendamento agendamento) {
        return agendamento.getvTransf().multiply(BigDecimal.valueOf(0.04));
    }

    private BigDecimal estimativaRegraC4(@NotNull Agendamento agendamento) {
        return agendamento.getvTransf().multiply(BigDecimal.valueOf(0.02));
    }

    @Test
    public void testa_taxa_regraA() throws AgendaException {
        agendamento.setDtEfeito(LocalDate.now());
        agenda.agendar(agendamento);
        assert agendamento.getvTaxa().equals(estimativaRegraA(agendamento));
    }

    @Test
    public void testa_taxa_regraB() throws AgendaException {
        agendamento.setDtEfeito(LocalDate.now().plusDays(5));
        agenda.agendar(agendamento);
        assert agendamento.getvTaxa().equals(estimativaRegraB(agendamento, 5));
    }

    @Test
    public void testa_taxa_regraC1() throws AgendaException {
        agendamento.setDtEfeito(LocalDate.now().plusDays(15));
        agenda.agendar(agendamento);
        assert agendamento.getvTaxa().equals(estimativaRegraC1(agendamento));
    }

    @Test
    public void testa_taxa_regraC2() throws AgendaException {
        agendamento.setDtEfeito(LocalDate.now().plusDays(25));
        agenda.agendar(agendamento);
        assert agendamento.getvTaxa().equals(estimativaRegraC2(agendamento));
    }

    @Test
    public void testa_taxa_regraC3() throws AgendaException {
        agendamento.setDtEfeito(LocalDate.now().plusDays(35));
        agenda.agendar(agendamento);
        assert agendamento.getvTaxa().equals(estimativaRegraC3(agendamento));
    }

    @Test
    public void testa_taxa_regraC4() throws AgendaException {
        agendamento.setDtEfeito(LocalDate.now().plusDays(45));
        agenda.agendar(agendamento);
        assert agendamento.getvTaxa().equals(estimativaRegraC4(agendamento));
    }

    @Test
    public void testa_taxa_regraC4_valorBaixo() throws AgendaException {
        agendamento.setDtEfeito(LocalDate.now().plusDays(45));
        agendamento.setvTransf(BigDecimal.ONE);
        assertThrows(AgendaException.class, () ->  agenda.agendar(agendamento));
    }

    @Test
    public void testa_taxa_regra50Dias() throws AgendaException {
        for (int diferencaDias = 0; diferencaDias <= 50; diferencaDias++) {
            agendamento.setDtEfeito(LocalDate.now().plusDays(diferencaDias));
            BigDecimal taxaEstimada;
            if (diferencaDias == 0) { // A
                taxaEstimada = estimativaRegraA(agendamento);
            } else if (diferencaDias <= 10) { // B
                taxaEstimada = estimativaRegraB(agendamento, diferencaDias);
            } else if (diferencaDias <= 20) { // C (1)
                taxaEstimada = estimativaRegraC1(agendamento);
            } else if (diferencaDias <= 30) { // C (2)
                taxaEstimada = estimativaRegraC2(agendamento);
            } else if (diferencaDias <= 40) { // C (3)
                taxaEstimada = estimativaRegraC3(agendamento);
            } else {
                taxaEstimada = estimativaRegraC4(agendamento);
            }
            agenda.agendar(agendamento);
            assert agendamento.getvTaxa().equals(taxaEstimada);
        }
    }

}
