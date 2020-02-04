package com.candidato.agendafinanceira;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IAgendamento {
    public String getContaOrigem();
    public String getContaDestino();
    public BigDecimal getValorTransf();
    public LocalDateTime getDataInclusao();
    public LocalDateTime getDataAgendamento();
}
