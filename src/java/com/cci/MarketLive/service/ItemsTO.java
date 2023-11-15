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
public class ItemsTO {
    
    private int id;
    private int idusuario;
    private int idproducto; 
    private double cantidad;
    private double precio;

    public ItemsTO() {
    }

    public ItemsTO(int id, int idusuario, int idproducto, double cantidad, double precio) {
        this.id = id;
        this.idusuario = idusuario;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.precio = precio;
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

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
