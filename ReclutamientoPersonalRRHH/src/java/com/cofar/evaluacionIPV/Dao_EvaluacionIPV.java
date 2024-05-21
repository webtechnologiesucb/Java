/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.cofar.evaluacionIPV;

import com.cofar.postulante.*;
import com.cofar.administrador.Tabulacion1Bean;
import com.cofar.util.jdbc.ConnectionDataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Jaime Chura
 * @version BACO 2.0 date 02-08-2016 time 04:46:03 PM
 */
public class Dao_EvaluacionIPV {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Dao_EvaluacionIPV.class);

    public List<PreguntasRespuestasBean> getListDetallePreguntas(int ini, int fin) {
        List<PreguntasRespuestasBean> list_detallePreguntas = new ArrayList<>();
        ConnectionDataBase connectionDataBase = new ConnectionDataBase();
        Connection connection = connectionDataBase.getConnectionDataBase_Sartorius();

        try {
            try (Statement statement = connection.createStatement()) {
                String query = "SELECT PREG.COD_PREGUNTA, PREG.NRO_PREGUNTA,"
                        + " PREG.DESCRIPCION_PREGUNTA "
                        + " ,IPV_1.DESCRIPCION_RESPUESTA DESCRIPCION_RESPUESTA_A"
                        + " ,IPV_2.DESCRIPCION_RESPUESTA DESCRIPCION_RESPUESTA_B"
                        + " ,IPV_3.DESCRIPCION_RESPUESTA DESCRIPCION_RESPUESTA_C"
                        + " FROM TBL_PREGUNTAS PREG"
                        + " left JOIN TBL_RESPUESTAS_IPV IPV_1 ON PREG.COD_PREGUNTA=IPV_1.COD_PREGUNTA and IPV_1.nro_respuesta=1 and IPV_1.COD_ESTADO=1"
                        + " left JOIN TBL_RESPUESTAS_IPV IPV_2 ON PREG.COD_PREGUNTA=IPV_2.COD_PREGUNTA and IPV_2.nro_respuesta=2 and IPV_2.COD_ESTADO=1"
                        + " left JOIN TBL_RESPUESTAS_IPV IPV_3 ON PREG.COD_PREGUNTA=IPV_3.COD_PREGUNTA and IPV_3.nro_respuesta=3 and IPV_3.COD_ESTADO=1"
                        + " WHERE PREG.COD_EVALUACION=2  and PREG.COD_ESTADO=1"
                         + "  and preg.nro_pregunta between " + ini + " and " + fin
                        + " ORDER BY PREG.nro_PREGUNTA";
                LOGGER.info("query: " + query);
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    PreguntasRespuestasBean detalle = new PreguntasRespuestasBean();
                    detalle.setCod_pregunta(rs.getInt("COD_PREGUNTA"));
                    detalle.setDescripcion_pregunta(rs.getString("DESCRIPCION_PREGUNTA"));
                    detalle.setNro_pregunta(rs.getInt("NRO_PREGUNTA"));
                    detalle.setDescripcion_respuestaA(rs.getString("DESCRIPCION_RESPUESTA_A"));
                    detalle.setDescripcion_respuestaB(rs.getString("DESCRIPCION_RESPUESTA_B"));
                    detalle.setDescripcion_respuestaC(rs.getString("DESCRIPCION_RESPUESTA_C"));
                    list_detallePreguntas.add(detalle);
                }
                statement.close();
            }
            connection.close();
        } catch (SQLException ex) {

            LOGGER.error(ex.getMessage());
        }
        return list_detallePreguntas;
    }

  
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean saveRespuestas(List<PreguntasRespuestasBean> listPreguntas, int cod_postulante) {

        try {
            ConnectionDataBase conn = new ConnectionDataBase();
            try (Connection connection = conn.getConnectionDataBase_Sartorius()) {
                try (Statement st = connection.createStatement()) {
                    for (PreguntasRespuestasBean listPregunta : listPreguntas) {
                        String query = "INSERT INTO [TBL_RESPUESTAS]"
                                + "           ([COD_POSTULANTE]"
                                + "           ,[COD_PREGUNTA]"
                                + "           ,[RESPUESTA]"
                                + "           ,[DESCRIPCION_RESPUESTA]"
                                + "           ,[COD_ESTADO]"
                                + "           ,[FECHA_REGISTRO])"
                                + "     VALUES"
                                + "           (" + cod_postulante + "," + listPregunta.getCod_pregunta()
                                + "," + listPregunta.getRespuesta()
                                + ",'',1,getdate())";
                        LOGGER.info("query: " + query);
                        st.execute(query);
                    }

                    st.close();
                }

                connection.close();
                return true;
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean existRespuestas(int cod_postulante) {
        boolean res = false;
        try {
            ConnectionDataBase conn = new ConnectionDataBase();
            try (Connection connection = conn.getConnectionDataBase_Sartorius()) {
                String consulta = "select * "
                        + "from tbl_respuestas "
                        + "where cod_estado=1 and cod_postulante=" + cod_postulante;
                LOGGER.info("query " + consulta);
                try (Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); ResultSet rs = st.executeQuery(consulta)) {
                    if (rs.next()) {
                        res = true;
                    }
                    rs.close();
                    st.close();
                }
                connection.close();
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return res;

    }

  
    @SuppressWarnings("CallToPrintStackTrace")
    public int getCodPostulante() {
        int codigo = 0;
        try {
            ConnectionDataBase conn = new ConnectionDataBase();
            try (Connection connection = conn.getConnectionDataBase_Sartorius()) {
                String consulta = "  select (isnull(max(cod_postulante),0)+1) cod_postulante  "
                        + " from tbl_postulante  ";
                LOGGER.info("query " + consulta);
                try (Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); ResultSet rs = st.executeQuery(consulta)) {
                    if (rs.next()) {
                        codigo = rs.getInt("cod_postulante");
                    }
                    rs.close();
                    st.close();
                }
                connection.close();
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return codigo;

    }

    public List<PreguntasBean> getListDetalleRespuestas(int cod_postulante) {
        List<PreguntasBean> list_detallePreguntas = new ArrayList<>();
        ConnectionDataBase connectionDataBase = new ConnectionDataBase();
        Connection connection = connectionDataBase.getConnectionDataBase_Sartorius();

        try {
            try (Statement statement = connection.createStatement()) {
                String query = "SELECT [COD_PREGUNTA]"
                        + "      ,[RESPUESTA]"
                        + "  FROM [TBL_RESPUESTAS] where cod_postulante=" + cod_postulante + " order by cod_pregunta";
                LOGGER.info("query: " + query);
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    PreguntasBean detalle = new PreguntasBean();
                    detalle.setCod_pregunta(rs.getInt("COD_PREGUNTA"));
                    detalle.setRespuesta(rs.getInt("RESPUESTA"));

                    list_detallePreguntas.add(detalle);
                }
                statement.close();
            }
            connection.close();
        } catch (SQLException ex) {

            LOGGER.error(ex.getMessage());
        }
        return list_detallePreguntas;
    }

    public List<Tabulacion1Bean> getListDetalleTabulacionIPV(int cod_postulante) {
        List<Tabulacion1Bean> list_detalle = new ArrayList<>();
        ConnectionDataBase connectionDataBase = new ConnectionDataBase();
        Connection connection = connectionDataBase.getConnectionDataBase_Sartorius_test1();

        try {
            try (Statement statement = connection.createStatement()) {
                String query = "exec [USP_GET_TABULACION_IPV_POSTULANTE] " + cod_postulante;
                LOGGER.info("query: " + query);
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    Tabulacion1Bean detalle = new Tabulacion1Bean();
                    detalle.setNombre(rs.getString("nombre"));
                    detalle.setValor(rs.getInt("valor"));
                    detalle.setNumero(rs.getInt("numero"));
                    detalle.setCod_escala(rs.getInt("cod_escala"));
                    list_detalle.add(detalle);
                }
                statement.close();
            }
            connection.close();
        } catch (SQLException ex) {

            LOGGER.error(ex.getMessage());
        }
        return list_detalle;
    }

    

    @SuppressWarnings("CallToPrintStackTrace")
    public int getLastRespuesta(int cod_postulante) {
        int max = 0;
        try {
            ConnectionDataBase conn = new ConnectionDataBase();
            try (Connection connection = conn.getConnectionDataBase_Sartorius()) {
                String consulta = " select MAX(preg.NRO_PREGUNTA) NRO_PREGUNTA"
                        + " from tbl_respuestas resp inner join TBL_PREGUNTAS preg"
                        + " on resp.COD_PREGUNTA=preg.COD_PREGUNTA"
                        + "  where resp.cod_estado=1 and cod_postulante=" + cod_postulante;
                LOGGER.info("query " + consulta);
                try (Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); ResultSet rs = st.executeQuery(consulta)) {
                    if (rs.next()) {
                        max = rs.getInt("nro_pregunta");
                    }
                    rs.close();
                    st.close();
                }
                connection.close();
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return max;

    }
}
