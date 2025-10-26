package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ServicoTO {

    private Long idServico;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotNull
    private Long idEspecialidade;


    public ServicoTO() {}

    public ServicoTO(Long idServico, String nome, Long idEspecialidade) {
        this.idServico = idServico;
        this.nome = nome;
        this.idEspecialidade = idEspecialidade;
    }

    public Long getIdServico() { return idServico; }
    public void setIdServico(Long idServico) { this.idServico = idServico; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Long getIdEspecialidade() { return idEspecialidade; }
    public void setIdEspecialidade(Long idEspecialidade) { this.idEspecialidade = idEspecialidade; }
}