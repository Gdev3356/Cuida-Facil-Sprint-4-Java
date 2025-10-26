package br.com.fiap.dao;

import br.com.fiap.to.ChatbotTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ChatbotDAO {

    private static final String TABLE_NAME = "T_CUIDA_FACIL_CHATBOT";

    private ChatbotTO mapResultSetToTO(ResultSet rs) throws SQLException {
        ChatbotTO atendimento = new ChatbotTO();
        atendimento.setIdAtendimento(rs.getLong("ID_ATENDIMENTO"));
        atendimento.setIdPaciente(rs.getLong("ID_PACIENTE"));
        atendimento.setHoraInteracao(rs.getTimestamp("HR_INTERACAO").toLocalDateTime());
        atendimento.setIntencaoUsuario(rs.getString("ID_INTENCAO_USUARIO"));
        atendimento.setTextoResposta(rs.getString("TXT_RESPOSTA")); // CLOB é lido como String
        return atendimento;
    }

    public List<ChatbotTO> findAll() {
        List<ChatbotTO> atendimentos = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID_ATENDIMENTO DESC";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                atendimentos.add(mapResultSetToTO(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta (findAll Chatbot): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return atendimentos.isEmpty() ? null : atendimentos;
    }

    public ChatbotTO findById(Long id) {
        ChatbotTO atendimento = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_ATENDIMENTO = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                atendimento = mapResultSetToTO(rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta (findById Chatbot): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return atendimento;
    }

    public ChatbotTO save(ChatbotTO atendimento) {
        // HR_INTERACAO usa DEFAULT SYSTIMESTAMP
        String sql = "INSERT INTO " + TABLE_NAME + " (ID_PACIENTE, ID_INTENCAO_USUARIO, TXT_RESPOSTA) VALUES (?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, atendimento.getIdPaciente());
            ps.setString(2, atendimento.getIntencaoUsuario());
            ps.setString(3, atendimento.getTextoResposta()); // CLOB é setado como String

            if (ps.executeUpdate() > 0) {
                return atendimento;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar (Chatbot): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_ATENDIMENTO = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir (Chatbot): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    // O Update não deve atualizar a HR_INTERACAO, talvez? Depende da regra.
    // Vamos assumir que pode atualizar tudo, exceto a hora da interação.
    public ChatbotTO update(ChatbotTO atendimento) {
        String sql = "UPDATE " + TABLE_NAME + " SET ID_PACIENTE=?, ID_INTENCAO_USUARIO=?, TXT_RESPOSTA=? WHERE ID_ATENDIMENTO=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, atendimento.getIdPaciente());
            ps.setString(2, atendimento.getIntencaoUsuario());
            ps.setString(3, atendimento.getTextoResposta());
            ps.setLong(4, atendimento.getIdAtendimento());

            if (ps.executeUpdate() > 0) {
                return atendimento;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar (Chatbot): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}