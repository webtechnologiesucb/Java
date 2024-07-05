package com.programacion.principal;

import com.programacion.forms.MDIContainer;

public class Main {

	public static void main(String[] args) {
		try {
			MDIContainer frm = new MDIContainer();
			frm.setVisible(true);
		} catch (Exception e) {
			System.out.println("Excepcion: " + e.getMessage());
		} 
	}
}
