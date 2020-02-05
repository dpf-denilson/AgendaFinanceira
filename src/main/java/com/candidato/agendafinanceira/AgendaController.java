package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;
import com.candidato.agendafinanceira.models.ModelAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
public class AgendaController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AgendamentoRepository repo;

    public AgendaController() {
    }

    @GetMapping("/")
    public String index() {
        return "Você requisitou um GET!";
    }

    @PostMapping("/")
    public String api(@RequestBody ModelAgendamento agendamento) {
        String resp = "";

        if (agendamento.getcOrigem().length() != 6) {
            // FIXME https://www.baeldung.com/spring-mvc-controller-custom-http-status-code
        }

        repo.save(new Agendamento(agendamento.getcOrigem(),
                agendamento.getcDestino(),
                BigDecimal.valueOf(agendamento.getvTransf()),
                BigDecimal.valueOf(5.25),
                agendamento.getDtEfeito())
        );

        for (Agendamento agd : repo.findAll()) {
            resp += agd.toString() + "\n";
        }
        return resp;
    }


}
