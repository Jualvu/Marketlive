package com.cci.MarketLive.service;

import com.cci.MarketLive.contracts.ICrud;
import com.cci.MarketLive.to.ProductoTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedProperty;

public class ProductoService extends Conexion implements ICrud<ProductoTO> {

    PreparedStatement stmt;
    ResultSet rs;
    List<ProductoTO> productos;

    @Override
    public boolean create(ProductoTO objeto) throws SQLException {
        try {

            LocalDateTime now = LocalDateTime.now();

            Timestamp fechaActual = Timestamp.valueOf(now);

            String query = "INSERT INTO productos (tipo, codigo, nombre, descripcion, precio, stock, fecha_creado, usuario_id, categoria_id, tienda_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, objeto.getTipo());
            stmt.setString(2, objeto.getCodigo());
            stmt.setString(3, objeto.getNombre());
            stmt.setString(4, objeto.getDescripcion());
            stmt.setDouble(5, objeto.getPrecio());
            stmt.setDouble(6, objeto.getStock());
            stmt.setTimestamp(7, fechaActual);
            stmt.setInt(8, 1);
            stmt.setInt(9, objeto.getCategoriaId());
            stmt.setInt(10, objeto.getTiendaId());

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

    public boolean createByUser(ProductoTO objeto, int usuarioId) throws SQLException {
        try {

            LocalDateTime now = LocalDateTime.now();

            Timestamp fechaActual = Timestamp.valueOf(now);

            String query = "INSERT INTO productos (tipo, codigo, nombre, descripcion, precio, stock, fecha_creado, usuario_id, categoria_id, tienda_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, objeto.getTipo());
            stmt.setString(2, objeto.getCodigo());
            stmt.setString(3, objeto.getNombre());
            stmt.setString(4, objeto.getDescripcion());
            stmt.setDouble(5, objeto.getPrecio());
            stmt.setDouble(6, objeto.getStock());
            stmt.setTimestamp(7, fechaActual);
            stmt.setInt(8, 1);
            stmt.setInt(9, objeto.getCategoriaId());
            stmt.setInt(10, objeto.getTiendaId());

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
        try {
            System.out.println("sa" + objeto.getId());
            String query = "UPDATE productos SET tipo = ?, codigo = ?, nombre = ?, descripcion = ?, precio = ?, stock = ? WHERE id = ?";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, objeto.getTipo());
            stmt.setString(2, objeto.getCodigo());
            stmt.setString(3, objeto.getNombre());
            stmt.setString(4, objeto.getDescripcion());
            stmt.setDouble(5, objeto.getPrecio());
            stmt.setDouble(6, objeto.getStock());
            stmt.setInt(7, objeto.getId());

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

    public boolean updateCantidad(ProductoTO objeto, double cantidad) throws SQLException {
        try {
            System.out.println("sa" + objeto.getId());
            String query = "UPDATE productos SET tipo = ?, codigo = ?, nombre = ?, descripcion = ?, precio = ?, stock = ? WHERE id = ?";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, objeto.getTipo());
            stmt.setString(2, objeto.getCodigo());
            stmt.setString(3, objeto.getNombre());
            stmt.setString(4, objeto.getDescripcion());
            stmt.setDouble(5, objeto.getPrecio());
            stmt.setDouble(6, objeto.getStock()+cantidad);
            stmt.setInt(7, objeto.getId());

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
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;

        try {
            String query1 = "DELETE FROM items WHERE producto_id = ?";
            stmt1 = super.getConexion().prepareStatement(query1);
            stmt1.setInt(1, id);
            stmt1.execute();

            String query2 = "DELETE FROM productos WHERE id = ?";
            stmt2 = super.getConexion().prepareStatement(query2);
            stmt2.setInt(1, id);
            stmt2.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            if (stmt1 != null) {
                stmt1.close();
            }
            if (stmt2 != null) {
                stmt2.close();
            }
        }
    }

    @Override
    public List<ProductoTO> readAll() throws SQLException {
        productos = new ArrayList<ProductoTO>();

        try {
            stmt = super.getConexion().prepareStatement("SELECT p.* , c.nombre as categoria_nombre , t.nombre as tienda_nombre "
                    + "FROM productos p "
                    + "INNER JOIN categorias c ON p.categoria_id = c.id "
                    + "INNER JOIN tiendas t ON p.tienda_id = t.id "
                    + "ORDER BY p.id DESC; ");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProductoTO productoTO = new ProductoTO();

                int id = rs.getInt("id");
                String tipo = rs.getString("tipo");
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                double stock = rs.getDouble("stock");
                int usuarioId = rs.getInt("usuario_id");
                int categoriaId = rs.getInt("categoria_id");
                String categoriaNombre = rs.getString("categoria_nombre");
                int tiendaId = rs.getInt("tienda_id");
                String tiendaNombre = rs.getString("tienda_nombre");

                productoTO.setId(id);
                productoTO.setTipo(tipo);
                productoTO.setCodigo(codigo);
                productoTO.setNombre(nombre);
                productoTO.setDescripcion(descripcion);
                productoTO.setPrecio(precio);
                productoTO.setStock(stock);
                productoTO.setUsuarioId(usuarioId);
                productoTO.setCategoriaId(categoriaId);
                productoTO.setCategoriaNombre(categoriaNombre);
                productoTO.setTiendaId(tiendaId);
                productoTO.setTiendaNombre(tiendaNombre);

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

    @Override
    public List<ProductoTO> readAllByUsuario(int usuarioId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ProductoTO> readAllByCategoria(int idCategoria) throws SQLException {
        productos = new ArrayList<ProductoTO>();

        try {

            stmt = super.getConexion().prepareStatement("SELECT p.* , c.nombre as categoria_nombre , t.nombre as tienda_nombre "
                    + "FROM productos p "
                    + "INNER JOIN categorias c ON p.categoria_id = c.id "
                    + "INNER JOIN tiendas t ON p.tienda_id = t.id "
                    + "WHERE categoria_id = ? "
                    + "ORDER BY p.id DESC; ");
            stmt.setInt(1, idCategoria);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProductoTO productoTO = new ProductoTO();

               int id = rs.getInt("id");
                String tipo = rs.getString("tipo");
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                double stock = rs.getDouble("stock");
                int usuarioId = rs.getInt("usuario_id");
                int categoriaId = rs.getInt("categoria_id");
                String categoriaNombre = rs.getString("categoria_nombre");
                int tiendaId = rs.getInt("tienda_id");
                String tiendaNombre = rs.getString("tienda_nombre");

                productoTO.setId(id);
                productoTO.setTipo(tipo);
                productoTO.setCodigo(codigo);
                productoTO.setNombre(nombre);
                productoTO.setDescripcion(descripcion);
                productoTO.setPrecio(precio);
                productoTO.setStock(stock);
                productoTO.setUsuarioId(usuarioId);
                productoTO.setCategoriaId(categoriaId);
                productoTO.setCategoriaNombre(categoriaNombre);
                productoTO.setTiendaId(tiendaId);
                productoTO.setTiendaNombre(tiendaNombre);

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
    
    public List<ProductoTO> readAllByTienda(int idTienda) throws SQLException {
        productos = new ArrayList<ProductoTO>();

        try {

            stmt = super.getConexion().prepareStatement("SELECT p.* , c.nombre as categoria_nombre , t.nombre as tienda_nombre "
                    + "FROM productos p "
                    + "INNER JOIN categorias c ON p.categoria_id = c.id "
                    + "INNER JOIN tiendas t ON p.tienda_id = t.id "
                    + "WHERE tienda_id = ? "
                    + "ORDER BY p.id DESC; ");
            stmt.setInt(1, idTienda);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProductoTO productoTO = new ProductoTO();

               int id = rs.getInt("id");
                String tipo = rs.getString("tipo");
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                double stock = rs.getDouble("stock");
                int usuarioId = rs.getInt("usuario_id");
                int categoriaId = rs.getInt("categoria_id");
                String categoriaNombre = rs.getString("categoria_nombre");
                int tiendaId = rs.getInt("tienda_id");
                String tiendaNombre = rs.getString("tienda_nombre");

                productoTO.setId(id);
                productoTO.setTipo(tipo);
                productoTO.setCodigo(codigo);
                productoTO.setNombre(nombre);
                productoTO.setDescripcion(descripcion);
                productoTO.setPrecio(precio);
                productoTO.setStock(stock);
                productoTO.setUsuarioId(usuarioId);
                productoTO.setCategoriaId(categoriaId);
                productoTO.setCategoriaNombre(categoriaNombre);
                productoTO.setTiendaId(tiendaId);
                productoTO.setTiendaNombre(tiendaNombre);

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
    
    public List<ProductoTO> listarBusqueda(String producto) throws SQLException {
        productos = new ArrayList<ProductoTO>();

        try {
            stmt = super.getConexion().prepareStatement("SELECT * FROM productos WHERE nombre LIKE ?");
            stmt.setString(1, "%" + producto + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProductoTO productoTO = new ProductoTO();

                int id = rs.getInt("id");
                String tipo = rs.getString("tipo");
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                double stock = rs.getDouble("stock");
                int usuarioId = rs.getInt("usuario_id");

                productoTO.setId(id);
                productoTO.setTipo(tipo);
                productoTO.setCodigo(codigo);
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
