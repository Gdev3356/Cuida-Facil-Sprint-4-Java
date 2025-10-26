package br.com.fiap.bo;

import br.com.fiap.dao.ChatbotDAO;
import br.com.fiap.to.ChatbotTO;
import java.util.List;

public class ChatbotBO {
    private ChatbotDAO chatbotDAO;

    public List<ChatbotTO> findAll() {
        chatbotDAO = new ChatbotDAO();
        return chatbotDAO.findAll();
    }

    public ChatbotTO findById(Long id) {
        chatbotDAO = new ChatbotDAO();
        return chatbotDAO.findById(id);
    }

    public ChatbotTO save(ChatbotTO atendimento) {
        chatbotDAO = new ChatbotDAO();
        return chatbotDAO.save(atendimento);
    }

    public boolean delete(Long id) {
        chatbotDAO = new ChatbotDAO();
        return chatbotDAO.delete(id);
    }

    public ChatbotTO update(ChatbotTO atendimento) {
        chatbotDAO = new ChatbotDAO();
        return chatbotDAO.update(atendimento);
    }
}