package controllers;

import core.Ex;
import core.JAbstractController;
import core.JAbstractModelBD;
import modelbd.Producto;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Código Lite - https://codigolite.com
 */
public class CProducto extends JAbstractController {

	private Producto prd;
	private InputStream itmp;
	
	public Producto getPrd() {
		return prd;
	}

	public void setPrd(Producto prd) {
		this.prd = prd;
	}

	public InputStream getItmp() {
		return itmp;
	}

	public void setItmp(InputStream itmp) {
		this.itmp = itmp;
	}

	@Override
	public ArrayList getRegistros() {
		return this.getRegistros(new String[] {}, null, null);
	}

	@Override
	public ArrayList getRegistros(Object[] cvl) {
		return this.getRegistros(new String[] {}, new String[] { Producto.COLUMNA_ACTIVO }, cvl);
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

	public Producto getRegistro(int id) {
		ArrayList<Producto> registros = this.getRegistros(new String[] { Producto.COLUMNA_PK }, new Object[] { id });
		return registros.get(0);
	}

	public Producto getRegistroPorCodigo(String id) {
		ArrayList<Producto> registros = this.getRegistros(new String[] { "codigo" }, new Object[] { id });
		return registros.get(0);
	}

	public ArrayList<Producto> getRegistros(String[] columnas, Object[] cvl) {
		return this.getRegistros(new String[] {}, columnas, cvl);
	}

	@Override
	public boolean guardarRegistro(JAbstractModelBD mdl) {
		Producto usr = (Producto) mdl;
		int gravado = 0;
		String campos = Producto.CAMPOS_NO_ID;

		Object[] valores = { usr.getCodigoBarras(), usr.getCodigo(), usr.getCodigoDelFabricante(), usr.getNombre(),
				usr.getCosto(), usr.getPrecioAlMayor(), usr.getPrecioAlMenor(), usr.getUtilidad(), usr.getAplicaIGV(),
				usr.getStockMinimo(), usr.getTipo(), usr.getIdMoneda(), usr.getImagen(null), usr.getIdClase(),
				usr.getIdMarca(), usr.getIdModelo(), usr.getUbicacion(), usr.getUnidadPrincipal(), usr.getPeso(),
				usr.getActivo(), };

		gravado = this.agregarRegistroPs(Producto.nt, this.stringToArray(campos, ","), valores);

		if (gravado == 1) {
			return true;
		}

		return false;
	}

	@Override
	public int actualizarRegistro(JAbstractModelBD mdl) {
		Producto usr = (Producto) mdl;
		int gravado = 0;
		String campos = Producto.CAMPOS_NOID_NOIMAGE;
		Object[] valores = { usr.getCodigoBarras(), usr.getCodigo(), usr.getCodigoDelFabricante(), usr.getNombre(),
				usr.getCosto(), usr.getPrecioAlMayor(), usr.getPrecioAlMenor(), usr.getUtilidad(), usr.getAplicaIGV(),
				usr.getStockMinimo(), usr.getTipo(), usr.getIdMoneda(), usr.getIdClase(), usr.getIdMarca(),
				usr.getIdModelo(), usr.getUbicacion(), usr.getUnidadPrincipal(), usr.getPeso(), usr.getActivo(),
				usr.getPrimaryKey() };

		if (usr.getImagen(null) != null) {
			campos = Producto.CAMPOS_NO_ID;
			valores = new Object[] { usr.getCodigoBarras(), usr.getCodigo(), usr.getCodigoDelFabricante(),
					usr.getNombre(), usr.getCosto(), usr.getPrecioAlMayor(), usr.getPrecioAlMenor(), usr.getUtilidad(),
					usr.getAplicaIGV(), usr.getStockMinimo(), usr.getTipo(), usr.getIdMoneda(), usr.getImagen(null),
					usr.getIdClase(), usr.getIdMarca(), usr.getIdModelo(), usr.getUbicacion(), usr.getUnidadPrincipal(),
					usr.getPeso(), usr.getActivo(), usr.getPrimaryKey() };
		}
		gravado = this.actualizarRegistroPs(Producto.nt,
				this.adjuntarSimbolo(campos, ",", "?") + Ex.WHERE + Producto.COLUMNA_PK + " = ? ", valores);

		return gravado;
	}

	@Override
	public ArrayList<Producto> getRegistros(String[] campos, String[] columnaId, Object[] id) {
		ArrayList<Producto> rgs = new ArrayList<Producto>();
		try {

			if (id != null) {
				this.setNumRegistros(this.getNumeroRegistros(Producto.nt, Producto.COLUMNA_ACTIVO,
						Producto.COLUMNA_ACTIVO, id));
			} else {
				this.setNumRegistros(this.getNumeroRegistros(Producto.nt, Producto.COLUMNA_ACTIVO));
				if (getRs().isClosed()) {
					System.out.println("resultset esta cerrado...");
				}
			}
			setRs(this.getRegistros(Producto.nt, campos, columnaId, id));
			if (this.getNumRegistros() < getFinalPag()) {
				setFinalPag(this.getNumRegistros());
			}
			if (getFinalPag() > inicioPag) {
				inicioPag -= (getFinalPag() - inicioPag) - 1;
			}
			Producto us = null;
			while (getRs().next()) {
				us = new Producto();
				us.setPrimaryKey(getRs().getInt(1));
				us.setIdProducto(getRs().getInt(1));
				us.setCodigoBarras(getRs().getString(2));
				us.setCodigo(getRs().getString(3));
				us.setCodigoDelFabricante(getRs().getString(4));
				us.setNombre(getRs().getString(5));
				us.setCosto(getRs().getDouble(6));
				us.setPrecioAlMayor(getRs().getDouble(7));
				us.setPrecioAlMenor(getRs().getDouble(8));
				us.setUtilidad(getRs().getInt(9));
				us.setAplicaIGV(getRs().getInt(10));
				us.setStockMinimo(getRs().getInt(11));
				us.setTipo(getRs().getString(12));
				us.setIdMoneda(getRs().getInt(13));
				// us.setImagen(rs.getBinaryStream(14));
				us.setIdClase(getRs().getInt(15));
				us.setIdMarca(getRs().getInt(16));
				us.setIdModelo(getRs().getInt(17));
				us.setUbicacion(getRs().getString(18));
				us.setUnidadPrincipal(getRs().getString(19));
				us.setPeso(getRs().getDouble(20));
				us.setActivo(getRs().getInt(21));
				rgs.add(us);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rgs;
	}

	public ImageIcon getFoto(Integer id) {
		InputStream archivo = this.getArchivo(Producto.nt, "imagen", Producto.COLUMNA_PK, id);
		setItmp(archivo);
		ImageIcon ii = null;
		if (archivo != null) {
			try {
				BufferedImage read = ImageIO.read(archivo);
				ii = new ImageIcon(read);
			} catch (IOException ex) {
				Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
			} catch (NullPointerException ex) {
				ii = null;
			}
		}
		return ii;
	}

	public boolean existeCodigo(String codigo) {
		return this.existe(Producto.nt, "codigo", codigo);
	}

	public boolean existeCodigoBarras(String codigoDeBarras) {
		return this.existe(Producto.nt, "cod_barras", codigoDeBarras);
	}

	public boolean existeCodigo(String codigo, Integer pk) {
		return this.existe(Producto.nt, "codigo", codigo, Producto.COLUMNA_PK, pk);
	}

	public boolean existeCodigoBarras(String codigoDeBarras, Integer pk) {
		return this.existe(Producto.nt, "cod_barras", codigoDeBarras, Producto.COLUMNA_PK, pk);
	}

	public int getTotalRegistros() {
		return this.getNumeroRegistros(Producto.nt, Producto.COLUMNA_PK);
	}
}
