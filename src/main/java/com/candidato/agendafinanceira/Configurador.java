package com.candidato.agendafinanceira;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Configurador {
/*
    @Bean
    public IAgendaService geraAgenda() {
        return new AgendaService();
    }
*/
    @Bean
    public ITaxaLogic geraTaxaLogic() {
        return new TaxaLogic();
    }

}
