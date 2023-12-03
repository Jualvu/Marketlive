package com.cci.MarketLive.to;

import java.sql.Timestamp;

public class DatoCompraTO {

    private int id;
    private String direccionEnvio;
    private double total;
    private Timestamp fechaCreacion;
    private int metodoPago;
    private int usuarioId;

    public DatoCompraTO() {
    }

    public DatoCompraTO(int id, String direccionEnvio, double total, Timestamp fechaCreacion, int metodoPago, int usuarioId) {
        this.id = id;
        this.direccionEnvio = direccionEnvio;
        this.total = total;
        this.fechaCreacion = fechaCreacion;
        this.metodoPago = metodoPago;
        this.usuarioId = usuarioId;
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

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DatoCompraTO{");
        sb.append("id=").append(id);
        sb.append(", direccionEnvio=").append(direccionEnvio);
        sb.append(", total=").append(total);
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append(", metodoPago=").append(metodoPago);
        sb.append(", usuarioId=").append(usuarioId);
        sb.append('}');
        return sb.toString();
    }

}
