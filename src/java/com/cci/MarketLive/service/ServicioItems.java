
package com.cci.MarketLive.service;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ServicioItems extends Conexion implements ICrud{
    
    PreparedStatement stmt;
    ResultSet rs;
    List<ItemsTO> items;

   //Agregar un item para el usuario que inicio sesion
    public boolean createForUser(ItemsTO objeto, int usuarioId, int productoId) throws SQLException {
        try {

            String query = "INSERT INTO items (usuario_id, producto_id, cantidad, precio) VALUES (?, ?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, productoId);
            stmt.setDouble(3, objeto.getCantidad());
            stmt.setDouble(4, objeto.getPrecio());

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

    //Agrega un item con Cantidad 1
    public boolean createOne(ItemsTO objeto, int usuarioId, int productoId) throws SQLException {
        try {

            String query = "INSERT INTO items (usuario_id, producto_id, cantidad, precio) VALUES (?, ?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, productoId);
            stmt.setDouble(3, 1);
            stmt.setDouble(4, objeto.getPrecio());

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
    
    
    //Lista todos los items del usuario que esta con sesion activa

    public List<ItemsTO> read(int usuarioId) throws SQLException {
        
        items = new ArrayList<ItemsTO>();

        try {
            stmt = super.getConexion().prepareStatement("SELECT * FROM items WHERE usuario_id=?");
            stmt.setInt(1, usuarioId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ItemsTO itemsTO = new ItemsTO();

                int id = rs.getInt("id");
                int idusuario = rs.getInt("usuario_id");
                int idproducto = rs.getInt("producto_id");
                double cantidad = rs.getDouble("cantidad");
                double precio = rs.getDouble("precio");

                itemsTO.setId(id);
                itemsTO.setIdusuario(idusuario);
                itemsTO.setIdproducto(idproducto);
                itemsTO.setCantidad(cantidad);
                itemsTO.setPrecio(precio);


                items.add(itemsTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null && rs != null) {
                rs.close();
                stmt.close();
            }
        }

        return items;
    }

    
   
    
    //Actualiza la cantidad del item que se selecciono
    public boolean updateCantidad(ItemsTO objeto, double cantidad) throws SQLException {
        try {

            String query = "UPDATE items SET cantidad = ?  WHERE (id=?) ";
            stmt = super.getConexion().prepareStatement(query);
            
            stmt.setDouble(1, cantidad);
            stmt.setInt(2, objeto.getId());

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

    //Elina item seleccionado
    public boolean delete(int id) throws SQLException {
       try {
            PreparedStatement stmt = super.getConexion().prepareStatement("DELETE FROM items WHERE id=?");
            stmt.setInt(1,id);
            stmt.executeUpdate();

            stmt.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el items: " + ex.getMessage());
            return false;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        
        
    }

    //elimina todos los items del carrito
    public boolean deleteAll(int usuarioId) throws SQLException {
       try {
            PreparedStatement stmt = super.getConexion().prepareStatement("DELETE * FROM items WHERE usuario_id=?");
            stmt.setInt(1,usuarioId);
            stmt.executeUpdate();

            stmt.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el items: " + ex.getMessage());
            return false;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        
        
    }
    
    
    //Lista todos
    public List<ItemsTO> readAll() throws SQLException {
        items = new ArrayList<ItemsTO>();

        try {
            stmt = super.getConexion().prepareStatement("SELECT * FROM items");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ItemsTO itemsTO = new ItemsTO();

                int id = rs.getInt("id");
                int idusuario = rs.getInt("usuario_id");
                int idproducto = rs.getInt("producto_id");
                double cantidad = rs.getDouble("cantidad");
                double precio = rs.getDouble("precio");

                itemsTO.setId(id);
                itemsTO.setIdusuario(idusuario);
                itemsTO.setIdproducto(idproducto);
                itemsTO.setCantidad(cantidad);
                itemsTO.setPrecio(precio);


                items.add(itemsTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null && rs != null) {
                rs.close();
                stmt.close();
            }
        }

        return items;
    }

    @Override
    public boolean create(Object objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
