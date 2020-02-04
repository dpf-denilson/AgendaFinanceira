package com.candidato.agendafinanceira;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class AgendaController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/")
    public String index() {
        return "Você requisitou um GET!";
    }

    @PostMapping("/")
    public String teste() {
        IDBHandler dbh = (IDBHandler)context.getBean("DBHandler");
        try {
            ResultSet result = dbh.runQuery("SELECT * FROM TESTE");
            String msg = "";
            while (result.next()) {
                for (int i = 0; i < 2; i++) {
                    msg += result.getString(i) + ", ";
                }
                msg += "\n";
            }
            return "Ok!\n" + msg;
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }


}
