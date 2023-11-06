/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.MarketLive.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author julio
 */
public class ServicioUsuario extends Service {
    
    
    public int consultar(String nombre, String clave){
        /*
        resultados :
        
        0 = no existe usuario
        1 = existe un usuario tipo Cliente
        2 = existe un usuario tipo Admin
        
        
        */
        
        int result = 0;
        
        try {
            
            PreparedStatement stmt = super.getConexion().prepareStatement("SELECT nombre, clave, administrador FROM marketlive.usuario");
            ResultSet rs = stmt.executeQuery();
            
  
            while (rs.next()){
                
                String nombre2 = rs.getString("nombre");
                String clave2 = rs.getString("clave");
                int admin2 = rs.getInt("administrador");

                if(nombre.equals(nombre2) && clave.equals(clave2)){
                    result = 1;
                    /*
                    if(admin2==0){
                        result = 1;
                    }else if(admin2==1){
                        result = 2;
                    }
                    */
                }else{
                    
                    result = 8;
                    
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            //System.out.println("Error al abrir Conexión: " + ex.getMessage());
            result= 5;
            ex.printStackTrace();
        }

        return result;
        
    }
    
    
    
    
    
}
