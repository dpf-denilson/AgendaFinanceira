package com.candidato.agendafinanceira;

import java.math.BigDecimal;

public interface ITaxaLogic {

    public BigDecimal calculaTaxa(IAgendamento agendamento);

}
