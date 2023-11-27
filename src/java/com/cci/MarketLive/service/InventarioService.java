
package com.cci.MarketLive.service;

import com.cci.MarketLive.contracts.ICrud;
import com.cci.MarketLive.to.InventarioTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class InventarioService extends Conexion implements ICrud<InventarioTO>{

    
    PreparedStatement stmt;
    ResultSet rs;
    List<InventarioTO> inventario;
    
    
    
    
    public boolean createForProduct(int idProducto, double cantidad) throws SQLException {
        
        try {

            LocalDateTime now = LocalDateTime.now();

            Timestamp fechaActual = Timestamp.valueOf(now);

            String query = "INSERT INTO inventario (producto_id, cantidad, fecha) VALUES (?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setInt(1, idProducto);
            stmt.setDouble(2, cantidad);
            stmt.setTimestamp(3, fechaActual);


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

    @Override
    public InventarioTO read(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(InventarioTO objeto) throws SQLException {
        try {
            System.out.println("sa" + objeto.getId());
            String query = "UPDATE inventario SET producto_id = ?, cantidad = ?, fecha = ? WHERE id = ?";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setInt(1, objeto.getIdproducto());
            stmt.setDouble(2, objeto.getCantidad());
            stmt.setTimestamp(3, objeto.getFecha());

            stmt.setInt(4, objeto.getId());

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InventarioTO> readAll() throws SQLException {
        
        inventario = new ArrayList<InventarioTO>();

        try {
            stmt = super.getConexion().prepareStatement("SELECT * FROM inventario");
            rs = stmt.executeQuery();

            while (rs.next()) {
                InventarioTO inventarioTO = new InventarioTO();

                int id = rs.getInt("id");
                int idproducto = rs.getInt("producto_id");
                Double cantidad = rs.getDouble("cantidad");
                Timestamp fecha = rs.getTimestamp("fecha");

                inventarioTO.setId(id);
                inventarioTO.setIdproducto(idproducto);
                inventarioTO.setCantidad(cantidad);
                inventarioTO.setFecha(fecha);


                inventario.add(inventarioTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null && rs != null) {
                rs.close();
                stmt.close();
            }
        }

        return inventario;
        
    }

    
    public List<InventarioTO> readAllByProducto(int productoId) throws SQLException {
        
        inventario = new ArrayList<InventarioTO>();

        try {
            stmt = super.getConexion().prepareStatement("SELECT * FROM inventario WHERE producto_id=?");
            stmt.setInt(1, productoId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                InventarioTO inventarioTO = new InventarioTO();

                int id = rs.getInt("id");
                int idproducto = rs.getInt("producto_id");
                Double cantidad = rs.getDouble("cantidad");
                Timestamp fecha = rs.getTimestamp("fecha");

                inventarioTO.setId(id);
                inventarioTO.setIdproducto(idproducto);
                inventarioTO.setCantidad(cantidad);
                inventarioTO.setFecha(fecha);


                inventario.add(inventarioTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null && rs != null) {
                rs.close();
                stmt.close();
            }
        }

        return inventario;
        
        
        
    }

    @Override
    public boolean create(InventarioTO objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InventarioTO> readAllByUsuario(int usuarioId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
