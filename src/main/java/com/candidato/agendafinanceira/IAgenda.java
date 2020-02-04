package com.candidato.agendafinanceira;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IAgenda {

    void AgendarTransf(IAgendamento agendamento) throws AgendaException;

}
