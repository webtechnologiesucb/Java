/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */

package com.cofar.evaluacionIPV;

/**
 *
 * @author Jaime Chura
 * @version BACO 2.0
 * date 24-08-2016
 * time 03:13:44 PM
 */
public class PreguntasRespuestasBean {
    private int cod_pregunta;
    private int nro_pregunta;
    private int respuesta;
    private String descripcion_pregunta;
    private String descripcion_respuestaA;
    private String descripcion_respuestaB;
    private String descripcion_respuestaC;

    public int getCod_pregunta() {
        return cod_pregunta;
    }

    public void setCod_pregunta(int cod_pregunta) {
        this.cod_pregunta = cod_pregunta;
    }

    public int getNro_pregunta() {
        return nro_pregunta;
    }

    public void setNro_pregunta(int nro_pregunta) {
        this.nro_pregunta = nro_pregunta;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public String getDescripcion_pregunta() {
        return descripcion_pregunta;
    }

    public void setDescripcion_pregunta(String descripcion_pregunta) {
        this.descripcion_pregunta = descripcion_pregunta;
    }

    public String getDescripcion_respuestaA() {
        return descripcion_respuestaA;
    }

    public void setDescripcion_respuestaA(String descripcion_respuestaA) {
        this.descripcion_respuestaA = descripcion_respuestaA;
    }

    public String getDescripcion_respuestaB() {
        return descripcion_respuestaB;
    }

    public void setDescripcion_respuestaB(String descripcion_respuestaB) {
        this.descripcion_respuestaB = descripcion_respuestaB;
    }

    public String getDescripcion_respuestaC() {
        return descripcion_respuestaC;
    }

    public void setDescripcion_respuestaC(String descripcion_respuestaC) {
        this.descripcion_respuestaC = descripcion_respuestaC;
    }
    
    
}
