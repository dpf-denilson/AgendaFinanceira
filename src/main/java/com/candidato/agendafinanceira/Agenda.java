package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Agenda implements IAgenda {

    @Autowired
    private AgendamentoRepository repo;

    private boolean validaConta(String conta) {
        return conta.length() != 6;
    }

    @Override
    public void agendar(Agendamento agendamento) throws AgendaException {
        if (validaConta(agendamento.getcOrigem())) {
            throw new AgendaException("Conta origem inválida.");
        }

        if (validaConta(agendamento.getcDestino())) {
            throw new AgendaException("Conta destino inválida.");
        }

        if (agendamento.getcOrigem().equals(agendamento.getcDestino())) {
            throw new AgendaException("Conta de origem e destino não podem ser as mesmas.");
        }

        if (agendamento.getvTransf().compareTo(BigDecimal.ZERO) <= 0) {
            throw new AgendaException("Valor de transferência inválido.");
        }

        if (agendamento.getDtEfeito().isBefore(LocalDate.now())) {
            throw new AgendaException("Data de agendamento inválida.");
        }

        repo.save(agendamento);
    }

    @Override
    public List<Agendamento> listar() {
        List<Agendamento> result = new ArrayList<Agendamento>();
        repo.findAll().forEach(result::add);
        return result;
    }

}
