package com.candidato.agendafinanceira.services;

import com.candidato.agendafinanceira.entities.Agendamento;
import com.candidato.agendafinanceira.exceptions.AgendaException;

import java.util.List;

public interface IAgendaService {

    void agendar(Agendamento agendamento) throws AgendaException;
    List<Agendamento> listar();

}
