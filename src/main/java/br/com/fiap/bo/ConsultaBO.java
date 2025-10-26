package br.com.fiap.bo;

import br.com.fiap.dao.ConsultaDAO;
import br.com.fiap.to.ConsultaTO;
import java.util.List;

public class ConsultaBO {
    private ConsultaDAO consultaDAO;

    public List<ConsultaTO> findAll() {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.findAll();
    }

    public ConsultaTO findById(Long id) {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.findById(id);
    }

    public ConsultaTO save(ConsultaTO consulta) {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.save(consulta);
    }

    public boolean delete(Long id) {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.delete(id);
    }

    public ConsultaTO update(ConsultaTO consulta) {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.update(consulta);
    }
}