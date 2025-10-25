package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UnidadeTO {

    private Long id;

    @NotBlank(message = "O código da unidade é obrigatório")
    @Size(min = 1, max = 10, message = "O código deve ter no máximo 10 caracteres")
    private String cdUnidade;

    @NotBlank(message = "O endereço é obrigatório")
    @Size(max = 100, message = "O endereço deve ter no máximo 100 caracteres")
    private String endereco;

    @NotBlank(message = "O CEP é obrigatório")
    @Size(min = 8, max = 8, message = "O CEP deve ter 8 dígitos")
    private String cep;

    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    private String telefone;

    public UnidadeTO() {
    }

    public UnidadeTO(Long id, String cdUnidade, String endereco, String cep, String telefone) {
        this.id = id;
        this.cdUnidade = cdUnidade;
        this.endereco = endereco;
        this.cep = cep;
        this.telefone = telefone;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCdUnidade() { return cdUnidade; }
    public void setCdUnidade(String cdUnidade) { this.cdUnidade = cdUnidade; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}