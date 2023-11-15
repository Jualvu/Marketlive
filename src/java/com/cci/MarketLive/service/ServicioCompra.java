package com.cci.MarketLive.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioCompra extends Conexion implements ICrud<CompraTO> {

    PreparedStatement stmt;
    ResultSet rs;
    List<CompraTO> compras;


    @Override
    public boolean create(CompraTO objeto) throws SQLException {
        try {

            String query = "INSERT INTO compras (id, estado, datos_compras_id) VALUES (?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setInt(1, objeto.getId());
            stmt.setInt(2, objeto.getIdDatosCompras());
            stmt.setString(3, objeto.getEstado());

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
    
    //Crear compra Pendiente
    public boolean createForUser(CompraTO objeto, int id) throws SQLException {
        try {

            String query = "INSERT INTO compras (id, estado, datos_compras_id) VALUES (?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, "Pendiente");
            stmt.setInt(2, id);

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
    
    //Completa la compra
    public boolean cancelarCompra(int id) throws SQLException{
        
        try {

            String query = "UPDATE compras SET estado = ?  WHERE (id=?) ";
            stmt = super.getConexion().prepareStatement(query);
            
            stmt.setString(1, "Completado");
            stmt.setInt(2, id);

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
    
    
    //Agregar el item a la tabla item_compra
    public boolean agregarItemCompra(int compraID, int itemID) throws SQLException{
        
        try {

            String query = "INSERT INTO item_compra (id, compra_id, item_id) VALUES (?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setInt(1, compraID);
            stmt.setInt(2, itemID);

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
    public CompraTO read(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(CompraTO objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CompraTO> readAll() throws SQLException {
        compras = new ArrayList<CompraTO>();

        try {
            stmt = super.getConexion().prepareStatement("SELECT * FROM compras");
            rs = stmt.executeQuery();

            while (rs.next()) {
                CompraTO compraTO = new CompraTO();

                int id = rs.getInt("id");
                String estado = rs.getString("estado");
                int idDatosCompras = rs.getInt("id_datos_compras");

                compraTO.setId(id);
                compraTO.setEstado(estado);
                compraTO.setIdDatosCompras(idDatosCompras);

                compras.add(compraTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null && rs != null) {
                rs.close();
                stmt.close();
            }
        }

        return compras;
    }
    
}


