package com.cci.MarketLive.service;


public class ItemCompraTO {
    
    private int id;
    private int idcompra;
    private int iditem;

    public ItemCompraTO() {
    }

    public ItemCompraTO(int id, int idcompra, int iditem) {
        this.id = id;
        this.idcompra = idcompra;
        this.iditem = iditem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getIditem() {
        return iditem;
    }

    public void setIditem(int iditem) {
        this.iditem = iditem;
    }

    void add(ItemCompraTO itemCompraTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
