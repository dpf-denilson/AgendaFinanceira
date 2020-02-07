package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;
import com.candidato.agendafinanceira.models.ModelAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class AgendaControllerApi {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private IAgenda agenda;

    @Autowired
    private ITaxaLogic logica;

    public AgendaControllerApi() {
    }

    private Agendamento converteModel(ModelAgendamento model) {
        return new Agendamento(model.getcOrigem(),
                model.getcDestino(),
                BigDecimal.valueOf(model.getvTransf()),
                BigDecimal.ZERO,
                model.getDtEfeito());
    }

    @PostMapping("/api/agendar")
    @ResponseBody
    public ResponseEntity agendarAPIEndpoint(@RequestBody ModelAgendamento model, BindingResult result) {
        try {
            if (result.hasErrors()) {
                throw new AgendaException("Objeto JSON inválido!");
            }

            Agendamento agendamento = converteModel(model);
            agendamento.setDtInclusao(LocalDate.now());
            agendamento.setvTaxa(logica.calculaTaxa(agendamento));
            agenda.agendar(agendamento);
            return new ResponseEntity(new Object() {public final boolean resposta = true; }, HttpStatus.CREATED);
        } catch (AgendaException ex) {
            return new ResponseEntity(new Object() {public final String resposta = ex.getMessage(); }, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/api/listar")
    public List<Agendamento> listarEndpoint() {
        return agenda.listar();
    }

}
