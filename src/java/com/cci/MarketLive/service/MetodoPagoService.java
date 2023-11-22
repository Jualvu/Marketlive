/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.MarketLive.service;

import com.cci.MarketLive.contracts.ICrud;
import com.cci.MarketLive.to.MetodoPagoTO;
import com.cci.MarketLive.to.ProductoTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chris
 */
public class MetodoPagoService extends Conexion implements ICrud<MetodoPagoTO> {

    PreparedStatement stmt;
    ResultSet rs;
    List<MetodoPagoTO> metodosPago;

    @Override
    public boolean create(MetodoPagoTO objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MetodoPagoTO read(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(MetodoPagoTO objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MetodoPagoTO> readAll() throws SQLException {
        metodosPago = new ArrayList<MetodoPagoTO>();

        try {
            stmt = super.getConexion().prepareStatement("SELECT * from metodos_pago");
            rs = stmt.executeQuery();

            while (rs.next()) {
                MetodoPagoTO metodoPagoTO = new MetodoPagoTO();

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");

                metodoPagoTO.setId(id);
                metodoPagoTO.setNombre(nombre);
                metodoPagoTO.setDescripcion(descripcion);

                metodosPago.add(metodoPagoTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null && rs != null) {
                rs.close();
                stmt.close();
            }
        }

        return metodosPago;
    }

    @Override
    public List<MetodoPagoTO> readAllByUsuario(int usuarioId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
