package com.cci.MarketLive.to;

public class ClienteTO {

    private int id;
    private String nombre;
    private String correo;
    private String contrasena;
    private int tipoRol;
    private int usuarioId;
    private String usuarioNombre;
    private String usuarioCorreo;
    private int usuarioTipoRol;

    public ClienteTO() {
    }

    public ClienteTO(int id, String nombre, String correo, String contrasena, int tipoRol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipoRol = tipoRol;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(int tipoRol) {
        this.tipoRol = tipoRol;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    public int getUsuarioTipoRol() {
        return usuarioTipoRol;
    }

   public void setUsuarioTipoRol(int tipoRol) {
        this.tipoRol = tipoRol;
    }
}
