package com.cci.MarketLive.to;

import java.io.Serializable;

public class CuentaTO implements Serializable {

    private String nombre;
    private String correo;
    private String direccion;
    private String fecha;
    private String passsword;
    private String confirmPassword;

    public CuentaTO() {
    }

    public CuentaTO(String nombre, String correo, String direccion, String fecha, String passsword, String confirmPassword) {
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.fecha = fecha;
        this.passsword = passsword;
        this.confirmPassword = confirmPassword;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CuentaTO{");
        sb.append("nombre=").append(nombre);
        sb.append(", correo=").append(correo);
        sb.append(", direccion=").append(direccion);
        sb.append(", fecha=").append(fecha);
        sb.append(", passsword=").append(passsword);
        sb.append(", confirmPassword=").append(confirmPassword);
        sb.append('}');
        return sb.toString();
    }

}
