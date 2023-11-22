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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CategoriaTO read(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(CategoriaTO objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CategoriaTO> readAll() throws SQLException {
        List<CategoriaTO> categorias = new ArrayList<CategoriaTO>();
        try {
            PreparedStatement stmt = super.getConexion().prepareStatement("SELECT * FROM categorias");
            ResultSet rs = stmt.executeQuery();
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
