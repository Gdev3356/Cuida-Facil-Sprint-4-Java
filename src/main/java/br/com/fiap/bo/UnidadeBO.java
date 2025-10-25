package br.com.fiap.bo;

import br.com.fiap.dao.UnidadeDAO;
import br.com.fiap.to.RemedioTO;

import java.util.ArrayList;

public class UnidadeBO {
    private UnidadeDAO unidadeDAO;

    public ArrayList<RemedioTO> findAll(){
        unidadeDAO = new UnidadeDAO();
        //aqui se implementa regras de negócio
        return unidadeDAO.findAll();
    }
}
