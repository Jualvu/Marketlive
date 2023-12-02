
package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.InventarioService;
import com.cci.MarketLive.service.ProductoService;
import com.cci.MarketLive.to.InventarioTO;
import com.cci.MarketLive.to.ProductoTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "inventarioController")
@SessionScoped

public class InventarioController implements Serializable{
    
    private List<InventarioTO> inventario;
    private InventarioTO selectedInventario;
    private List<InventarioTO> selectedInventarios;
    private GeneralHelper generalHelper;
    private List<InventarioTO> inventarioByProducto;
    
    private InventarioService inventarioService;
    private ProductoService productoService;
    
    private ProductoTO producto;
    
    private double cantidad;
    
    private int idProducto;
    
    @ManagedProperty("#{productoController}")
    private ProductoController productoController;
    
    
    public InventarioController() {

        inventario = new ArrayList<>();
        selectedInventarios = new ArrayList<>();
        generalHelper = new GeneralHelper();
        inventarioByProducto = new ArrayList<>();
        
        

        try {
            productoService = new ProductoService();
            inventarioService = new InventarioService();
    
            setInventario(inventarioService.readAll());
            setInventarioByProducto(inventarioService.readAllByProducto(idProducto));
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public List<InventarioTO> read(int id) throws SQLException{
        
        inventario = new ArrayList<InventarioTO>();
        
        inventarioService = new InventarioService();
        inventario = inventarioService.readAllByProducto(id);
        
        
        return inventario;
        
        
    }
    public void readInventario(int id){
        
        try {
            
            setInventarioByProducto(inventarioService.readAllByProducto(id));

            for (InventarioTO inv : getInventarioByProducto()) {
                System.out.println("sa" + inv.getCantidad());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveInventario(ProductoTO producto, double cantidad2) {
        try {
            
            cantidad= cantidad2;
            
            inventarioService.createForProduct(productoController.getSelectedProducto().getId(), cantidad2);
            productoController.updateProductoCantidad(producto, cantidad);
            
            generalHelper.redireccionar("/faces/productos_admin.xhtml");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto actualizado"));
            
            
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("error inventario"));
            e.printStackTrace();
        } finally {
            setSelectedInventario(null);
        }
    }
    
    
    
    public List<InventarioTO> getInventario() {
        return inventario;
    }

    public void setInventario(List<InventarioTO> inventario) {
        this.inventario = inventario;
    }

    public InventarioTO getSelectedInventario() {
        return selectedInventario;
    }

    public void setSelectedInventario(InventarioTO selectedInventario) {
        this.selectedInventario = selectedInventario;
    }

    public List<InventarioTO> getSelectedInventarios() {
        return selectedInventarios;
    }

    public void setSelectedInventarios(List<InventarioTO> selectedInventarios) {
        this.selectedInventarios = selectedInventarios;
    }

    public GeneralHelper getGeneralHelper() {
        return generalHelper;
    }

    public void setGeneralHelper(GeneralHelper generalHelper) {
        this.generalHelper = generalHelper;
    }

    public InventarioService getInventarioService() {
        return inventarioService;
    }

    public void setInventarioService(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    public List<InventarioTO> getInventarioByProducto() {
        return inventarioByProducto;
    }

    public void setInventarioByProducto(List<InventarioTO> inventarioByProducto) {
        this.inventarioByProducto = inventarioByProducto;
    }

    public ProductoTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoTO producto) {
        this.producto = producto;
    }

    public ProductoController getProductoController() {
        return productoController;
    }

    public void setProductoController(ProductoController productoController) {
        this.productoController = productoController;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoService getProductoService() {
        return productoService;
    }

    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    
    
    
}
