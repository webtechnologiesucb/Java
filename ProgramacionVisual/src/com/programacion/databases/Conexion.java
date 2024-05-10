package com.programacion.databases;

import java.sql.Connection;
import java.sql.DriverManager; // jdbc
import java.sql.SQLException; // operaciones

// Patron Singleton
public class Conexion {

    private static Conexion instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/sakila";
    private String username = "root";
    private String password = "";//"admin";

    private Conexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // paquete jdbc
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Fallo la conexion : " + ex.getMessage());
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
