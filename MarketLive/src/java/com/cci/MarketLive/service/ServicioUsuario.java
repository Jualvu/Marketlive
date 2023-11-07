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
    
    
    public int consultar(String usuario, String clave){
        int result = 0;
        
        try {

            PreparedStatement stmt = super.getConexion().prepareStatement("SELECT nombre, clave FROM usuario");
            ResultSet rs = stmt.executeQuery();
            
  
            while (rs.next()){
                
                String usuario2 = rs.getString("nombre");
                String clave2 = rs.getString("clave");

                if(usuario.equals(usuario2) && clave.equals(clave2) ){
                    result = 1;
                }
            }

            rs.close();
            stmt.close();
            
        } catch (SQLException ex) {
            //System.out.println("Error al abrir Conexión: " + ex.getMessage());
            result= 7;
            ex.printStackTrace();
        }

        return result;
        
    }
    
    
    
}
