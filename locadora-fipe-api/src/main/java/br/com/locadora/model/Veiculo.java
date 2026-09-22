package br.com.locadora.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "veiculo")
public class Veiculo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    private Long id;

    @NotBlank @Column(nullable = false, unique = true, length = 10)
    private String placa;
    @NotBlank @Column(nullable = false)
    private String marca;
    @NotBlank @Column(nullable = false)
    private String modelo;
    @NotBlank @Column(nullable = false, length = 30)
    private String tipo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
