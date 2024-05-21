/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.cofar.administrador;

import com.cofar.postulante.*;
import com.cofar.util.session.ControllerAccesoSistema;
import com.cofar.util.session.Util;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.apache.logging.log4j.LogManager;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Jaime Chura
 * @version BACO 2.0 date 02-08-2016 time 04:51:10 PM
 */
@ManagedBean
@SessionScoped
public class Controller_Administrador {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Controller_Administrador.class);

    private PostulanteBean postulante = new PostulanteBean();

    public PostulanteBean getPostulante() {
        return postulante;
    }

    public void setPostulante(PostulanteBean postulante) {
        this.postulante = postulante;
    }

    public void guardarPostulante() {
        Dao_Reclutamiento dao = new Dao_Reclutamiento();
        ControllerAccesoSistema ac = (ControllerAccesoSistema) Util.getSessionBean("controllerAccesoSistema");
        LOGGER.info("Usuario: " + ac.getUsuarioModuloBean().getCodUsuarioGlobal());
        postulante.setCod_postulante(Integer.parseInt(ac.getUsuarioModuloBean().getCodUsuarioGlobal()));
        dao.updatePostulante(postulante);
        //listadoPostulantes = dao.getListPostulantes();
        visible_NuevoPostulante = false;
        int cod_evaluacion = dao.getCodEvaluacionActual(postulante.getCod_postulante());
        String context_Path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        String path_redirect = context_Path;
        if (cod_evaluacion == 1) {
            path_redirect = context_Path + "/postulante/indexPostulante.xhtml";
        } else if (cod_evaluacion == 2) {
            path_redirect = context_Path + "/postulante/indexEvaluacionIPV.xhtml";
        }
        try {

            LOGGER.debug("redirect: " + path_redirect);

            FacesContext.getCurrentInstance().getExternalContext().redirect(path_redirect);

        } catch (IOException ex1) {
            LOGGER.fatal("Excepcion redirect - recurso inexistente.");

        }

    }

    private List<PostulanteBean> listadoPostulantes = new ArrayList<>();

    public List<PostulanteBean> getListadoPostulantes() {
        return listadoPostulantes;
    }

    public void setListadoPostulantes(List<PostulanteBean> listadoPostulantes) {
        this.listadoPostulantes = listadoPostulantes;
    }

    @PostConstruct
    public void init() {
        Dao_Reclutamiento dao = new Dao_Reclutamiento();
        ControllerAccesoSistema ac = (ControllerAccesoSistema) Util.getSessionBean("controllerAccesoSistema");
        if (ac != null) {
            LOGGER.info("Usuario: " + ac.getUsuarioModuloBean().getCodUsuarioGlobal());

            listadoPostulantes = dao.getListPostulantes(Integer.parseInt(ac.getUsuarioModuloBean().getCodUsuarioGlobal().equals("") ? "0" : ac.getUsuarioModuloBean().getCodUsuarioGlobal()), ac.isPerfil_administrador());
            visible_NuevoPostulante = false;
            visible_PDF_Respuesta = false;
            total_prueba = 0;
            parametro = new Parametros_Bean();
            createLineModelsInit();
        }
        else{
            LOGGER.debug("session nula.");
        }
    }
    private List<PostulanteBean> listadoPostulantesFilter;

    public List<PostulanteBean> getListadoPostulantesFilter() {
        return listadoPostulantesFilter;
    }

    public void setListadoPostulantesFilter(List<PostulanteBean> listadoPostulantesFilter) {
        this.listadoPostulantesFilter = listadoPostulantesFilter;
    }
    private boolean visible_NuevoPostulante;

    public boolean isVisible_NuevoPostulante() {
        return visible_NuevoPostulante;
    }

    public void setVisible_NuevoPostulante(boolean visible_NuevoPostulante) {
        this.visible_NuevoPostulante = visible_NuevoPostulante;
    }

    public void actionListener_nuevoPostulante() {

        visible_NuevoPostulante = true;

        postulante = new PostulanteBean();
        Dao_Reclutamiento dao = new Dao_Reclutamiento();
        listAreas = dao.getListaAreasEmpresa();

        listCargos = new ArrayList<>();

    }

    public void actionListener_cancelarNuevoPostulante() {
        visible_NuevoPostulante = false;
        ControllerAccesoSistema ac = (ControllerAccesoSistema) Util.getSessionBean("controllerAccesoSistema");
        LOGGER.info("Usuario: " + ac.getUsuarioModuloBean().getCodUsuarioGlobal());
        ac.redirect_homePage();
    }

    public void actionListener_generarDatosAcceso() {
        if (postulante.getAp_paterno().length() > 1 && postulante.getAp_materno().length() > 1 && postulante.getNombres().length() > 1) {
            postulante.setNombre_usuario("" + postulante.getAp_paterno().charAt(0)
                    + postulante.getAp_materno().charAt(0)
                    + postulante.getNombres().charAt(0)
                    + "-" + new SimpleDateFormat("yyyyMMdd").format(postulante.getFecha_nac())
            );
        }
        postulante.setPassword(postulante.getNumid());
    }

    private List<PreguntasBean> listadoRespuestas = new ArrayList<>();

    public List<PreguntasBean> getListadoRespuestas() {
        return listadoRespuestas;
    }

    public void setListadoRespuestas(List<PreguntasBean> listadoRespuestas) {
        this.listadoRespuestas = listadoRespuestas;
    }
    private boolean visible_RespuestasPostulante = false;

    public boolean isVisible_RespuestasPostulante() {
        return visible_RespuestasPostulante;
    }

    public void setVisible_RespuestasPostulante(boolean visible_RespuestasPostulante) {
        this.visible_RespuestasPostulante = visible_RespuestasPostulante;
    }

    public void actionListener_verRespuestas() {

    }

    public String getCargarListado() {
        Dao_Reclutamiento dao = new Dao_Reclutamiento();
        LOGGER.info("Usuario: " + selectedPostulante.getCod_postulante());

        listadoRespuestas = dao.getListDetalleRespuestas(selectedPostulante.getCod_postulante());
        return null;
    }

    private PostulanteBean selectedPostulante;

    public PostulanteBean getSelectedPostulante() {
        return selectedPostulante;
    }

    public void setSelectedPostulante(PostulanteBean selectedPostulante) {
        this.selectedPostulante = selectedPostulante;
    }

    public String getCargarTabulacion1() {
        Dao_Reclutamiento dao = new Dao_Reclutamiento();
        LOGGER.info("Postulante: " + selectedPostulante.getCod_postulante());

        listadoTabulacion1 = dao.getListDetalleTabulacion1(selectedPostulante.getCod_postulante());
        return null;
    }

    private List<Tabulacion1Bean> listadoTabulacion1 = new ArrayList<>();

    public List<Tabulacion1Bean> getListadoTabulacion1() {
        return listadoTabulacion1;
    }

    public void setListadoTabulacion1(List<Tabulacion1Bean> listadoTabulacion1) {
        this.listadoTabulacion1 = listadoTabulacion1;
    }

    public void action_verRespuestasPDF() {
        if (selectedPostulante != null) {
            String context_Path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            LOGGER.debug("URL contruida preview: " + URL_PDF_Respuestas);
            URL_PDF_Respuestas = context_Path + "/Servlet_RespuestasPostulante";
            URL_PDF_Respuestas += "?cod_postulante=" + selectedPostulante.getCod_postulante();
            LOGGER.debug("URL contruida post: " + URL_PDF_Respuestas);

            visible_PDF_Respuesta = true;

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Selección de Postulante", "Debe seleccionar un Postulante para visualizarla en formato PDF.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    private String URL_PDF_Respuestas;

    public String getURL_PDF_Respuestas() {
        return URL_PDF_Respuestas;
    }

    public void setURL_PDF_Respuestas(String URL_PDF_Respuestas) {
        this.URL_PDF_Respuestas = URL_PDF_Respuestas;
    }

    private boolean visible_PDF_Respuesta = false;

    public boolean isVisible_PDF_Respuesta() {
        return visible_PDF_Respuesta;
    }

    public void setVisible_PDF_Respuesta(boolean visible_PDF_Respuesta) {
        this.visible_PDF_Respuesta = visible_PDF_Respuesta;
    }

    public void actionListener_cancelarverPDF() {
        visible_PDF_Respuesta = false;
        visible_PDF_Evaluacion = false;
        visible_PDF_EvaluacionIPV = false;
        visible_PDF_RespuestaIPV = false;
        LOGGER.debug("visible PDF: " + visible_PDF_Respuesta);
    }

    private List<Tabulacion1Bean> listadoResultadosEvaluacion = new ArrayList<>();

    public List<Tabulacion1Bean> getListadoResultadosEvaluacion() {
        return listadoResultadosEvaluacion;
    }

    public void setListadoResultadosEvaluacion(List<Tabulacion1Bean> listadoResultadosEvaluacion) {
        this.listadoResultadosEvaluacion = listadoResultadosEvaluacion;
    }

    public String getCargarResultadosEvaluacion() {
        total_prueba = 0;
        Dao_Reclutamiento dao = new Dao_Reclutamiento();
        LOGGER.info("Postulante Resultados: " + selectedPostulante.getCod_postulante());

        listadoTabulacion1 = dao.getListDetalleTabulacion1(selectedPostulante.getCod_postulante());

        /*LOGGER.info("Postulante Resultados: " + 1);

        listadoTabulacion1 = dao.getListDetalleTabulacion1(2);
         */
        List<Tabulacion1Bean> listadoTabulacion2 = new ArrayList<>();
        for (Tabulacion1Bean tabulacion2 : listadoTabulacion1) {
            //tabulacion2.setValor(0);
            listadoTabulacion2.add(tabulacion2);
        }
        listadoResultadosEvaluacion = new ArrayList<>();

        int[][] matrizTabulacion2 = new int[50][50];
        int[] valoresEST = {3, 3, 3, 3, 3, 9, 12, 15, 17, 17, 24, 24, 29, 33, 37, 40, 42, 48, 50, 55, 58, 63, 67, 76, 83, 87, 97};
        int[] valoresANS = {3, 9, 17, 24, 29, 33, 37, 40, 42, 45, 48, 52, 55, 58, 60, 67, 71, 71, 76, 83, 85, 91, 97, 97, 97, 97};
        int[] valoresAUC = {3, 3, 3, 3, 3, 3, 3, 9, 9, 12, 15, 17, 24, 24, 29, 33, 37, 40, 45, 50, 55, 63, 71, 76, 87, 97};
        int[] valoresEFI = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 9, 12, 17, 17, 24, 29, 37, 42, 52, 60, 71, 91};
        int[] valoresCSG = {3, 12, 12, 12, 12, 12, 12, 12, 15, 15, 17, 17, 17, 24, 24, 29, 29, 33, 37, 42, 45, 52, 60, 76, 97};
        int[] valoresIND = {9, 9, 12, 17, 24, 33, 40, 48, 52, 60, 67, 76, 83, 91, 97, 97, 97, 97, 97, 97};
        int[] valoresDOM = {3, 3, 12, 17, 24, 33, 37, 42, 48, 52, 55, 58, 63, 67, 71, 71, 76, 83, 83, 85, 91, 91, 97, 97, 97};
        int[] valoresCCG = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 9, 12, 17, 24, 29, 33, 42, 50, 63, 76};
        int[] valoresSOC = {24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 29, 29, 29, 33, 33, 33, 37, 40, 42, 45, 50, 55, 63, 76, 97};
        int[] valoresAJS = {3, 15, 15, 15, 15, 17, 17, 17, 17, 17, 24, 24, 29, 37, 42, 52, 60, 76, 97, 97};
        int[] valoresAGR = {3, 17, 24, 33, 37, 42, 48, 52, 58, 63, 67, 71, 76, 83, 85, 87, 97, 97, 97, 97, 97, 97};
        int[] valoresTOL = {3, 3, 3, 3, 3, 3, 3, 9, 12, 17, 24, 29, 37, 42, 50, 55, 63, 71, 83, 97};
        int[] valoresINS = {3, 3, 3, 3, 3, 3, 3, 3, 3, 9, 9, 9, 12, 17, 17, 24, 33, 40, 50, 63, 83};
        int[] valoresINH = {17, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 29, 37, 48, 67, 97};
        int[] valoresLID = {0, 0, 12, 17, 24, 29, 37, 42, 48, 52, 55, 60, 63, 67, 71, 76, 83, 87, 97, 97};
        int[] valoresSIN = {3, 3, 12, 18, 29, 40, 48, 55, 60, 67, 76, 76, 83, 93, 97, 97, 97, 97, 97, 97, 97, 97};
        int[] valoresDES = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 9, 12, 15, 17, 17, 24, 24, 29, 37, 40, 48, 58, 76};
        //int[] valoresCNT = {15, 17, 24, 24, 24, 24, 29, 29, 37, 45, 58, 76, 97, 97};
        int pos = 0;
        for (Tabulacion1Bean tab2 : listadoTabulacion2) {
            switch (tab2.getNumero()) {
                case 1:
                    tab2.setValor(valoresEST[tab2.getValor()]);
                    break;
                case 2:
                    tab2.setValor(valoresANS[tab2.getValor()]);
                    break;
                case 3:
                    tab2.setValor(valoresAUC[tab2.getValor()]);
                    break;
                case 4:
                    tab2.setValor(valoresEFI[tab2.getValor()]);
                    break;
                case 5:
                    tab2.setValor(valoresCSG[tab2.getValor()]);
                    break;
                case 6:
                    tab2.setValor(valoresIND[tab2.getValor()]);
                    break;
                case 7:
                    tab2.setValor(valoresDOM[tab2.getValor()]);
                    break;
                case 8:
                    tab2.setValor(valoresCCG[tab2.getValor()]);
                    break;
                case 9:
                    tab2.setValor(valoresSOC[tab2.getValor()]);
                    break;
                case 10:
                    tab2.setValor(valoresAJS[tab2.getValor()]);
                    break;
                case 11:
                    tab2.setValor(valoresAGR[tab2.getValor()]);
                    break;
                case 12:
                    tab2.setValor(valoresTOL[tab2.getValor()]);
                    break;
                case 13:
                    tab2.setValor(valoresINS[tab2.getValor()]);
                    break;
                case 14:
                    tab2.setValor(valoresINH[tab2.getValor()]);
                    break;
                case 15:
                    tab2.setValor(valoresLID[tab2.getValor()]);
                    break;
                case 16:
                    tab2.setValor(valoresSIN[tab2.getValor()]);
                    break;
                case 17:
                    tab2.setValor(valoresDES[tab2.getValor()]);
                    break;

            }
            matrizTabulacion2[pos][0] = tab2.getValor();
            pos++;
        }
        matrizTabulacion2[0][1] = matrizTabulacion2[0][0] * 3;
        matrizTabulacion2[0][4] = matrizTabulacion2[0][0] * 1;
        matrizTabulacion2[0][5] = matrizTabulacion2[0][0] * 1;
        matrizTabulacion2[0][8] = matrizTabulacion2[0][0] * 2;

        matrizTabulacion2[1][2] = matrizTabulacion2[1][0] * 4;
        matrizTabulacion2[1][3] = matrizTabulacion2[1][0] * 1;
        matrizTabulacion2[1][5] = matrizTabulacion2[1][0] * 1;
        matrizTabulacion2[1][7] = matrizTabulacion2[1][0] * 3;
        matrizTabulacion2[1][9] = matrizTabulacion2[1][0] * 1;

        matrizTabulacion2[2][1] = matrizTabulacion2[2][0] * 2;
        matrizTabulacion2[2][3] = matrizTabulacion2[2][0] * 1;
        matrizTabulacion2[2][5] = matrizTabulacion2[2][0] * 1;
        matrizTabulacion2[2][8] = matrizTabulacion2[2][0] * 1;
        matrizTabulacion2[2][9] = matrizTabulacion2[2][0] * 1;

        matrizTabulacion2[3][3] = matrizTabulacion2[3][0] * 2;
        matrizTabulacion2[3][5] = matrizTabulacion2[3][0] * 1;
        matrizTabulacion2[3][7] = matrizTabulacion2[3][0] * 2;
        matrizTabulacion2[3][9] = matrizTabulacion2[3][0] * 1;

        matrizTabulacion2[4][1] = matrizTabulacion2[4][0] * 3;
        matrizTabulacion2[4][4] = matrizTabulacion2[4][0] * 1;
        matrizTabulacion2[4][5] = matrizTabulacion2[4][0] * 1;
        matrizTabulacion2[4][10] = matrizTabulacion2[4][0] * 1;

        matrizTabulacion2[5][5] = matrizTabulacion2[5][0] * 5;
        matrizTabulacion2[5][7] = matrizTabulacion2[5][0] * 1;

        matrizTabulacion2[6][1] = matrizTabulacion2[6][0] * 1;
        matrizTabulacion2[6][3] = matrizTabulacion2[6][0] * 3;
        matrizTabulacion2[6][5] = matrizTabulacion2[6][0] * 1;
        matrizTabulacion2[6][8] = matrizTabulacion2[6][0] * 1;

        matrizTabulacion2[7][2] = matrizTabulacion2[7][0] * 3;
        matrizTabulacion2[7][5] = matrizTabulacion2[7][0] * 2;
        matrizTabulacion2[7][7] = matrizTabulacion2[7][0] * 5;
        matrizTabulacion2[7][9] = matrizTabulacion2[7][0] * 1;

        matrizTabulacion2[8][1] = matrizTabulacion2[8][0] * 3;
        matrizTabulacion2[8][4] = matrizTabulacion2[8][0] * 1;
        matrizTabulacion2[8][6] = matrizTabulacion2[8][0] * 4;
        matrizTabulacion2[8][8] = matrizTabulacion2[8][0] * 2;
        matrizTabulacion2[8][10] = matrizTabulacion2[8][0] * 2;

        matrizTabulacion2[9][4] = matrizTabulacion2[9][0] * 1;
        matrizTabulacion2[9][6] = matrizTabulacion2[9][0] * 5;
        matrizTabulacion2[9][7] = matrizTabulacion2[9][0] * 1;
        matrizTabulacion2[9][10] = matrizTabulacion2[9][0] * 2;

        matrizTabulacion2[10][1] = matrizTabulacion2[10][0] * 1;
        matrizTabulacion2[10][4] = matrizTabulacion2[10][0] * 3;
        matrizTabulacion2[10][6] = matrizTabulacion2[10][0] * 2;
        matrizTabulacion2[10][10] = matrizTabulacion2[10][0] * 6;

        matrizTabulacion2[11][2] = matrizTabulacion2[11][0] * 2;
        matrizTabulacion2[11][3] = matrizTabulacion2[11][0] * 2;
        matrizTabulacion2[11][5] = matrizTabulacion2[11][0] * 1;
        matrizTabulacion2[11][9] = matrizTabulacion2[11][0] * 6;

        matrizTabulacion2[12][2] = matrizTabulacion2[12][0] * 3;
        matrizTabulacion2[12][3] = matrizTabulacion2[12][0] * 1;
        matrizTabulacion2[12][5] = matrizTabulacion2[12][0] * 1;
        matrizTabulacion2[12][7] = matrizTabulacion2[12][0] * 5;
        matrizTabulacion2[12][9] = matrizTabulacion2[12][0] * 1;

        matrizTabulacion2[13][2] = matrizTabulacion2[13][0] * 2;
        matrizTabulacion2[13][4] = matrizTabulacion2[13][0] * 2;
        matrizTabulacion2[13][6] = matrizTabulacion2[13][0] * 2;
        matrizTabulacion2[13][7] = matrizTabulacion2[13][0] * 4;
        matrizTabulacion2[13][10] = matrizTabulacion2[13][0] * 3;

        matrizTabulacion2[14][3] = matrizTabulacion2[14][0] * 4;
        matrizTabulacion2[14][8] = matrizTabulacion2[14][0] * 1;
        matrizTabulacion2[14][9] = matrizTabulacion2[14][0] * 1;

        matrizTabulacion2[15][2] = matrizTabulacion2[15][0] * 3;
        matrizTabulacion2[15][3] = matrizTabulacion2[15][0] * 5;
        matrizTabulacion2[15][7] = matrizTabulacion2[15][0] * 1;
        matrizTabulacion2[15][9] = matrizTabulacion2[15][0] * 3;

        matrizTabulacion2[16][4] = matrizTabulacion2[16][0] * 1;
        matrizTabulacion2[16][7] = matrizTabulacion2[16][0] * 2;

        matrizTabulacion2[17][1] = 700;
        matrizTabulacion2[17][3] = 50;
        matrizTabulacion2[17][5] = 400;
        matrizTabulacion2[17][8] = 350;
        matrizTabulacion2[17][9] = 450;

        for (int i = 0; i < 11; i++) {
            int suma = 0;
            for (int j = 0; j < 18; j++) {
                suma += matrizTabulacion2[j][i];
            }
            matrizTabulacion2[18][i] = suma;

        }
        for (Tabulacion1Bean tab2 : listadoTabulacion2) {
            System.out.println(" " + tab2.getNumero() + "  " + tab2.getNombre() + "   " + tab2.getValor());
        }

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print("\t" + matrizTabulacion2[i][j]);
            }
            System.out.println("");
        }

        int[] puntuacion = new int[6];
        puntuacion[0] = matrizTabulacion2[18][1] - matrizTabulacion2[18][2];
        puntuacion[1] = matrizTabulacion2[18][3] - matrizTabulacion2[18][4];
        puntuacion[2] = matrizTabulacion2[18][5] - matrizTabulacion2[18][6];
        puntuacion[3] = matrizTabulacion2[18][7] - matrizTabulacion2[18][8];
        puntuacion[4] = matrizTabulacion2[18][9] - matrizTabulacion2[18][10];

        for (int i = 0; i < 5; i++) {
            Tabulacion1Bean res = new Tabulacion1Bean();
            switch (i) {
                case 0:
                    res.setNombre_largo("AJUSTE");
                    res.setNombre("AJUSTE");
                    break;
                case 1:
                    res.setNombre_largo("LIDERAZGO");
                    res.setNombre("LIDERAZGO");
                    break;
                case 2:
                    res.setNombre_largo("DEPENDENCIA");
                    res.setNombre("DEPENDENCIA");
                    break;
                case 3:
                    res.setNombre_largo("CONSENSO");
                    res.setNombre("CONSENSO");
                    break;
                case 4:
                    res.setNombre_largo("EXTRAVERSION");
                    res.setNombre("EXTRAVERSION");
                    break;
            }
            System.out.println("P" + i + ": " + puntuacion[i]);
            res.setNumero(i + 18);
            res.setValor(puntuacion[i] / 10);
            //listadoTabulacion2.add(res);
        }
        for (Tabulacion1Bean tab : listadoTabulacion2) {
            listadoResultadosEvaluacion.add(tab);
            System.out.println(tab.getNumero() + " " + tab.getNombre_largo() + " " + tab.getValor());
        }
        for (Tabulacion1Bean tab : listadoResultadosEvaluacion) {
            int valor = tab.getValor();
            if (tab.getNumero() == 2 || tab.getNumero() == 11 || tab.getNumero() == 17) {
                valor = 100 - tab.getValor();
            }
            total_prueba += valor;
        }
        total_prueba = total_prueba / 22;
        return null;
    }

    private List<ResultadoBean> listadoResultadoPuntuacion = new ArrayList<>();

    public List<ResultadoBean> getListadoResultadoPuntuacion() {
        return listadoResultadoPuntuacion;
    }

    public void setListadoResultadoPuntuacion(List<ResultadoBean> listadoResultadoPuntuacion) {
        this.listadoResultadoPuntuacion = listadoResultadoPuntuacion;
    }

    public static void main(String args[]) {
        Controller_Administrador ca = new Controller_Administrador();
        ca.getCargarResultadosEvaluacion();
    }

    private int total_prueba;

    public int getTotal_prueba() {
        return total_prueba;
    }

    public void setTotal_prueba(int total_prueba) {
        this.total_prueba = total_prueba;
    }
    private String URL_PDF_Evaluacion;
    private boolean visible_PDF_Evaluacion;

    public String getURL_PDF_Evaluacion() {
        return URL_PDF_Evaluacion;
    }

    public void setURL_PDF_Evaluacion(String URL_PDF_Evaluacion) {
        this.URL_PDF_Evaluacion = URL_PDF_Evaluacion;
    }

    public boolean isVisible_PDF_Evaluacion() {
        return visible_PDF_Evaluacion;
    }

    public void setVisible_PDF_Evaluacion(boolean visible_PDF_Evaluacion) {
        this.visible_PDF_Evaluacion = visible_PDF_Evaluacion;
    }

    public void action_verEvaluacionPDF() {
        if (selectedPostulante != null) {
            String context_Path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            LOGGER.debug("URL contruida preview: " + URL_PDF_Evaluacion);
            URL_PDF_Evaluacion = context_Path + "/Servlet_EvaluacionPostulante";
            URL_PDF_Evaluacion += "?cod_postulante=" + selectedPostulante.getCod_postulante();
            LOGGER.debug("URL contruida post: " + URL_PDF_Evaluacion);

            visible_PDF_Evaluacion = true;

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Selección de Postulante", "Debe seleccionar un Postulante para visualizarla en formato PDF.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void action_verEvaluacionIPVPDF() {
        if (selectedPostulante != null) {
            String context_Path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            LOGGER.debug("URL contruida preview IPV: " + URL_PDF_Evaluacion);
            URL_PDF_Evaluacion = context_Path + "/Servlet_EvaluacionIPVPostulante";
            URL_PDF_Evaluacion += "?cod_postulante=" + selectedPostulante.getCod_postulante();
            LOGGER.debug("URL contruida post: " + URL_PDF_Evaluacion);
            submittedBase64Str();
            visible_PDF_EvaluacionIPV = true;

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Selección de Postulante", "Debe seleccionar un Postulante para visualizarla en formato PDF.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    private boolean visible_PDF_EvaluacionIPV;

    public boolean isVisible_PDF_EvaluacionIPV() {
        return visible_PDF_EvaluacionIPV;
    }

    public void setVisible_PDF_EvaluacionIPV(boolean visible_PDF_EvaluacionIPV) {
        this.visible_PDF_EvaluacionIPV = visible_PDF_EvaluacionIPV;
    }

    private Parametros_Bean parametro;

    public Parametros_Bean getParametro() {
        return parametro;
    }

    public void setParametro(Parametros_Bean parametro) {
        this.parametro = parametro;
    }

    public void changeEstadoCivil() {

        System.out.println("click change" + " " + postulante.getSexo() + " "
                + postulante.getCod_estadocivil());
        if (postulante.getSexo().equals("F")
                && postulante.getCod_estadocivil() == 2) {
            showApellidoEsposo = true;
            System.out.println(true);
        } else {
            showApellidoEsposo = false;
        }
    }

    private boolean showApellidoEsposo = false;

    public boolean isShowApellidoEsposo() {
        return showApellidoEsposo;
    }

    public void setShowApellidoEsposo(boolean showApellidoEsposo) {
        this.showApellidoEsposo = showApellidoEsposo;
    }

    private List<Pais_Bean> listPaises = new ArrayList<>();

    public List<Pais_Bean> getListPaises() {
        return listPaises;
    }

    public void setListPaises(List<Pais_Bean> listPaises) {
        this.listPaises = listPaises;
    }

    public String getCargarDatosPostulanteSession() {
        Dao_Reclutamiento dao = new Dao_Reclutamiento();
        ControllerAccesoSistema ac = (ControllerAccesoSistema) Util.getSessionBean("controllerAccesoSistema");
        LOGGER.info("Usuario: " + ac.getUsuarioModuloBean().getCodUsuarioGlobal());
        int cod_postulante = Integer.parseInt(ac.getUsuarioModuloBean().getCodUsuarioGlobal());
        postulante = dao.getPostulante(cod_postulante);
         Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -35);
        postulante.setFecha_nac(calendar.getTime());
        listPaises = dao.getListPaises();
        return null;
    }

    public void actionListener_cancelarNuevoPostulanteAdmin() {
        visible_NuevoPostulante = false;

    }

    public void guardarPostulanteAdmin() {
        Dao_Reclutamiento dao = new Dao_Reclutamiento();
        ControllerAccesoSistema ac = (ControllerAccesoSistema) Util.getSessionBean("controllerAccesoSistema");
        LOGGER.info("Usuario: " + ac.getUsuarioModuloBean().getCodUsuarioGlobal());
        postulante.setCod_postulante(dao.getCodPostulante());
        postulante.setAp_paterno(postulante.getNombre_usuario());
        postulante.setNumid(postulante.getPassword());
        boolean existNumID = dao.existNumeroIdentificacion(postulante.getNumid());
        if (!existNumID) {
            dao.savePostulante(postulante, Integer.parseInt(ac.getUsuarioModuloBean().getCodUsuarioGlobal()), selectedEvaluaciones);
            listadoPostulantes = dao.getListPostulantes(Integer.parseInt(ac.getUsuarioModuloBean().getCodUsuarioGlobal()), ac.isPerfil_administrador());

            visible_NuevoPostulante = false;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "NO se pudo registrar al postulante. El número de identificación ya existe.", "NO se pudo registrar al postulante. El número de identifacicón ya existe.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void action_verRespuestasIPVPDF() {
        if (selectedPostulante != null) {
            String context_Path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            LOGGER.debug("URL contruida preview: " + URL_PDF_Respuestas);
            URL_PDF_Respuestas = context_Path + "/Servlet_RespuestasIPVPostulante";
            URL_PDF_Respuestas += "?cod_postulante=" + selectedPostulante.getCod_postulante();
            LOGGER.debug("URL contruida post IPV: " + URL_PDF_Respuestas);

            visible_PDF_Respuesta = true;

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Selección de Postulante", "Debe seleccionar un Postulante para visualizarla en formato PDF.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }
    private boolean visible_PDF_RespuestaIPV = false;

    public boolean isVisible_PDF_RespuestaIPV() {
        return visible_PDF_RespuestaIPV;
    }

    public void setVisible_PDF_RespuestaIPV(boolean visible_PDF_RespuestaIPV) {
        this.visible_PDF_RespuestaIPV = visible_PDF_RespuestaIPV;
    }

    private String[] selectedEvaluaciones;

    public String[] getSelectedEvaluaciones() {
        return selectedEvaluaciones;
    }

    public void setSelectedEvaluaciones(String[] selectedEvaluaciones) {
        this.selectedEvaluaciones = selectedEvaluaciones;
    }

    public void onAreaChange() {
        Dao_Reclutamiento dao = new Dao_Reclutamiento();
        LOGGER.debug("cod area seleccionada: " + postulante.getArea().getCodAreaEmpresa());
        if (postulante.getArea().getCodAreaEmpresa() == 0) {

            listCargos = new ArrayList<>();
        } else {

            listCargos = dao.getListCargos(postulante.getArea().getCodAreaEmpresa());

        }
        postulante.setCargo(new Cargos_Bean());

    }

    private List<Cargos_Bean> listCargos = new ArrayList<>();
    private List<AreasEmpresa_Bean> listAreas = new ArrayList<>();

    public List<Cargos_Bean> getListCargos() {
        return listCargos;
    }

    public void setListCargos(List<Cargos_Bean> listCargos) {
        this.listCargos = listCargos;
    }

    public List<AreasEmpresa_Bean> getListAreas() {
        return listAreas;
    }

    public void setListAreas(List<AreasEmpresa_Bean> listAreas) {
        this.listAreas = listAreas;
    }

    private String base64Str;

    public String getBase64Str() {
        return base64Str;
    }

    public void setBase64Str(String base64Str) {
        this.base64Str = base64Str;
    }

    private LineChartModel lineModel;

    public LineChartModel getLineModel() {
        return lineModel;
    }
    private LineChartModel lineModelExportImage;

    public LineChartModel getLineModelExportImage() {
        return lineModelExportImage;
    }

    public String getCargarLineModel() {
        LOGGER.debug("postulante_ " + selectedPostulante.getCod_postulante());
        createLineModel();
        createLineModelExportImage();
        return null;
    }

    public void createLineModel() {
        lineModel = initCategoryModel();
        System.out.println("fin init model");
        lineModel.setTitle("Resultado Evaluación IPV");
        lineModel.setLegendPosition("ne");
        lineModel.setShowPointLabels(true);
        lineModel.setAnimate(true);
        lineModel.setMouseoverHighlight(true);
        
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
        yAxis.setTickInterval("1");
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Escalas"));
        yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("Valores");

        yAxis.setMin(0);
        yAxis.setMax(10);
        System.out.println("result model "+lineModel.getSeries());
    }
    
     public void createLineModelExportImage() {
        lineModelExportImage = initCategoryModel();

        lineModelExportImage.setTitle("Resultado Evaluación IPV");
        
        Axis yAxis = lineModelExportImage.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
        yAxis.setTickInterval("1");
        lineModelExportImage.getAxes().put(AxisType.X, new CategoryAxis("Escalas"));
        yAxis = lineModelExportImage.getAxis(AxisType.Y);
        yAxis.setLabel("Valores");

        yAxis.setMin(0);
        yAxis.setMax(10);
        lineModelExportImage.getSeries().get(0).setLabel(null);
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();

        ChartSeries serie_evaluacion = new ChartSeries();
        serie_evaluacion.setLabel("Evaluación");
        
        Dao_Reclutamiento dao = new Dao_Reclutamiento();
        ControllerAccesoSistema ac = (ControllerAccesoSistema) Util.getSessionBean("controllerAccesoSistema");
        LOGGER.info("Usuario: " + ac.getUsuarioModuloBean().getCodUsuarioGlobal());

        List<Tabulacion1Bean> detalle = dao.getListDetalleEvaluacion(selectedPostulante.getCod_postulante(), 2);
        for (Tabulacion1Bean tabulacion1Bean : detalle) {
            serie_evaluacion.set(tabulacion1Bean.getNombre(), tabulacion1Bean.getValor());
        }
        model.addSeries(serie_evaluacion);
        System.out.println("model completado "+model.getSeries()+"  "+detalle.size());
        return model;
    }

    public void submittedBase64Str() {
        // You probably want to have a more comprehensive check here. 
        // In this example I only use a simple check
        System.out.println("click submitted");
        if (base64Str.split(",").length > 1) {
            System.out.println("base64 is true");
            String encoded = base64Str.split(",")[1];
            byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(encoded.getBytes());
            // Write to a .png file
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String conextPath = (String) servletContext.getRealPath("/img");
            
            LOGGER.error("Write to a .png file -- context : " + conextPath);
            try {
                if(selectedPostulante==null){
                    selectedPostulante=new PostulanteBean();
                }
                File imageFile = new File(conextPath+"\\chart_"+selectedPostulante.getCod_postulante()+".png");
                if (imageFile.exists()) {
                    imageFile.delete();
                    imageFile = new File(conextPath+"\\chart_"+selectedPostulante.getCod_postulante()+".png");
                }
                RenderedImage renderedImage = ImageIO.read(new ByteArrayInputStream(decoded));
                System.out.println("test " + imageFile.getAbsolutePath());
                ImageIO.write(renderedImage, "png", imageFile); // use a proper path & file name here.
            } catch (IOException e) {
                LOGGER.debug("Excepcion producida " + e.getMessage());
            }
        }

    }

    private void createLineModelsInit() {
        lineModel = initLinearModel();
        LOGGER.debug("Init linear model");
        lineModel.setTitle("Linear Chart");
        lineModel.setLegendPosition("e");
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);

        yAxis.setMin(0);
        yAxis.setMax(10);
        
        lineModelExportImage = initLinearModel();
         LOGGER.debug("Init linear model export");
       
        lineModelExportImage.setTitle("Linear Chart");
        lineModelExportImage.setLegendPosition("e");
        Axis yAxis2 = lineModelExportImage.getAxis(AxisType.Y);
        yAxis2.setMin(0);
        yAxis2.setMax(10);

        yAxis2.setMin(0);
        yAxis2.setMax(10);
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");

        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }
}
