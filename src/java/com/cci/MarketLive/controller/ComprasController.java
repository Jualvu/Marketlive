package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.CompraTO;
import com.cci.MarketLive.service.ItemsTO;
import com.cci.MarketLive.service.ServicioCompra;
import com.cci.MarketLive.service.ServicioItems;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "ComprasController")
@SessionScoped
public class ComprasController implements Serializable{
    
    private List<CompraTO> compras;
    private CompraTO selectedCompra;
    private List<CompraTO> selectedCompras;

    private ServicioCompra servicioCompra;
    private ServicioItems servicioItems;
    
    
    @ManagedProperty("#{loginController}")
    private LoginController loginController;
    
    @ManagedProperty("#{itemController}")
    private ItemsController itemsController;
    
    @ManagedProperty("#{datosCompraController}")
    private DatosCompraController datosCompraController;
    
    
    public ComprasController() {

        compras = new ArrayList<>();
        selectedCompras = new ArrayList<>();

        try {
            ServicioCompra servicioCompra = new ServicioCompra();
            this.compras = servicioCompra.readAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void createCompra() throws SQLException{

        boolean compraDone = servicioCompra.createForUser(selectedCompra,datosCompraController.getSelectedDatosCompra().getId());
        
        if(compraDone=true){

            List<ItemsTO> items = new ArrayList<ItemsTO>();
            items = servicioItems.read(loginController.getId());
            
            for(ItemsTO item: items){
                servicioCompra.agregarItemCompra(selectedCompra.getId(), item.getId());
            }
            
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item añadido"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error"));
        }
        
        
        
    }
    
    
    
    
    
}
