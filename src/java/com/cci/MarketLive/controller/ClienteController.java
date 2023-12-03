package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.ClienteService;
import com.cci.MarketLive.to.UsuarioTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "clienteController")
@SessionScoped
public class ClienteController implements Serializable {

    private List<UsuarioTO> usuarios;
    private UsuarioTO selectedUsuario;
    private List<UsuarioTO> selectedUsuarios;
    private ClienteService servicioCliente;

    public ClienteController() {
        usuarios = new ArrayList<>();
        selectedUsuarios = new ArrayList<>();

        try {
            servicioCliente = new ClienteService();
            setUsuarios(servicioCliente.readAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUsuario() {
        try {
            if (this.selectedUsuario.getId() == 0) {
                servicioCliente.create(selectedUsuario);
                setUsuarios(servicioCliente.readAll());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario agregado"));
            } else {
                servicioCliente.update(selectedUsuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario actualizado"));
            }

            PrimeFaces.current().executeScript("PF('manageClienteDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-clientes");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al guardar el usuario"));
            e.printStackTrace();
        } finally {
            setSelectedUsuario(null);
        }
    }

    public void deleteUsuario() {
        try {
            boolean delete = servicioCliente.delete(this.selectedUsuario.getId());

            if (delete) {
                this.usuarios.remove(this.selectedUsuario);
                this.selectedUsuarios.remove(this.selectedUsuario);
                this.selectedUsuario = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Eliminado"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al eliminar el usuario"));
            }

            PrimeFaces.current().executeScript("PF('manageClienteDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-clientes");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UsuarioTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioTO> usuarios) {
        this.usuarios = usuarios;
    }

    public UsuarioTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(UsuarioTO selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<UsuarioTO> getSelectedUsuarios() {
        return selectedUsuarios;
    }

    public void setSelectedUsuarios(List<UsuarioTO> selectedUsuarios) {
        this.selectedUsuarios = selectedUsuarios;
    }

    public void openNew() {
        this.selectedUsuario = new UsuarioTO();
    }

    public String getRolName(int roleId) {
        switch (roleId) {
            case 1:
                return "Admin";
            case 2:
                return "Admin_Tienda";
            case 3:
                return "Cliente";
            default:
                return "Desconocido";
        }
    }
}
