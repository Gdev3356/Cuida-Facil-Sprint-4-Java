package br.com.fiap.dao;

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
            psInsert.setLong(8, consulta.getIdUnidade());
            psInsert.setLong(9, consulta.getIdEspecialidade());

            if (psInsert.executeUpdate() > 0) {
                return consulta;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar (Consulta): " + e.getMessage());
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