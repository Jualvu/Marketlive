package com.cci.MarketLive.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import com.cci.MarketLive.controller.LoginController;

public class ServicioProducto extends Conexion implements ICrud<ProductoTO> {

    PreparedStatement stmt;
    ResultSet rs;
    List<ProductoTO> productos;
    @ManagedProperty("#{loginController}")
    private LoginController loginController;

    @Override
    public boolean create(ProductoTO objeto) throws SQLException {
        try {

            String query = "INSERT INTO productos (tipo, codigo, sku, nombre, descripcion, precio, stock, usuario_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, objeto.getTipo());
            stmt.setString(2, objeto.getCodigo());
            stmt.setString(3, objeto.getSku());
            stmt.setString(4, objeto.getNombre());
            stmt.setString(5, objeto.getDescripcion());
            stmt.setDouble(6, objeto.getPrecio());
            stmt.setDouble(7, objeto.getStock());
            stmt.setInt(8, loginController.getId());

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
    public ProductoTO read(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ProductoTO objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductoTO> readAll() throws SQLException {
        productos = new ArrayList<ProductoTO>();

        try {
            stmt = super.getConexion().prepareStatement("SELECT * FROM productos");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProductoTO productoTO = new ProductoTO();

                int id = rs.getInt("id");
                String tipo = rs.getString("tipo");
                String codigo = rs.getString("codigo");
                String sku = rs.getString("sku");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                double stock = rs.getDouble("stock");
                int usuarioId = rs.getInt("usuario_id");

                productoTO.setId(id);
                productoTO.setTipo(tipo);
                productoTO.setCodigo(codigo);
                productoTO.setSku(sku);
                productoTO.setNombre(nombre);
                productoTO.setDescripcion(descripcion);
                productoTO.setPrecio(precio);
                productoTO.setStock(stock);
                productoTO.setUsuarioId(usuarioId);

                productos.add(productoTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null && rs != null) {
                rs.close();
                stmt.close();
            }
        }

        return productos;
    }
}
