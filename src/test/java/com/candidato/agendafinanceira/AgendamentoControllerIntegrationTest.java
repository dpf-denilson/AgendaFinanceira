package com.candidato.agendafinanceira;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AgendaController.class)
public class AgendamentoControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IAgendaService agenda;

    @Test
    public void test1()
            throws Exception {
        // FIXME https://www.baeldung.com/spring-boot-testing
    }

}
