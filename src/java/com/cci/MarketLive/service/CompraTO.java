/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.MarketLive.service;

/**
 *
 * @author julio
 */
public class CompraTO {
    
    private int id;
    private int idusuario;
    private int idproducto;
    private int cantidad;

    public CompraTO() {
    }

    public CompraTO(int id, int idusuario, int idproducto, int cantidad) {
        this.id = id;
        this.idusuario = idusuario;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
