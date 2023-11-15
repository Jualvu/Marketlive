package com.cci.MarketLive.service;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public abstract class Conexion {
 
    private Connection conectar = null;
    private String url = "jdbc:mysql://localhost:3306/market_live?serverTimezone=UTC&zeroDateTimeBehavior=CONVERT_TO_NULL";
    private String usuario = "root";
    private String password = "admin";
 
    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    private Connection conectarBBDD() throws SQLException {
        conectar = DriverManager.getConnection(url, usuario, password);
        return conectar;
    }
 
    public Connection getConexion() throws SQLException {
        if (conectar == null) {
            conectarBBDD();
        }
        return conectar;
    }
}