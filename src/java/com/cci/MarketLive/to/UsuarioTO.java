package com.cci.MarketLive.to;

import java.sql.Timestamp;

public class UsuarioTO {

    private int id;
    private String nombre;
    private String correo;
    private String password;
    private int roleId;
    private Timestamp fechaCreada;
    private String roleNombre;

    public UsuarioTO() {
    }

    public UsuarioTO(int id, String nombre, String correo, String password, int roleId) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.fechaCreada = fechaCreada;
        this.password = password;
        this.roleId = roleId;
        this.roleNombre = roleNombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setFechaCreada(Timestamp fechaCreada) {
        this.fechaCreada = fechaCreada;
    }

    public String getRoleNombre() {
        return roleNombre;
    }

    public void setRoleNombre(String roleNombre) {
        this.roleNombre = roleNombre;
    }

}
