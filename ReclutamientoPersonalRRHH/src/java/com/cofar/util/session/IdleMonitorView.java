/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cofar.util.session;

import com.cofar.util.jdbc.ConnectionDataBase;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Jaime Chura
 * @version BACO 2.0
 * date 01-04-2015
 * time 04:53:11 PM
 */
@ManagedBean
public class IdleMonitorView {
     
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ConnectionDataBase.class);

    public void onIdle() {
         show_panel=true;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                                        "Sin Actividad.", "Esta todo bien?"));
               LOGGER.info("el valor de show_panel: "+show_panel);

       
    }
 
    public void onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Bienvendo de nuevo", "Bien, hora de trabajar!"));
        LOGGER.info("el valor de show_panel: "+show_panel);
    }
    private boolean show_panel;

    public boolean isShow_panel() {
        return show_panel;
    }

    public void setShow_panel(boolean show_panel) {
        this.show_panel = show_panel;
    }
    public void welcomeListener() {
        FacesContext.getCurrentInstance().addMessage(
        null,
        new FacesMessage(FacesMessage.SEVERITY_WARN, "Hallo",
            "Du bist wieder eingeloggt."));          

    }

    public void logoutListener() throws IOException {
        FacesContext.getCurrentInstance().addMessage(
        null,
        new FacesMessage(FacesMessage.SEVERITY_WARN,
            "Du bist ausgeloggt!", "Bis dann"));

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        ExternalContext ec =FacesContext.getCurrentInstance().getExternalContext();
        System.out.println(ec.getRequestContextPath()+"/login.xhtml");
        ec.redirect(ec.getRequestContextPath()+"/login.xhtml");
        FacesMessage msg = new FacesMessage("Information ", " Die Session ist abgelaufen. Bitte melden sie sich erneut an.");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    }
}