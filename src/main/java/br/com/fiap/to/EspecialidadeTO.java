package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EspecialidadeTO {

    private Long idEspecialidade;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @Size(max = 500)
    private String descricao;

    @Size(max = 500)
    private String urlImagemEspecialidades;

    public EspecialidadeTO() {}

    public EspecialidadeTO(Long idEspecialidade, String nome, String descricao, String urlImagemEspecialidades) {
        this.idEspecialidade = idEspecialidade;
        this.nome = nome;
        this.descricao = descricao;
        this.urlImagemEspecialidades = urlImagemEspecialidades;
    }

    public Long getIdEspecialidade() { return idEspecialidade; }
    public void setIdEspecialidade(Long idEspecialidade) { this.idEspecialidade = idEspecialidade; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getUrlImagemEspecialidades() { return urlImagemEspecialidades; }
    public void setUrlImagemEspecialidades(String urlImagemEspecialidades) { this.urlImagemEspecialidades = urlImagemEspecialidades; }
}