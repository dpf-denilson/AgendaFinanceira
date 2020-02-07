package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;

import java.util.List;

public interface IAgenda {

    void agendar(Agendamento agendamento) throws AgendaException;
    List<Agendamento> listar();

}
