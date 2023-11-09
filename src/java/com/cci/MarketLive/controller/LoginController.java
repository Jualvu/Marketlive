package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.*;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author julio
 */
@ManagedBean(name = "loginController")
@SessionScoped

public class LoginController implements Serializable {

    private int id;
    private String correo;
    private String password;
    private GeneralHelper generalHelper;
    private ServicioUsuario servicioUsuario;

    public LoginController() {
        servicioUsuario = new ServicioUsuario();
        generalHelper = new GeneralHelper();
    }

    public void ingresar() {

        try {

            if (this.correo != "" && this.password != "") {
                UsuarioTO usuarioLogin = servicioUsuario.login(this.correo, this.password);

                this.setId(usuarioLogin.getId());

                if (usuarioLogin.getCorreo().equals(this.correo) && usuarioLogin.getPassword().equals(this.password)) {

                    if (usuarioLogin.getRol().equals("admin")) {
                        generalHelper.redireccionar("/faces/panel_admin.xhtml");

                    } else {
                        generalHelper.redireccionar("/faces/index.xhtml");
                    }

                } else {
                    FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No encontrado"));
                }

            } else {
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Rellene los campos"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No encontrado"));
        }
    }

    public void cerrarSesion() {
        setCorreo("");
        setPassword("");

        generalHelper.redireccionar("/faces/index.xhtml");

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
