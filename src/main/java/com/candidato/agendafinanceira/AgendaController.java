package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;
import com.candidato.agendafinanceira.models.ModelAgendamento;
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
    private IAgendaService agenda;

    public AgendaController() {
    }

    @GetMapping({"/", "/agendar", "/error"})
    public String retornaParaCadastro() {
        return "add-agendamento";
    }

    @GetMapping("/add-agendamento")
    public String showCadastro(Model model) {
        Agendamento agd = new Agendamento();
        //Define valores padrão para facilitar digitação no form do Thymeleaf
        agd.setcOrigem("000000");
        agd.setcDestino("000000");
        agd.setDtInclusao(LocalDate.now()); ;
        agd.setDtEfeito(LocalDate.now());
        agd.setvTransf(BigDecimal.valueOf(101.00));
        agd.setvTaxa(BigDecimal.ZERO);

        model.addAttribute("modelAgendamento", agd);
        return "add-agendamento";
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

                model.addAttribute("agendamentos", agenda.listar());
            }
        } catch (AgendaException ex) {
            erros.add("Erro ao gerar agendamento - " + ex.getMessage());
        }
        if (erros.size() > 0) {
            model.addAttribute("erros", erros);
        }
        return "add-agendamento";
    }

}
