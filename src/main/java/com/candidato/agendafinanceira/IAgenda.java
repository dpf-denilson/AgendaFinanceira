package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;

public interface IAgenda {

    void AgendarTransf(Agendamento agendamento) throws AgendaException;

}
