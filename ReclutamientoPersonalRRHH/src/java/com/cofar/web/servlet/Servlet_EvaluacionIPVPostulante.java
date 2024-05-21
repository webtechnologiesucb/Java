/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cofar.web.servlet;

import com.cofar.util.jdbc.ConnectionDataBase;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Jaime Chura
 */
@WebServlet(name = "Servlet_EvaluacionIPVPostulante", urlPatterns = {"/Servlet_EvaluacionIPVPostulante"})
public class Servlet_EvaluacionIPVPostulante extends HttpServlet {
 private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Servlet_EvaluacionIPVPostulante.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @SuppressWarnings({"CallToPrintStackTrace", "UseSpecificCatch", "empty-statement"})
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.debug("Bienvenido al metodo del servlet que genera el PDF (EvaluacionPostulante IPV)..");

        String cod_postulante = request.getParameter("cod_postulante");
        
        LOGGER.debug("param cod: " + cod_postulante);
         //parametros para el jasper
        String PREFIX = "/administrador/jasper_Postulante/"; //direccion donde se encuentran las compilaciones de los reportes		        
        String SUFFIX = ".jasper";//Sufijo de la extension de la compilacion de los reportes		        
        String type = "application/pdf"; // tipo de salida en formato pdf
        Map<String, Object> parameters = new HashMap();
        parameters.put("cod_postulante", Integer.parseInt(cod_postulante));
        parameters.put("SUBREPORT_DIR", getServletContext()
                .getRealPath(PREFIX)+File.separator);
       
        String name = "evaluacionPostulante_IPV";//nombre del recurso de generacion del reporte

        InputStream stream = getServletConfig().getServletContext().getResourceAsStream(PREFIX + name + SUFFIX);
        LOGGER.debug("stream: " + stream);
        if (stream == null) {
            throw new IllegalArgumentException("Nombre de reporte no conocido:" + name);
        } else {
            ConnectionDataBase conn = new ConnectionDataBase();
            Connection connection = conn.getConnectionDataBase_SartoriusServlet(request);
            try {

                LOGGER.debug(getServletContext()
                        .getRealPath("/img/logocofar.png"));
                FileInputStream input = new FileInputStream(getServletContext()
                        .getRealPath("/img/logocofar.png"));
                FileInputStream input_chart = new FileInputStream(getServletContext()
                        .getRealPath("/img/chart_"+cod_postulante+".png"));
                parameters.put("logo", input);
                parameters.put("chart", input_chart);
                LOGGER.debug("parameters: " + parameters);

                JasperPrint jasperPrint = JasperFillManager.fillReport(stream, parameters, connection);
                response.setContentType(type);
                JRExporter exporter = new JRPdfExporter();
                LOGGER.info("exporter definido.");
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exporter.exportReport();
                LOGGER.info("se generó el reporte en formato pdf.");
                connection.close();
            } catch (RuntimeException e) {
                
                throw e;
            } catch (Exception e) {
                
            } finally {
                try {
                    stream.close();
                   connection.close();
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                    LOGGER.error("exCEpcion PRODUCIDA");
                }
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
