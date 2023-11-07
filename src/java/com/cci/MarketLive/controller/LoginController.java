/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.ServicioUsuario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author julio
 */

@ManagedBean(name = "loginController")
@SessionScoped

public class LoginController implements Serializable {
    
    private String nombre;
    private String clave;
    private boolean admin;

    public LoginController() {
    }
    
    
    public void redireccionar(String ruta){
        
        HttpServletRequest request;
        try{
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        }catch(Exception e){
            
        }
        
    }

    
    public void ingresar(){
       
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        int user = servicioUsuario.consultar(this.nombre, this.clave);
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR,this.nombre ,this.clave));
        //Un usuario tipo Cliente accede
        if(user==1){
            redireccionar("/faces/Compras.xhtml");
            
            
        }
        //Un usuario tipo Admin accede
        else if(user==2){
            //redireccionar();
        }
        
        else{
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR,String.valueOf(servicioUsuario.consultar(this.nombre, this.clave)) ,""));
            //FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos inválidos", "La clave o correo no son correctos"));
            
        }

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
