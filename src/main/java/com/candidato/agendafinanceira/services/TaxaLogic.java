package com.candidato.agendafinanceira.services;

import com.candidato.agendafinanceira.entities.Agendamento;
import com.candidato.agendafinanceira.exceptions.AgendaException;
import org.springframework.stereotype.Service;

import static java.time.temporal.ChronoUnit.DAYS;

import java.math.BigDecimal;

@Service
public class TaxaLogic implements ITaxaLogic {

    @Override
    public BigDecimal calculaTaxa(Agendamento agendamento) throws AgendaException {
        long diferencaDias = DAYS.between(agendamento.getDtInclusao(), agendamento.getDtEfeito());

        BigDecimal taxa = BigDecimal.ZERO;

        if (diferencaDias < 0) {
            throw new AgendaException("[Lógica] Data de agendamento inválida.");
        } else if (diferencaDias == 0) { // A
            taxa = BigDecimal.valueOf(3);
            taxa = taxa.add(agendamento.getvTransf().multiply(BigDecimal.valueOf(0.03)));
        } else if (diferencaDias <= 10) { // B
            taxa = BigDecimal.valueOf(12 * diferencaDias);
        } else if (diferencaDias <= 20) { // C (1)
            taxa = agendamento.getvTransf().multiply(BigDecimal.valueOf(0.08));
        } else if (diferencaDias <= 30) { // C (2)
            taxa = agendamento.getvTransf().multiply(BigDecimal.valueOf(0.06));
        } else if (diferencaDias <= 40) { // C (3)
            taxa = agendamento.getvTransf().multiply(BigDecimal.valueOf(0.04));
        } else if (agendamento.getvTransf().compareTo(BigDecimal.valueOf(100000)) == 1 ) { // C (4)
            taxa = agendamento.getvTransf().multiply(BigDecimal.valueOf(0.02));
        } else {
            throw new AgendaException("[Lógica] Nenhuma regra aplicável.");
        }

        return taxa;
    }
}
