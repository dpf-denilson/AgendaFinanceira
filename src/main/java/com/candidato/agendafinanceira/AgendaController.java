package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AgendaController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private IAgenda agenda;

    @Autowired
    private ITaxaLogic logica;

    public AgendaController() {
    }

    @GetMapping({"/add-agendamento", "/"})
    public String showSignUpForm(Model model) {
        Agendamento agd = new Agendamento();
        //Define valores padrão para facilitar digitação no form do Thymeleaf
        agd.setcOrigem("000000");
        agd.setcDestino("000000");
        agd.setDtEfeito(LocalDate.now());
        agd.setvTransf(BigDecimal.valueOf(100.00));

        model.addAttribute("agendamento", agd);
        return "add-agendamento";
    }

    @PostMapping("/agendar")
    public String agendarEndpoint(@Valid Agendamento agendamento, BindingResult result, Model model) {
        List<String> erros = new ArrayList<String>();
        try {
            if (result.hasErrors()) {
                erros.add("Erro ao interpretar dados.");
            }
            agendamento.setDtInclusao(LocalDate.now());
            agendamento.setvTaxa(logica.calculaTaxa(agendamento));
            agenda.agendar(agendamento);

            model.addAttribute("agendamentos", agenda.listar());
        } catch (AgendaException ex) {
            erros.add("Erro ao gerar agendamento - " + ex.getMessage());
        }
        if (erros.size() > 0) {
            model.addAttribute("erros", erros);
        }
        return "add-agendamento";
    }

}
