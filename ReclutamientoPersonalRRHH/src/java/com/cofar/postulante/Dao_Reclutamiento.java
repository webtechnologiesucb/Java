/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.cofar.postulante;

import com.cofar.administrador.Tabulacion1Bean;
import com.cofar.util.jdbc.ConnectionDataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Jaime Chura
 * @version BACO 2.0 date 02-08-2016 time 04:46:03 PM
 */
public class Dao_Reclutamiento {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Dao_Reclutamiento.class);

    public List<PreguntasBean> getListDetallePreguntas(int ini, int fin) {
        List<PreguntasBean> list_detallePreguntas = new ArrayList<>();
        ConnectionDataBase connectionDataBase = new ConnectionDataBase();
        Connection connection = connectionDataBase.getConnectionDataBase_Sartorius();

        try {
            try (Statement statement = connection.createStatement()) {
                String query = "SELECT [COD_PREGUNTA]"
                        + "      ,[DESCRIPCION_PREGUNTA]"
                        + "      ,[NRO_PREGUNTA]"
                        + "      ,[COD_ESTADO]"
                        + "      ,[FECHA_REGISTRO]"
                        + "  FROM [TBL_PREGUNTAS]"
                        + "  where cod_estado=1 and cod_evaluacion=1"
                        + "  and nro_pregunta between " + ini + " and " + fin
                        + "  order by nro_pregunta";
                LOGGER.info("query: " + query);
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    PreguntasBean detalle = new PreguntasBean();
                    detalle.setCod_pregunta(rs.getInt("COD_PREGUNTA"));
                    detalle.setDescripcion_pregunta(rs.getString("DESCRIPCION_PREGUNTA"));
                    detalle.setNro_pregunta(rs.getInt("NRO_PREGUNTA"));

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

    public List<PostulanteBean> getListPostulantes(int cod_usuario, boolean all) {
        List<PostulanteBean> list_postulantes = new ArrayList<>();
        ConnectionDataBase connectionDataBase = new ConnectionDataBase();
        Connection connection = connectionDataBase.getConnectionDataBase_Sartorius();

        try {
            try (Statement statement = connection.createStatement()) {
                String query = "exec [USP_GET_LISTA_POSTULANTES]";

                query += (all ? "NULL" : cod_usuario);

                LOGGER.info("query: " + query);
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    PostulanteBean postulante = new PostulanteBean();
                    postulante.setCod_postulante(rs.getInt("COD_POSTULANTE"));
                    postulante.setAp_esposo(rs.getString("AP_ESPOSO_POSTULANTE"));
                    postulante.setAp_materno(rs.getString("AP_MATERNO_POSTULANTE"));
                    postulante.setAp_paterno(rs.getString("AP_PATERNO_POSTULANTE"));
                    postulante.setCod_estado(rs.getInt("COD_ESTADO"));
                    postulante.setCod_estadocivil(rs.getInt("COD_ESTADOCIVIL"));
                    postulante.setCod_paisnacimiento(rs.getInt("COD_PAISNACIMIENTO"));
                    postulante.setCod_tipoid(rs.getInt("COD_TIPOID"));
                    postulante.setDireccionDomicilio(rs.getString("DIRECCION_POSTULANTE"));
                    postulante.setEmail(rs.getString("EMAIL_POSTULANTE"));
                    postulante.setFax(rs.getString("FAX_POSTULANTE"));
                    postulante.setFecha_nac(rs.getTimestamp("FECHA_NACIMIENTO_POSTULANTE"));
                    postulante.setFecha_registro(rs.getTimestamp("FECHA_REGISTRO"));
                    postulante.setNombre_usuario(rs.getString("USUARIO_POSTULANTE"));
                    postulante.setNombres(rs.getString("NOMBRE_POSTULANTE"));
                    postulante.setNumCelular(rs.getString("NUMCELULAR_POSTULANTE"));
                    postulante.setNumTelefono(rs.getString("NUMTELEFONO_POSTULANTE"));
                    postulante.setNumid(rs.getString("NUMID_POSTULANTE"));
                    postulante.setObservaciones(rs.getString("OBSERVACIONES_POSTULANTE"));
                    postulante.setPassword(rs.getString("PASSWORD_POSTULANTE"));
                    postulante.setSexo(rs.getString("GENERO_POSTULANTE"));
                    postulante.getCargo().setDescripcion_cargo(rs.getString("descripcion_cargo"));
                    postulante.getArea().setNombreAreaEmpresa(rs.getString("nombre_area_empresa"));
                    postulante.getEstado_cps().setCod_estado(rs.getInt("cod_estado_cps"));
                    postulante.getEstado_ipv().setCod_estado(rs.getInt("cod_estado_ipv"));
                    postulante.getEstado_cps().setNombre_estado(rs.getString("nombre_estado_cps"));
                    postulante.getEstado_ipv().setNombre_estado(rs.getString("nombre_estado_ipv"));
                    list_postulantes.add(postulante);
                }
                statement.close();
            }
            connection.close();
        } catch (SQLException ex) {

            LOGGER.error(ex.getMessage());
        }
        return list_postulantes;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean saveRespuestas(List<PreguntasBean> listPreguntas, int cod_postulante) {

        try {
            ConnectionDataBase conn = new ConnectionDataBase();
            try (Connection connection = conn.getConnectionDataBase_Sartorius()) {
                try (Statement st = connection.createStatement()) {
                    for (PreguntasBean listPregunta : listPreguntas) {
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
    public boolean existRespuestas(int cod_postulante, int cod_evaluacion) {
        boolean res = false;
        try {
            ConnectionDataBase conn = new ConnectionDataBase();
            try (Connection connection = conn.getConnectionDataBase_Sartorius()) {
                String consulta = "select * "
                        + " from tbl_respuestas resp inner join tbl_preguntas preg on preg.cod_pregunta=resp.cod_pregunta"
                        + " where resp.cod_estado=1 and cod_postulante=" + cod_postulante + " and preg.cod_evaluacion=" + cod_evaluacion;
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
    public boolean savePostulante(PostulanteBean postulante, int cod_usuario, String[] evaluaciones) {
        ConnectionDataBase conn = new ConnectionDataBase();
        Connection connection = conn.getConnectionDataBase_Sartorius();
        Statement st;
        try {

            String query = "INSERT INTO [TBL_POSTULANTE]"
                    + "           ([COD_POSTULANTE]"
                    + "           ,[NUMID_POSTULANTE]"
                    + "           ,[AP_PATERNO_POSTULANTE]"
                    + "           ,[USUARIO_POSTULANTE]"
                    + "           ,[PASSWORD_POSTULANTE]"
                    + "           ,[FECHA_REGISTRO]"
                    + "           ,[COD_ESTADO], [COD_PERSONAL]"
                    + "           ,[COD_AREA_EMPRESA], [CODIGO_CARGO]"
                    + ")"
                    + "     VALUES"
                    + "           (" + postulante.getCod_postulante() + ","
                    + "'" + postulante.getNumid() + "',"
                    + "'" + postulante.getAp_paterno() + "',"
                    + "'" + postulante.getNombre_usuario() + "',"
                    + "'" + postulante.getPassword() + "'"
                    + ",getdate(),1,"
                    + cod_usuario + "," + postulante.getArea().getCodAreaEmpresa()
                    + "," + postulante.getCargo().getCodigo_cargo() + ")";
            LOGGER.info("query: " + query);

            st = connection.createStatement();
            st.execute(query);
            for (String evaluacion : evaluaciones) {
                query = "insert into TBL_EVALUACION_POSTULANTE_HABILITADO"
                        + " (cod_postulante, cod_evaluacion, fecha_registro, cod_estado)"
                        + " values(" + postulante.getCod_postulante() + "," + evaluacion + ",getdate(),1)";
                LOGGER.info("--> query: " + query);
                st.execute(query);
            }
            st.close();
            connection.close();
            return true;

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return false;

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

    public List<Tabulacion1Bean> getListDetalleTabulacion1(int cod_postulante) {
        List<Tabulacion1Bean> list_detalle = new ArrayList<>();
        ConnectionDataBase connectionDataBase = new ConnectionDataBase();
        Connection connection = connectionDataBase.getConnectionDataBase_Sartorius_test1();

        try {
            try (Statement statement = connection.createStatement()) {
                String query = "exec [USP_GET_TABULACION1_POSTULANTE] " + cod_postulante;
                LOGGER.info("query: " + query);
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    Tabulacion1Bean detalle = new Tabulacion1Bean();
                    detalle.setNombre(rs.getString("nombre"));
                    detalle.setValor(rs.getInt("valor"));
                    detalle.setNumero(rs.getInt("numero"));
                    detalle.setNombre_largo(rs.getString("nombre_largo"));
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
    public boolean saveEvaluacionPostulante(List<Tabulacion1Bean> listEvaluacion, int cod_postulante, int cod_evaluacion) {

        try {
            ConnectionDataBase conn = new ConnectionDataBase();
            try (Connection connection = conn.getConnectionDataBase_Sartorius()) {
                try (Statement st = connection.createStatement()) {
                    for (Tabulacion1Bean eval : listEvaluacion) {
                        String query = "INSERT INTO [TBL_EVALUACION_POSTULANTE]"
                                + "           ([COD_POSTULANTE]"
                                + "           ,[NUMERO]"
                                + "           ,[ABREVIATURA]"
                                + "           ,[NOMBRE_LARGO]"
                                + "           ,[VALOR]"
                                + "           ,[FECHA_REGISTRO], cod_evaluacion, cod_Escala)"
                                + "     VALUES"
                                + "           (" + cod_postulante + "," + eval.getNumero()
                                + ",'" + eval.getNombre()
                                + "','" + eval.getNombre_largo() + "'," + eval.getValor() + ",getdate()," + cod_evaluacion + ","+eval.getCod_escala()+")";
                        LOGGER.info("query: " + query);
                        st.execute(query);
                    }
                    String updateEvaluacion = " update TBL_EVALUACION_POSTULANTE_HABILITADO set cod_estado=2 where cod_postulante=" + cod_postulante + " and cod_evaluacion=" + cod_evaluacion;
                    st.execute(updateEvaluacion);

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
    public int getLastRespuesta(int cod_postulante, int cod_evaluacion) {
        int max = 0;
        try {
            ConnectionDataBase conn = new ConnectionDataBase();
            try (Connection connection = conn.getConnectionDataBase_Sartorius()) {
                String consulta = " select isnull(MAX(preg.NRO_PREGUNTA),0) NRO_PREGUNTA"
                        + " from tbl_respuestas resp inner join TBL_PREGUNTAS preg"
                        + " on resp.COD_PREGUNTA=preg.COD_PREGUNTA"
                        + "  where resp.cod_estado=1 and cod_postulante=" + cod_postulante + " and preg.cod_Evaluacion=" + cod_evaluacion;
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

    public List<Pais_Bean> getListPaises() {
        List<Pais_Bean> list_paises = new ArrayList<>();
        ConnectionDataBase connectionDataBase = new ConnectionDataBase();
        Connection connection = connectionDataBase.getConnectionDataBase_Sartorius();

        try {
            try (Statement statement = connection.createStatement()) {
                String query = "SELECT [COD_PAIS]"
                        + "      ,[NOMBRE_PAIS]"
                        + "  FROM [PAISES] where cod_estado_registro=1 order by nombre_pais asc";
                LOGGER.info("query: " + query);
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    Pais_Bean pais = new Pais_Bean();
                    pais.setCod_pais(rs.getInt("COD_PAIS"));
                    pais.setDescripcion(rs.getString("NOMBRE_PAIS"));

                    list_paises.add(pais);
                }
                statement.close();
            }
            connection.close();
        } catch (SQLException ex) {

            LOGGER.error(ex.getMessage());
        }
        return list_paises;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updatePostulante(PostulanteBean postulante) {

        try {
            ConnectionDataBase conn = new ConnectionDataBase();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            try (Connection connection = conn.getConnectionDataBase_Sartorius()) {
                String query = "UPDATE [TBL_POSTULANTE]\n"
                        + "   SET [NUMID_POSTULANTE] = '" + postulante.getNumid() + "'"
                        + "      ,[NOMBRE_POSTULANTE] = '" + (postulante.getNombres() == null ? "" : postulante.getNombres().toUpperCase()) + "'"
                        + "      ,[AP_PATERNO_POSTULANTE] = '" + (postulante.getAp_paterno() == null ? "" : postulante.getAp_paterno().toUpperCase()) + "'"
                        + "      ,[AP_MATERNO_POSTULANTE] = '" + (postulante.getAp_materno() == null ? "" : postulante.getAp_materno().toUpperCase()) + "'"
                        + "      ,[FECHA_NACIMIENTO_POSTULANTE] = '" + sdf.format(postulante.getFecha_nac()) + "'"
                        + "      ,[GENERO_POSTULANTE] = '" + postulante.getSexo() + "'"
                        + "      ,[COD_ESTADO] = 2"
                        + "      ,[COD_TIPOID] = '" + postulante.getCod_tipoid() + "'"
                        + "      ,[COD_ESTADOCIVIL] = '" + postulante.getCod_estadocivil() + "'"
                        + "      ,[AP_ESPOSO_POSTULANTE] = '" + (postulante.getAp_esposo() == null ? "" : postulante.getAp_esposo().toUpperCase()) + "'"
                        + "      ,[COD_PAISNACIMIENTO] = '" + postulante.getCod_paisnacimiento() + "'"
                        + "      ,[DIRECCION_POSTULANTE] = '" + postulante.getDireccionDomicilio() + "'"
                        + "      ,[NUMTELEFONO_POSTULANTE] = '" + postulante.getNumTelefono() + "'"
                        + "      ,[NUMCELULAR_POSTULANTE] = '" + postulante.getNumCelular() + "'"
                        + "      ,[EMAIL_POSTULANTE] = '" + postulante.getEmail() + "'"
                        + "      ,[FAX_POSTULANTE] = '" + postulante.getFax() + "'"
                        + "      ,[FECHA_ACTUALIZACION] = getdate()"
                        + " WHERE cod_postulante="
                        + postulante.getCod_postulante();
                LOGGER.info("query: " + query);

                try (Statement st = connection.createStatement()) {
                    st.execute(query);
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
    public PostulanteBean getPostulante(int cod_postulante) {
        PostulanteBean postulante = new PostulanteBean();
        try {
            ConnectionDataBase conn = new ConnectionDataBase();
            try (Connection connection = conn.getConnectionDataBase_Sartorius()) {
                String consulta = "  SELECT [COD_POSTULANTE]"
                        + "      ,[NUMID_POSTULANTE]"
                        + "      ,[NOMBRE_POSTULANTE]"
                        + "      ,[AP_PATERNO_POSTULANTE]"
                        + "      ,[AP_MATERNO_POSTULANTE]"
                        + "      ,[USUARIO_POSTULANTE]"
                        + "      ,[PASSWORD_POSTULANTE]"
                        + "      ,[FECHA_NACIMIENTO_POSTULANTE]"
                        + "      ,[GENERO_POSTULANTE]"
                        + "      ,[FECHA_REGISTRO]"
                        + "      ,[COD_ESTADO]"
                        + "      ,[COD_PERSONAL]"
                        + "      ,[COD_TIPOID]"
                        + "      ,[COD_ESTADOCIVIL]"
                        + "      ,[AP_ESPOSO_POSTULANTE]"
                        + "      ,[COD_PAISNACIMIENTO]"
                        + "      ,[DIRECCION_POSTULANTE]"
                        + "      ,[NUMTELEFONO_POSTULANTE]"
                        + "      ,[NUMCELULAR_POSTULANTE]"
                        + "      ,[EMAIL_POSTULANTE]"
                        + "      ,[FAX_POSTULANTE]"
                        + "      ,[FECHA_ACTUALIZACION]"
                        + "      ,[OBSERVACIONES_POSTULANTE]"
                        + "  FROM [TBL_POSTULANTE] where cod_postulante= " + cod_postulante;
                LOGGER.info("query " + consulta);
                try (Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); ResultSet rs = st.executeQuery(consulta)) {
                    if (rs.next()) {
                        postulante.setCod_postulante(cod_postulante);
                        postulante.setAp_esposo(rs.getString("AP_ESPOSO_POSTULANTE"));
                        postulante.setAp_materno(rs.getString("AP_MATERNO_POSTULANTE"));
                        postulante.setAp_paterno(rs.getString("AP_PATERNO_POSTULANTE"));
                        postulante.setCod_estado(rs.getInt("COD_ESTADO"));
                        postulante.setCod_estadocivil(rs.getInt("COD_ESTADOCIVIL"));
                        postulante.setCod_paisnacimiento(rs.getInt("COD_PAISNACIMIENTO"));
                        postulante.setCod_tipoid(rs.getInt("COD_TIPOID"));
                        postulante.setDireccionDomicilio(rs.getString("DIRECCION_POSTULANTE"));
                        postulante.setEmail(rs.getString("EMAIL_POSTULANTE"));
                        postulante.setFax(rs.getString("FAX_POSTULANTE"));
                        postulante.setFecha_nac(rs.getTimestamp("FECHA_NACIMIENTO_POSTULANTE"));
                        postulante.setFecha_registro(rs.getTimestamp("FECHA_REGISTRO"));
                        postulante.setNombre_usuario(rs.getString("USUARIO_POSTULANTE"));
                        postulante.setNombres(rs.getString("NOMBRE_POSTULANTE"));
                        postulante.setNumCelular(rs.getString("NUMCELULAR_POSTULANTE"));
                        postulante.setNumTelefono(rs.getString("NUMTELEFONO_POSTULANTE"));
                        postulante.setNumid(rs.getString("NUMID_POSTULANTE"));
                        postulante.setObservaciones(rs.getString("OBSERVACIONES_POSTULANTE"));
                        postulante.setPassword(rs.getString("PASSWORD_POSTULANTE"));
                        postulante.setSexo(rs.getString("GENERO_POSTULANTE"));
                    }
                    rs.close();
                    st.close();
                }
                connection.close();
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return postulante;

    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean existPermisosAdministrador(int cod_personal) {
        boolean res = false;
        try {
            ConnectionDataBase conn = new ConnectionDataBase();
            try (Connection connection = conn.getConnectionDataBase_Sartorius()) {
                String consulta = "select * "
                        + " from TBL_PERSONAL_PERMISOS_RECLUTAMIENTO"
                        + " where cod_personal=" + cod_personal;
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

    public List<AreasEmpresa_Bean> getListaAreasEmpresa() {
        List<AreasEmpresa_Bean> list_AreasEmpresa = new ArrayList<>();
        ConnectionDataBase connectionDataBase = new ConnectionDataBase();
        Connection connection = connectionDataBase.getConnectionDataBase_Sartorius();

        try {
            try (Statement statement = connection.createStatement()) {
                String query = "select a.COD_AREA_EMPRESA,a.NOMBRE_AREA_EMPRESA  "
                        + " from AREAS_EMPRESA a  where a.COD_ESTADO_REGISTRO = 1"
                        + " order by a.NOMBRE_AREA_EMPRESA";
                LOGGER.info("query: " + query);
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    AreasEmpresa_Bean area = new AreasEmpresa_Bean();
                    area.setCodAreaEmpresa(rs.getInt("cod_area_empresa"));
                    area.setNombreAreaEmpresa(rs.getString("nombre_area_empresa"));
                    list_AreasEmpresa.add(area);
                }
                statement.close();
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Dao_Reclutamiento.class.getName()).log(Level.SEVERE, null, ex);
            LOGGER.error(ex.getMessage());
        }
        return list_AreasEmpresa;
    }

    public List<Cargos_Bean> getListCargos(int cod_area_empresa) {
        List<Cargos_Bean> list_cargos = new ArrayList<>();
        ConnectionDataBase connectionDataBase = new ConnectionDataBase();
        Connection connection = connectionDataBase.getConnectionDataBase_Sartorius();

        try {
            try (Statement statement = connection.createStatement()) {
                String query = " select c.codigo_cargo, c.descripcion_cargo "
                        + " from CARGOS_EN_EMPRESA cemp "
                        + " inner join CARGOS c on cemp.codigo_cargo=c.codigo_cargo "
                        + " where c.cod_Estado_registro=1 and cod_area_empresa=" + cod_area_empresa;
                LOGGER.info("query: " + query);
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    Cargos_Bean cargo = new Cargos_Bean();
                    cargo.setCodigo_cargo(rs.getInt("CODIGO_CARGO"));
                    cargo.setDescripcion_cargo(rs.getString("DESCRIPCION_CARGO"));

                    list_cargos.add(cargo);
                }
                statement.close();
            }
            connection.close();
        } catch (SQLException ex) {

            LOGGER.error(ex.getMessage());
        }
        return list_cargos;
    }

    public int getCodEvaluacionActual(int cod_postulante) {
        int cod_evaluacion = 0;
        ConnectionDataBase connectionDataBase = new ConnectionDataBase();
        Connection connection = connectionDataBase.getConnectionDataBase_Sartorius();

        try {
            try (Statement statement = connection.createStatement()) {
                String query = "SELECT top 1 [COD_EVALUACION]"
                        + "  FROM [TBL_EVALUACION_POSTULANTE_HABILITADO] where cod_estado in(1,3) and cod_postulante=" + cod_postulante + " order by cod_evaluacion asc";
                LOGGER.info("query: " + query);
                // los estados validos para continuar con la evaluación actual son 1. pendiente y 3. en proceso
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {

                    cod_evaluacion = (rs.getInt("COD_EVALUACION"));
                }
                statement.close();
            }
            connection.close();
        } catch (SQLException ex) {

            LOGGER.error(ex.getMessage());
        }
        return cod_evaluacion;
    }

    public List<Tabulacion1Bean> getListDetalleEvaluacion(int cod_postulante, int cod_evaluacion) {
        List<Tabulacion1Bean> list_detalle = new ArrayList<>();
        ConnectionDataBase connectionDataBase = new ConnectionDataBase();
        Connection connection = connectionDataBase.getConnectionDataBase_Sartorius();

        try {
            try (Statement statement = connection.createStatement()) {
                String query = "exec [USP_GET_EVALUACION_POSTULANTE] " + cod_postulante + ", " + cod_evaluacion;
                LOGGER.info("query: " + query);
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    System.out.println("valor "+rs.getString("nombre"));
                    Tabulacion1Bean detalle = new Tabulacion1Bean();
                    detalle.setNombre(rs.getString("nombre"));
                    detalle.setValor(rs.getInt("valor"));
                    detalle.setNumero(rs.getInt("numero"));
                    detalle.setNombre_largo(rs.getString("nombre_largo"));
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
    public boolean existNumeroIdentificacion(String numero_identificacion) {
        boolean res = false;
        try {
            ConnectionDataBase conn = new ConnectionDataBase();
            try (Connection connection = conn.getConnectionDataBase_Sartorius()) {
                String consulta = "select * "
                        + " from TBL_POSTULANTE"
                        + " where numid_postulante='" + numero_identificacion+"'";
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
    public boolean updateInicioEvaluacionPostulante(int cod_postulante, int cod_evaluacion) {
        ConnectionDataBase conn = new ConnectionDataBase();
        Connection connection = conn.getConnectionDataBase_Sartorius();
        Statement st;
        try {

            String query = "UPDATE TBL_EVALUACION_POSTULANTE_HABILITADO set cod_estado=3 where cod_postulante="+cod_postulante+" and cod_Evaluacion="+cod_evaluacion;
            LOGGER.info("query: " + query);

            st = connection.createStatement();
            st.execute(query);
            
            st.close();
            connection.close();
            return true;

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

}
