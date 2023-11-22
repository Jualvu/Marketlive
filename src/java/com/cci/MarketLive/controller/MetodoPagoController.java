package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.MetodoPagoService;
import com.cci.MarketLive.to.MetodoPagoTO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "metodoPagoController")
@SessionScoped
public class MetodoPagoController {

    private List<MetodoPagoTO> metodosPago;
    private MetodoPagoService metodoPagoService;

    public MetodoPagoController() {
        try {
            metodosPago = new ArrayList<>();
            metodoPagoService = new MetodoPagoService();

            setMetodosPago(metodoPagoService.readAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<MetodoPagoTO> getMetodosPago() {
        return metodosPago;
    }

    public void setMetodosPago(List<MetodoPagoTO> metodosPago) {
        this.metodosPago = metodosPago;
    }

}
