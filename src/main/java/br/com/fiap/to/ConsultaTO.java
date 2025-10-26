package br.com.fiap.to;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class ConsultaTO {

    private Long idConsulta;

    @NotBlank
    @Size(max = 16)
    private String protocolo;

    @NotNull
    @FutureOrPresent
    private LocalDateTime dataConsulta;

    @NotBlank
    @Pattern(regexp = "AGENDADA|REAGENDADA|CONCLUIDA|CANCELADA")
    private String status;

    @NotBlank
    @Pattern(regexp = "PRESENCIAL|TELECONSULTA")
    private String tipoAtendimento;

    @NotNull
    private Long idPaciente;

    @NotNull
    private Long idMedico;

    @NotNull
    private Long idUnidade;

    @NotNull
    private Long idEspecialidade;

    public ConsultaTO() {}

    public ConsultaTO(Long idConsulta, String protocolo, LocalDateTime dataConsulta, String status, String tipoAtendimento, Long idPaciente, Long idMedico, Long idUnidade, Long idEspecialidade) {
        this.idConsulta = idConsulta;
        this.protocolo = protocolo;
        this.dataConsulta = dataConsulta;
        this.status = status;
        this.tipoAtendimento = tipoAtendimento;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.idUnidade = idUnidade;
        this.idEspecialidade = idEspecialidade;
    }

    public Long getIdConsulta() { return idConsulta; }
    public void setIdConsulta(Long idConsulta) { this.idConsulta = idConsulta; }
    public String getProtocolo() { return protocolo; }
    public void setProtocolo(String protocolo) { this.protocolo = protocolo; }
    public LocalDateTime getDataConsulta() { return dataConsulta; }
    public void setDataConsulta(LocalDateTime dataConsulta) { this.dataConsulta = dataConsulta; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getTipoAtendimento() { return tipoAtendimento; }
    public void setTipoAtendimento(String tipoAtendimento) { this.tipoAtendimento = tipoAtendimento; }
    public Long getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Long idPaciente) { this.idPaciente = idPaciente; }
    public Long getIdMedico() { return idMedico; }
    public void setIdMedico(Long idMedico) { this.idMedico = idMedico; }
    public Long getIdUnidade() { return idUnidade; }
    public void setIdUnidade(Long idUnidade) { this.idUnidade = idUnidade; }
    public Long getIdEspecialidade() { return idEspecialidade; }
    public void setIdEspecialidade(Long idEspecialidade) { this.idEspecialidade = idEspecialidade; }
}