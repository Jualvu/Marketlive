package com.cci.MarketLive.controller;

import com.cci.MarketLive.to.ProductoTO;
import com.cci.MarketLive.service.ProductoService;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "productoController")
@SessionScoped
public class ProductoController implements Serializable {

    private List<ProductoTO> productos;
    private ProductoTO selectedProducto;
    private List<ProductoTO> selectedProductos;
    private List<ProductoTO> busqueda;
    
    private ProductoService servicioProducto;

    String producto;
    
    public ProductoController() {

        productos = new ArrayList<>();
        selectedProductos = new ArrayList<>();
        busqueda = new ArrayList<>();
        
        try {
            servicioProducto = new ProductoService();
            this.productos = servicioProducto.readAll();
            this.busqueda = servicioProducto.listarBusqueda(producto);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscar(String producto) throws SQLException{
        
        this.busqueda = servicioProducto.listarBusqueda(producto);
    }
    
    
    public List<ProductoTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoTO> listaProductos) {
        this.productos = listaProductos;
    }

    public ProductoTO getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(ProductoTO selectedProducto) {
        this.selectedProducto = selectedProducto;
    }

    public List<ProductoTO> getSelectedProductos() {
        return selectedProductos;
    }

    public void setSelectedProductos(List<ProductoTO> selectedProductos) {
        this.selectedProductos = selectedProductos;
    }

    public List<ProductoTO> getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(List<ProductoTO> busqueda) {
        this.busqueda = busqueda;
    }

    public ProductoService getServicioProducto() {
        return servicioProducto;
    }

    public void setServicioProducto(ProductoService servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    
    
    public void openNew() {
        this.selectedProducto = new ProductoTO();
    }

    public void saveProducto() {
        try {
            if (this.selectedProducto.getId() == 0) {

                ProductoTO productoTO = new ProductoTO();
                productoTO.setTipo(this.selectedProducto.getTipo());
                productoTO.setCodigo(this.selectedProducto.getCodigo());
                productoTO.setNombre(this.selectedProducto.getNombre());
                productoTO.setDescripcion(this.selectedProducto.getDescripcion());
                productoTO.setPrecio(this.selectedProducto.getPrecio());
                productoTO.setStock(this.selectedProducto.getStock());
                productoTO.setUsuarioId(this.selectedProducto.getUsuarioId());

                servicioProducto.create(productoTO);

                this.productos.add(this.selectedProducto);

                selectedProducto.setId(0);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto agregado"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto actualizado"));
                /* ProyectoTO ProductoTO = new ProyectoTO();
                proyectoTO.setCodigo(this.selectedProyecto.getCodigo());
                proyectoTO.setNombre(this.selectedProyecto.getNombre());
                proyectoTO.setDescripcion(this.selectedProyecto.getDescripcion());

                Boolean update = servicioProyecto.update(proyectoTO);

                if (update) {
                    for (ProyectoTO producto : this.proyectos) {
                        if (proyecto.getId() == this.selectedProyecto.getId()) {
                            proyecto.setCodigo(this.selectedProyecto.getCodigo());
                            proyecto.setNombre(this.selectedProyecto.getNombre());
                            proyecto.setDescripcion(this.selectedProyecto.getDescripcion());
                            break; // Terminamos el bucle una vez que se ha actualizado el proyecto
                        }
                    }

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proyecto actualizado"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar el proyecto", null));

                }*/
            }

            PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProducto() {

    }

    public boolean hasSelectedProductos() {
        return this.selectedProductos != null && !this.selectedProductos.isEmpty();
    }

    public void deleteSelectedProducts() {
        this.productos.removeAll(this.selectedProductos);
        this.selectedProductos = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proyecto eliminado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }
}
