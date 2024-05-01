
package controllers;

import core.Ex;
import core.JAbstractController;
import core.JAbstractModelBD;
import modelbd.SimpleModelo;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ferz Código Lite - https://codigolite.com
 */
public class CSimpleModelo extends JAbstractController implements Serializable {

	public SimpleModelo sm;
	public static final int OCLASE = SimpleModelo.OCLASE;
	public static final int OMARCA = SimpleModelo.OMARCA;
	public static final int OMODELO = SimpleModelo.OMODELO;
	private int tipoModelo;

	public CSimpleModelo(int tipoModelo) {
		this.tipoModelo = tipoModelo;
		sm = new SimpleModelo(tipoModelo);

	}

	public CSimpleModelo() {
	}

	@Override
	public ArrayList<SimpleModelo> getRegistros() {
		return this.getRegistros(new String[] {}, null, null);
	}

	public ArrayList<SimpleModelo> getRegistros(String[] campos, String[] columnaId, Object[] id) {
		ArrayList<SimpleModelo> rgs = new ArrayList<SimpleModelo>();
		try {

			if (id != null) {
				this.setNumRegistros(this.getNumeroRegistros(sm.getNombreTabla(), sm.getCampoExistencial(),
						sm.getCampoExistencial(), id));
			} else {
				this.setNumRegistros(this.getNumeroRegistros(sm.getNombreTabla(), sm.getCampoExistencial()));
				if (getRs().isClosed()) {
					System.out.println("resultset esta cerrado...");
				}
			}
			setRs(this.getRegistros(sm.getNombreTabla(), campos, columnaId, id));
			if (this.getNumRegistros() < getFinalPag()) {
				setFinalPag(this.getNumRegistros());
			}
			if (getFinalPag() > inicioPag) {
				inicioPag -= (getFinalPag() - inicioPag) - 1;
			}
			while (getRs().next()) {
				sm = new SimpleModelo(tipoModelo);
				sm.setPrimaryKey(getRs().getInt(1));
				sm.setNombre(getRs().getString(2));
				sm.setActivo(getRs().getInt(3));
				rgs.add(sm);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rgs;

	}

	public ArrayList<SimpleModelo> getRegistros(String[] columna, Object[] valor) {
		return getRegistros(new String[] {}, columna, valor);
	}

	@Override
	public ArrayList getRegistros(Object[] cvl) {
		return this.getRegistros(new String[] {}, cvl != null ? new String[] { SimpleModelo.ACTIVO } : null, cvl);
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
		sm = (SimpleModelo) mdl;
		int gravado = 0;
		String campos = "nombre, activo";
		Object[] valores = { sm.getNombre(), sm.getActivo() };

		gravado = this.agregarRegistroPs(sm.getNombreTabla(), this.stringToArray(campos, ","), valores);

		if (gravado == 1)
			return true;

		return false;
	}

	@Override
	public int actualizarRegistro(JAbstractModelBD mdl) {
		sm = (SimpleModelo) mdl;
		int gravado = 0;
		String campos = "nombre, activo";
		Object[] valores = { sm.getNombre(), sm.getActivo(), sm.getPrimaryKey() };

		gravado = this.actualizarRegistroPs(sm.getNombreTabla(),
				this.adjuntarSimbolo(campos, ",", "?") + Ex.WHERE + mdl.getCampoClavePrimaria() + " = ? ", valores);
		System.out.println("grabadoo " + gravado);
		return gravado;
	}

}
