package com.cci.MarketLive.to;

import java.sql.Timestamp;

public class FacturaTO {

    private int id;
    private int compraId;
    private int numeroFactura;
    private String rutaPdf;
    private Timestamp fechaCreacion;

    public FacturaTO() {
    }

    public FacturaTO(int id, int compraId, int numeroFactura, String rutaPdf, Timestamp fechaCreacion) {
        this.id = id;
        this.compraId = compraId;
        this.numeroFactura = numeroFactura;
        this.rutaPdf = rutaPdf;
        this.fechaCreacion = fechaCreacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getRutaPdf() {
        return rutaPdf;
    }

    public void setRutaPdf(String rutaPdf) {
        this.rutaPdf = rutaPdf;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
