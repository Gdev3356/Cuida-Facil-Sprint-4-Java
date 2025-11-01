package br.com.fiap.dao;

import br.com.fiap.to.UnidadeTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnidadeDAO {

    private UnidadeTO mapResultSetToTO(ResultSet rs) throws SQLException {
        UnidadeTO unidade = new UnidadeTO();
        unidade.setIdUnidade(rs.getLong("ID_UNIDADE"));
        unidade.setCdUnidade(rs.getString("CD_UNIDADE"));
        unidade.setEndereco(rs.getString("END_UNIDADE"));
        unidade.setTelefone(rs.getString("TEL_UNIDADE"));
        unidade.setHorario(rs.getString("HR_UNIDADE"));
        unidade.setCep(rs.getString("CEP_UNIDADE"));
        unidade.setUrlImagemUnidades(rs.getString("URL_IMAGEM_UNIDADES"));
        return unidade;
    }

    public List<UnidadeTO> findAll() {
        List<UnidadeTO> unidades = new ArrayList<>();
        String sql = "SELECT ID_UNIDADE, CD_UNIDADE, END_UNIDADE, TEL_UNIDADE, HR_UNIDADE, CEP_UNIDADE, URL_IMAGEM_UNIDADES FROM T_CUIDA_FACIL_UNIDADES ORDER BY ID_UNIDADE";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                unidades.add(mapResultSetToTO(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta (findAll): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return unidades.isEmpty() ? null : unidades;
    }

    public UnidadeTO findById(Long id) {
        UnidadeTO unidade = null;
        String sql = "SELECT ID_UNIDADE, CD_UNIDADE, END_UNIDADE, TEL_UNIDADE, HR_UNIDADE, CEP_UNIDADE, URL_IMAGEM_UNIDADES FROM T_CUIDA_FACIL_UNIDADES WHERE ID_UNIDADE = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                unidade = mapResultSetToTO(rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta (findById): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return unidade;
    }

    public UnidadeTO save(UnidadeTO unidade) {
        String sql = "INSERT INTO T_CUIDA_FACIL_UNIDADES (CD_UNIDADE, END_UNIDADE, TEL_UNIDADE, HR_UNIDADE, CEP_UNIDADE, URL_IMAGEM_UNIDADES) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, unidade.getCdUnidade());
            ps.setString(2, unidade.getEndereco());
            ps.setString(3, unidade.getTelefone());
            ps.setString(4, unidade.getHorario());
            ps.setString(5, unidade.getCep());
            ps.setString(6, unidade.getUrlImagemUnidades());

            if (ps.executeUpdate() > 0) {
                return unidade;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_CUIDA_FACIL_UNIDADES WHERE ID_UNIDADE = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public UnidadeTO update(UnidadeTO unidade) {
        String sql = "UPDATE T_CUIDA_FACIL_UNIDADES SET CD_UNIDADE=?, END_UNIDADE=?, TEL_UNIDADE=?, HR_UNIDADE=?, CEP_UNIDADE=?, URL_IMAGEM_UNIDADES=? WHERE ID_UNIDADE=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, unidade.getCdUnidade());
            ps.setString(2, unidade.getEndereco());
            ps.setString(3, unidade.getTelefone());
            ps.setString(4, unidade.getHorario());
            ps.setString(5, unidade.getCep());
            ps.setString(6, unidade.getUrlImagemUnidades());
            ps.setLong(7, unidade.getIdUnidade()); // Chave para o WHERE

            if (ps.executeUpdate() > 0) {
                return unidade;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}