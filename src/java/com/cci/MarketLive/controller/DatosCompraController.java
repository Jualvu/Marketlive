package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.DatoCompraService;
import com.cci.MarketLive.to.DatoCompraTO;
import com.cci.MarketLive.to.UsuarioTO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "datosCompraController")
@SessionScoped
public class DatosCompraController {

    private GeneralHelper generalHelper;
    private DatoCompraService datosCompraService;
    UsuarioTO usuarioTO;
    private DatoCompraTO selectedDatoCompra;

    public DatosCompraController() {
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

    public void createDatosCompra() {

        DatoCompraTO datoCompraTO = new DatoCompraTO();

        try {
            if (getSelectedDatoCompra().getDireccionEnvio() != null && getSelectedDatoCompra().getMetodoPago() != 0) {

                datoCompraTO.setDireccionEnvio(this.selectedDatoCompra.getDireccionEnvio());
                datoCompraTO.setMetodoPago(this.selectedDatoCompra.getMetodoPago());
                datoCompraTO.setUsuarioId(usuarioTO.getId());

                boolean create = this.datosCompraService.create(datoCompraTO);

                if (create) {
                    System.out.println("sasa");
                    generalHelper.redireccionar("/faces/detalles_carrito.xhtml");
                } else {
                    System.out.println("error");
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Rellene los campos"));
            }
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
