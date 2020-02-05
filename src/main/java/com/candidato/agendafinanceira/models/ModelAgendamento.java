package com.candidato.agendafinanceira.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ModelAgendamento {
    private String cOrigem;
    private String cDestino;
    private Float vTransf;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = JsonFormat.DEFAULT_TIMEZONE)
    private LocalDateTime dtEfeito;

    public ModelAgendamento() {
    }

    public ModelAgendamento(String cOrigem, String cDestino, Float vTransf) {
        this.cOrigem = cOrigem;
        this.cDestino = cDestino;
        this.vTransf = vTransf;
    }

    public String getcOrigem() {
        return cOrigem;
    }

    public void setcOrigem(String cOrigem) {
        this.cOrigem = cOrigem;
    }

    public String getcDestino() {
        return cDestino;
    }

    public void setcDestino(String cDestino) {
        this.cDestino = cDestino;
    }

    public Float getvTransf() {
        return vTransf;
    }

    public void setvTransf(Float vTransf) {
        this.vTransf = vTransf;
    }

    public LocalDateTime getDtEfeito() {
        return dtEfeito;
    }

    public void setDtEfeito(LocalDateTime dtEfeito) {
        this.dtEfeito = dtEfeito;
    }
}
