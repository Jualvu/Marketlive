package com.cci.MarketLive.service;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public abstract class Conexion {

   private static Connection conectar = null;
   private static String url = "jdbc:mysql://localhost:3306/market_live?serverTimezone=UTC&zeroDateTimeBehavior=CONVERT_TO_NULL";
   private static String usuario = "root";
   private static String password = "Root123!"
           + "";

   static {
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
           throw new RuntimeException("No se pudo cargar el driver de la base de datos", e);
       }
   }

   public Conexion() {
       if (conectar == null) {
           try {
               conectar = DriverManager.getConnection(url, usuario, password);
           } catch (SQLException e) {
               e.printStackTrace();
               throw new RuntimeException("No se pudo conectar a la base de datos", e);
           }
       }
   }

   public Connection getConexion() {
       return conectar;
   }
}