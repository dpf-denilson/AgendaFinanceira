package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;

import java.math.BigDecimal;

public interface ITaxaLogic {

    public BigDecimal calculaTaxa(Agendamento agendamento) throws AgendaException;

}
