package com.cci.MarketLive.to;

import java.sql.Timestamp;

public class DatoCompraTO {

    private int id;
    private String direccionEnvio;
    private double total;
    private Timestamp fechaCreacion;
    private int metodoPago;
    private int usuario_id;

    public DatoCompraTO() {
    }

    public DatoCompraTO(int id, String direccionEnvio, double total, Timestamp fechaCreacion, int metodoPago, int usuario_id) {
        this.id = id;
        this.direccionEnvio = direccionEnvio;
        this.total = total;
        this.fechaCreacion = fechaCreacion;
        this.metodoPago = metodoPago;
        this.usuario_id = usuario_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(int metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

}
