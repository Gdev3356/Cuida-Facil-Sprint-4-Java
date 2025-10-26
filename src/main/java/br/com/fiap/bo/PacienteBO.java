package br.com.fiap.bo;

import br.com.fiap.dao.PacienteDAO;
import br.com.fiap.to.PacienteTO;
import br.com.fiap.exception.BusinessRuleException;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.exception.DAOException;
import java.time.LocalDate;
import java.util.List;

public class PacienteBO {
    private PacienteDAO pacienteDAO;

    public List<PacienteTO> findAll() throws DAOException {
        pacienteDAO = new PacienteDAO();
        return pacienteDAO.findAll();
    }

    public PacienteTO findById(Long id) throws IdNotFoundException, DAOException {
        pacienteDAO = new PacienteDAO();
        PacienteTO paciente = pacienteDAO.findById(id);
        if (paciente == null) {
            throw new IdNotFoundException("Paciente com ID " + id + " não encontrado.");
        }
        return paciente;
    }

    public PacienteTO save(PacienteTO paciente) throws BusinessRuleException, DAOException {
        pacienteDAO = new PacienteDAO();
        if (pacienteDAO.findByCpf(paciente.getCpf()) != null) {
            throw new BusinessRuleException("O CPF " + paciente.getCpf() + " já está cadastrado.");
        }
        if (paciente.getDataNascimento() != null && paciente.getDataNascimento().isAfter(LocalDate.now().minusYears(18))) {
            throw new BusinessRuleException("O paciente deve ser maior de 18 anos para cadastro.");
        }

        return pacienteDAO.save(paciente);
    }

    public boolean delete(Long id) throws DAOException {
        pacienteDAO = new PacienteDAO();
        return pacienteDAO.delete(id);
    }

    public PacienteTO update(PacienteTO paciente) throws BusinessRuleException, IdNotFoundException, DAOException {
        pacienteDAO = new PacienteDAO();
        if (pacienteDAO.findById(paciente.getIdPaciente()) == null) {
            throw new IdNotFoundException("Paciente com ID " + paciente.getIdPaciente() + " não pode ser atualizado, pois não existe.");
        }
        return pacienteDAO.update(paciente);
    }
}