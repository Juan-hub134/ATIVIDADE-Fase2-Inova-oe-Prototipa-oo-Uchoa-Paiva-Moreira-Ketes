package br.com.locadora.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @NotBlank @Column(nullable = false, unique = true, length = 14)
    private String cpf;
    @NotBlank @Column(nullable = false, length = 100)
    private String nome;
    private String sobrenome;
    private String endereco;
    @Column(name = "dados_bancarios")
    private String dadosBancarios;
    @NotBlank @Email @Column(nullable = false, unique = true)
    private String email;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getDadosBancarios() { return dadosBancarios; }
    public void setDadosBancarios(String dadosBancarios) { this.dadosBancarios = dadosBancarios; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
