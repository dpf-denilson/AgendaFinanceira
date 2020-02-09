package com.candidato.agendafinanceira.services;

import com.candidato.agendafinanceira.entities.Agendamento;
import com.candidato.agendafinanceira.exceptions.AgendaException;

import java.math.BigDecimal;

public interface ITaxaLogic {

    public BigDecimal calculaTaxa(Agendamento agendamento) throws AgendaException;

}
