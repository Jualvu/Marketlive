/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.MarketLive.service;

import com.cci.MarketLive.controller.LoginController;
import com.cci.MarketLive.controller.ProductoController;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author julio
 */
public abstract class ServicioCompra extends Conexion implements ICrud<CompraTO>{
    
    PreparedStatement stmt;
    ResultSet rs;
    List<CompraTO> compras;
    @ManagedProperty("#{loginController}")
    private LoginController loginController;
    
    @ManagedProperty("#{productoController}")
    private ProductoController productoController;
    
    @Override
    public boolean create(CompraTO objeto) throws SQLException {
        try {

            int idusuario = loginController.getId();
            int idproducto = productoController.getSelectedProducto().getId();
            
            String query = "INSERT INTO compras (idusuario, idproducto, cantidad) VALUES (?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setInt(1, idusuario);
            stmt.setInt(2, idproducto);
            stmt.setInt(3, objeto.getCantidad());
            
            stmt.execute();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    
    
}
