package com.cci.MarketLive.service;

import com.cci.MarketLive.contracts.ICrud;
import com.cci.MarketLive.to.CategoriaTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaService extends Conexion implements ICrud<CategoriaTO> {

    PreparedStatement stmt;
    ResultSet rs;
    List<CategoriaTO> categoria;

    @Override
    public boolean create(CategoriaTO objeto) throws SQLException {
        try {
            String query = "INSERT INTO categorias (nombre, descripcion) VALUES (?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getDescripcion());

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
    public CategoriaTO read(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(CategoriaTO objeto) throws SQLException {
        try {
            String query = "UPDATE categorias SET nombre = ?, descripcion = ? WHERE id = ?";
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
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;

        try {
            
            String query1 = "DELETE FROM productos WHERE categoria_id = ?";
            stmt1 = super.getConexion().prepareStatement(query1);
            stmt1.setInt(1, id);
            stmt1.execute();

            String query2 = "DELETE FROM categorias WHERE id = ?";
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
        public List<CategoriaTO> readAll() throws SQLException {
        List<CategoriaTO> categorias = new ArrayList<CategoriaTO>();
        try {
            stmt = super.getConexion().prepareStatement("SELECT * FROM categorias order by id desc");
            rs = stmt.executeQuery();
            while (rs.next()) {
                CategoriaTO categoria = new CategoriaTO();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categorias.add(categoria);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return categorias;
    }

    @Override
        public List<CategoriaTO> readAllByUsuario(int usuarioId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
