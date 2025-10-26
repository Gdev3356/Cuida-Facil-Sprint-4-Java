package br.com.fiap.bo;

import br.com.fiap.dao.ServicoDAO;
import br.com.fiap.to.ServicoTO;
import java.util.List;

public class ServicoBO {
    private ServicoDAO servicoDAO;

    public List<ServicoTO> findAll() {
        servicoDAO = new ServicoDAO();
        return servicoDAO.findAll();
    }

    public ServicoTO findById(Long id) {
        servicoDAO = new ServicoDAO();
        return servicoDAO.findById(id);
    }

    public ServicoTO save(ServicoTO servico) {
        servicoDAO = new ServicoDAO();
        // Regra: Verificar se a Especialidade (ID) existe antes de salvar
        return servicoDAO.save(servico);
    }

    public boolean delete(Long id) {
        servicoDAO = new ServicoDAO();
        return servicoDAO.delete(id);
    }

    public ServicoTO update(ServicoTO servico) {
        servicoDAO = new ServicoDAO();
        return servicoDAO.update(servico);
    }
}