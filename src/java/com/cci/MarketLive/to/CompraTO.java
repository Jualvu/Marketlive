package com.cci.MarketLive.to;

public class CompraTO {

    private int id;
    private Boolean estado;
    private int datosCompra;

    public CompraTO() {
    }

    public CompraTO(int id, Boolean estado, int datosCompra) {
        this.id = id;
        this.estado = estado;
        this.datosCompra = datosCompra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getDatosCompra() {
        return datosCompra;
    }

    public void setDatosCompra(int datosCompra) {
        this.datosCompra = datosCompra;
    }

}
