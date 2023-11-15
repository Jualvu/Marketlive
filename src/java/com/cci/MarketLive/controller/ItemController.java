package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.ItemService;
import com.cci.MarketLive.to.ItemTO;
import com.cci.MarketLive.to.UsuarioTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "itemController")
@SessionScoped
public class ItemController implements Serializable {

    private ItemTO itemTO;
    private List<ItemTO> items;
    private ItemService servicioItem;
    private int totalItems;
    private double totalPrecio;
    private GeneralHelper generalHelper;
    UsuarioTO usuarioTO;

    public ItemController() {
        items = new ArrayList<>();

        try {

            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            // Obtener la sesión
            HttpSession session = (HttpSession) externalContext.getSession(false);

            // Obtener el atributo de la sesión
            usuarioTO = (UsuarioTO) session.getAttribute("usuarioTO");

            servicioItem = new ItemService();

            if (usuarioTO != null) {
                this.items = servicioItem.readAllByUsuario(usuarioTO.getId());
                this.totalPrecio = servicioItem.calcularSumaPrecio(usuarioTO.getId());
            }

            countItems();
            generalHelper = new GeneralHelper();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarItemCarrito(int idProducto, double precioProducto) {

        itemTO = new ItemTO();

        try {

            if (usuarioTO != null) {

                itemTO.setUsuarioId(usuarioTO.getId());
                itemTO.setProductoId(idProducto);
                itemTO.setCantidad(1);
                itemTO.setPrecio(precioProducto);

                servicioItem.create(itemTO);
                this.items = servicioItem.readAllByUsuario(usuarioTO.getId());
                countItems();

                generalHelper.redireccionar("/faces/index.xhtml");

            } else {

                generalHelper.redireccionar("/faces/login.xhtml");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void countItems() {
        System.out.println("sasa");
        if (items != null) {
            setTotalItems(items.size());
        } else {
            setTotalItems(0);
        }
    }

    public List<ItemTO> getItems() {
        return items;
    }

    public void setItems(List<ItemTO> items) {
        this.items = items;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public double getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(double totalPrecio) {
        this.totalPrecio = totalPrecio;
    }

}
