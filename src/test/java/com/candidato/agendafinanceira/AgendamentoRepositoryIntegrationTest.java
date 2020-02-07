package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;

@DataJpaTest
public class AgendamentoRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AgendamentoRepository repo;

    @Test
    public void inclui() {
        Agendamento agendamento = new Agendamento("000001", "000002", BigDecimal.valueOf(123.45), BigDecimal.ZERO, LocalDate.of(2020, 2, 7));

    }
}
