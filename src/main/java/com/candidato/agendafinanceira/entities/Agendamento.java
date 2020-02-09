package com.candidato.agendafinanceira.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "AGENDA")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Length(max = 6, min = 6)
    @Column(nullable = false, length = 6)
    private String cOrigem;
    @Length(max = 6, min = 6)
    @Column(nullable = false, length = 6)
    private String cDestino;
    @DecimalMin(value = "0.01")
    @Column(nullable = false)
    private BigDecimal vTransf;
    @Positive
    @Column(nullable = false)
    private BigDecimal vTaxa;
    @PastOrPresent
    @Column(nullable = false)
    private LocalDate dtInclusao;
    @FutureOrPresent
    @Column(nullable = false)
    private LocalDate dtEfeito;

    public Agendamento() {
    }

    public Agendamento(String cOrigem, String cDestino, BigDecimal vTransf, BigDecimal vTaxa, LocalDate dtEfeito) {
        this.cOrigem = cOrigem;
        this.cDestino = cDestino;
        this.vTransf = vTransf;
        this.vTaxa = vTaxa;
        this.dtInclusao = LocalDate.now();
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

    public LocalDate getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(LocalDate dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public LocalDate getDtEfeito() {
        return dtEfeito;
    }

    public void setDtEfeito(LocalDate dtEfeito) {
        this.dtEfeito = dtEfeito;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return  "CO: " + this.cOrigem +
                " / CD: " + this.cDestino +
                " / DTI: " + this.dtInclusao.format(fmt) +
                " / DTE: " + this.dtEfeito.format(fmt) +
                " / VLR: " + this.vTransf.toString() +
                " / TX: " + this.vTaxa.toString();
    }
}
