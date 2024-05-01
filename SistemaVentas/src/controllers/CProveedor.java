package controllers;

import core.Ex;
import core.JAbstractController;
import core.JAbstractModelBD;
import modelbd.Proveedor;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Código Lite - https://codigolite.com
 */
public class CProveedor extends JAbstractController {
	private Proveedor prv;

	@Override
	public ArrayList<Proveedor> getRegistros(Object[] op) {
		return this.getRegistros(new String[] {}, op != null ? new String[] { Proveedor.campoActivo } : null, op);
	}

	public ArrayList<Proveedor> getRegistros(String[] columna, Object[] valor) {
		return getRegistros(new String[] {}, columna, valor);
	}

	public Proveedor getRegistroPorPk(Integer id) {
		try {
			setRs(this.selectPorPk(Proveedor.nt, "idproveedor", id));
			while (getRs().next()) {
				prv = new Proveedor();
				prv.setPrimaryKey(getRs().getInt(1));
				prv.setIdproveedor(getRs().getInt(1));
				prv.setRuc(getRs().getString(2));
				prv.setRazonSocial(getRs().getString(3));
				prv.setDireccion(getRs().getString(4));
				prv.setCiudad(getRs().getString(5));
				prv.setTelefono(getRs().getString(6));
				prv.setNextel(getRs().getString(7));
				prv.setMovil(getRs().getString(8));
				prv.setFax(getRs().getString(9));
				prv.setCtaBancaria(getRs().getString(10));
				prv.setNomContacto(getRs().getString(11));
				prv.setEmail(getRs().getString(12));
				prv.setRubro(getRs().getString(13));
				prv.setProductos(getRs().getString(14));
				prv.setActivo(getRs().getInt(15));

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return prv;
	}

	@Override
	public ArrayList<Proveedor> getRegistros(String[] campos, String[] columnaId, Object[] id) {

		ArrayList<Proveedor> rgs = new ArrayList<Proveedor>();
		try {

			if (id != null) {
				this.setNumRegistros(this.getNumeroRegistros(Proveedor.nt, Proveedor.campoActivo, Proveedor.campoActivo,
						id));
			} else {
				this.setNumRegistros(this.getNumeroRegistros(Proveedor.nt, Proveedor.campoActivo));
				if (getRs().isClosed()) {
					System.out.println("resultset esta cerrado...");
				}
			}
			setRs(this.getRegistros(Proveedor.nt, campos, columnaId, id));
			if (this.getNumRegistros() < getFinalPag()) {
				setFinalPag(this.getNumRegistros());
			}
			if (getFinalPag() > inicioPag) {
				inicioPag -= (getFinalPag() - inicioPag) - 1;
			}
			while (getRs().next()) {
				prv = new Proveedor();
				prv.setPrimaryKey(getRs().getInt(1));
				prv.setIdproveedor(getRs().getInt(1));
				prv.setRuc(getRs().getString(2));
				prv.setRazonSocial(getRs().getString(3));
				prv.setDireccion(getRs().getString(4));
				prv.setCiudad(getRs().getString(5));
				prv.setTelefono(getRs().getString(6));
				prv.setNextel(getRs().getString(7));
				prv.setMovil(getRs().getString(8));
				prv.setFax(getRs().getString(9));
				prv.setCtaBancaria(getRs().getString(10));
				prv.setNomContacto(getRs().getString(11));
				prv.setEmail(getRs().getString(12));
				prv.setRubro(getRs().getString(13));
				prv.setProductos(getRs().getString(14));
				prv.setActivo(getRs().getInt(15));
				rgs.add(prv);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rgs;

	}

	@Override
	public ArrayList<JAbstractModelBD> getRegistros() {
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
		prv = (Proveedor) mdl;
		int gravado = 0;
		String campos = "ruc, razon_social, direccion, ciudad, telefono, nextel, movil, fax, cta_bancaria, nom_contacto, email, rubro, productos";

		Object[] valores = { prv.getRuc(), prv.getRazonSocial(), prv.getDireccion(), prv.getCiudad(), prv.getTelefono(),
				prv.getNextel(), prv.getMovil(), prv.getFax(), prv.getCtaBancaria(), prv.getNomContacto(),
				prv.getEmail(), prv.getRubro(), prv.getProductos() };

		gravado = this.agregarRegistroPs(prv.getNombreTabla(), this.stringToArray(campos, ","), valores);

		if (gravado == 1)
			return true;

		return false;
	}

	@Override
	public int actualizarRegistro(JAbstractModelBD mdl) {
		prv = (Proveedor) mdl;
		int gravado = 0;
		String campos = "ruc, razon_social, direccion, ciudad, telefono, nextel, movil, fax, cta_bancaria, nom_contacto, email, rubro, productos, activo";

		Object[] valores = { prv.getRuc(), prv.getRazonSocial(), prv.getDireccion(), prv.getCiudad(), prv.getTelefono(),
				prv.getNextel(), prv.getMovil(), prv.getFax(), prv.getCtaBancaria(), prv.getNomContacto(),
				prv.getEmail(), prv.getRubro(), prv.getProductos(), prv.getActivo(), prv.getPrimaryKey() };

		gravado = this.actualizarRegistroPs(prv.getNombreTabla(),
				this.adjuntarSimbolo(campos, ",", "?") + Ex.WHERE + mdl.getCampoClavePrimaria() + " = ? ", valores);

		return gravado;
	}

	public Proveedor getProveedor() {
		if (prv == null) {
			prv = new Proveedor();
		}
		return prv;
	}

	public void setProveedor(JAbstractModelBD prv) {
		this.prv = (Proveedor) prv;
	}

}
