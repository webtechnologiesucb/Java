package controllers;

import core.Ex;
import core.JAbstractController;
import core.JAbstractModelBD;
import modelbd.EncargadoAlmacen;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Código Lite <https://codigolite.com>
 */
public class CEncargadoAlmacen extends JAbstractController implements Serializable {

	private CUsuario controllerUsuario;
	private CAlmacen controllerAlmacen;

	@Override
	public ArrayList<EncargadoAlmacen> getRegistros() {
		return this.getRegistros(new String[] {}, null, null);
	}

	@Override
	public ArrayList getRegistros(Object[] cvl) {
		throw new UnsupportedOperationException("No soportado");
	}

	public ArrayList<EncargadoAlmacen> getRegistrosPorEncargado(Integer idu) {
		return this.getRegistros(new String[] {}, new String[] { "idusuario" }, new Object[] { idu });
	}

	/**
	 * 
	 * @param valores muestra registro de acuerdo a los campos idusuario e
	 *                idalmacen. primero idusuario, segundo idAlmacen
	 * @return arraylist de EncargadoAlmacen;
	 */
	public ArrayList<EncargadoAlmacen> getRegistroPorEA(Object[] valores) {
		return this.getRegistro(new String[] { "idusuario", "idalmacen" }, valores);
	}

	private ArrayList<EncargadoAlmacen> getRegistro(String[] campos, Object[] valores) {
		return this.getRegistros(new String[] {}, campos, valores);
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
		EncargadoAlmacen al = (EncargadoAlmacen) mdl;
		int gravado = 0;
		String campos = EncargadoAlmacen.CAMPOS_NO_ID;
		Object[] valores = { al.getUsuario().getPrimaryKey(), al.getAlmacen().getPrimaryKey(), al.getTipoCargo(),
				al.getActivo() };

		gravado = this.agregarRegistroPs(EncargadoAlmacen.nt, this.stringToArray(campos, ","), valores);

		if (gravado == 1)
			return true;

		return false;
	}

	@Override
	public int actualizarRegistro(JAbstractModelBD mdl) {
		EncargadoAlmacen al = (EncargadoAlmacen) mdl;
		int gravado = 0;
		String campos = EncargadoAlmacen.CAMPOS_NO_ID;
		Object[] valores = { al.getUsuario().getPrimaryKey(), al.getAlmacen().getPrimaryKey(), al.getTipoCargo(),
				al.getActivo(), al.getPrimaryKey() };

		gravado = this.actualizarRegistroPs(EncargadoAlmacen.nt,
				this.adjuntarSimbolo(campos, ",", "?") + Ex.WHERE + mdl.getCampoClavePrimaria() + " = ? ", valores);
		System.out.println("grabadoo " + gravado);
		return gravado;
	}

	public EncargadoAlmacen getRegistroPorPk(Integer id) {
		EncargadoAlmacen sm = null;
		try {
			setRs(this.selectPorPk(EncargadoAlmacen.nt, EncargadoAlmacen.COLUMNA_PK, id));
			controllerUsuario = null;
			controllerAlmacen = null;
			while (getRs().next()) {
				sm = new EncargadoAlmacen();
				sm.setUsuario(controllerUsuario.getRegistroPorPk(getRs().getInt(1)));
				sm.setAlmacen(controllerAlmacen.getRegistroPorPk(getRs().getInt(2)));
				sm.setTipoCargo(getRs().getString(3));
				sm.setActivo(getRs().getInt(4));

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return sm;
	}

	@Override
	public ArrayList<EncargadoAlmacen> getRegistros(String[] campos, String[] columnaId, Object[] id) {
		ArrayList<EncargadoAlmacen> rgs = new ArrayList<>();
		try {

			if (id != null) {
				this.setNumRegistros(this.getNumeroRegistros(EncargadoAlmacen.nt, EncargadoAlmacen.COLUMNA_ACTIVO,
						EncargadoAlmacen.COLUMNA_ACTIVO, id));
			} else {
				this.setNumRegistros(this.getNumeroRegistros(EncargadoAlmacen.nt, EncargadoAlmacen.COLUMNA_ACTIVO));
				if (getRs().isClosed()) {
					System.out.println("resultset esta cerrado...");
				}
			}
			setRs(this.getRegistros(EncargadoAlmacen.nt, campos, columnaId, id));
			if (this.getNumRegistros() < getFinalPag()) {
				setFinalPag(this.getNumRegistros());
			}
			if (getFinalPag() > inicioPag) {
				inicioPag -= (getFinalPag() - inicioPag) - 1;
			}
			EncargadoAlmacen sm = null;
			CUsuario controllerUsuario = null;
			CAlmacen controllerAlmacen = null;
			while (getRs().next()) {
				controllerUsuario = new CUsuario();
				controllerAlmacen = new CAlmacen();
				sm = new EncargadoAlmacen();
				sm.setPrimaryKey(getRs().getInt(1));
				sm.setUsuario(controllerUsuario.getRegistroPorPk(getRs().getInt(2)));
				sm.setAlmacen(controllerAlmacen.getRegistroPorPk(getRs().getInt(3)));
				sm.setTipoCargo(getRs().getString(4));
				sm.setActivo(getRs().getInt(5));
				rgs.add(sm);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rgs;
	}

}
