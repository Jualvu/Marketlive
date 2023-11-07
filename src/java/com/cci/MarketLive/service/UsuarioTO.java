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
public class UsuarioTO {
    
    private String nombre;
    private String clave;
    private boolean admin;

    public UsuarioTO() {
    }

    public UsuarioTO(String nombre, String clave, boolean admin) {
        this.nombre = nombre;
        this.clave = clave;
        this.admin = admin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    
}
