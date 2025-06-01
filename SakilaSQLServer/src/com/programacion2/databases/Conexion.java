package com.programacion2.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Conexion instance;
    private Connection connection;
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=sakila;encrypt=true;trustServerCertificate=true";
    private String username = "sa";
    private String password = "12345678";

    private Conexion() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Fallo la conexión: " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Conexion getInstance() throws SQLException {
        if (instance == null) {
            instance = new Conexion();
        } else if (instance.getConnection().isClosed()) {
            instance = new Conexion();
        }
        return instance;
    }
}
