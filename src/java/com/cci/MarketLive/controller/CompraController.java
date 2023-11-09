/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.MarketLive.controller;
import com.cci.MarketLive.service.CompraTO;
import com.cci.MarketLive.service.ServicioCompra;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author julio
 */

@ManagedBean(name = "compraController")
@SessionScoped

public class CompraController {
    
    private int id;
    private int idusuario;
    private int idproducto;
    private int cantidad;
    private GeneralHelper generalHelper;
    private ServicioCompra servicioCompra;
    
    private List<CompraTO> compras;
    private CompraTO selectedCompra;
    private List<CompraTO> selectedCompras;

    
    @ManagedProperty("#{loginController}")
    private LoginController loginController;
    
    public void comprar(){
        

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

    public GeneralHelper getGeneralHelper() {
        return generalHelper;
    }

    public void setGeneralHelper(GeneralHelper generalHelper) {
        this.generalHelper = generalHelper;
    }

    public ServicioCompra getServicioCompra() {
        return servicioCompra;
    }

    public void setServicioCompra(ServicioCompra servicioCompra) {
        this.servicioCompra = servicioCompra;
    }
    
    
    
    
    
    
}
