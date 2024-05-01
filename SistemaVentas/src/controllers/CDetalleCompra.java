/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import core.Ex;
import core.JAbstractController;
import core.JAbstractModelBD;
import modelbd.Compra;
import modelbd.DetalleCompra;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Código Lite <https://codigolite.com>
 */
public class CDetalleCompra extends JAbstractController {

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

	public ArrayList<DetalleCompra> getRegistros(String[] columnas, Object[] cvl) {
		return this.getRegistros(new String[] {}, columnas, cvl);
	}

	public ArrayList<DetalleCompra> getRegistrosPorCompra(Integer pkCompra) {
		return this.getRegistros(new String[] {}, new String[] { "idcompra" }, new Object[] { pkCompra });
	}

	@Override
	public boolean guardarRegistro(JAbstractModelBD mdl) {
		DetalleCompra usr = (DetalleCompra) mdl;
		int gravado = 0;
		String campos = DetalleCompra.CAMPOS_NO_ID;

		Object[] valores = { usr.getIdcompra().getPrimaryKey(), usr.getIdProducto().getPrimaryKey(), usr.getCantidad(),
				usr.getImporte(), usr.getDescuento(), usr.getActivo() };

		gravado = this.agregarRegistroPs(DetalleCompra.nt, this.stringToArray(campos, ","), valores);

		if (gravado == 1) {
			usr.getIdProducto();
		}
		return false;
	}

	@Override
	public int actualizarRegistro(JAbstractModelBD mdl) {
		DetalleCompra usr = (DetalleCompra) mdl;
		int gravado = 0;
		String campos = DetalleCompra.CAMPOS_NO_ID;

		Object[] valores = { usr.getIdcompra().getPrimaryKey(), usr.getIdProducto().getPrimaryKey(), usr.getCantidad(),
				usr.getImporte(), usr.getDescuento(), usr.getActivo(), usr.getPrimaryKey() };

		gravado = this.actualizarRegistroPs(DetalleCompra.nt,
				this.adjuntarSimbolo(campos, ",", "?") + Ex.WHERE + DetalleCompra.COLUMNA_PK + " = ? ", valores);

		return gravado;
	}

	@Override
	public ArrayList<DetalleCompra> getRegistros(String[] campos, String[] columnaId, Object[] id) {
		ArrayList<DetalleCompra> rgs = new ArrayList<DetalleCompra>();
		try {

			if (id != null) {
				this.setNumRegistros(this.getNumeroRegistros(DetalleCompra.nt, DetalleCompra.COLUMNA_ACTIVO,
						DetalleCompra.COLUMNA_ACTIVO, id));
			} else {
				this.setNumRegistros(this.getNumeroRegistros(DetalleCompra.nt, DetalleCompra.COLUMNA_ACTIVO));
				if (getRs().isClosed()) {
					System.out.println("resultset esta cerrado...");
				}
			}
			setRs(this.getRegistros(DetalleCompra.nt, campos, columnaId, id));
			if (this.getNumRegistros() < getFinalPag()) {
				setFinalPag(this.getNumRegistros());
			}
			if (getFinalPag() > inicioPag) {
				inicioPag -= (getFinalPag() - inicioPag) - 1;
			}
			DetalleCompra us = null;
			CCompra controllerCompra = null;
			while (getRs().next()) {
				controllerCompra = new CCompra();
				us = new DetalleCompra();
				us.setPrimaryKey(getRs().getInt(1));
				us.setId(getRs().getInt(1));
				us.setIdcompra(controllerCompra.getRegistroPorPk(getRs().getInt(2)));
				us.setIdProducto(new CProducto().getRegistro(getRs().getInt(3)));
				us.setCantidad(getRs().getInt(4));
				us.setImporte(getRs().getDouble(5));
				us.setDescuento(getRs().getDouble(6));
				rgs.add(us);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rgs;
	}

	public boolean existeProductoEnCompra(Integer idCompra, Integer idProducto) {
		return this.existeDosPk(DetalleCompra.nt, "idcompra", idCompra, "idproducto", idProducto);
	}

}
