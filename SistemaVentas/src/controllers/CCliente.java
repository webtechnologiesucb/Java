package controllers;

import core.JAbstractController;
import core.JAbstractModelBD;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Código Lite - https://codigolite.com
 */
public class CCliente extends JAbstractController implements Serializable {

	@Override
	public ArrayList getRegistros() {
		throw new UnsupportedOperationException("No soportado");
	}

	@Override
	public ArrayList getRegistros(Object[] cvl) {
		throw new UnsupportedOperationException("No soportado");
	}

	@Override
	public JAbstractModelBD getRegistro() {
		throw new UnsupportedOperationException("No soportado");
	}

	@Override
	public JAbstractModelBD getRegistro(JAbstractModelBD mdl, boolean opcion) {
		throw new UnsupportedOperationException("No soportado");
	}

	@Override
	public JAbstractModelBD buscarRegistro(Object cvl) {
		throw new UnsupportedOperationException("No soportado");
	}

	@Override
	public boolean guardarRegistro(JAbstractModelBD mdl) {
		throw new UnsupportedOperationException("No soportado");
	}

	@Override
	public int actualizarRegistro(JAbstractModelBD mdl) {
		throw new UnsupportedOperationException("No soportado");
	}

	@Override
	public ArrayList getRegistros(String[] campos, String[] columnaId, Object[] id) {
		throw new UnsupportedOperationException("No soportado");
	}
}
