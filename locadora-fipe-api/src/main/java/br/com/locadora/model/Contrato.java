package br.com.locadora.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "contrato")
public class Contrato {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_contrato")
    private Long numeroContrato;

    @NotNull @Column(nullable = false)
    private LocalDate data;
    @NotBlank @Column(name = "tipo_pagamento", nullable = false)
    private String tipoPagamento;
    @NotNull @Column(name = "inicio_vigencia", nullable = false)
    private LocalDate inicioVigencia;
    @NotNull @Column(name = "fim_vigencia", nullable = false)
    private LocalDate fimVigencia;

    @ManyToOne(optional = false) @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @ManyToOne(optional = false) @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;
    @ManyToOne(optional = false) @JoinColumn(name = "id_atendente")
    private Atendente atendente;

    public Long getNumeroContrato() { return numeroContrato; }
    public void setNumeroContrato(Long numeroContrato) { this.numeroContrato = numeroContrato; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public String getTipoPagamento() { return tipoPagamento; }
    public void setTipoPagamento(String tipoPagamento) { this.tipoPagamento = tipoPagamento; }
    public LocalDate getInicioVigencia() { return inicioVigencia; }
    public void setInicioVigencia(LocalDate inicioVigencia) { this.inicioVigencia = inicioVigencia; }
    public LocalDate getFimVigencia() { return fimVigencia; }
    public void setFimVigencia(LocalDate fimVigencia) { this.fimVigencia = fimVigencia; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
    public Atendente getAtendente() { return atendente; }
    public void setAtendente(Atendente atendente) { this.atendente = atendente; }
}
