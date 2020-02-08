package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgendaService implements IAgendaService {

    @Autowired
    private AgendamentoRepository repo;

    @Autowired
    private ITaxaLogic logica;

    @Override
    public void agendar(Agendamento agendamento) throws AgendaException {

        if (agendamento.getcOrigem().equals(agendamento.getcDestino())) {
            throw new AgendaException("Conta de origem e destino não podem ser as mesmas.");
        }

        if (agendamento.getvTransf().compareTo(BigDecimal.ZERO) <= 0) {
            throw new AgendaException("Valor de transferência inválido.");
        }

        if (agendamento.getDtEfeito().isBefore(LocalDate.now())) {
            throw new AgendaException("Data de agendamento inválida.");
        }

        agendamento.setvTaxa(logica.calculaTaxa(agendamento));

        repo.save(agendamento);
    }

    @Override
    public List<Agendamento> listar() {
        List<Agendamento> result = new ArrayList<Agendamento>();
        repo.findAll().forEach(result::add);
        return result;
    }

}
