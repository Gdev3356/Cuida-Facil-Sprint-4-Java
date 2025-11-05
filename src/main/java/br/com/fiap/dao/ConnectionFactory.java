package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");

            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter conexão", e);
        }
    }
}

