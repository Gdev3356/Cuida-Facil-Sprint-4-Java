package br.com.fiap.to;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class PacienteTO {

    private Long idPaciente;

    @NotBlank
    @Size(max = 11)
    private String cpf;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @Size(max = 20)
    private String telefone;

    @Email
    @Size(max = 100)
    private String email;

    @NotNull
    @PastOrPresent
    private LocalDate dataNascimento;

    @Size(max = 8)
    private String cep;

    public PacienteTO() {}

    public PacienteTO(Long idPaciente, String cpf, String nome, String telefone, String email, LocalDate dataNascimento, String cep) {
        this.idPaciente = idPaciente;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
    }

    public Long getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Long idPaciente) { this.idPaciente = idPaciente; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
}