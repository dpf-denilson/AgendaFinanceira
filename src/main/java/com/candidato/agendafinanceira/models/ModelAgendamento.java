package com.candidato.agendafinanceira.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class ModelAgendamento {
    @NotBlank(message = "Necessário informar conta de origem")
    @Length(max = 6, min = 6, message = "Formato de conta de origem inválido")
    private String cOrigem;
    @NotBlank(message = "Necessário informar conta de destino")
    @Length(max = 6, min = 6, message = "Formato de conta de destino inválido")
    private String cDestino;
    @NotNull(message = "Necessário informar valor da transferência")
    @Positive(message = "Valor da transferência deve ser maior que zero")
    @DecimalMin(value = "0.01", message = "Valor transferência não deve ser inferior a um centavo")
    private Float vTransf;
    @NotNull(message = "Necessário informar data de agendamento")
    @FutureOrPresent(message = "Data de agendamento não pode ser anterior a data atual")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dtEfeito;

    public ModelAgendamento() {
    }

    public ModelAgendamento(String cOrigem, String cDestino, Float vTransf, LocalDate dtEfeito) {
        this.cOrigem = cOrigem;
        this.cDestino = cDestino;
        this.vTransf = vTransf;
        this.dtEfeito = dtEfeito;
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

    public LocalDate getDtEfeito() {
        return dtEfeito;
    }

    public void setDtEfeito(LocalDate dtEfeito) {
        this.dtEfeito = dtEfeito;
    }

}
