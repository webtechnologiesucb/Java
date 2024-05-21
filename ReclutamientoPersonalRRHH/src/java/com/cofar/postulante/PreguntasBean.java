/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */

package com.cofar.postulante;

/**
 *
 * @author Jaime Chura
 * @version BACO 2.0
 * date 02-08-2016
 * time 04:47:14 PM
 */
public class PreguntasBean {
    private int cod_pregunta;
    private String descripcion_pregunta;
    private int nro_pregunta;

    public int getCod_pregunta() {
        return cod_pregunta;
    }

    public void setCod_pregunta(int cod_pregunta) {
        this.cod_pregunta = cod_pregunta;
    }

    public String getDescripcion_pregunta() {
        return descripcion_pregunta;
    }

    public void setDescripcion_pregunta(String descripcion_pregunta) {
        this.descripcion_pregunta = descripcion_pregunta;
    }

    public int getNro_pregunta() {
        return nro_pregunta;
    }

    public void setNro_pregunta(int nro_pregunta) {
        this.nro_pregunta = nro_pregunta;
    }
    private int respuesta;

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }
    
}
