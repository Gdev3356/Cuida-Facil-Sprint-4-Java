package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UnidadeTO {

    private Long idUnidade;

    @NotBlank
    @Size(max = 20)
    private String cdUnidade;

    @NotBlank
    @Size(max = 255)
    private String endereco; // Mapeia para END_UNIDADE

    @Size(max = 20)
    private String telefone; // Mapeia para TEL_UNIDADE

    @Size(max = 100)
    private String horario; // Mapeia para HR_UNIDADE

    @Size(max = 8)
    private String cep; // Mapeia para CEP_UNIDADE

    public UnidadeTO() {
    }

    public UnidadeTO(Long idUnidade, String cdUnidade, String endereco, String telefone, String horario, String cep) {
        this.idUnidade = idUnidade;
        this.cdUnidade = cdUnidade;
        this.endereco = endereco;
        this.telefone = telefone;
        this.horario = horario;
        this.cep = cep;
    }

    public Long getIdUnidade() { return idUnidade; }
    public void setIdUnidade(Long idUnidade) { this.idUnidade = idUnidade; }

    public String getCdUnidade() { return cdUnidade; }
    public void setCdUnidade(String cdUnidade) { this.cdUnidade = cdUnidade; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
}