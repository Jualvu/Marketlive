
package com.cci.MarketLive.service;

import com.cci.MarketLive.contracts.ICrud;
import com.cci.MarketLive.to.ProductoTO;
import com.cci.MarketLive.to.TiendaTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TiendaService extends Conexion implements ICrud<TiendaTO>{

    PreparedStatement stmt;
    ResultSet rs;
    List<TiendaTO> tiendas;
    List<ProductoTO> productos;
    
    
    @Override
    public boolean create(TiendaTO objeto) throws SQLException {
       try {

            String query = "INSERT INTO tiendas (nombre, descripcion, usuario_id) VALUES (?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getDescripcion());
            stmt.setInt(2, objeto.getIdUsuario());

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

    public boolean createForUser(TiendaTO objeto, int usuarioID) throws SQLException {
       try {

            String query = "INSERT INTO tiendas (nombre, descripcion, usuario_id) VALUES (?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getDescripcion());
            stmt.setInt(3, usuarioID);

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
    public TiendaTO read(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
        
        
    }

    @Override
    public boolean update(TiendaTO objeto) throws SQLException {
        try {
            String query = "UPDATE tiendas SET nombre = ?, descripcion = ? WHERE id = ?";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getDescripcion());
            stmt.setInt(3, objeto.getId());

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
        throw new UnsupportedOperationException("Not supported yet."); 
        
        
        
    }

    @Override
    public List<TiendaTO> readAll() throws SQLException {
        
        List<TiendaTO> tiendas = new ArrayList<TiendaTO>();
        try {
            stmt = super.getConexion().prepareStatement("SELECT * FROM tiendas order by id desc");
            rs = stmt.executeQuery();
            while (rs.next()) {
                TiendaTO tienda = new TiendaTO();
                tienda.setId(rs.getInt("id"));
                tienda.setNombre(rs.getString("nombre"));
                tienda.setDescripcion(rs.getString("descripcion"));
                tiendas.add(tienda);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return tiendas;
        
        
    }
    

    @Override
    public List<TiendaTO> readAllByUsuario(int usuarioId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
        
        
        
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
    
    
}
