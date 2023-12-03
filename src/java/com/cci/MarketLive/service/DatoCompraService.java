package com.cci.MarketLive.service;

import com.cci.MarketLive.contracts.ICrud;
import com.cci.MarketLive.to.DatoCompraTO;
import com.cci.MarketLive.to.ItemTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class DatoCompraService extends Conexion implements ICrud<DatoCompraTO> {

    PreparedStatement stmt;
    ResultSet rs;
    List<ItemTO> items;

    @Override
    public boolean create(DatoCompraTO objeto) throws SQLException {
        try {

            LocalDateTime now = LocalDateTime.now();

            Timestamp fechaActual = Timestamp.valueOf(now);

            String query = "INSERT INTO datos_compras (direccion_envio, total, fecha_creacion, metodos_pago_id, usuario_id) VALUES (?, ?, ?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, objeto.getDireccionEnvio());
            stmt.setDouble(2, 0.00);
            stmt.setTimestamp(3, fechaActual);
            stmt.setInt(4, objeto.getMetodoPago());
            stmt.setInt(5, objeto.getUsuarioId());

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
    public DatoCompraTO read(int id) throws SQLException {
        DatoCompraTO datoCompraTO = new DatoCompraTO();
        try {
            String sql = "SELECT * FROM datos_compras WHERE usuario_id = ?";
            stmt = super.getConexion().prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {

                int idObtenido = rs.getInt("id");
                String direccionEnvio = rs.getString("direccion_envio");

                datoCompraTO.setId(idObtenido);
                datoCompraTO.setDireccionEnvio(direccionEnvio);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null && rs != null) {
                rs.close();
                stmt.close();
            }
        }
        return datoCompraTO;
    }

    @Override
    public boolean update(DatoCompraTO objeto) throws SQLException {
        try {

            String query = "UPDATE datos_compras SET direccion_envio = ? WHERE usuario_id = ?";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, objeto.getDireccionEnvio());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DatoCompraTO> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DatoCompraTO> readAllByUsuario(int usuarioId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
