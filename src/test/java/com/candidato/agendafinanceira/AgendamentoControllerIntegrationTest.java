package com.candidato.agendafinanceira;

import com.candidato.agendafinanceira.entities.Agendamento;
import com.candidato.agendafinanceira.models.ModelAgendamento;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AgendaControllerApi.class)
public class AgendamentoControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IAgendaService agenda;

    private ModelAgendamento model;
    private static ObjectMapper mapper;

    private ModelAgendamento geraModeloAgendamento() {
        return new ModelAgendamento("000001", "000002", 100.00f, LocalDate.now());
    }

    @BeforeAll
    private static void initAll() {
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
    }

    @BeforeEach
    private void initEach() {
        model = geraModeloAgendamento();
    }

    private ResultActions realizaAgendamento(ModelAgendamento mdl) throws Exception {
        return mvc.perform(post("/api/agendar")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(mapper.writeValueAsString(mdl)));
    }

    @Test
    public void givenAgendamento_thenReturnCreated() throws Exception {
        realizaAgendamento(model)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.resposta", is(true)));
    }

    @Test
    public void givenInvalidAgendamento_thenReturnUnable() throws Exception {
        model.setcDestino("1234567");
        realizaAgendamento(model)
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void givenAgendamentos_whenGetAgendamentos_thenReturnCreated() throws Exception {
        List<ModelAgendamento> models = new ArrayList<ModelAgendamento>();
        List<Agendamento> entities = new ArrayList<Agendamento>();

        models.add(geraModeloAgendamento());
        models.get(0).setcOrigem("000003");
        realizaAgendamento(models.get(0));
        models.add(geraModeloAgendamento());
        models.get(1).setcOrigem("000004");
        realizaAgendamento(models.get(1));

        models.forEach(mdl -> entities.add(Util.converteModel(mdl)));
        given(agenda.listar()).willReturn(entities);

        mvc.perform(get("/api/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].cOrigem", is("000003")))
                .andExpect(jsonPath("$[1].cOrigem", is("000004")));
    }

}
