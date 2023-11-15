package com.cci.MarketLive.to;

import java.io.Serializable;
import java.sql.Timestamp;

public class UsuarioTO implements Serializable {

    private int id;
    private String nombre;
    private String correo;
    private String password;
    private Timestamp fechaCreada;
    private int roleId;
    private String roleNombre;

    public UsuarioTO() {
    }

    public UsuarioTO(int id, String nombre, String correo, String password, Timestamp fechaCreada, int roleId, String roleNombre) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.fechaCreada = fechaCreada;
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

    public Timestamp getFechaCreada() {
        return fechaCreada;
    }

    public void setFechaCreada(Timestamp fechaCreada) {
        this.fechaCreada = fechaCreada;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleNombre() {
        return roleNombre;
    }

    public void setRoleNombre(String roleNombre) {
        this.roleNombre = roleNombre;
    }

    @Override
    public String toString() {
        return "UsuarioTO{" + "id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", password=" + password + ", fechaCreada=" + fechaCreada + ", roleId=" + roleId + ", roleNombre=" + roleNombre + '}';
    }

}
