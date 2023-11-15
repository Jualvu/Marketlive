
package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.DatosCompraTO;
import com.cci.MarketLive.service.ServicioDatosCompra;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name = "datosCompraController")
@SessionScoped
public class DatosCompraController implements Serializable{

    private List<DatosCompraTO> datosCompras;
    private DatosCompraTO selectedDatosCompra;
    private List<DatosCompraTO> selectedDatosCompras;

    private ServicioDatosCompra servicioDatosCompra;
    
    @ManagedProperty("#{loginController}")
    private LoginController loginController;
    
    
    
    public DatosCompraController() {
        
        datosCompras = new ArrayList<>();
        selectedDatosCompras = new ArrayList<>();

        try {
            servicioDatosCompra = new ServicioDatosCompra();
            this.datosCompras = servicioDatosCompra.readAll();

        } catch (Exception e) {
            e.printStackTrace();
        }             
    }
    
    public void save() throws SQLException{
        boolean done = false;
        done = servicioDatosCompra.createForUser(selectedDatosCompra, loginController.getId());
        
        
        if(done=true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item añadido"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error"));
        }
        
    }
    
    public void update() throws SQLException{
        boolean done = false;
        done = servicioDatosCompra.updateForUser(selectedDatosCompra, loginController.getId());
        if(done=true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item añadido"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error"));
        }
        
    }
    
    
    public void openNew() {
        this.selectedDatosCompra = new DatosCompraTO();
    }

    //Getters y Setters
    
    public List<DatosCompraTO> getDatosCompras() {
        return datosCompras;
    }

    public void setDatosCompras(List<DatosCompraTO> datosCompras) {
        this.datosCompras = datosCompras;
    }

    public DatosCompraTO getSelectedDatosCompra() {
        return selectedDatosCompra;
    }

    public void setSelectedDatosCompra(DatosCompraTO selectedDatosCompra) {
        this.selectedDatosCompra = selectedDatosCompra;
    }

    public List<DatosCompraTO> getSelectedDatosCompras() {
        return selectedDatosCompras;
    }

    public void setSelectedDatosCompras(List<DatosCompraTO> selectedDatosCompras) {
        this.selectedDatosCompras = selectedDatosCompras;
    }

    public ServicioDatosCompra getServicioDatosCompra() {
        return servicioDatosCompra;
    }

    public void setServicioDatosCompra(ServicioDatosCompra servicioDatosCompra) {
        this.servicioDatosCompra = servicioDatosCompra;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
    
    
    
    
    
    
    
    
    
    
    
}
