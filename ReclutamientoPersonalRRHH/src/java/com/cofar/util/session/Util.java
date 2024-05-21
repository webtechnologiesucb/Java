package com.cofar.util.session;


import java.sql.Date;


import java.util.Map;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jaime Chura
 * @version BACO 2.0 date 06-04-2015 time 06:25:31 PM
 */
public class Util {

    public Util() {
    }

    /**
     * metodo que nos retorna un objeto que se encuentra en session
     *
     * @param namebean es el nombre del objeto que queremos que nos retorne
     * @return
     */
    public static Object getSessionBean(String namebean) {
        Map map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return map.get(namebean);
    }

    /**
     * metodo que nos retorna un objeto que se encuentra en session
     *
     * @param nameobjectsession es el nombre del objeto que queremos que nos
     * retorne
     */

    /**
     * metodo que nos retorna un objeto que se encuentra en session
     *
     * @param nameobjectsession es el nombre del objeto que queremos que nos
     * retorne
     * @return
     */
    public static Object getSession(String nameobjectsession) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getSession().getAttribute(nameobjectsession);
    }

    /**
     * metodo que nos retorna un String que se encuentra en session
     *
     * @param nameobjectsession es el nombre del objeto que queremos que nos
     * retorne
     */

    /**
     * metodo que nos retorna un String que se encuentra en session
     *
     * @param name
     * @return
     */
    public static String getParameter(String name) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getParameter(name);
    }

    public static Date converterStringDate(String fecha) {
        String values[] = fecha.split("/");
        if (values.length == 0) {
            throw new java.lang.IllegalArgumentException();
        }
        String format = values[2] + "-" + values[1] + "-" + values[0];
        Date date = java.sql.Date.valueOf(format);
        return date;

    }

    public static void setSession(String name, Object obj) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.setAttribute(name, obj);
    }

    public static void removeSession(String name) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().removeAttribute(name);
    }

    
}
