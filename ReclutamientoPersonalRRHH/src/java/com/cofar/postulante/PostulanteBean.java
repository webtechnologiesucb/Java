/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.cofar.postulante;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Jaime Chura
 * @version BACO 2.0 date 03-08-2016 time 04:54:30 PM
 */
public class PostulanteBean {

    public PostulanteBean() {
       
    }

    
    
    private String nombres;
    private String ap_paterno;
    private String ap_materno;
    private String numid;
    private Date fecha_nac=new Date();
    private String sexo;
    private String observaciones;

    
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getAp_paterno() {
        return ap_paterno;
    }

    public void setAp_paterno(String ap_paterno) {
        this.ap_paterno = ap_paterno;
    }

    public String getAp_materno() {
        return ap_materno;
    }

    public void setAp_materno(String ap_materno) {
        this.ap_materno = ap_materno;
    }

    public String getNumid() {
        return numid;
    }

    public void setNumid(String numid) {
        this.numid = numid;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    private int cod_postulante;

    public int getCod_postulante() {
        return cod_postulante;
    }

    public void setCod_postulante(int cod_postulante) {
        this.cod_postulante = cod_postulante;
    }

    private Date fecha_registro;
    private int cod_estado;
    private String nombre_usuario;
    private String password;

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public int getCod_estado() {
        return cod_estado;
    }

    public void setCod_estado(int cod_estado) {
        this.cod_estado = cod_estado;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private String nombre_estado;

    public String getNombre_estado() {
        return nombre_estado;
    }

    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }

    private int cod_tipoid;

    public int getCod_tipoid() {
        return cod_tipoid;
    }

    public void setCod_tipoid(int cod_tipoid) {
        this.cod_tipoid = cod_tipoid;
    }
    private int cod_estadocivil;

    public int getCod_estadocivil() {
        return cod_estadocivil;
    }

    public void setCod_estadocivil(int cod_estadocivil) {
        this.cod_estadocivil = cod_estadocivil;
    }
    private String ap_esposo;

    public String getAp_esposo() {
        return ap_esposo;
    }

    public void setAp_esposo(String ap_esposo) {
        this.ap_esposo = ap_esposo;
    }
    private int cod_paisnacimiento;

    public int getCod_paisnacimiento() {
        return cod_paisnacimiento;
    }

    public void setCod_paisnacimiento(int cod_paisnacimiento) {
        this.cod_paisnacimiento = cod_paisnacimiento;
    }

    private String direccionDomicilio;

    public String getDireccionDomicilio() {
        return direccionDomicilio;
    }

    public void setDireccionDomicilio(String direccionDomicilio) {
        this.direccionDomicilio = direccionDomicilio;
    }

    private String numTelefono;
    private String numCelular;
    private String fax;
    private String email;

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Cargos_Bean cargo = new Cargos_Bean();
    private AreasEmpresa_Bean area = new AreasEmpresa_Bean();

    public Cargos_Bean getCargo() {
        return cargo;
    }

    public void setCargo(Cargos_Bean cargo) {
        this.cargo = cargo;
    }

    public AreasEmpresa_Bean getArea() {
        return area;
    }

    public void setArea(AreasEmpresa_Bean area) {
        this.area = area;
    }

    private Estado_Bean estado_cps = new Estado_Bean();
    private Estado_Bean estado_ipv = new Estado_Bean();

    public Estado_Bean getEstado_cps() {
        return estado_cps;
    }

    public void setEstado_cps(Estado_Bean estado_cps) {
        this.estado_cps = estado_cps;
    }

    public Estado_Bean getEstado_ipv() {
        return estado_ipv;
    }

    public void setEstado_ipv(Estado_Bean estado_ipv) {
        this.estado_ipv = estado_ipv;
    }

}
