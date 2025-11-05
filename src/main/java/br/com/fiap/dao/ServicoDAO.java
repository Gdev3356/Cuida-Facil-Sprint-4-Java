package br.com.fiap.dao;

import br.com.fiap.to.ServicoTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    private static final String TABLE_NAME = "T_CUIDA_FACIL_SERVICOS";

    private ServicoTO mapResultSetToTO(ResultSet rs) throws SQLException {
        ServicoTO servico = new ServicoTO();
        servico.setIdServico(rs.getLong("ID_SERVICO"));
        servico.setNome(rs.getString("NM_SERVICO"));
        servico.setIdEspecialidade(rs.getLong("ID_ESPECIALIDADE"));
        return servico;
    }

    public List<ServicoTO> findAll() {
        List<ServicoTO> servicos = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID_SERVICO";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                servicos.add(mapResultSetToTO(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta (findAll Servico): " + e.getMessage());
        }
        return servicos.isEmpty() ? null : servicos;
    }

    public ServicoTO findById(Long id) {
        ServicoTO servico = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_SERVICO = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                servico = mapResultSetToTO(rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta (findById Servico): " + e.getMessage());
        }
        return servico;
    }

    public ServicoTO save(ServicoTO servico) {
        String sql = "INSERT INTO " + TABLE_NAME + " (NM_SERVICO, ID_ESPECIALIDADE) VALUES (?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, servico.getNome());
            ps.setLong(2, servico.getIdEspecialidade());

            if (ps.executeUpdate() > 0) {
                return servico;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar (Servico): " + e.getMessage());
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_SERVICO = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir (Servico): " + e.getMessage());
        }
        return false;
    }

    public ServicoTO update(ServicoTO servico) {
        String sql = "UPDATE " + TABLE_NAME + " SET NM_SERVICO=?, ID_ESPECIALIDADE=? WHERE ID_SERVICO=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, servico.getNome());
            ps.setLong(2, servico.getIdEspecialidade());
            ps.setLong(3, servico.getIdServico());

            if (ps.executeUpdate() > 0) {
                return servico;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar (Servico): " + e.getMessage());
        }
        return null;
    }
}