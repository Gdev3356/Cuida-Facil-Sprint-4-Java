package br.com.fiap.bo;

import br.com.fiap.dao.EspecialidadeDAO;
import br.com.fiap.to.EspecialidadeTO;
import java.util.List;

public class EspecialidadeBO {
    private EspecialidadeDAO especialidadeDAO;

    public List<EspecialidadeTO> findAll() {
        especialidadeDAO = new EspecialidadeDAO();
        return especialidadeDAO.findAll();
    }

    public EspecialidadeTO findById(Long id) {
        especialidadeDAO = new EspecialidadeDAO();
        return especialidadeDAO.findById(id);
    }

    public EspecialidadeTO save(EspecialidadeTO especialidade) {
        especialidadeDAO = new EspecialidadeDAO();
        return especialidadeDAO.save(especialidade);
    }

    public boolean delete(Long id) {
        especialidadeDAO = new EspecialidadeDAO();
        return especialidadeDAO.delete(id);
    }

    public EspecialidadeTO update(EspecialidadeTO especialidade) {
        especialidadeDAO = new EspecialidadeDAO();
        return especialidadeDAO.update(especialidade);
    }
}