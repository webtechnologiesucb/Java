/*
 * ManagedCliente.java
 
 * Created on 25 de febrero de 2008, 17:46
 *
 */
package com.cofar.util.session;

import com.cofar.postulante.Dao_Reclutamiento;
import com.cofar.util.jdbc.ConnectionDataBase;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Jaime Chura
 * @company COFAR
 */
@ManagedBean
@SessionScoped
public class ControllerAccesoSistema {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ControllerAccesoSistema.class);

    /**
     * Creates a new instance of ManagedCliente
     */
    private UsuarioModulos usuarioModuloBean = new UsuarioModulos();
    private String codigoModulo;
    private String mensajeErrorGlobal;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ControllerAccesoSistema() {
        clearUsuario();
    }

    public String clearUsuario() {
        UsuarioModulos cc = new UsuarioModulos();
        setUsuarioModuloBean(cc);
        return "";
    }

    public void action_VerficarUsuario() {

        clear_sessionBeans();
        switch (perfil_usuario) {
            case 2:
                accesoAdministrador();
                break;
            case 1:
                accesoPostulante();
                break;
        }

    }

    @SuppressWarnings("null")
    public void accesoAdministrador() {
        ConnectionDataBase conn = new ConnectionDataBase();
        Connection connection = null;
        String cod_modulo = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("cod_modulo_sistema");
        Dao_Reclutamiento dao = new Dao_Reclutamiento();
        try {
            connection = conn.getConnectionDataBase_Sartorius();
            Statement st;
            ResultSet rs;

            String sql = "exec [USP_GET_USER] " + cod_modulo + ",'" + usuarioModuloBean.getNombreUsuario() + "',";
            sql += " '" + usuarioModuloBean.getContraseniaUsuario() + "'";
            LOGGER.info("query:" + sql);
            st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);
            if (rs.next()) {
                UsuarioModulos bean = new UsuarioModulos();
                bean.setCodUsuarioGlobal(rs.getString("cod_personal"));
                bean.setNombrePilaUsuarioGlobal(rs.getString("nombre_pila"));
                bean.setApPaternoUsuarioGlobal(rs.getString("ap_paterno_personal"));
                bean.setApMaternoUsuarioGlobal(rs.getString("ap_materno_personal"));
                bean.setNombre_completo(rs.getString("nombres_personal"));

                bean.setCod_modulo(rs.getString("cod_modulo"));
                usuarioModuloBean = bean;
                String context_Path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

                perfil_administrador = dao.existPermisosAdministrador(Integer.parseInt(usuarioModuloBean.getCodUsuarioGlobal()));
                String path_redirect = context_Path + "/administrador/indexAdministrador.xhtml";
                try {
                    LOGGER.debug("redirect: " + path_redirect);
                    FacesContext.getCurrentInstance().getExternalContext().redirect(path_redirect);
                } catch (IOException ex1) {
                    LOGGER.fatal("Excepcion redirect - recurso inexistente." + ex1.getMessage());
                }
            } else {

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos Incorrectos", "El Ususario y/o contraseña son incorrectos.");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }

        } catch (SQLException e) {
            LOGGER.fatal("Excepcion producida SQL." + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerAccesoSistema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @SuppressWarnings("null")
    public void accesoPostulante() {
        Dao_Reclutamiento dao = new Dao_Reclutamiento();

        ConnectionDataBase conn = new ConnectionDataBase();
        Connection connection = null;
        try {
            connection = conn.getConnectionDataBase_Sartorius();
            Statement st;
            ResultSet rs;

            String sql = "select * from tbl_postulante"
                    + " where usuario_postulante='" + usuarioModuloBean.getNombreUsuario() + "'"
                    + " and password_postulante='" + usuarioModuloBean.getContraseniaUsuario() + "'";

            LOGGER.info("query:" + sql);
            st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);
            if (rs.next()) {
                UsuarioModulos bean = new UsuarioModulos();
                bean.setCodUsuarioGlobal(rs.getString("cod_postulante"));
                bean.setApPaternoUsuarioGlobal(rs.getString("ap_paterno_postulante"));
                bean.setApMaternoUsuarioGlobal(rs.getString("ap_materno_postulante"));
                bean.setNombre_completo(rs.getString("nombre_postulante"));
                bean.setCodEstado(rs.getInt("cod_estado"));
                usuarioModuloBean = bean;
                String context_Path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                String path_redirect = null;

                LOGGER.info("Usuario: " + usuarioModuloBean.getCodUsuarioGlobal());
                if (usuarioModuloBean.getCodEstado() == 1) {
                    path_redirect = context_Path + "/postulante/agregarPostulante.xhtml";

                } else {
                    cod_evaluacion = dao.getCodEvaluacionActual(Integer.parseInt(usuarioModuloBean.getCodUsuarioGlobal()));
                    dao.updateInicioEvaluacionPostulante(Integer.parseInt(usuarioModuloBean.getCodUsuarioGlobal()), cod_evaluacion);
                    switch (cod_evaluacion) {
                        case 0: {
                            LOGGER.debug("El postulante ya registró las respuestas anteriormente");
                            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sin Evaluaciones pendientes", "Su usuario ya completó las evaluaciones que le fueron asignadas. Consulte con el administrador del sistema.");
                            FacesContext.getCurrentInstance().addMessage(null, msg);
                            break;
                        }
                        case 1: {
                            path_redirect = context_Path + "/postulante/indexPostulante.xhtml";
                            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Evaluación Completada", "Su usuario no completó el CPS.");
                            FacesContext.getCurrentInstance().addMessage(null, msg);
                            break;
                        }
                        case 2: {
                            path_redirect = context_Path + "/postulante/indexEvaluacionIPV.xhtml";
                            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Evaluación Completada", "Su usuario no completó el IPV.");
                            FacesContext.getCurrentInstance().addMessage(null, msg);
                            break;
                        }
                        default:
                            break;
                    }
                }
                if (path_redirect != null) {
                    try {
                        LOGGER.debug("redirect postulante: " + path_redirect);
                        FacesContext.getCurrentInstance().getExternalContext().redirect(path_redirect);
                    } catch (IOException ex1) {
                        LOGGER.fatal("Excepcion redirect - recurso inexistente." + ex1.getMessage());
                    }
                }

            } else {
                LOGGER.debug("Datos Incorrectos");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos Incorrectos", "El Ususario y/o contraseña son incorrectos.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (SQLException e) {
            LOGGER.fatal("Excepcion SQL." + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerAccesoSistema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void redirect_homePage() {
        String context_Path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        String path_redirect = context_Path;

        try {
            clear_sessionBeans();
            LOGGER.debug("redirect: " + path_redirect);

            FacesContext.getCurrentInstance().getExternalContext().redirect(path_redirect);

        } catch (IOException ex1) {
            LOGGER.fatal("Excepcion redirect - recurso inexistente.");

        }
    }

    public UsuarioModulos getUsuarioModuloBean() {
        return usuarioModuloBean;
    }

    public void setUsuarioModuloBean(UsuarioModulos usuarioModuloBean) {
        this.usuarioModuloBean = usuarioModuloBean;
    }

    public String getCodigoModulo() {
        return codigoModulo;
    }

    public void setCodigoModulo(String codigoModulo) {
        this.codigoModulo = codigoModulo;
    }

    public String getMensajeErrorGlobal() {
        return mensajeErrorGlobal;
    }

    public void setMensajeErrorGlobal(String mensajeErrorGlobal) {
        this.mensajeErrorGlobal = mensajeErrorGlobal;
    }

    private boolean visible_cambiarAlmacen;

    public boolean isVisible_cambiarAlmacen() {
        return visible_cambiarAlmacen;
    }

    public void setVisible_cambiarAlmacen(boolean visible_cambiarAlmacen) {
        this.visible_cambiarAlmacen = visible_cambiarAlmacen;
    }

    public void action_CancelarCambiarAlmacen() {
        visible_cambiarAlmacen = false;
        LOGGER.debug("control cancelar valor: " + visible_cambiarAlmacen);
    }

    public void clear_sessionBeans() {
        Util.removeSession("controller_Administrador");
        Util.removeSession("controller_Reclutamiento");
        Util.removeSession("controller_EvaluacionIPV");
    }

    private int perfil_usuario = 0;

    public int getPerfil_usuario() {
        return perfil_usuario;
    }

    public void setPerfil_usuario(int perfil_usuario) {
        this.perfil_usuario = perfil_usuario;
    }

    public void onPerfilChange() {

        LOGGER.debug("cod perfil seleccionado: " + perfil_usuario);
        renderedEvaluaciones = (perfil_usuario == 1);
    }

    private boolean renderedEvaluaciones = false;

    public boolean isRenderedEvaluaciones() {
        return renderedEvaluaciones;
    }

    public void setRenderedEvaluaciones(boolean renderedEvaluaciones) {
        this.renderedEvaluaciones = renderedEvaluaciones;
    }

    private int cod_evaluacion;

    public int getCod_evaluacion() {
        return cod_evaluacion;
    }

    public void setCod_evaluacion(int cod_evaluacion) {
        this.cod_evaluacion = cod_evaluacion;
    }
    private boolean perfil_administrador = false;

    public boolean isPerfil_administrador() {
        return perfil_administrador;
    }

    public void setPerfil_administrador(boolean perfil_administrador) {
        this.perfil_administrador = perfil_administrador;
    }
}
