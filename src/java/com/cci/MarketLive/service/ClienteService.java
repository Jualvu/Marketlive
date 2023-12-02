package com.cci.MarketLive.service;

import com.cci.MarketLive.to.UsuarioTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClienteService extends Conexion {

    PreparedStatement stmt;
    ResultSet rs;
    List<UsuarioTO> clientes;

    public boolean create(UsuarioTO cliente) throws SQLException {
        try {
            LocalDateTime now = LocalDateTime.now();
            Timestamp fechaActual = Timestamp.valueOf(now);

            String query = "INSERT INTO usuarios (nombre, correo, password, fecha_creado, role_id) VALUES (?, ?, ?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getCorreo());
            stmt.setString(3, cliente.getPassword());
            stmt.setTimestamp(4, fechaActual);
            stmt.setInt(5, cliente.getRoleId());
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

    public UsuarioTO read(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean update(UsuarioTO cliente) throws SQLException {
        try {
            String query = "UPDATE usuarios SET nombre = ?, correo = ?, role_id = ? WHERE id = ?";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getCorreo());
            stmt.setInt(3, cliente.getRoleId());
            stmt.setInt(4, cliente.getId());
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

    public boolean delete(int id) throws SQLException {
        try {
            String query = "DELETE FROM usuarios WHERE id = ?";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setInt(1, id);
            stmt.execute();
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

    public List<UsuarioTO> readAll() throws SQLException {
        clientes = new ArrayList<UsuarioTO>();
        try {
            stmt = super.getConexion().prepareStatement("SELECT * FROM usuarios");
            rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioTO cliente = new UsuarioTO();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setRoleId(rs.getInt("role_id"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null && rs != null) {
                rs.close();
                stmt.close();
            }
        }
        return clientes;
    }
}
