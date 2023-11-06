package com.cci.MarketLive.service;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Service {

    protected Connection conectar = null;
    protected String url = "jdbc:mysql://localhost:3306/marketlive?serverTimezone=UTC&zeroDateTimeBehavior=convertToNull"; //Se busca el URL en el server
    protected String usuario = "root"; //El usuario del MySQL
    protected String password = "Root123!"; // La contraseña del MySQL

    //Constructor
    public Service() {
    }

    public Connection conectarBBDD() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexión Exitosa " + conectar);
        } catch (ClassNotFoundException ex) {

            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conectar;
    }

    public Connection getConexion() throws SQLException {
        if (conectar == null) {
            conectarBBDD();
        }
        return conectar;
    }
}
