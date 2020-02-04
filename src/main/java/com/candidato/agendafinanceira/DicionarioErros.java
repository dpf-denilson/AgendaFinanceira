package com.candidato.agendafinanceira;

public enum DicionarioErros {

    STATEMENT(0, "Houve erro ao gerar o statement SQL."),
    RUNCOMMAND(1, "Erro ao executar comando."),
    RUNQUERY(2, "Erro ao realizar consulta.");

    private final int codigo;
    private final int severidade;
    private final String descricao;

    private DicionarioErros(int codigo, String descricao, int severidade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.severidade = severidade;
    }

    private DicionarioErros(int codigo, String descricao) {
        this(codigo, descricao, 0);
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getSeveridade() {
        return severidade;
    }

    public boolean isCritico() {
        return severidade > 10;
    }

    @Override
    public String toString() {
        return codigo + " [" + severidade + (isCritico() ? " - Crítico" : "") + "]: " + descricao;
    }

}
