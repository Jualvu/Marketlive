
package com.cci.MarketLive.to;

import java.sql.Timestamp;

public class InventarioTO {
    
    private int id;
    private int idproducto;
    private double cantidad;
    private Timestamp fecha;

    public InventarioTO() {
    }

    public InventarioTO(int id, int idproducto, double cantidad, Timestamp fecha) {
        this.id = id;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    
    
}
