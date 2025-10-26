package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MedicoTO {

    private Long idMedico;

    @NotBlank
    @Size(max = 15)
    private String crm;

    @NotBlank
    @Size(max = 100)
    private String nome;

    public MedicoTO() {}

    public MedicoTO(Long idMedico, String crm, String nome) {
        this.idMedico = idMedico;
        this.crm = crm;
        this.nome = nome;
    }

    public Long getIdMedico() { return idMedico; }
    public void setIdMedico(Long idMedico) { this.idMedico = idMedico; }
    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}