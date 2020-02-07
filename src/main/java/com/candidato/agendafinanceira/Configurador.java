package com.candidato.agendafinanceira;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurador {

    @Bean
    public IAgenda geraAgenda() {
        return new Agenda();
    }

    @Bean
    public ITaxaLogic geraTaxaLogic() {
        return new TaxaLogic();
    }

}
