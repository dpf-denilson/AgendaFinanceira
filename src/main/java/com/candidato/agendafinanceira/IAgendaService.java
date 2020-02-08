package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;

import java.util.List;

public interface IAgendaService {

    void agendar(Agendamento agendamento) throws AgendaException;
    List<Agendamento> listar();

}
