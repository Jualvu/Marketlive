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
    private GeneralHelper generalHelper;
    private List<ProductoTO> busqueda;

    private ProductoService servicioProducto;

    private String producto;

    public ProductoController() {

        productos = new ArrayList<>();
        selectedProductos = new ArrayList<>();
        generalHelper = new GeneralHelper();
        busqueda = new ArrayList<ProductoTO>();

        try {
            servicioProducto = new ProductoService();
            setProductos(servicioProducto.readAll());
            setBusqueda(servicioProducto.listarBusqueda(producto));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscar(String producto) {
        try {
            setBusqueda(servicioProducto.listarBusqueda(producto));

            for (ProductoTO pro : getBusqueda()) {
                System.out.println("sa" + pro.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                productoTO.setCategoriaId(this.selectedProducto.getCategoriaId());
                productoTO.setUsuarioId(this.selectedProducto.getUsuarioId());

                servicioProducto.create(productoTO);

                setProductos(servicioProducto.readAll());

                selectedProducto.setId(0);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto agregado"));
            } else {

                ProductoTO productoTO = new ProductoTO();
                productoTO.setId(this.selectedProducto.getId());
                productoTO.setTipo(this.selectedProducto.getTipo());
                productoTO.setCodigo(this.selectedProducto.getCodigo());
                productoTO.setNombre(this.selectedProducto.getNombre());
                productoTO.setDescripcion(this.selectedProducto.getDescripcion());
                productoTO.setPrecio(this.selectedProducto.getPrecio());
                productoTO.setStock(this.selectedProducto.getStock());

                servicioProducto.update(productoTO);

                generalHelper.redireccionar("/faces/productos_admin.xhtml");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto actualizado"));
            }

            PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("error producto"));
            e.printStackTrace();
        } finally {
            setSelectedProducto(null);
        }
    }

    public void deleteProducto() {
        try {
            boolean delete = servicioProducto.delete(this.selectedProducto.getId());

            if (delete) {
                this.productos.remove(this.selectedProducto);
                this.selectedProductos.remove(this.selectedProducto);
                this.selectedProducto = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Eliminado"));
                PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al eliminar el producto"));
                PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hasSelectedProductos() {
        return this.selectedProductos != null && !this.selectedProductos.isEmpty();
    }
}
