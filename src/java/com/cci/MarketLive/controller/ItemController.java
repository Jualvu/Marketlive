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
    private double subtotal;
    private double iva;
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
                setItems(servicioItem.readAllByUsuario(usuarioTO.getId()));
                calcTotal();
            }

            countItems();
            generalHelper = new GeneralHelper();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarItemCarrito(int idProducto, double precioProducto) {

        itemTO = new ItemTO();
        ItemTO itemObtenido = new ItemTO();

        try {
            if (usuarioTO != null) {

                itemObtenido = servicioItem.readd(idProducto, usuarioTO.getId());

                if (itemObtenido.getId() == 0) {
                    itemTO.setUsuarioId(usuarioTO.getId());
                    itemTO.setProductoId(idProducto);
                    itemTO.setCantidad(1);
                    itemTO.setPrecio(precioProducto);

                    servicioItem.create(itemTO);
                    setItems(servicioItem.readAllByUsuario(usuarioTO.getId()));
                    countItems();
                    calcTotal();

                } else {
                    itemTO.setId(itemObtenido.getId());
                    itemTO.setCantidad(itemObtenido.getCantidad() + 1);

                    servicioItem.update(itemTO);
                    setItems(servicioItem.readAllByUsuario(usuarioTO.getId()));
                    countItems();
                    calcTotal();
                }

            } else {
                generalHelper.redireccionar("/faces/login.xhtml");
            }
            generalHelper.redireccionar("/faces/index.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            itemObtenido = null;
        }
    }

    public void eliminarItem(int id) {
        try {

            servicioItem.delete(id);
            setItems(servicioItem.readAllByUsuario(usuarioTO.getId()));
            countItems();
            calcTotal();
            generalHelper.redireccionar("/faces/index.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateItems() {
        try {

            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void limpiarCarrito() {
        try {
            servicioItem.limpiarCarrito(usuarioTO.getId());
            setItems(servicioItem.readAllByUsuario(usuarioTO.getId()));
            setTotalItems(0);
            setTotalPrecio(0);
            setSubtotal(0);
            setIva(0);
            generalHelper.redireccionar("/faces/index.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void countItems() {
        if (items != null) {
            setTotalItems(items.size());
        } else {
            setTotalItems(0);
        }
    }

    private void calcTotal() {
        try {
            double total = servicioItem.calcularSumaPrecio(usuarioTO.getId());
            setIva(total * 0.13);
            setTotalPrecio(total);
            setSubtotal(getTotalPrecio() - getIva());
        } catch (Exception e) {
            e.printStackTrace();
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

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(double totalPrecio) {
        this.totalPrecio = totalPrecio;
    }

}
