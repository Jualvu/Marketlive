package com.cci.MarketLive.controller;

import javax.servlet.http.HttpServletRequest;
import javax.faces.context.FacesContext;

public class GeneralHelper {

    public void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {
            // Manejo de excepciones (si es necesario)
        }
    }

}
