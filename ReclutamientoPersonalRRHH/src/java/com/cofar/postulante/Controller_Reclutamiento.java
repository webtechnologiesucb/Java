/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.cofar.postulante;

import com.cofar.administrador.Controller_Administrador;
import com.cofar.util.session.ControllerAccesoSistema;
import com.cofar.util.session.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Jaime Chura
 * @version BACO 2.0 date 02-08-2016 time 04:51:10 PM
 */
@ManagedBean
@SessionScoped
public class Controller_Reclutamiento {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Controller_Reclutamiento.class);

    private List<PreguntasBean> lista_preguntas = new ArrayList<>();

    public List<PreguntasBean> getLista_preguntas() {
        return lista_preguntas;
    }

    public void setLista_preguntas(List<PreguntasBean> lista_preguntas) {
        this.lista_preguntas = lista_preguntas;
    }

    @PostConstruct
    public void init() {
        Dao_Reclutamiento dao = new Dao_Reclutamiento();
        ControllerAccesoSistema ac = (ControllerAccesoSistema) Util.getSessionBean("controllerAccesoSistema");
        LOGGER.info("Usuario: " + ac.getUsuarioModuloBean().getCodUsuarioGlobal());

        nro_preg_ini = dao.getLastRespuesta(Integer.parseInt(ac.getUsuarioModuloBean().getCodUsuarioGlobal()), 1) + 1;
        nro_preg_fin = nro_preg_ini + 9;
        lista_preguntas = dao.getListDetallePreguntas(nro_preg_ini, nro_preg_fin);

        LOGGER.debug("postConstruct Administrador reset values.");

    }

    public void actionGuardarRespuestas() {
        Dao_Reclutamiento dao = new Dao_Reclutamiento();
        ControllerAccesoSistema ac = (ControllerAccesoSistema) Util.getSessionBean("controllerAccesoSistema");
        LOGGER.info("Usuario: " + ac.getUsuarioModuloBean().getCodUsuarioGlobal());

        boolean swRespuestasCompletada = true;
        for (PreguntasBean pregunta : lista_preguntas) {
            if (pregunta.getRespuesta() == 0) {
                swRespuestasCompletada = false;
            }
        }
        if (!swRespuestasCompletada) {
            LOGGER.debug("No completó todas las respuestas");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Guardar Respuestas", "Debe responder a todas las preguntas.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {

            dao.saveRespuestas(lista_preguntas, Integer.parseInt(ac.getUsuarioModuloBean().getCodUsuarioGlobal()));
            nro_preg_ini += 10;
            nro_preg_fin += 10;
            if (nro_preg_fin <= 240) {
                lista_preguntas = dao.getListDetallePreguntas(nro_preg_ini, nro_preg_fin);
            } else {
                Controller_Administrador admin = new Controller_Administrador();

                PostulanteBean usuarioModulos = new PostulanteBean();
                usuarioModulos.setCod_postulante(Integer.parseInt(ac.getUsuarioModuloBean().getCodUsuarioGlobal()));
                admin.setSelectedPostulante(usuarioModulos);
                admin.getCargarResultadosEvaluacion();

                dao.saveEvaluacionPostulante(admin.getListadoResultadosEvaluacion(), Integer.parseInt(ac.getUsuarioModuloBean().getCodUsuarioGlobal()), 1);

                visible_mensajeConclusion = true;
            }
        }

    }

    private int nro_preg_ini;
    private int nro_preg_fin;

    public int getNro_preg_ini() {
        return nro_preg_ini;
    }

    public void setNro_preg_ini(int nro_preg_ini) {
        this.nro_preg_ini = nro_preg_ini;
    }

    public int getNro_preg_fin() {
        return nro_preg_fin;
    }

    public void setNro_preg_fin(int nro_preg_fin) {
        this.nro_preg_fin = nro_preg_fin;
    }

    private boolean visible_instrucciones = true;

    public boolean isVisible_instrucciones() {
        return visible_instrucciones;
    }

    public void setVisible_instrucciones(boolean visible_instrucciones) {
        this.visible_instrucciones = visible_instrucciones;
    }

    public void cerrar_instrucciones() {
        visible_instrucciones = false;
    }

    public void ver_instrucciones() {
        visible_instrucciones = true;
    }

    private boolean visible_mensajeConclusion = false;

    public boolean isVisible_mensajeConclusion() {
        return visible_mensajeConclusion;
    }

    public void setVisible_mensajeConclusion(boolean visible_mensajeConclusion) {
        this.visible_mensajeConclusion = visible_mensajeConclusion;
    }

    public void action_ConfirmarConclusionEvaluacion() {
        LOGGER.debug("cerrar");
        visible_mensajeConclusion = false;
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar Respuestas", "Concluyó la evaluación satisfactoriamente.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Dao_Reclutamiento dao = new Dao_Reclutamiento();
        ControllerAccesoSistema ac = (ControllerAccesoSistema) Util.getSessionBean("controllerAccesoSistema");
        LOGGER.info("Usuario: " + ac.getUsuarioModuloBean().getCodUsuarioGlobal());

        int cod_evaluacion = dao.getCodEvaluacionActual(Integer.parseInt(ac.getUsuarioModuloBean().getCodUsuarioGlobal()));
        if (cod_evaluacion == 0) {
            ac.redirect_homePage();
        } else if (cod_evaluacion == 2) {
            dao.updateInicioEvaluacionPostulante(Integer.parseInt(ac.getUsuarioModuloBean().getCodUsuarioGlobal()), cod_evaluacion);
                    
            String context_Path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            String path_redirect = context_Path + "/postulante/indexEvaluacionIPV.xhtml";
            try {
                LOGGER.debug("redirect postulante despues CPS: " + path_redirect);
                FacesContext.getCurrentInstance().getExternalContext().redirect(path_redirect);
            } catch (IOException ex1) {
                LOGGER.fatal("Excepcion redirect - recurso inexistente." + ex1.getMessage());
            }

        }

    }

}
