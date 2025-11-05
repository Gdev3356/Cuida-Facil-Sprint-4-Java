package br.com.fiap.dao;

import br.com.fiap.to.EspecialidadeTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadeDAO {

    private static final String TABLE_NAME = "T_CUIDA_FACIL_ESPECIALIDADES";

    private EspecialidadeTO mapResultSetToTO(ResultSet rs) throws SQLException {
        EspecialidadeTO especialidade = new EspecialidadeTO();
        especialidade.setIdEspecialidade(rs.getLong("ID_ESPECIALIDADE"));
        especialidade.setNome(rs.getString("NM_ESPECIALIDADE"));
        especialidade.setDescricao(rs.getString("DESCRICAO"));
        especialidade.setUrlImagemEspecialidades(rs.getString("URL_IMAGEM_ESPECIALIDADES"));
        return especialidade;
    }

    public List<EspecialidadeTO> findAll() {
        List<EspecialidadeTO> especialidades = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID_ESPECIALIDADE";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                especialidades.add(mapResultSetToTO(rs));
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta (findAll Especialidade): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return especialidades.isEmpty() ? null : especialidades;
    }

    public EspecialidadeTO findById(Long id) {
        EspecialidadeTO especialidade = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_ESPECIALIDADE = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    especialidade = mapResultSetToTO(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta (findById Especialidade): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return especialidade;
    }

    public EspecialidadeTO save(EspecialidadeTO especialidade) {
        String sql = "INSERT INTO " + TABLE_NAME +
                     " (NM_ESPECIALIDADE, DESCRICAO, URL_IMAGEM_ESPECIALIDADES) " +
                     "VALUES (?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, especialidade.getNome());
            ps.setString(2, especialidade.getDescricao());
            ps.setString(3, especialidade.getUrlImagemEspecialidades());

            if (ps.executeUpdate() > 0) {
                return especialidade;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar (Especialidade): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_ESPECIALIDADE = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir (Especialidade): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
    }

    public EspecialidadeTO update(EspecialidadeTO especialidade) {
        String sql = "UPDATE " + TABLE_NAME +
                     " SET NM_ESPECIALIDADE=?, DESCRICAO=?, URL_IMAGEM_ESPECIALIDADES=? " +
                     "WHERE ID_ESPECIALIDADE=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, especialidade.getNome());
            ps.setString(2, especialidade.getDescricao());
            ps.setString(3, especialidade.getUrlImagemEspecialidades());
            ps.setLong(4, especialidade.getIdEspecialidade());

            if (ps.executeUpdate() > 0) {
                return especialidade;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar (Especialidade): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }
}