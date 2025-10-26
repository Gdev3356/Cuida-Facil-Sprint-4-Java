package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class ChatbotTO {

    private Long idAtendimento;

    @NotNull
    private Long idPaciente;

    private LocalDateTime horaInteracao;

    @NotBlank
    @Size(max = 100)
    private String intencaoUsuario;

    @NotBlank
    private String textoResposta;


    public ChatbotTO() {}

    public ChatbotTO(Long idAtendimento, Long idPaciente, LocalDateTime horaInteracao, String intencaoUsuario, String textoResposta) {
        this.idAtendimento = idAtendimento;
        this.idPaciente = idPaciente;
        this.horaInteracao = horaInteracao;
        this.intencaoUsuario = intencaoUsuario;
        this.textoResposta = textoResposta;
    }

    public Long getIdAtendimento() { return idAtendimento; }
    public void setIdAtendimento(Long idAtendimento) { this.idAtendimento = idAtendimento; }
    public Long getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Long idPaciente) { this.idPaciente = idPaciente; }
    public LocalDateTime getHoraInteracao() { return horaInteracao; }
    public void setHoraInteracao(LocalDateTime horaInteracao) { this.horaInteracao = horaInteracao; }
    public String getIntencaoUsuario() { return intencaoUsuario; }
    public void setIntencaoUsuario(String intencaoUsuario) { this.intencaoUsuario = intencaoUsuario; }
    public String getTextoResposta() { return textoResposta; }
    public void setTextoResposta(String textoResposta) { this.textoResposta = textoResposta; }
}