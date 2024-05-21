/*
 * TipoCliente.java
 * Created on 19 de febrero de 2008, 16:53
 */

package com.cofar.postulante;

/**
 *
 *  @author Wilmer Manzaneda Chavez
 *  @company COFAR
 */public class AreasEmpresa_Bean {
     
     /** Creates a new instance of TipoCliente */
     private int codAreaEmpresa;
     private String codFilial;
     private String nombreFilial;
     private String NivelArea;
     private String nombreAreaEmpresa;
     private String obsAreaEmpresa;
     private String codEstadoRegistro;
     private String nombreEstadoRegistro;
     private String nroOrden;
     private String areaRaiz;
     private Boolean checked;
 
     private boolean swSi;
     private boolean swNo;
     public int getCodAreaEmpresa() {
         return codAreaEmpresa;
     }
     
     public void setCodAreaEmpresa(int codAreaEmpresa) {
         this.codAreaEmpresa = codAreaEmpresa;
     }
     
     public String getCodFilial() {
         return codFilial;
     }
     
     public void setCodFilial(String codFilial) {
         this.codFilial = codFilial;
     }
     
     public String getNivelArea() {
         return NivelArea;
     }
     
     public void setNivelArea(String NivelArea) {
         this.NivelArea = NivelArea;
     }
     
     public String getNombreAreaEmpresa() {
         return nombreAreaEmpresa;
     }
     
     public void setNombreAreaEmpresa(String nombreAreaEmpresa) {
         this.nombreAreaEmpresa = nombreAreaEmpresa;
     }
     
     public String getObsAreaEmpresa() {
         return obsAreaEmpresa;
     }
     
     public void setObsAreaEmpresa(String obsAreaEmpresa) {
         this.obsAreaEmpresa = obsAreaEmpresa;
     }
     
     public String getCodEstadoRegistro() {
         return codEstadoRegistro;
     }
     
     public void setCodEstadoRegistro(String codEstadoRegistro) {
         this.codEstadoRegistro = codEstadoRegistro;
     }
     
     public Boolean getChecked() {
         return checked;
     }
     
     public void setChecked(Boolean checked) {
         this.checked = checked;
     }
     
     public String getNombreEstadoRegistro() {
         return nombreEstadoRegistro;
     }
     
     public void setNombreEstadoRegistro(String nombreEstadoRegistro) {
         this.nombreEstadoRegistro = nombreEstadoRegistro;
     }
     
     public String getNombreFilial() {
         return nombreFilial;
     }
     
     public void setNombreFilial(String nombreFilial) {
         this.nombreFilial = nombreFilial;
     }
     
    
     
     public String getNroOrden() {
         return nroOrden;
     }
     
     public void setNroOrden(String nroOrden) {
         this.nroOrden = nroOrden;
     }
     
     public String getAreaRaiz() {
         return areaRaiz;
     }
     
     public void setAreaRaiz(String areaRaiz) {
         this.areaRaiz = areaRaiz;
     }

    public boolean isSwSi() {
        return swSi;
    }

    public void setSwSi(boolean swSi) {
        this.swSi = swSi;
    }

    public boolean isSwNo() {
        return swNo;
    }

    public void setSwNo(boolean swNo) {
        this.swNo = swNo;
    }
     
     
 }
