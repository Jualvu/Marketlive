/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.ProductoTO;
import com.cci.MarketLive.service.ServicioProducto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author julio
 */

@ManagedBean(name = "productoController")
@SessionScoped


public class ProductoController implements Serializable {
    
    private int id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String tienda;
    private int cantidad;
    private double precio;
    private List<ProductoTO> listaProductos;
    private ProductoTO selectedProducto;

     public ProductoController() {
         
    }

    public void openNew(){
        this.selectedProducto = new ProductoTO();
    }
     
    public void listarProductos(){
        ServicioProducto servicioProducto = new ServicioProducto();
        this.listaProductos = servicioProducto.listarProductos();
        
    }
     
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<ProductoTO> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ProductoTO> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ProductoTO getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(ProductoTO selectedProducto) {
        this.selectedProducto = selectedProducto;
    }
    
     
    
   


}
