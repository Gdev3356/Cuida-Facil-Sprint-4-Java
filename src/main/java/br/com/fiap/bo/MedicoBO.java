package br.com.fiap.bo;

import br.com.fiap.dao.MedicoDAO;
import br.com.fiap.to.MedicoTO;
import java.util.List;

public class MedicoBO {
    private MedicoDAO medicoDAO;

    public List<MedicoTO> findAll() {
        medicoDAO = new MedicoDAO();
        return medicoDAO.findAll();
    }

    public MedicoTO findById(Long id) {
        medicoDAO = new MedicoDAO();
        return medicoDAO.findById(id);
    }

    public MedicoTO save(MedicoTO medico) {
        medicoDAO = new MedicoDAO();
        return medicoDAO.save(medico);
    }

    public boolean delete(Long id) {
        medicoDAO = new MedicoDAO();
        return medicoDAO.delete(id);
    }

    public MedicoTO update(MedicoTO medico) {
        medicoDAO = new MedicoDAO();
        return medicoDAO.update(medico);
    }
}