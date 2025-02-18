package com.cci.MarketLive.service;

import com.cci.MarketLive.contracts.ICrud;
import com.cci.MarketLive.to.ItemTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemService extends Conexion implements ICrud<ItemTO> {

    PreparedStatement stmt;
    ResultSet rs;
    List<ItemTO> items;

    @Override
    public boolean create(ItemTO objeto) throws SQLException {
        try {

            String query = "INSERT INTO items (usuario_id, producto_id, cantidad, precio) VALUES (?,?,?,?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setInt(1, objeto.getUsuarioId());
            stmt.setInt(2, objeto.getProductoId());
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

    public ItemTO readd(int id, int idUsuario) throws SQLException {
        ItemTO itemTO = new ItemTO();
        try {
            String sql = "SELECT im.*, pd.nombre AS nombre_producto "
                    + "FROM items im "
                    + "JOIN productos pd ON im.producto_id = pd.id "
                    + "WHERE im.usuario_id = ? AND im.producto_id = ?";
            stmt = super.getConexion().prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, id);

            rs = stmt.executeQuery();

            while (rs.next()) {

                int idObtenido = rs.getInt("id");
                int usuarioId = rs.getInt("usuario_id");
                int productoId = rs.getInt("producto_id");
                double cantidad = rs.getDouble("cantidad");
                double precio = rs.getDouble("precio");
                String nombreProducto = rs.getString("nombre_producto");

                itemTO.setId(idObtenido);
                itemTO.setUsuarioId(usuarioId);
                itemTO.setProductoId(productoId);
                itemTO.setCantidad(cantidad);
                itemTO.setPrecio(precio);
                itemTO.setNombreProducto(nombreProducto);

                items.add(itemTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null && rs != null) {
                rs.close();
                stmt.close();
            }
        }
        return itemTO;
    }

    @Override
    public boolean update(ItemTO objeto) throws SQLException {
        try {
            String query = "UPDATE items SET cantidad = ? WHERE id = ?";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setDouble(1, objeto.getCantidad());
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

    @Override
    public boolean delete(int id) throws SQLException {
        try {
            String query = "DELETE FROM items WHERE id = ?";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setInt(1, id);

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
    public List<ItemTO> readAll() throws SQLException {
        items = new ArrayList<ItemTO>();

        try {
            String sql = "SELECT im.*, pd.nombre AS nombre_producto "
                    + "FROM items im "
                    + "JOIN productos pd ON im.producto_id = pd.id "
                    + "WHERE im.usuario_id = ?";

            stmt = super.getConexion().prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                ItemTO itemTO = new ItemTO();

                int id = rs.getInt("id");
                int usuarioId = rs.getInt("usuario_id");
                int productoId = rs.getInt("producto_id");
                double cantidad = rs.getDouble("cantidad");
                double precio = rs.getDouble("precio");
                String nombreProducto = rs.getString("nombre_producto");

                itemTO.setId(id);
                itemTO.setUsuarioId(usuarioId);
                itemTO.setProductoId(productoId);
                itemTO.setCantidad(cantidad);
                itemTO.setPrecio(precio);
                itemTO.setNombreProducto(nombreProducto);

                items.add(itemTO);
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
    public List<ItemTO> readAllByUsuario(int usuarioId) throws SQLException {
        items = new ArrayList<ItemTO>();

        try {
            String sql = "SELECT im.*, pd.nombre AS nombre_producto "
                    + "FROM items im "
                    + "JOIN productos pd ON im.producto_id = pd.id "
                    + "WHERE im.usuario_id = ?";

            stmt = super.getConexion().prepareStatement(sql);

            stmt.setInt(1, usuarioId);

            rs = stmt.executeQuery();

            while (rs.next()) {
                ItemTO itemTO = new ItemTO();

                int id = rs.getInt("id");
                int usuarioIdObtenido = rs.getInt("usuario_id");
                int productoId = rs.getInt("producto_id");
                double cantidad = rs.getDouble("cantidad");
                double precio = rs.getDouble("precio");
                String nombreProducto = rs.getString("nombre_producto");

                itemTO.setId(id);
                itemTO.setUsuarioId(usuarioIdObtenido);
                itemTO.setProductoId(productoId);
                itemTO.setCantidad(cantidad);
                itemTO.setPrecio(precio);
                itemTO.setNombreProducto(nombreProducto);

                items.add(itemTO);
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

    public boolean limpiarCarrito(int id) throws SQLException {
        try {
            String query1 = "DELETE FROM items WHERE usuario_id = ?";
            stmt = super.getConexion().prepareStatement(query1);
            stmt.setInt(1, id);
            stmt.execute();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public double calcularSumaPrecio(int idUsuario) throws SQLException {
        double totalPrecio = 0.0;

        try {
            String sql = "SELECT SUM(im.cantidad * pd.precio) AS total "
                    + "FROM items im "
                    + "JOIN productos pd ON im.producto_id = pd.id "
                    + "WHERE im.usuario_id = ?";

            stmt = super.getConexion().prepareStatement(sql);
            stmt.setInt(1, idUsuario);

            rs = stmt.executeQuery();

            if (rs.next()) {
                totalPrecio = rs.getDouble("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null && rs != null) {
                rs.close();
                stmt.close();
            }
        }

        return totalPrecio;
    }

    @Override
    public ItemTO read(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
