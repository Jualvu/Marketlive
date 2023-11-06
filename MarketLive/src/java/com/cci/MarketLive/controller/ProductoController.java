/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.ProductoTO;
import java.io.Serializable;
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
    
    private List<ProductoTO> products;
    private ProductoTO selectedProducto;

     public ProductoController() {
    }
    

    
   


}
