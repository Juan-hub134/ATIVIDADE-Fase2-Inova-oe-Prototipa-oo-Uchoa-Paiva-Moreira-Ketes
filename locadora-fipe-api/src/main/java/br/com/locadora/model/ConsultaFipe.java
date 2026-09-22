package br.com.locadora.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "consulta_fipe")
public class ConsultaFipe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    @NotBlank @Column(name = "codigo_fipe", nullable = false)
    private String codigoFipe;
    @NotNull @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;
    @Column(name = "mes_referencia")
    private String mesReferencia;
    @NotNull @Column(name = "data_consulta", nullable = false)
    private LocalDate dataConsulta;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
    public String getCodigoFipe() { return codigoFipe; }
    public void setCodigoFipe(String codigoFipe) { this.codigoFipe = codigoFipe; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public String getMesReferencia() { return mesReferencia; }
    public void setMesReferencia(String mesReferencia) { this.mesReferencia = mesReferencia; }
    public LocalDate getDataConsulta() { return dataConsulta; }
    public void setDataConsulta(LocalDate dataConsulta) { this.dataConsulta = dataConsulta; }
}
