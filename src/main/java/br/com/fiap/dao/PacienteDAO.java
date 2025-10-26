package br.com.fiap.dao;

import br.com.fiap.to.PacienteTO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    private static final String TABLE_NAME = "T_CUIDA_FACIL_PACIENTES";

    private PacienteTO mapResultSetToTO(ResultSet rs) throws SQLException {
        PacienteTO paciente = new PacienteTO();
        paciente.setIdPaciente(rs.getLong("ID_PACIENTE"));
        paciente.setCpf(rs.getString("CPF_PACIENTE"));
        paciente.setNome(rs.getString("NM_PACIENTE"));
        paciente.setTelefone(rs.getString("TEL_PACIENTE"));
        paciente.setEmail(rs.getString("EMAIL_PACIENTE"));
        paciente.setDataNascimento(rs.getDate("NASC_PACIENTE").toLocalDate());
        paciente.setCep(rs.getString("CEP_PACIENTE"));
        return paciente;
    }

    public List<PacienteTO> findAll() {
        List<PacienteTO> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID_PACIENTE";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pacientes.add(mapResultSetToTO(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta (findAll Paciente): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return pacientes.isEmpty() ? null : pacientes;
    }

    public PacienteTO findById(Long id) {
        PacienteTO paciente = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_PACIENTE = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                paciente = mapResultSetToTO(rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta (findById Paciente): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return paciente;
    }

    public PacienteTO save(PacienteTO paciente) {
        String sql = "INSERT INTO " + TABLE_NAME + " (CPF_PACIENTE, NM_PACIENTE, TEL_PACIENTE, EMAIL_PACIENTE, NASC_PACIENTE, CEP_PACIENTE) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, paciente.getCpf());
            ps.setString(2, paciente.getNome());
            ps.setString(3, paciente.getTelefone());
            ps.setString(4, paciente.getEmail());
            ps.setDate(5, Date.valueOf(paciente.getDataNascimento()));
            ps.setString(6, paciente.getCep());

            if (ps.executeUpdate() > 0) {
                return paciente;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar (Paciente): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_PACIENTE = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir (Paciente): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public PacienteTO update(PacienteTO paciente) {
        String sql = "UPDATE " + TABLE_NAME + " SET CPF_PACIENTE=?, NM_PACIENTE=?, TEL_PACIENTE=?, EMAIL_PACIENTE=?, NASC_PACiente=?, CEP_PACIENTE=? WHERE ID_PACIENTE=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, paciente.getCpf());
            ps.setString(2, paciente.getNome());
            ps.setString(3, paciente.getTelefone());
            ps.setString(4, paciente.getEmail());
            ps.setDate(5, Date.valueOf(paciente.getDataNascimento()));
            ps.setString(6, paciente.getCep());
            ps.setLong(7, paciente.getIdPaciente());

            if (ps.executeUpdate() > 0) {
                return paciente;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar (Paciente): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}