package com.cci.MarketLive.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "rutasController")
@SessionScoped

public class RutasController implements Serializable {

    private GeneralHelper generalHelper;

    public void redireccionar(String ruta) {
        generalHelper = new GeneralHelper();
        generalHelper.redireccionar("/faces/" + ruta);
    }

}
