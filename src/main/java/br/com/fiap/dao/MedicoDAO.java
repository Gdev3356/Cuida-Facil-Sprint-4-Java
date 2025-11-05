package br.com.fiap.dao;

import br.com.fiap.to.MedicoTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    private static final String TABLE_NAME = "T_CUIDA_FACIL_MEDICOS";

    private MedicoTO mapResultSetToTO(ResultSet rs) throws SQLException {
        MedicoTO medico = new MedicoTO();
        medico.setIdMedico(rs.getLong("ID_MEDICO"));
        medico.setCrm(rs.getString("ID_CRM"));
        medico.setNome(rs.getString("NM_MEDICO"));
        medico.setUrlImagemMedico(rs.getString("URL_IMAGEM_MEDICO"));
        return medico;
    }

    public List<MedicoTO> findAll() {
        List<MedicoTO> medicos = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID_MEDICO";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                medicos.add(mapResultSetToTO(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta (findAll Medico): " + e.getMessage());
        }
        return medicos.isEmpty() ? null : medicos;
    }

    public MedicoTO findById(Long id) {
        MedicoTO medico = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_MEDICO = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                medico = mapResultSetToTO(rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta (findById Medico): " + e.getMessage());
        }
        return medico;
    }

    public MedicoTO save(MedicoTO medico) {
        String sql = "INSERT INTO " + TABLE_NAME + " (ID_CRM, NM_MEDICO, URL_IMAGEM_MEDICO) VALUES (?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, medico.getCrm());
            ps.setString(2, medico.getNome());
            ps.setString(3, medico.getUrlImagemMedico());

            if (ps.executeUpdate() > 0) {
                return medico;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar (Medico): " + e.getMessage());
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_MEDICO = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir (Medico): " + e.getMessage());
        }
        return false;
    }

    public MedicoTO update(MedicoTO medico) {
        String sql = "UPDATE " + TABLE_NAME + " SET ID_CRM=?, NM_MEDICO=?, URL_IMAGEM_MEDICO=? WHERE ID_MEDICO=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, medico.getCrm());
            ps.setString(2, medico.getNome());
            ps.setString(3, medico.getUrlImagemMedico());
            ps.setLong(4, medico.getIdMedico());

            if (ps.executeUpdate() > 0) {
                return medico;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar (Medico): " + e.getMessage());
        }
        return null;
    }
}