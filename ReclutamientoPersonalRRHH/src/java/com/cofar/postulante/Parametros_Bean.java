package com.cofar.postulante;

/**
 * @author Jaime
 *
 */

public class Parametros_Bean {
	private String user;
	private String password;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String mensajeValidacion;
	public String getMensajeValidacion() {
		return mensajeValidacion;
	}
	public void setMensajeValidacion(String mensajeValidacion) {
		this.mensajeValidacion = mensajeValidacion;
	}
	private String CLI_PASSWORD;
	public String getCLI_PASSWORD() {
		return CLI_PASSWORD;
	}
	public void setCLI_PASSWORD(String cLI_PASSWORD) {
		CLI_PASSWORD = cLI_PASSWORD;
	}
	private String CLI_NEWPASSWORD;
	private String CLI_NEWPASSWORDCONFIRM;
	public String getCLI_NEWPASSWORD() {
		return CLI_NEWPASSWORD;
	}
	public void setCLI_NEWPASSWORD(String cLI_NEWPASSWORD) {
		CLI_NEWPASSWORD = cLI_NEWPASSWORD;
	}
	public String getCLI_NEWPASSWORDCONFIRM() {
		return CLI_NEWPASSWORDCONFIRM;
	}
	public void setCLI_NEWPASSWORDCONFIRM(String cLI_NEWPASSWORDCONFIRM) {
		CLI_NEWPASSWORDCONFIRM = cLI_NEWPASSWORDCONFIRM;
	}
	
	private String mensaje_nueva_tarjeta;
	public String getMensaje_nueva_tarjeta() {
		return mensaje_nueva_tarjeta;
	}
	public void setMensaje_nueva_tarjeta(String mensaje_nueva_tarjeta) {
		this.mensaje_nueva_tarjeta = mensaje_nueva_tarjeta;
	}
	 private String texto_buscar;
	public String getTexto_buscar() {
		return texto_buscar;
	}
	public void setTexto_buscar(String texto_buscar) {
		this.texto_buscar = texto_buscar;
	}
	 
	private String mensaje_tabla;
	public String getMensaje_tabla() {
		return mensaje_tabla;
	}
	public void setMensaje_tabla(String mensaje_tabla) {
		this.mensaje_tabla = mensaje_tabla;
	}
	
	private int index_tab_active;
	
	public int getIndex_tab_active() {
		return index_tab_active;
	}
	
	public void setIndex_tab_active(int index_tab_active) {
		this.index_tab_active = index_tab_active;
	}
	
	
	
}
