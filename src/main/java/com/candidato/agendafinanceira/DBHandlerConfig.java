package com.candidato.agendafinanceira;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackageClasses = DBHandler.class)
public class DBHandlerConfig {

    @Bean
    @Scope("singleton")
    public String dbHandler() {
        return "jdbc:h2:mem:";
    }
}
