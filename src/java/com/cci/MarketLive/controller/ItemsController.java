package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.ItemsTO;
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

@ManagedBean(name = "itemsController")
@SessionScoped
public class ItemsController implements Serializable{
    
    private List<ItemsTO> items;
    private ItemsTO selectedItem;
    private List<ItemsTO> selectedItems;

    private ServicioItems servicioItems;
    
    
    @ManagedProperty("#{loginController}")
    private LoginController loginController;
    
    @ManagedProperty("#{productoController}")
    private ProductoController productoController;
    
    public ItemsController() {

        items = new ArrayList<>();
        selectedItems = new ArrayList<>();

        try {
            servicioItems = new ServicioItems();
            this.items = servicioItems.readAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Agrega item
    public void save() throws SQLException{
        boolean done = false;
        done = servicioItems.createForUser(selectedItem, loginController.getId(), productoController.getSelectedProducto().getId());
        if(done=true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item añadido"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error"));
        }
        
    }
    //Actualiza cantidad
    public void updateCantidad() throws SQLException{
        boolean done = false;
        done = servicioItems.updateCantidad(selectedItem, selectedItem.getCantidad());
        if(done=true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item añadido"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error"));
        }
        
    }
    
    //Elimina item seleccionado
    public void delete() throws SQLException{
        
        boolean done = false;
        done = servicioItems.delete(selectedItem.getId());
        if(done=true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item añadido"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error"));
        }
    }
    
    public void openNew() {
        this.selectedItem = new ItemsTO();
    }

    public List<ItemsTO> getItems() {
        return items;
    }

    public void setItems(List<ItemsTO> items) {
        
        this.items = items;
    }

    public ItemsTO getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(ItemsTO selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<ItemsTO> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<ItemsTO> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public ServicioItems getServicioItems() {
        return servicioItems;
    }

    public void setServicioItems(ServicioItems servicioItems) {
        this.servicioItems = servicioItems;
    }
    
    
    
    
    
}
