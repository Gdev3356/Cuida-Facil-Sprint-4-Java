package br.com.fiap.to;

import java.time.LocalDateTime;

public class ConsultaDetalhadaTO {
    private Long idConsulta;
    private String protocolo;
    private LocalDateTime dataConsulta;
    private String status;
    private String tipoAtendimento;

    private Long idPaciente;
    private Long idMedico;
    private Long idUnidade;
    private Long idEspecialidade;

    private String nomePaciente;
    private String nomeMedico;
    private String nomeUnidade;
    private String nomeEspecialidade;

    private String crmMedico;
    private String enderecoUnidade;

    public ConsultaDetalhadaTO() {}

    public ConsultaDetalhadaTO(Long idConsulta, String protocolo, LocalDateTime dataConsulta,
                               String status, String tipoAtendimento,
                               Long idPaciente, String nomePaciente,
                               Long idMedico, String nomeMedico, String crmMedico,
                               Long idUnidade, String nomeUnidade, String enderecoUnidade,
                               Long idEspecialidade, String nomeEspecialidade) {
        this.idConsulta = idConsulta;
        this.protocolo = protocolo;
        this.dataConsulta = dataConsulta;
        this.status = status;
        this.tipoAtendimento = tipoAtendimento;
        this.idPaciente = idPaciente;
        this.nomePaciente = nomePaciente;
        this.idMedico = idMedico;
        this.nomeMedico = nomeMedico;
        this.crmMedico = crmMedico;
        this.idUnidade = idUnidade;
        this.nomeUnidade = nomeUnidade;
        this.enderecoUnidade = enderecoUnidade;
        this.idEspecialidade = idEspecialidade;
        this.nomeEspecialidade = nomeEspecialidade;
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

    public String getNomePaciente() { return nomePaciente; }
    public void setNomePaciente(String nomePaciente) { this.nomePaciente = nomePaciente; }

    public Long getIdMedico() { return idMedico; }
    public void setIdMedico(Long idMedico) { this.idMedico = idMedico; }

    public String getNomeMedico() { return nomeMedico; }
    public void setNomeMedico(String nomeMedico) { this.nomeMedico = nomeMedico; }

    public String getCrmMedico() { return crmMedico; }
    public void setCrmMedico(String crmMedico) { this.crmMedico = crmMedico; }

    public Long getIdUnidade() { return idUnidade; }
    public void setIdUnidade(Long idUnidade) { this.idUnidade = idUnidade; }

    public String getNomeUnidade() { return nomeUnidade; }
    public void setNomeUnidade(String nomeUnidade) { this.nomeUnidade = nomeUnidade; }

    public String getEnderecoUnidade() { return enderecoUnidade; }
    public void setEnderecoUnidade(String enderecoUnidade) { this.enderecoUnidade = enderecoUnidade; }

    public Long getIdEspecialidade() { return idEspecialidade; }
    public void setIdEspecialidade(Long idEspecialidade) { this.idEspecialidade = idEspecialidade; }

    public String getNomeEspecialidade() { return nomeEspecialidade; }
    public void setNomeEspecialidade(String nomeEspecialidade) { this.nomeEspecialidade = nomeEspecialidade; }
}