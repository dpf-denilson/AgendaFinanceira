package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;
import com.candidato.agendafinanceira.models.ModelAgendamento;

import java.math.BigDecimal;

public abstract class Util {

    public static Agendamento converteModel(ModelAgendamento model) {
        return new Agendamento(model.getcOrigem(),
                model.getcDestino(),
                BigDecimal.valueOf(model.getvTransf()),
                BigDecimal.ZERO,
                model.getDtEfeito());
    }

}
