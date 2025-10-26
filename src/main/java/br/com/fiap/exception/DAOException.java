package br.com.fiap.exception;

import java.sql.SQLException;

public class DAOException extends RuntimeException {

    // Construtor para envolver a SQLException original
    public DAOException(String message, SQLException cause) {
        super(message, cause);
    }
}