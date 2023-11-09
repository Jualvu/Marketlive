/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.MarketLive.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julio
 */
public class ServicioProducto extends Service{
    
    private List<ProductoTO> productos;
    
    public List<ProductoTO> listarProductos(){
        
      List<ProductoTO> listaRetorno = new ArrayList<ProductoTO>();
        
        try {

            PreparedStatement stmt = super.getConexion().prepareStatement("SELECT idproducto, codigo, nombre, descripcion, tipo, tienda, cantidad, precio FROM marketlive.producto");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idproducto");
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String tipo = rs.getString("tipo");
                String tienda = rs.getString("tienda");
                int cantidad = rs.getInt("cantidad");
                Double precio = rs.getDouble("precio");
                        

                ProductoTO productoTO = new ProductoTO();
                productoTO.setId(id);
                productoTO.setCodigo(codigo);
                productoTO.setNombre(nombre);
                productoTO.setDescripcion(descripcion);
                productoTO.setTipo(tipo);
                productoTO.setTienda(tienda);
                productoTO.setCantidad(cantidad);
                productoTO.setPrecio(precio);
                listaRetorno.add(productoTO);

            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            //System.out.println("Error al abrir Conexión: " + ex.getMessage());
            ex.printStackTrace();
        }

        return listaRetorno;

    }
    
}
