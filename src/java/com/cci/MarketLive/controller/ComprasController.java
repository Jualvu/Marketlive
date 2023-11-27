package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.DatoCompraService;
import com.cci.MarketLive.to.DatoCompraTO;
import com.cci.MarketLive.to.UsuarioTO;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.List;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "comprasController")
@SessionScoped
public class ComprasController {

    private GeneralHelper generalHelper;
    private DatoCompraService datosCompraService;
    UsuarioTO usuarioTO;
    private DatoCompraTO selectedDatoCompra;

    @ManagedProperty(value = "#{itemController}")
    private ItemController itemController;

    public ItemController getItemController() {
        return itemController;
    }

    public void setItemController(ItemController itemController) {
        this.itemController = itemController;
    }

    public ComprasController() {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        // Obtener la sesión
        HttpSession session = (HttpSession) externalContext.getSession(false);

        // Obtener el atributo de la sesión
        usuarioTO = (UsuarioTO) session.getAttribute("usuarioTO");

        generalHelper = new GeneralHelper();
        datosCompraService = new DatoCompraService();
        selectedDatoCompra = new DatoCompraTO();
    }

    public void realizarCompra() {

        try {
            DatoCompraTO datoCompra = datosCompraService.read(usuarioTO.getId());

            if (datoCompra.getId() != 0) {
                generalHelper.redireccionar("/faces/factura.xhtml");
                getItemController().limpiarCarrito();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Compra Realizada"));

            } else {
                generalHelper.redireccionar("/faces/datosCompra.xhtml");
            }

            PrimeFaces.current().ajax().update("form:messages", "form:form_index");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DatoCompraTO getSelectedDatoCompra() {
        return selectedDatoCompra;
    }

    public void setSelectedDatoCompra(DatoCompraTO selectedDatoCompra) {
        this.selectedDatoCompra = selectedDatoCompra;
    }

}
