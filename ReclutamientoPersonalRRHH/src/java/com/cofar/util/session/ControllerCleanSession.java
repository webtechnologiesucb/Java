/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.cofar.util.session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Jaime Chura
 * @version BACO 2.0 date 05-05-2015 time 06:02:53 PM
 */
@ManagedBean
@RequestScoped
public class ControllerCleanSession {

    public ControllerCleanSession() {
        Util.removeSession("controllerAccesoSistema");
        Util.removeSession("treeEventsView");
    }

    public String getBienvenidaLogin() {
        Util.removeSession("controllerAccesoSistema");
        Util.removeSession("treeEventsView");
        return "Ingreso al Sistema";
    }
}
