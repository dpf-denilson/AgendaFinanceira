package com.candidato.agendafinanceira.controllers;

import com.candidato.agendafinanceira.exceptions.AgendaException;
import com.candidato.agendafinanceira.services.IAgendaService;
import com.candidato.agendafinanceira.Util;
import com.candidato.agendafinanceira.entities.Agendamento;
import com.candidato.agendafinanceira.models.ModelAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AgendaControllerView {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private IAgendaService agenda;

    private void anexaListagem(Model model) {
        model.addAttribute("agendamentos", agenda.listar());
    }

    private void anexaValoresDefault(Model model) {
        ModelAgendamento agd = new ModelAgendamento();

        //Define valores padrão para facilitar digitação no form do Thymeleaf
        agd.setcOrigem("000001");
        agd.setcDestino("000002");
        agd.setDtEfeito(LocalDate.now());
        agd.setvTransf(100.00f);

        model.addAttribute("modelAgendamento", agd);
    }

    @GetMapping({"/", "/agendar", "/error"})
    public String showCadastro(Model model) {
        anexaValoresDefault(model);
        anexaListagem(model);
        return "agendar";
    }

    @PostMapping("/agendar")
    public String agendarEndpoint(@Valid ModelAgendamento modelAgendamento, BindingResult result, Model model) {
        List<String> erros = new ArrayList<String>();
        try {
            if (result.hasErrors()) {
                erros.add("Erro ao interpretar dados.");
            } else {
                Agendamento agendamento = Util.converteModel(modelAgendamento);
                agendamento.setDtInclusao(LocalDate.now());
                agenda.agendar(agendamento);
            }
        } catch (AgendaException ex) {
            erros.add("Erro ao gerar agendamento - " + ex.getMessage());
        }

        if (erros.size() > 0) {
            model.addAttribute("erros", erros);
        }
        anexaListagem(model);
        return "agendar";
    }

}
