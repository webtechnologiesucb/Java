package controllers;

import core.JAbstractController;
import core.JAbstractModelBD;
import modelbd.Bitacora;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ferz Código Lite - https://codigolite.com
 */
public class CBitacora extends JAbstractController implements Serializable {

	@Override
	public ArrayList getRegistros() {
		throw new UnsupportedOperationException("No soportado");
	}

	@Override
	public ArrayList<Bitacora> getRegistros(Object[] cvl) {
		return getRegistros(new String[] {}, new String[] { "idusuario" }, cvl);
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
	public ArrayList<Bitacora> getRegistros(String[] campos, String[] columnaId, Object[] id) {
		ArrayList<Bitacora> rgs = new ArrayList<Bitacora>();
		try {
			if (id != null) {
				this.setNumRegistros(this.getNumeroRegistros(Bitacora.nt, Bitacora.COLUMNA_USER, Bitacora.COLUMNA_USER,
						id));
			} else {
				this.setNumRegistros(this.getNumeroRegistros(Bitacora.nt, Bitacora.COLUMNA_USER));
				if (getRs().isClosed()) {
					System.out.println("resultset esta cerrado...");
				}
			}
			setRs(this.getRegistros(Bitacora.nt, campos, columnaId, id));
			if (this.getNumRegistros() < getFinalPag()) {
				setFinalPag(this.getNumRegistros());
			}
			if (getFinalPag() > inicioPag) {
				inicioPag -= (getFinalPag() - inicioPag) - 1;
			}
			Bitacora bt = new Bitacora();
			while (getRs().next()) {
				bt.setIdbitacora(getRs().getLong(1));
				bt.setIdusuario(getRs().getInt(2));
				bt.setOs(getRs().getString(3));
				bt.setArquitectura(getRs().getString(4));
				bt.setVersion(getRs().getString(5));
				bt.setUsuario(getRs().getString(6));
				bt.setUltimaActividad(getRs().getLong(7));
				bt.setFechaActividad(getRs().getLong(8));
				rgs.add(bt);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rgs;
	}
}
