
package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.AuthService;
import com.cci.MarketLive.service.ProductoService;
import com.cci.MarketLive.service.TiendaService;
import com.cci.MarketLive.to.ProductoTO;
import com.cci.MarketLive.to.TiendaTO;
import com.cci.MarketLive.to.UsuarioTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "tiendaController")
@SessionScoped
public class TiendaController implements Serializable{
    
    private List<TiendaTO> tiendas;
    private TiendaTO selectedTienda;
    private List<TiendaTO> selectedTiendas;
    private List<ProductoTO> selectedProductosByTienda;
    private GeneralHelper generalHelper;
    
    private TiendaService servicioTienda;

    private int idTienda;
    private String tienda;
    private String tiendaNombre;
    private int selectedTiendaId;
    
    UsuarioTO usuarioTO = new UsuarioTO();
    private ProductoService servicioProducto;
    
    public TiendaController() {
        
        tiendas = new ArrayList<>();
        selectedTiendas = new ArrayList<>();
        selectedProductosByTienda = new ArrayList<ProductoTO>();
        generalHelper = new GeneralHelper();
        
        
        try {

            selectedTienda = new TiendaTO();
            selectedTiendas = new ArrayList<>();
            servicioTienda = new TiendaService();
            
            tiendas = servicioTienda.readAll();
            servicioProducto = new ProductoService();
            
            idTienda = selectedTienda.getId();
            setSelectedProductosByTienda(servicioProducto.readAllByTienda(idTienda));
            
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            // Obtener la sesión
            HttpSession session = (HttpSession) externalContext.getSession(false);

            // Obtener el atributo de la sesión
            usuarioTO = (UsuarioTO) session.getAttribute("usuarioTO");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void saveTienda(){
        try {

            if (getSelectedTienda().getId() == 0) {
                boolean response = servicioTienda.createForUser(getSelectedTienda(), usuarioTO.getId() );

                if (response) {

                    tiendas = servicioTienda.readAll();

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tienda creada exitosamente"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al crear la tienda"));
                }
            } else {

                boolean response = servicioTienda.update(selectedTienda);

                if (response) {
                    tiendas = servicioTienda.readAll();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tienda actualizada exitosamente"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al actualizar la tienda"));
                }

            }

            PrimeFaces.current().executeScript("PF('manageTiendaDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-tiendas");

        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error en la base de datos"));
            e.printStackTrace();
        }
        
    }
    
    public void deleteTienda(){
        try {
            boolean delete = servicioTienda.delete(this.selectedTienda.getId());

            if (delete) {
                this.tiendas.remove(this.selectedTienda);
                this.selectedTiendas.remove(this.selectedTienda);
                this.selectedTienda = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tienda Eliminada"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al eliminar la tienda"));
            }

            PrimeFaces.current().executeScript("PF('manageTiendaDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-tiendas");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void readProductoByTienda(int idTienda2, String tiendaNombre2) {

        try {
            tiendaNombre = tiendaNombre2;
            idTienda = idTienda2;
            setSelectedProductosByTienda(servicioProducto.readAllByTienda(idTienda2));

            for (ProductoTO pro : getSelectedProductosByTienda()) {
                System.out.println("sa" + pro.getNombre());
            }
            
            generalHelper.redireccionar("/faces/tiendaProductos.xhtml");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    
    public void openNew() {
        this.selectedTienda = new TiendaTO();
    }
    
    
    public List<TiendaTO> getTiendas() {
        return tiendas;
    }

    public void setTiendas(List<TiendaTO> tiendas) {
        this.tiendas = tiendas;
    }

    public TiendaTO getSelectedTienda() {
        return selectedTienda;
    }

    public void setSelectedTienda(TiendaTO selectedTienda) {
        this.selectedTienda = selectedTienda;
    }

    public List<TiendaTO> getSelectedTiendas() {
        return selectedTiendas;
    }

    public void setSelectedTiendas(List<TiendaTO> selectedTiendas) {
        this.selectedTiendas = selectedTiendas;
    }

    public TiendaService getServicioTienda() {
        return servicioTienda;
    }

    public void setServicioTienda(TiendaService servicioTienda) {
        this.servicioTienda = servicioTienda;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public int getSelectedTiendaId() {
        return selectedTiendaId;
    }

    public void setSelectedTiendaId(int selectedTiendaId) {
        this.selectedTiendaId = selectedTiendaId;
    }

    public UsuarioTO getUsuarioTO() {
        return usuarioTO;
    }

    public void setUsuarioTO(UsuarioTO usuarioTO) {
        this.usuarioTO = usuarioTO;
    }

    public List<ProductoTO> getSelectedProductosByTienda() {
        return selectedProductosByTienda;
    }

    public void setSelectedProductosByTienda(List<ProductoTO> selectedProductosByTienda) {
        this.selectedProductosByTienda = selectedProductosByTienda;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public ProductoService getServicioProducto() {
        return servicioProducto;
    }

    public void setServicioProducto(ProductoService servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    public GeneralHelper getGeneralHelper() {
        return generalHelper;
    }

    public void setGeneralHelper(GeneralHelper generalHelper) {
        this.generalHelper = generalHelper;
    }

    public String getTiendaNombre() {
        return tiendaNombre;
    }

    public void setTiendaNombre(String tiendaNombre) {
        this.tiendaNombre = tiendaNombre;
    }
    
    
    
    
}
