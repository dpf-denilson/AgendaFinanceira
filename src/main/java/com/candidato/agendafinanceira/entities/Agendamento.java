package com.candidato.agendafinanceira.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "AGENDA")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, length = 6)
    private String cOrigem;
    @Column(nullable = false, length = 6)
    private String cDestino;
    @Column(nullable = false)
    private BigDecimal vTransf;
    @Column(nullable = false)
    private BigDecimal vTaxa;
    @Column(nullable = false)
    private LocalDateTime dtInclusao;
    @Column(nullable = false)
    private LocalDateTime dtEfeito;

    public Agendamento() {
    }

    public Agendamento(String cOrigem, String cDestino, BigDecimal vTransf, BigDecimal vTaxa, LocalDateTime dtEfeito) {
        assert(cOrigem.length() == 6);
        assert(cDestino.length() == 6);
        assert(vTransf.compareTo(BigDecimal.ZERO) > 0);
        assert(dtEfeito.isAfter(LocalDateTime.now()));

        this.cOrigem = cOrigem;
        this.cDestino = cDestino;
        this.vTransf = vTransf;
        this.vTaxa = vTaxa;
        this.dtInclusao = LocalDateTime.now();
        this.dtEfeito = dtEfeito;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcOrigem() {
        return cOrigem;
    }

    public void setcOrigem(String cOrigem) {
        this.cOrigem = cOrigem;
    }

    public String getcDestino() {
        return cDestino;
    }

    public void setcDestino(String cDestino) {
        this.cDestino = cDestino;
    }

    public BigDecimal getvTransf() {
        return vTransf;
    }

    public void setvTransf(BigDecimal vTransf) {
        this.vTransf = vTransf;
    }

    public BigDecimal getvTaxa() {
        return vTaxa;
    }

    public void setvTaxa(BigDecimal vTaxa) {
        this.vTaxa = vTaxa;
    }

    public LocalDateTime getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(LocalDateTime dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public LocalDateTime getDtEfeito() {
        return dtEfeito;
    }

    public void setDtEfeito(LocalDateTime dtEfeito) {
        this.dtEfeito = dtEfeito;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return  "CO: " + this.cOrigem +
                " / CD: " + this.cDestino +
                " / DTI: " + this.dtInclusao.format(fmt) +
                " / DTE: " + this.dtEfeito.format(fmt) +
                " / VLR: " + this.vTransf.toString() +
                " / TX: " + this.vTaxa.toString();
    }
}
