
package com.cci.MarketLive.service;


public class DatosCompraTO {
    
    private int id;
    private String direccionEnvio;
    private double total;
    private String fechaCreacion;
    private int metodosPagoId;
    private int usuarioId;

    public DatosCompraTO() {
    }

    public DatosCompraTO(int id, String direccionEnvio, double total, String fechaCreacion, int metodosPagoId, int usuarioId) {
        this.id = id;
        this.direccionEnvio = direccionEnvio;
        this.total = total;
        this.fechaCreacion = fechaCreacion;
        this.metodosPagoId = metodosPagoId;
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

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getMetodosPagoId() {
        return metodosPagoId;
    }

    public void setMetodosPagoId(int metodosPagoId) {
        this.metodosPagoId = metodosPagoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    
    
}
