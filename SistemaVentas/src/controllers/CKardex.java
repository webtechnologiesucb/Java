package controllers;

import core.Ex;
import core.JAbstractController;
import core.JAbstractModelBD;
import modelbd.Kardex;
import util.Helper;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Código Lite - https://codigolite.com
 */
public class CKardex extends JAbstractController {

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

	public JAbstractModelBD buscarRegistro(Object[] cvl) {
		ArrayList<Kardex> registros = this.getRegistros(new String[] {}, new String[] { "documento", "idProductos" },
				cvl);
		if (registros.size() > 0) {
			return registros.get(0);
		}
		return null;
	}

	@Override
	public boolean guardarRegistro(JAbstractModelBD mdl) {
		Kardex usr = (Kardex) mdl;
		int gravado = 0;
		String campos = Kardex.NO_FULL_NOM_CAMPOS;

		Object[] valores = { usr.getProducto().getPrimaryKey(),
				Helper.getFechaFormateada(usr.getFecha(), Helper.ANIO_MES_DIA), usr.getDocumento(),
				usr.getNumeroDeDocumento(), usr.getEntrada(), usr.getSalida(), usr.getPrecio(), usr.getValor(),
				usr.getStockActual(), usr.getValorTotal(), usr.getActivo(), };

		gravado = this.agregarRegistroPs(Kardex.nt, this.stringToArray(campos, ","), valores);

		if (gravado == 1) {
			return true;
		}
		return false;
	}

	@Override
	public int actualizarRegistro(JAbstractModelBD mdl) {
		Kardex usr = (Kardex) mdl;
		int gravado = 0;
		String campos = Kardex.NO_FULL_NOM_CAMPOS;

		Object[] valores = { usr.getProducto().getPrimaryKey(),
				Helper.getFechaFormateada(usr.getFecha(), Helper.ANIO_MES_DIA), usr.getDocumento(),
				usr.getNumeroDeDocumento(), usr.getEntrada(), usr.getSalida(), usr.getPrecio(), usr.getValor(),
				usr.getStockActual(), usr.getValorTotal(), usr.getActivo(), usr.getPrimaryKey() };

		gravado = this.actualizarRegistroPs(Kardex.nt,
				this.adjuntarSimbolo(campos, ",", "?") + Ex.WHERE + Kardex.COLUMNA_PK + " = ? ", valores);

		return gravado;
	}

	@Override
	public ArrayList<Kardex> getRegistros(String[] campos, String[] columnaId, Object[] id) {
		ArrayList<Kardex> rgs = new ArrayList<Kardex>();
		try {

			if (id != null) {
				this.setNumRegistros(this.getNumeroRegistros(Kardex.nt, Kardex.COLUMNA_ACTIVO, Kardex.COLUMNA_ACTIVO,
						id));
			} else {
				this.setNumRegistros(this.getNumeroRegistros(Kardex.nt, Kardex.COLUMNA_ACTIVO));
				if (getRs().isClosed()) {
					System.out.println("resultset esta cerrado...");
				}
			}
			setRs(this.getRegistros(Kardex.nt, campos, columnaId, id));
			if (this.getNumRegistros() < getFinalPag()) {
				setFinalPag(this.getNumRegistros());
			}
			if (getFinalPag() > inicioPag) {
				inicioPag -= (getFinalPag() - inicioPag) - 1;
			}
			Kardex us = null;
			CProducto cpr = new CProducto();
			while (getRs().next()) {
				us = new Kardex();
				us.setPrimaryKey(getRs().getInt(1));
				us.setProducto(cpr.getRegistro(getRs().getInt(2)));
				us.setFecha(getRs().getDate(3));
				us.setDocumento(getRs().getString(4));
				us.setNumeroDeDocumento(getRs().getString(5));
				us.setEntrada(getRs().getInt(6));
				us.setSalida(getRs().getInt(7));
				us.setPrecio(getRs().getDouble(8));
				us.setValor(getRs().getDouble(9));
				us.setStockActual(getRs().getInt(10));
				us.setValorTotal(getRs().getDouble(11));
				us.setActivo(getRs().getInt(12));
				rgs.add(us);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rgs;
	}

}
