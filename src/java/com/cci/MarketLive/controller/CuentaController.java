package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.AuthService;
import com.cci.MarketLive.service.DatoCompraService;
import com.cci.MarketLive.to.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import javax.annotation.PostConstruct;

@ManagedBean(name = "cuentaController")
@SessionScoped
public class CuentaController implements Serializable {

    private GeneralHelper generalHelper;
    private UsuarioTO usuarioTO;
    private DatoCompraService datosCompraService;
    private AuthService authService;
    private CuentaTO cuentaTO;
    private DatoCompraTO datoCompraTO;

    public CuentaController() {
        construc();
    }

    @PostConstruct
    public void init() {
        construc();
    }

    private void construc() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            // Obtener la sesión
            HttpSession session = (HttpSession) externalContext.getSession(false);

            // Obtener el atributo de la sesión
            usuarioTO = (UsuarioTO) session.getAttribute("usuarioTO");

            generalHelper = new GeneralHelper();

            datosCompraService = new DatoCompraService();
            authService = new AuthService();

            // Supongamos que cuentaTO.getFechaCreado() devuelve un Timestamp
            Timestamp timestamp = usuarioTO.getFechaCreada();

            // Crear un formato de fecha
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Puedes ajustar el formato según tus necesidades

            // Convertir el Timestamp a String
            String fechaComoString = dateFormat.format(timestamp);

            datoCompraTO = datosCompraService.read(usuarioTO.getId());

            System.out.println("" + datoCompraTO);

            cuentaTO = new CuentaTO();

            cuentaTO.setNombre(usuarioTO.getNombre());
            cuentaTO.setCorreo(usuarioTO.getCorreo());
            cuentaTO.setDireccion(datoCompraTO.getDireccionEnvio());
            cuentaTO.setFecha(fechaComoString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        try {
            UsuarioTO usuario = new UsuarioTO();
            usuario.setId(usuarioTO.getId());
            usuario.setNombre(cuentaTO.getNombre());
            usuario.setCorreo(cuentaTO.getCorreo());

            DatoCompraTO datoCompra = new DatoCompraTO();
            datoCompra.setId(usuarioTO.getId());
            datoCompra.setDireccionEnvio(cuentaTO.getDireccion());

            Boolean response = authService.updateData(usuario);
            Boolean response2 = true;

            if (getDatoCompraTO().getDireccionEnvio() != null) {
                response2 = datosCompraService.update(datoCompra);
            }

            if (response && response2) {

                generalHelper.redireccionar("/faces/login.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al actualizar los datos"));
            }

            PrimeFaces.current().ajax().update("form_main:messagesCuenta", "form_main:dt-cuenta");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePassword() {
        try {
            if (cuentaTO.getPasssword().equals(cuentaTO.getConfirmPassword())) {
                UsuarioTO usuario = new UsuarioTO();

                usuario.setId(usuarioTO.getId());
                usuario.setPassword(cuentaTO.getPasssword());

                Boolean response = authService.updatePassword(usuario);

                if (response) {

                    generalHelper.redireccionar("/faces/login.xhtml");
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al actualizadar"));
                    FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al actualizar la contraseña"));

                }
            } else {
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las Contraseñas no coinciden"));
            }

            PrimeFaces.current().ajax().update("form_main:messagesCuenta", "form_main:dt-cuenta");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CuentaTO getCuentaTO() {
        return cuentaTO;
    }

    public void setCuentaTO(CuentaTO cuentaTO) {
        this.cuentaTO = cuentaTO;
    }

    public DatoCompraTO getDatoCompraTO() {
        return datoCompraTO;
    }

    public void setDatoCompraTO(DatoCompraTO datoCompraTO) {
        this.datoCompraTO = datoCompraTO;
    }

}
