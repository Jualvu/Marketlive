package com.cci.MarketLive.service;


public class CompraTO {
    
    private int id;
    private String estado;
    private int idDatosCompras;

    public CompraTO() {
    }

    public CompraTO(int id, String estado, int idDatosCompras) {
        this.id = id;
        this.estado = estado;
        this.idDatosCompras = idDatosCompras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdDatosCompras() {
        return idDatosCompras;
    }

    public void setIdDatosCompras(int idDatosCompras) {
        this.idDatosCompras = idDatosCompras;
    }

    void add(CompraTO compraTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
