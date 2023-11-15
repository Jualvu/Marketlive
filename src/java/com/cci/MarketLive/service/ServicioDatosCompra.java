
package com.cci.MarketLive.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ServicioDatosCompra extends Conexion implements ICrud<DatosCompraTO> {

    PreparedStatement stmt;
    ResultSet rs;
    List<DatosCompraTO> datosCompras;

    public ServicioDatosCompra() {
    }
    
    
    
    @Override
    public boolean create(DatosCompraTO objeto) throws SQLException {
         try {

            String query = "INSERT INTO datos_compra (id, direccion_envio, total, fecha_creacion, metodos_pago_id, usuario_id) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getDireccionEnvio());
            stmt.setDouble(3, objeto.getTotal());
            stmt.setString(4, objeto.getFechaCreacion());
            stmt.setInt(5, objeto.getMetodosPagoId());
            stmt.setInt(6, objeto.getUsuarioId());

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
    //Crea los datos para el usuario que inicio sesion
    public boolean createForUser(DatosCompraTO objeto, int id) throws SQLException {
         try {

            String query = "INSERT INTO datos_compra (id, direccion_envio, total, fecha_creacion, metodos_pago_id, usuario_id) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getDireccionEnvio());
            stmt.setDouble(3, objeto.getTotal());
            stmt.setString(4, objeto.getFechaCreacion());
            stmt.setInt(5, objeto.getMetodosPagoId());
            stmt.setInt(6, id);

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
    public DatosCompraTO read(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(DatosCompraTO objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Actualiza informacion para usuario que inicio sesion
    public boolean updateForUser(DatosCompraTO objeto, int id) throws SQLException {
        
        try {

            String query = "UPDATE datos_compra SET direccion_envio = ?, total = ?, fecha_creacion = ?, metodos_pago_id = ? WHERE (id = ?) ";
            stmt = super.getConexion().prepareStatement(query);
            
            stmt.setString(1, objeto.getDireccionEnvio());
            stmt.setDouble(2, objeto.getTotal());
            stmt.setString(3, objeto.getFechaCreacion());
            stmt.setInt(4, objeto.getMetodosPagoId());
            stmt.setInt(5, id);

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DatosCompraTO> readAll() throws SQLException {
        datosCompras = new ArrayList<DatosCompraTO>();

        try {
            stmt = super.getConexion().prepareStatement("SELECT * FROM datos_compras");
            rs = stmt.executeQuery();

            while (rs.next()) {
                DatosCompraTO datosCompraTO = new DatosCompraTO();

                int id = rs.getInt("id");
                String direccionEnvio = rs.getString("direccion_envio");
                double total = rs.getDouble("total");
                String fechaCreacion = rs.getString("fecha_creacion");
                int metodosPagoId = rs.getInt("metodos_pago_id");
                int usuarioId = rs.getInt("usuario_id");
                
                datosCompraTO.setId(id);
                datosCompraTO.setDireccionEnvio(direccionEnvio);
                datosCompraTO.setTotal(total);
                datosCompraTO.setFechaCreacion(fechaCreacion);
                datosCompraTO.setMetodosPagoId(metodosPagoId);
                datosCompraTO.setUsuarioId(usuarioId);

                datosCompras.add(datosCompraTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null && rs != null) {
                rs.close();
                stmt.close();
            }
        }

        return datosCompras;
    }
    
}
