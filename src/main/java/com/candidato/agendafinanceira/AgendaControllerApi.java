package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;
import com.candidato.agendafinanceira.models.ModelAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AgendaControllerApi {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private IAgendaService agenda;

    public AgendaControllerApi() {
    }

    @PostMapping("/agendar")
    @ResponseBody
    public ResponseEntity agendarAPIEndpoint(@Valid @RequestBody ModelAgendamento model, BindingResult result) {
        try {
            if (result.hasErrors()) {
                throw new AgendaException("Objeto JSON inválido!");
            }

            Agendamento agendamento = Util.converteModel(model);
            agendamento.setDtInclusao(LocalDate.now());
            agenda.agendar(agendamento);
            return new ResponseEntity(new Object() {public final boolean resposta = true; }, HttpStatus.CREATED);
        } catch (AgendaException ex) {
            return new ResponseEntity(new Object() {public final String resposta = ex.getMessage(); }, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/listar")
    public List<Agendamento> listarEndpoint() {
        return agenda.listar();
    }

}
