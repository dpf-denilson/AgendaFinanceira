package com.candidato.agendafinanceira;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBHandler {

    public void createTable(String tableDef) throws SQLException;
    public boolean existTable(String tableName) throws SQLException;
    public ResultSet runQuery(String query) throws SQLException;
    public void runCommand(String comm) throws SQLException;

}
