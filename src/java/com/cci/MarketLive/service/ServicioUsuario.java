package com.cci.MarketLive.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicioUsuario extends Conexion {

    PreparedStatement stmt;
    ResultSet rs;

    public UsuarioTO login(String correo, String password) throws SQLException {
        UsuarioTO usuarioTO = new UsuarioTO();

        try {
            stmt = super.getConexion().prepareStatement("SELECT * FROM usuarios where correo = ? AND password = ?");
            stmt.setString(1, correo);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int idObtenido = rs.getInt("id");
                String nombreObtenido = rs.getString("nombre");
                String correoObtenido = rs.getString("correo");
                String passwordObtenido = rs.getString("password");
                String rolObtenido = rs.getString("rol");

                usuarioTO.setId(idObtenido);
                usuarioTO.setNombre(nombreObtenido);
                usuarioTO.setCorreo(correoObtenido);
                usuarioTO.setPassword(passwordObtenido);
                usuarioTO.setRol(rolObtenido);
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

            String query = "INSERT INTO usuarios (nombre, correo, password) VALUES (?, ?, ?)";
            stmt = super.getConexion().prepareStatement(query);
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getCorreo());
            stmt.setString(3, objeto.getPassword());
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
