/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.cofar.evaluacionIPV;

import com.cofar.postulante.*;
import com.cofar.administrador.Tabulacion1Bean;
import com.cofar.util.session.ControllerAccesoSistema;
import com.cofar.util.session.Util;
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
public class Controller_EvaluacionIPV {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Controller_EvaluacionIPV.class);

    private List<PreguntasRespuestasBean> lista_preguntas = new ArrayList<>();

    public List<PreguntasRespuestasBean> getLista_preguntas() {
        return lista_preguntas;
    }

    public void setLista_preguntas(List<PreguntasRespuestasBean> lista_preguntas) {
        this.lista_preguntas = lista_preguntas;
    }

    @PostConstruct
    public void init() {
        Dao_EvaluacionIPV dao = new Dao_EvaluacionIPV();
        Dao_Reclutamiento daoR = new Dao_Reclutamiento();
        ControllerAccesoSistema ac = (ControllerAccesoSistema) Util.getSessionBean("controllerAccesoSistema");
        LOGGER.info("Usuario: " + ac.getUsuarioModuloBean().getCodUsuarioGlobal());

        nro_preg_ini = daoR.getLastRespuesta(Integer.parseInt(ac.getUsuarioModuloBean().getCodUsuarioGlobal()), 2) + 1;
        nro_preg_fin = nro_preg_ini + 9;
        lista_preguntas = dao.getListDetallePreguntas(nro_preg_ini, nro_preg_fin);

        LOGGER.debug("postConstruct Administrador reset values.");
    }

    public void actionGuardarRespuestas() {
        Dao_EvaluacionIPV dao = new Dao_EvaluacionIPV();
        Dao_Reclutamiento dao_rec=new Dao_Reclutamiento();
        ControllerAccesoSistema ac = (ControllerAccesoSistema) Util.getSessionBean("controllerAccesoSistema");
        LOGGER.info("Usuario: " + ac.getUsuarioModuloBean().getCodUsuarioGlobal());

        boolean swRespuestasCompletada = true;
        for (PreguntasRespuestasBean pregunta : lista_preguntas) {
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
            if (nro_preg_fin <= 90) {
                lista_preguntas = dao.getListDetallePreguntas(nro_preg_ini, nro_preg_fin);
            } else {

                List<Tabulacion1Bean> listadoResultadosEvaluacion = getCargarResultadosEvaluacionIPV(Integer.parseInt(ac.getUsuarioModuloBean().getCodUsuarioGlobal()));
                dao_rec.saveEvaluacionPostulante(listadoResultadosEvaluacion, Integer.parseInt(ac.getUsuarioModuloBean().getCodUsuarioGlobal()),2);

               visible_mensajeConclusion=true;
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

    public List<Tabulacion1Bean> getCargarResultadosEvaluacionIPV(int cod_postulante) {

        Dao_EvaluacionIPV dao = new Dao_EvaluacionIPV();
        LOGGER.info("Postulante Resultados: " + cod_postulante);

        List<Tabulacion1Bean> listadoTabulacion1 = dao.getListDetalleTabulacionIPV(cod_postulante);

        /*LOGGER.info("Postulante Resultados: " + 1);

        listadoTabulacion1 = dao.getListDetalleTabulacion1(2);
         */
        Tabulacion1Bean A = new Tabulacion1Bean();
        A.setNombre("R");
        A.setNumero(2);
        A.setValor(0);
        Tabulacion1Bean B = new Tabulacion1Bean();
        B.setNombre("A");
        B.setNumero(3);
        B.setValor(0);
        List<Tabulacion1Bean> listadoTabulacion2 = new ArrayList<>();
        for (Tabulacion1Bean tabulacion2 : listadoTabulacion1) {
            //tabulacion2.setValor(0);
            if (tabulacion2.getNumero() == 4 || tabulacion2.getNumero() == 5 || tabulacion2.getNumero() == 6 || tabulacion2.getNumero() == 7) {
                A.setValor(A.getValor() + tabulacion2.getValor());
            }
            if (tabulacion2.getNumero() == 8 || tabulacion2.getNumero() == 9 || tabulacion2.getNumero() == 10 || tabulacion2.getNumero() == 11) {
                B.setValor(B.getValor() + tabulacion2.getValor());
            }
            listadoTabulacion2.add(tabulacion2);
        }
        listadoTabulacion2.add(A);
        listadoTabulacion2.add(B);

        int[] valoresDGV = {1, 1, 1, 1, 1, 1, 2, 3, 3, 4, 5, 6, 7, 8, 8, 9, 10, 10, 10, 10, 10, 10};
        int[] valoresR = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        int[] valoresA = {1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 4, 5, 5, 6, 6, 7, 8, 8, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        int[] valoresI = {1, 1, 2, 3, 5, 6, 7, 8, 9, 10, 10, 10};
        int[] valoresII = {1, 1, 2, 4, 5, 6, 7, 8, 10, 10, 10, 10};
        int[] valoresIII = {1, 1, 2, 3, 4, 5, 6, 8, 9, 10, 10, 10};
        int[] valoresIV = {1, 1, 1, 1, 2, 4, 5, 7,9};
        int[] valoresV = {1, 1, 2, 3, 4, 6, 7, 8, 9, 10, 10, 10};
        int[] valoresVI = {1, 2, 3, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        int[] valoresVII = {1, 2, 4, 5, 7, 8, 9, 10, 10};
        int[] valoresVIII = {3, 5, 6, 8, 9, 10, 10, 10, 10};
        int[] valoresIX = {1, 1, 2, 3, 4, 5, 7, 8, 10};
        int pos = 0;
        for (Tabulacion1Bean tab2 : listadoTabulacion2) {
            switch (tab2.getNumero()) {
                case 1:
                    tab2.setValor(valoresDGV[tab2.getValor()]);
                    break;
                case 2:
                    tab2.setValor(valoresR[tab2.getValor()]);
                    break;
                case 3:
                    tab2.setValor(valoresA[tab2.getValor()]);
                    break;
                case 4:
                    tab2.setValor(valoresI[tab2.getValor()]);
                    break;
                case 5:
                    tab2.setValor(valoresII[tab2.getValor()]);
                    break;
                case 6:
                    tab2.setValor(valoresIII[tab2.getValor()]);
                    break;
                case 7:
                    tab2.setValor(valoresIV[tab2.getValor()]);
                    break;
                case 8:
                    tab2.setValor(valoresV[tab2.getValor()]);
                    break;
                case 9:
                    tab2.setValor(valoresVI[tab2.getValor()]);
                    break;
                case 10:
                    tab2.setValor(valoresVII[tab2.getValor()]);
                    break;
                case 11:
                    tab2.setValor(valoresVIII[tab2.getValor()]);
                    break;
                case 12:
                    tab2.setValor(valoresIX[tab2.getValor()]);
                    break;

            }

            pos++;
        }

        for (Tabulacion1Bean tab2 : listadoTabulacion2) {
            System.out.println(" " + tab2.getNumero() + "  " + tab2.getNombre() + "   " + tab2.getValor());
        }

        List<Tabulacion1Bean> listadoResultadosEvaluacion = new ArrayList<>();
        for (Tabulacion1Bean tab : listadoTabulacion2) {
            listadoResultadosEvaluacion.add(tab);
            System.out.println(tab.getNumero() + " " + tab.getNombre_largo() + " " + tab.getValor());
        }

        return listadoResultadosEvaluacion;
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
        ControllerAccesoSistema ac = (ControllerAccesoSistema) Util.getSessionBean("controllerAccesoSistema");

        ac.redirect_homePage();
    }
}
