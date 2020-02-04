package com.candidato.agendafinanceira;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DBHandler implements IDBHandler{

    private final String url;

    public DBHandler(String url) {
        this.url = url;
    }

    @Override
    public void createTable(String tableDef) throws SQLException {

    }

    @Override
    public boolean existTable(String tableName) throws SQLException {
        return false;
    }

    @Override
    public void runCommand(String comm) throws SQLException {
        try {
            getStatement().execute(comm);
        } catch(SQLException ex) {
            geraLog(DicionarioErros.RUNCOMMAND, ex);
            throw ex;
        }
    }

    @Override
    public ResultSet runQuery(String query) throws SQLException {
        try {
            return getStatement().executeQuery(query);
        } catch (SQLException ex) {
            geraLog(DicionarioErros.RUNQUERY, ex);
            throw ex;
        }
    }

    private Statement getStatement() throws SQLException {
        try {
            Connection con = DriverManager.getConnection(url);
            return con.createStatement();
        } catch (SQLException ex) {
            geraLog(DicionarioErros.STATEMENT, ex);
            throw ex;
        }
    }

    private void geraLog(DicionarioErros erro, SQLException ex) {
        // FIXME Implementar LOG
    }


}
