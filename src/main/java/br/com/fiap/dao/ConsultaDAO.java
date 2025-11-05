package br.com.fiap.dao;

import br.com.fiap.to.ConsultaDetalhadaTO;
import br.com.fiap.to.ConsultaTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    private static final String TABLE_NAME = "T_CUIDA_FACIL_CONSULTAS";

    private ConsultaTO mapResultSetToTO(ResultSet rs) throws SQLException {
        ConsultaTO consulta = new ConsultaTO();
        consulta.setIdConsulta(rs.getLong("ID_CONSULTA"));
        consulta.setProtocolo(rs.getString("CD_PROTOCOLO"));
        consulta.setDataConsulta(rs.getTimestamp("DT_CONSULTA").toLocalDateTime());
        consulta.setStatus(rs.getString("FL_STATUS"));
        consulta.setTipoAtendimento(rs.getString("TP_ATENDIMENTO"));
        consulta.setIdPaciente(rs.getLong("ID_PACIENTE"));
        consulta.setIdMedico(rs.getLong("ID_MEDICO"));
        consulta.setIdUnidade(rs.getLong("ID_UNIDADE"));
        consulta.setIdEspecialidade(rs.getLong("ID_ESPECIALIDADE"));
        return consulta;
    }

    private ConsultaDetalhadaTO mapResultSetToDTO(ResultSet rs) throws SQLException {
        ConsultaDetalhadaTO dto = new ConsultaDetalhadaTO();
        dto.setIdConsulta(rs.getLong("ID_CONSULTA"));
        dto.setProtocolo(rs.getString("CD_PROTOCOLO"));
        dto.setDataConsulta(rs.getTimestamp("DT_CONSULTA").toLocalDateTime());
        dto.setStatus(rs.getString("FL_STATUS"));
        dto.setTipoAtendimento(rs.getString("TP_ATENDIMENTO"));

        dto.setIdPaciente(rs.getLong("ID_PACIENTE"));
        dto.setIdMedico(rs.getLong("ID_MEDICO"));
        dto.setIdUnidade(rs.getLong("ID_UNIDADE"));
        dto.setIdEspecialidade(rs.getLong("ID_ESPECIALIDADE"));

        dto.setNomePaciente(rs.getString("NOME_PACIENTE"));
        dto.setNomeMedico(rs.getString("NOME_MEDICO"));
        dto.setCrmMedico(rs.getString("CRM_MEDICO"));
        dto.setNomeUnidade(rs.getString("NOME_UNIDADE"));
        dto.setEnderecoUnidade(rs.getString("ENDERECO_UNIDADE"));
        dto.setNomeEspecialidade(rs.getString("NOME_ESPECIALIDADE"));

        return dto;
    }

    public List<ConsultaDetalhadaTO> findAllDetalhadas() {
        List<ConsultaDetalhadaTO> consultas = new ArrayList<>();

        String sql = """
            SELECT 
                C.ID_CONSULTA,
                C.CD_PROTOCOLO,
                C.DT_CONSULTA,
                C.FL_STATUS,
                C.TP_ATENDIMENTO,
                C.ID_PACIENTE,
                P.NM_PACIENTE AS NOME_PACIENTE,
                C.ID_MEDICO,
                M.NM_MEDICO AS NOME_MEDICO,
                M.ID_CRM AS CRM_MEDICO,
                C.ID_UNIDADE,
                U.CD_UNIDADE AS NOME_UNIDADE,
                U.END_UNIDADE AS ENDERECO_UNIDADE,
                C.ID_ESPECIALIDADE,
                E.NM_ESPECIALIDADE AS NOME_ESPECIALIDADE
            FROM T_CUIDA_FACIL_CONSULTAS C
            INNER JOIN T_CUIDA_FACIL_PACIENTES P ON C.ID_PACIENTE = P.ID_PACIENTE
            INNER JOIN T_CUIDA_FACIL_MEDICOS M ON C.ID_MEDICO = M.ID_MEDICO
            INNER JOIN T_CUIDA_FACIL_UNIDADES U ON C.ID_UNIDADE = U.ID_UNIDADE
            INNER JOIN T_CUIDA_FACIL_ESPECIALIDADES E ON C.ID_ESPECIALIDADE = E.ID_ESPECIALIDADE
            ORDER BY C.DT_CONSULTA DESC
        """;

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                consultas.add(mapResultSetToDTO(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar consultas detalhadas: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return consultas.isEmpty() ? null : consultas;
    }

    public ConsultaDetalhadaTO findByIdDetalhada(Long id) {
        ConsultaDetalhadaTO consulta = null;

        String sql = """
            SELECT 
                C.ID_CONSULTA,
                C.CD_PROTOCOLO,
                C.DT_CONSULTA,
                C.FL_STATUS,
                C.TP_ATENDIMENTO,
                C.ID_PACIENTE,
                P.NM_PACIENTE AS NOME_PACIENTE,
                C.ID_MEDICO,
                M.NM_MEDICO AS NOME_MEDICO,
                M.ID_CRM AS CRM_MEDICO,
                C.ID_UNIDADE,
                U.CD_UNIDADE AS NOME_UNIDADE,
                U.END_UNIDADE AS ENDERECO_UNIDADE,
                C.ID_ESPECIALIDADE,
                E.NM_ESPECIALIDADE AS NOME_ESPECIALIDADE
            FROM T_CUIDA_FACIL_CONSULTAS C
            INNER JOIN T_CUIDA_FACIL_PACIENTES P ON C.ID_PACIENTE = P.ID_PACIENTE
            INNER JOIN T_CUIDA_FACIL_MEDICOS M ON C.ID_MEDICO = M.ID_MEDICO
            INNER JOIN T_CUIDA_FACIL_UNIDADES U ON C.ID_UNIDADE = U.ID_UNIDADE
            INNER JOIN T_CUIDA_FACIL_ESPECIALIDADES E ON C.ID_ESPECIALIDADE = E.ID_ESPECIALIDADE
            WHERE C.ID_CONSULTA = ?
        """;

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                consulta = mapResultSetToDTO(rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar consulta detalhada por ID: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return consulta;
    }

    public List<ConsultaTO> findAll() {
        List<ConsultaTO> consultas = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY DT_CONSULTA DESC";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                consultas.add(mapResultSetToTO(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta (findAll Consulta): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return consultas.isEmpty() ? null : consultas;
    }

    public ConsultaTO findById(Long id) {
        ConsultaTO consulta = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_CONSULTA = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                consulta = mapResultSetToTO(rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta (findById Consulta): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return consulta;
    }

    public ConsultaTO save(ConsultaTO consulta) {
        String sqlId = "SELECT NVL(MAX(ID_CONSULTA), 0) + 1 FROM " + TABLE_NAME;
        String sqlInsert = "INSERT INTO " + TABLE_NAME +
                           " (ID_CONSULTA, CD_PROTOCOLO, DT_CONSULTA, FL_STATUS, TP_ATENDIMENTO, " +
                           "ID_PACIENTE, ID_MEDICO, ID_UNIDADE, ID_ESPECIALIDADE) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        System.out.println("========= DAO SAVE DEBUG =========");
        System.out.println("Protocolo: " + consulta.getProtocolo());
        System.out.println("Data: " + consulta.getDataConsulta());
        System.out.println("Status: " + consulta.getStatus());
        System.out.println("Tipo: " + consulta.getTipoAtendimento());
        System.out.println("ID Paciente: " + consulta.getIdPaciente());
        System.out.println("ID Medico: " + consulta.getIdMedico());
        System.out.println("ID Unidade: " + consulta.getIdUnidade()); // ← CRÍTICO
        System.out.println("ID Especialidade: " + consulta.getIdEspecialidade());
        System.out.println("==================================");

        try (PreparedStatement psId = ConnectionFactory.getConnection().prepareStatement(sqlId);
             PreparedStatement psInsert = ConnectionFactory.getConnection().prepareStatement(sqlInsert)) {

            ResultSet rs = psId.executeQuery();
            Long novoId = 1L;
            if (rs.next()) {
                novoId = rs.getLong(1);
            }
            consulta.setIdConsulta(novoId);

            psInsert.setLong(1, novoId);
            psInsert.setString(2, consulta.getProtocolo());
            psInsert.setTimestamp(3, Timestamp.valueOf(consulta.getDataConsulta()));
            psInsert.setString(4, consulta.getStatus());
            psInsert.setString(5, consulta.getTipoAtendimento());
            psInsert.setLong(6, consulta.getIdPaciente());
            psInsert.setLong(7, consulta.getIdMedico());
            psInsert.setLong(8, consulta.getIdUnidade()); // ← VERIFIQUE SE ESTÁ NULL
            psInsert.setLong(9, consulta.getIdEspecialidade());

            System.out.println("Executando INSERT com ID_UNIDADE = " + consulta.getIdUnidade());

            int rowsAffected = psInsert.executeUpdate();

            System.out.println("Linhas afetadas: " + rowsAffected);

            if (rowsAffected > 0) {
                System.out.println("Consulta salva com sucesso! ID: " + novoId);
                return consulta;
            }
        } catch (SQLException e) {
            System.err.println("ERRO SQL ao salvar consulta: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_CONSULTA = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir (Consulta): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public ConsultaTO update(ConsultaTO consulta) {
        String sql = "UPDATE " + TABLE_NAME +
                     " SET CD_PROTOCOLO=?, DT_CONSULTA=?, FL_STATUS=?, TP_ATENDIMENTO=?, " +
                     "ID_PACIENTE=?, ID_MEDICO=?, ID_UNIDADE=?, ID_ESPECIALIDADE=? " +
                     "WHERE ID_CONSULTA=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, consulta.getProtocolo());
            ps.setTimestamp(2, Timestamp.valueOf(consulta.getDataConsulta()));
            ps.setString(3, consulta.getStatus());
            ps.setString(4, consulta.getTipoAtendimento());
            ps.setLong(5, consulta.getIdPaciente());
            ps.setLong(6, consulta.getIdMedico());
            ps.setLong(7, consulta.getIdUnidade());
            ps.setLong(8, consulta.getIdEspecialidade());
            ps.setLong(9, consulta.getIdConsulta());

            if (ps.executeUpdate() > 0) {
                return consulta;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar (Consulta): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}