package br.com.fiap.to;

import java.util.List;

public class UnidadeTO {
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String horario;
    private List<String> servicos;

    public UnidadeTO() {}

    public UnidadeTO(int id, String nome, String endereco, String telefone, String horario, List<String> servicos) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.horario = horario;
        this.servicos = servicos;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    public List<String> getServicos() { return servicos; }
    public void setServicos(List<String> servicos) { this.servicos = servicos; }

    public String getServicosFormatados() {
        if (servicos == null || servicos.isEmpty()) return "Nenhum serviço listado.";
        return String.join("\n- ", servicos);
    }
}