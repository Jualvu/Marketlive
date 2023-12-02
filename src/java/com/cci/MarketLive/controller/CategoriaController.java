package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.CategoriaService;
import com.cci.MarketLive.to.CategoriaTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "categoriaController")
@RequestScoped
public class CategoriaController implements Serializable {

    private List<CategoriaTO> categorias;
    private CategoriaTO selectedCategoria;
    private List<CategoriaTO> selectedCategorias;

    private CategoriaService servicioCategoria;

    private String categoria;

    public CategoriaController() {
        try {
            servicioCategoria = new CategoriaService();
            selectedCategoria = new CategoriaTO();

            categorias = servicioCategoria.readAll();
            selectedCategorias = new ArrayList<>();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void openNew(CategoriaTO categoria) {
        selectedCategoria = categoria;
    }

    public void saveCategoria() {

        try {

            if (getSelectedCategoria().getId() == 0) {
                boolean response = servicioCategoria.create(getSelectedCategoria());

                if (response) {

                    categorias = servicioCategoria.readAll();

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoría creada exitosamente"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al crear la categoría"));
                }
            } else {

                boolean response = servicioCategoria.update(selectedCategoria);

                if (response) {
                    categorias = servicioCategoria.readAll();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoría actualizada exitosamente"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al actualizar la categoría"));
                }

            }

            PrimeFaces.current().executeScript("PF('manageCategoriaDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");

        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error en la base de datos"));
            e.printStackTrace();
        }
    }

    public void deleteCategoria() {
        try {
            System.out.println("ID a eliminar: " + getSelectedCategoria().getId());
            boolean delete = servicioCategoria.delete(this.selectedCategoria.getId());

            if (delete) {
                this.categorias.remove(this.selectedCategoria);
                this.selectedCategorias.remove(this.selectedCategoria);
                this.selectedCategoria = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoria Eliminada"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al eliminar la categoria"));
            }

            PrimeFaces.current().executeScript("PF('manageCategoriaDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-categoria");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        System.out.println("Estableciendo selectedCategoria: " + selectedCategoria.getId());
        this.selectedCategoria = selectedCategoria;
    }

    public List<CategoriaTO> getSelectedCategorias() {
        return selectedCategorias;
    }

    public void setSelectedCategorias(List<CategoriaTO> selectedCategorias) {
        this.selectedCategorias = selectedCategorias;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
