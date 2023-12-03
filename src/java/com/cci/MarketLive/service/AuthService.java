package com.cci.MarketLive.service;

import com.cci.MarketLive.to.UsuarioTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AuthService extends Conexion {

    PreparedStatement stmt;
    ResultSet rs;

    public UsuarioTO login(String correo, String password) throws SQLException {
        UsuarioTO usuarioTO = new UsuarioTO();

        try {
            stmt = super.getConexion().prepareStatement("SELECT u.*, roles.nombre AS role_nombre "
                    + "FROM usuarios AS u "
                    + "JOIN roles ON u.role_id = roles.id "
                    + "WHERE u.correo = ? AND u.password = ?");

            stmt.setString(1, correo);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int idObtenido = rs.getInt("id");
                String nombreObtenido = rs.getString("nombre");
                String correoObtenido = rs.getString("correo");
                String passwordObtenido = rs.getString("password");
                Timestamp fechaCreada = rs.getTimestamp("fecha_creado");
                int roleId = rs.getInt("role_id");
                String roleNombre = rs.getString("role_nombre");

                usuarioTO.setId(idObtenido);
                usuarioTO.setNombre(nombreObtenido);
                usuarioTO.setCorreo(correoObtenido);
                usuarioTO.setPassword(passwordObtenido);
                usuarioTO.setFechaCreada(fechaCreada);
                usuarioTO.setRoleId(roleId);
                usuarioTO.setRoleNombre(roleNombre);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null && rs != null) {
                rs.close();
                stmt.close();
            }
        }
        return usuarioTO;
    }

    public boolean register(UsuarioTO objeto) throws SQLException {
        try {

            // Obtener la fecha y hora actual
            java.util.Date utilDate = new java.util.Date();
            Timestamp fechaActual = new Timestamp(utilDate.getTime());

            String query = "INSERT INTO usuarios (nombre, correo, password, fecha_creado, role_id) VALUES (?, ?, ?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getCorreo());
            stmt.setString(3, objeto.getPassword());
            stmt.setTimestamp(4, fechaActual);
            stmt.setInt(5, objeto.getRoleId());
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

    public boolean updatePassword(UsuarioTO objeto) throws SQLException {
        try {

            String query = "UPDATE usuarios SET password = ? WHERE id = ?";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, objeto.getPassword());
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

    public boolean updateData(UsuarioTO objeto) throws SQLException {
        try {

            String query = "UPDATE usuarios SET nombre = ?, correo = ? WHERE id = ?";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getCorreo());
            stmt.setInt(3, objeto.getId());

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

}
