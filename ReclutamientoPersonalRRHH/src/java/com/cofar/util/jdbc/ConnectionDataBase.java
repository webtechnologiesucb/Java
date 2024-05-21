/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cofar.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Jaime Chura
 * @version BACO 2.0 date 31-03-2015 time 02:43:56 PM
 */
public class ConnectionDataBase {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionDataBase.class);
    private String jdbcConection;

    /**
     *
     * @return connection jdbc
     */
    public String getJdbcConection() {
        return jdbcConection;
    }

    /**
     *
     * @param jdbcConection el tipo de coneccion de base de datos.
     */
    public void setJdbcConection(String jdbcConection) {
        this.jdbcConection = jdbcConection;
    }

    /**
     *
     * @return ip del server configurado
     */
    public String getIp() {
        return ip;
    }

    /**
     *
     * @param ip la ip configurada en el web.xml
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     *
     * @return el usuario de acceso a la base de datos
     */
    public String getUser() {
        return user;
    }

    /**
     *
     * @param user user especificado en web.xml
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     *
     * @return el password de acceso a la base de datos
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password el password configurado en el web.xml
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return el nombre de la base de datos.
     */
    public String getNameDataBase() {
        return nameDataBase;
    }

    /**
     *
     * @param nameDataBase el nombre de la base de datos configurado en el
     * web.xml
     */
    public void setNameDataBase(String nameDataBase) {
        this.nameDataBase = nameDataBase;
    }

    private String ip;
    private String user;
    private String password;
    private String nameDataBase;

    /**
     *
     * @return la conección de la base de datos,
     */
    public Connection getConnectionDataBase_Sartorius() {

        Connection connection = null;

        jdbcConection = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("driver");
        ip = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("host");
        user = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("user");
        password = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("password");
        nameDataBase = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("database");
        if (jdbcConection.equals("com.microsoft.sqlserver.jdbc.SQLServerDriver")) {
            try {
                Class.forName(jdbcConection);
                connection = DriverManager.getConnection("jdbc:sqlserver://" + ip + ";dataBase=" + nameDataBase, user, password);
                Statement statement=connection.createStatement();
                statement.execute("set dateformat 'ymd'");
            } catch (ClassNotFoundException e) {
                LOGGER.fatal("EL driver de la BD " + nameDataBase + " no se encuentra fisicamente en la aplicación.");
            } catch (SQLException e) {
                 LOGGER.fatal("El driver no se encuentra configurado correctamente off[" + ip +","+nameDataBase+","+password+","+user+"].");
            }
        }
        return connection;
    }
    
     public Connection getConnectionDataBase_SartoriusServlet(HttpServletRequest request) {

        Connection connection = null;

        jdbcConection = request.getServletContext().getInitParameter("driver");
        ip = request.getServletContext().getInitParameter("host");
        user = request.getServletContext().getInitParameter("user");
        password = request.getServletContext().getInitParameter("password");
        nameDataBase = request.getServletContext().getInitParameter("database");
        if (jdbcConection.equals("com.microsoft.sqlserver.jdbc.SQLServerDriver")) {
            try {
                Class.forName(jdbcConection);
                connection = DriverManager.getConnection("jdbc:sqlserver://" + ip + ";dataBase=" + nameDataBase, user, password);
            } catch (ClassNotFoundException e) {
                LOGGER.fatal("EL driver de la BD " + nameDataBase + " no se encuentra fisicamente en la aplicación.");
            } catch (SQLException e) {
                LOGGER.fatal("El driver no se encuentra configurado correctamente[" + ip +","+nameDataBase+","+password+","+user+"].");
            }
        }
        return connection;
    }
    
     public Connection getConnectionDataBase_Sartorius_test1() {

        Connection connection = null;

        jdbcConection = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        ip = "172.16.10.21";
        user = "sa";
        password = "m0t1t4s@2009";
        nameDataBase = "SARTORIUS";
        if (jdbcConection.equals("com.microsoft.sqlserver.jdbc.SQLServerDriver")) {
            try {
                Class.forName(jdbcConection);
                connection = DriverManager.getConnection("jdbc:sqlserver://" + ip + ";dataBase=" + nameDataBase, user, password);
                Statement statement=connection.createStatement();
                statement.execute("set dateformat ymd");
            } catch (ClassNotFoundException e) {
                LOGGER.fatal("EL driver de la BD " + nameDataBase + " no se encuentra fisicamente en la aplicación.");
            } catch (SQLException e) {
                LOGGER.fatal("El driver no se encuentra configurado correctamente static[" + ip + "].");
            }
        }
        return connection;
    }
}
