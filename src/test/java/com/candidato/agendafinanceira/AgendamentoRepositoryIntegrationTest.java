package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;
import com.candidato.agendafinanceira.repositories.AgendamentoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class AgendamentoRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AgendamentoRepository repo;

    private Agendamento agendamento;

    public Agendamento geraAgendamentoDefault() {
        return new Agendamento(
                "000001",
                "000002",
                BigDecimal.valueOf(123.45),
                BigDecimal.valueOf(1.23),
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

    @Test
    public void inclui_cOrigem_nulo() {
        agendamento.setcOrigem(null);
        assertThrows(DataIntegrityViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_cOrigem_vazio() {
        agendamento.setcOrigem("");
        assertThrows(ConstraintViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_cOrigem_curtoDemais() {
        agendamento.setcOrigem("12345");
        assertThrows(ConstraintViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_cOrigem_longoDemais() {
        agendamento.setcOrigem("1234567");
        assertThrows(ConstraintViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_cDestino_nulo() {
        agendamento.setcDestino(null);
        assertThrows(DataIntegrityViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_cDestino_vazio() {
        agendamento.setcDestino("");
        assertThrows(ConstraintViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_cDestino_curtoDemais() {
        agendamento.setcDestino("12345");
        assertThrows(ConstraintViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_cDestino_longoDemais() {
        agendamento.setcDestino("1234567");
        assertThrows(ConstraintViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_vTransf_nulo() {
        agendamento.setvTransf(null);
        assertThrows(DataIntegrityViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_vTransf_negativo() {
        agendamento.setvTransf(BigDecimal.valueOf(-1));
        assertThrows(ConstraintViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_vTransf_zero() {
        agendamento.setvTransf(BigDecimal.valueOf(0));
        assertThrows(ConstraintViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_vTaxa_nulo() {
        agendamento.setvTaxa(null);
        assertThrows(DataIntegrityViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_vTaxa_negativo() {
        agendamento.setvTaxa(BigDecimal.valueOf(-1));
        assertThrows(ConstraintViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_dtInclusao_nula() {
        agendamento.setDtInclusao(null);
        assertThrows(DataIntegrityViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_dtInclusao_futura() {
        agendamento.setDtInclusao(LocalDate.now().plusDays(1));
        assertThrows(ConstraintViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_dtEfeito_nula() {
        agendamento.setDtEfeito(null);
        assertThrows(DataIntegrityViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_dtEfeito_passada() {
        agendamento.setDtEfeito(LocalDate.now().plusDays(-1));
        assertThrows(ConstraintViolationException.class, () -> repo.save(agendamento));
    }

    @Test
    public void inclui_default() {
        long id = repo.save(agendamento).getId();
        assert agendamento == repo.findById(id);
    }

}