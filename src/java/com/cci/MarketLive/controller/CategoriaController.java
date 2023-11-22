package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.CategoriaService;
import com.cci.MarketLive.to.CategoriaTO;
import com.cci.MarketLive.to.ProductoTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "categoriaController")
@RequestScoped
public class CategoriaController implements Serializable {

    private List<CategoriaTO> categorias;
    private CategoriaTO selectedCategoria;
    private List<ProductoTO> selectedProductos;
    private CategoriaTO categoriaSeleccionada;

    private CategoriaService servicioCategoria;

    String categoria;

    public CategoriaController() {
        servicioCategoria = new CategoriaService();
        try {
            categorias = servicioCategoria.readAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CategoriaController(List<CategoriaTO> categorias, CategoriaTO selectedCategoria, List<ProductoTO> selectedProductos, CategoriaTO categoriaSeleccionada, CategoriaService servicioCategoria, String categoria) {
        this.categorias = categorias;
        this.selectedCategoria = selectedCategoria;
        this.selectedProductos = selectedProductos;
        this.categoriaSeleccionada = categoriaSeleccionada;
        this.servicioCategoria = servicioCategoria;
        this.categoria = categoria;
        this.servicioCategoria = new CategoriaService();
        
    }

    public List<CategoriaTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaTO> categorias) {
        this.categorias = categorias;
    }

    public CategoriaTO getSelectedCategoria() {
        return selectedCategoria;
    }

    public void setSelectedCategoria(CategoriaTO selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }

    public List<ProductoTO> getSelectedProductos() {
        return selectedProductos;
    }

    public void setSelectedProductos(List<ProductoTO> selectedProductos) {
        this.selectedProductos = selectedProductos;
    }

    public CategoriaTO getCategoriaSeleccionada() {
        return categoriaSeleccionada;
    }

    public void setCategoriaSeleccionada(CategoriaTO categoriaSeleccionada) {
        this.categoriaSeleccionada = categoriaSeleccionada;
    }

    public CategoriaService getServicioCategoria() {
        return servicioCategoria;
    }

    public void setServicioCategoria(CategoriaService servicioCategoria) {
        this.servicioCategoria = servicioCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
