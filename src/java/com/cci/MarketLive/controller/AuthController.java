package com.cci.MarketLive.controller;

import com.cci.MarketLive.to.UsuarioTO;
import com.cci.MarketLive.service.*;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "authController")
@SessionScoped

public class AuthController implements Serializable {

    private String nombre;
    private String correo;
    private String password;
    private int roleId;
    private UsuarioTO usuarioTO;
    private GeneralHelper generalHelper;
    private AuthService authService;

    public AuthController() {
        authService = new AuthService();
        generalHelper = new GeneralHelper();

        //Por default esta el rol de cliente
        this.setRoleId(3);
    }

    public void ingresar() {

        try {

            if (validarCampos("login")) {
                UsuarioTO usuarioLogin = authService.login(getCorreo(), getPassword());

                if (usuarioLogin != null && usuarioLogin.getId() != 0) {

                    // Guardar el UsuarioTO en la sesión de JSF
                    FacesContext context = FacesContext.getCurrentInstance();
                    HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
                    session.setAttribute("usuarioTO", usuarioLogin);

                    redirigirSegunRol(usuarioLogin);

                } else {
                    FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Credenciales invalidas"));
                }

            } else {
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Rellene los campos"));
            }

        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error de base de datos"));
        } finally {
            limpiarCampos();
        }
    }

    public void registrarse() {
        try {

            if (validarCampos("register")) {
                usuarioTO = new UsuarioTO();
                usuarioTO.setNombre(getNombre());
                usuarioTO.setCorreo(getCorreo());
                usuarioTO.setPassword(getPassword());
                usuarioTO.setRoleId(getRoleId());

                Boolean register = authService.register(usuarioTO);

                if (register) {
                    generalHelper.redireccionar("/faces/login.xhtml");
                }
            } else {
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Rellene los campos"));
            }

        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error de base de datos"));
        } finally {
            limpiarCampos();
        }
    }

    public void cerrarSesion() {
        limpiarCampos();

        // Invalidar la sesión de JSF
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Redireccionar a la página de inicio
        generalHelper.redireccionar("/faces/index.xhtml");
    }

    private void redirigirSegunRol(UsuarioTO usuarioLogin) {
        if (usuarioLogin != null && usuarioLogin.getRoleNombre().equals("admin")) {
            generalHelper.redireccionar("/faces/productos_admin.xhtml");
        } else {
            generalHelper.redireccionar("/faces/index.xhtml");
        }
    }

    private Boolean validarCampos(String tipo) {
        switch (tipo) {
            case "login":
                return !getCorreo().equals("") && !getPassword().equals("");

            case "register":
                return !getNombre().equals("") && !getCorreo().equals("") && !getPassword().equals("") && getRoleId() >= 1;

            default:
                return false;
        }
    }

    private void limpiarCampos() {
        setNombre("");
        setCorreo("");
        setPassword("");
        setRoleId(0);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public UsuarioTO getUsuarioTO() {
        return usuarioTO;
    }

    public void setUsuarioTO(UsuarioTO usuarioTO) {
        this.usuarioTO = usuarioTO;
    }

    public GeneralHelper getGeneralHelper() {
        return generalHelper;
    }

    public void setGeneralHelper(GeneralHelper generalHelper) {
        this.generalHelper = generalHelper;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

}
