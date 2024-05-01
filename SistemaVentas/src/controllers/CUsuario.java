package controllers;

import core.CriterioSQL;
import core.Ex;
import core.JAbstractController;
import core.JAbstractModelBD;
import managerbd.BaseConexion;
import modelbd.Bitacora;
import modelbd.Usuario;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Código Lite - https://codigolite.com
 */
public class CUsuario extends JAbstractController {
	private Usuario us;
	private InputStream itmp;

	public CUsuario() {

	}

	@Override
	public ArrayList<Usuario> getRegistros() {
		return this.getRegistros(new String[] {}, null, null);
	}

	@Override
	public ArrayList<Usuario> getRegistros(Object[] cvl) {
		return this.getRegistros(new String[] {}, cvl != null ? new String[] { Usuario.COLUMNA_ACTIVO } : null, cvl);
	}

	public Usuario getLoginUser(String login) {
		ArrayList<Usuario> reg = this.getRegistros(new String[] {}, new String[] { "login" }, new Object[] { login });
		if (!reg.isEmpty()) {
			return reg.get(0);
		}
		return null;
	}

	public ArrayList<Usuario> getRegistros(String[] columnas, Object[] cvl) {
		return this.getRegistros(new String[] {}, columnas, cvl);
	}

	public ArrayList<Usuario> getRegistrosPorTipoCargo(String cargo) {
		return this.getRegistros(new String[] {}, new String[] { "tipo_cargo" }, new Object[] { cargo });
	}

	public ArrayList<Usuario> getRegistrosDni(String dni) {
		return this.getRegistros(new String[] {}, new String[] { "dni" }, new Object[] { dni });
	}

	public ArrayList<Usuario> getRegistrosCodigo(String codigo) {
		return this.getRegistros(new String[] {}, new String[] { "codigo" }, new Object[] { codigo });
	}

	public ArrayList<Usuario> getRegistrosLogin(String login) {
		return this.getRegistros(new String[] {}, new String[] { "login" }, new Object[] { login });
	}

	public ArrayList<Usuario> getRegistrosFecha(long valor) {
		return this.getRegistrosMn(new String[] {}, new String[] { "fec_registro" }, new Object[] { valor });
	}

	public ArrayList<Usuario> getRegistrosFecha(long valor1, long valor) {
		return this.getRegistrosMn(new String[] {}, new String[] { "fec_registro", "fec_registro" },
				new Object[] { valor1, valor });
	}

	@Override
	public Usuario getRegistro() {
		ArrayList<Usuario> registros = this.getRegistros();
		return registros.get(0);
	}

	public Usuario getRegitro(String id) {
		ArrayList<Usuario> registros = this.getRegistros(new String[] { "dni" }, new Object[] { id });
		return registros.get(0);
	}

	/**
	 * 
	 * @param mdl    modelo
	 * @param opcion si true llenara el modelos con todos los datos, de lo contrario
	 *               solo obtendra la clave primaria.
	 * @return
	 */
	@Override
	public JAbstractModelBD getRegistro(JAbstractModelBD mdl, boolean opcion) {
		us = (Usuario) mdl;
		CriterioSQL criterio = new CriterioSQL(Usuario.nt);
		criterio.setSelect(
				"idu,dni,codigo,login,nombre,apellidos,clave,salt,fono,sexo,tipo_cargo,fec_registro,fec_baja");
		criterio.addCondicion("dni", us.getDni());
		
		try {
			setRs(BaseConexion.getConexion().createStatement().executeQuery(criterio.getConsultaSQL()));
			if (getRs().next()) {
				us.setPrimaryKey(getRs().getInt(1));
				us.setIdu(getRs().getInt(1));
				if (opcion) {
					us.setDni(getRs().getString(2));
					us.setCodigo(getRs().getString(3));
					us.setLogin(getRs().getString(4));
					us.setNombre(getRs().getString(5));
					us.setApellidos(getRs().getString(6));
					us.setClave(getRs().getString(7));
					us.setSalt(getRs().getString(8));
					us.setFono(getRs().getString(9));
					us.setSexo(getRs().getString(10));
					us.setTipoCargo(getRs().getString(11));
					us.setFecRegistro(getRs().getLong(12));
					us.setFecBaja(getRs().getLong(13));
				}
				getRs().close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return us;
	}

	@Override
	public JAbstractModelBD buscarRegistro(Object cvl) {
		throw new UnsupportedOperationException("No soportado");
	}

	@Override
	public boolean guardarRegistro(JAbstractModelBD mdl) {

		Usuario usr = (Usuario) mdl;
		int gravado = 0;
		String campos = Usuario.NO_FULL_NOM_CAMPOS;

		Object[] valores = { usr.getDni(), usr.getCodigo(), usr.getLogin(), usr.getNombre(), usr.getApellidos(),
				usr.getFoto(null), usr.getClave(), usr.getSalt(), usr.getFechaNacimiento(), usr.getFono(),
				usr.getFono2(), usr.getCelular(), usr.getNextel(), usr.getEmail(), usr.getSexo(null),
				usr.getTipoCargo(), usr.getDescipcion(), usr.getActivo(), usr.getFecRegistro(), usr.getFecBaja() };

		gravado = this.agregarRegistroPs(Usuario.nt, this.stringToArray(campos, ","), valores);

		if (gravado == 1) {
			// getRegitro(usr.getDni());
			try {
				this.grabarBitacora(this.getUltimoUsuario());
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return true;
		}

		return false;
	}

	@Override
	public int actualizarRegistro(JAbstractModelBD mdl) {
		Usuario usr = (Usuario) mdl;
		int gravado = 0;
		String campos = Usuario.SIN_FOTO_CLAVE;

		Object[] valores = { usr.getDni(), usr.getCodigo(), usr.getLogin(), usr.getNombre(), usr.getApellidos(),
				usr.getFechaNacimiento(), usr.getFono(), usr.getFono2(), usr.getCelular(), usr.getNextel(),
				usr.getEmail(), usr.getSexo(null), usr.getTipoCargo(), usr.getDescipcion(), usr.getActivo(),
				usr.getFecRegistro(), usr.getFecBaja(), usr.getPrimaryKey() };
		gravado = this.actualizarRegistroPs(Usuario.nt,
				this.adjuntarSimbolo(campos, ",", "?") + Ex.WHERE + Usuario.COLUMNA_PK + " = ? ", valores);
		return gravado;
	}

	public int actualizarConFoto(JAbstractModelBD mdl) {
		Usuario usr = (Usuario) mdl;
		int gravado = 0;
		String campos = Usuario.CON_FOTO;

		Object[] valores = { usr.getDni(), usr.getCodigo(), usr.getLogin(), usr.getNombre(), usr.getApellidos(),
				usr.getFoto(null), usr.getFechaNacimiento(), usr.getFono(), usr.getFono2(), usr.getCelular(),
				usr.getNextel(), usr.getEmail(), usr.getSexo(null), usr.getTipoCargo(), usr.getDescipcion(),
				usr.getActivo(), usr.getFecRegistro(), usr.getFecBaja(), usr.getPrimaryKey() };
		gravado = this.actualizarRegistroPs(Usuario.nt,
				this.adjuntarSimbolo(campos, ",", "?") + Ex.WHERE + Usuario.COLUMNA_PK + " = ? ", valores);

		return gravado;
	}

	public int actualizarPass(JAbstractModelBD mdl) {
		Usuario usr = (Usuario) mdl;
		int gravado = 0;
		String campos = "clave,salt";

		Object[] valores = { usr.getClave(), usr.getSalt(), usr.getPrimaryKey() };
		gravado = this.actualizarRegistroPs(Usuario.nt,
				this.adjuntarSimbolo(campos, ",", "?") + Ex.WHERE + Usuario.COLUMNA_PK + " = ? ", valores);

		return gravado;
	}

	public int grabarEnBitacora(Usuario us) {
		try {
			return this.grabarBitacora(us);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	private int grabarBitacora(Usuario us) throws SQLException {
		Calendar cr = Calendar.getInstance();
		Bitacora bt = getBitacora(us);
		setSql("INSERT INTO gv_bitacora (" + Bitacora.COLUMNAS_NO_ID + ")" + " VALUES (?, ?, ?, ?, ?, ?, ?)");
		this.setPs(BaseConexion.getConexion().prepareStatement(getSql()));
		getPs().setInt(1, us.getPrimaryKey());
		getPs().setString(2, System.getProperty("os.name"));
		getPs().setString(3, System.getProperty("os.arch"));
		getPs().setString(4, System.getProperty("os.version"));
		getPs().setString(5, System.getProperty("user.name"));
		if (bt != null) {
			getPs().setLong(6, bt.getFechaActividad());
		} else {
			getPs().setLong(6, cr.getTime().getTime());
		}
		getPs().setLong(7, cr.getTime().getTime());
		return getPs().executeUpdate();

	}

	private Bitacora getBitacora(Usuario us) {
		try {
			Bitacora bt = new Bitacora();
			setSql("select * from gv_bitacora where idusuario = " + us.getPrimaryKey()
					+ " order by fecha_actividad desc limit 1");
			setRs(BaseConexion.getConexion().createStatement().executeQuery(getSql()));
			if (getRs().next()) {
				bt.setIdbitacora(getRs().getLong(1));
				bt.setIdusuario(getRs().getInt(2));
				bt.setOs(getRs().getString(3));
				bt.setArquitectura(getRs().getString(4));
				bt.setVersion(getRs().getString(5));
				bt.setUsuario(getRs().getString(6));
				bt.setUltimaActividad(getRs().getLong(7));
				bt.setFechaActividad(getRs().getLong(8));
				return bt;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Usuario getRegistroPorPk(Integer id) {
		try {
			setRs(this.selectPorPk(Usuario.nt, Usuario.COLUMNA_PK, id));
			while (getRs().next()) {
				us = new Usuario();
				us.setPrimaryKey(getRs().getInt(1));
				us.setIdu(getRs().getInt(1));
				us.setDni(getRs().getString(2));
				us.setCodigo(getRs().getString(3));
				us.setLogin(getRs().getString(4));
				us.setNombre(getRs().getString(5));
				us.setApellidos(getRs().getString(6));
				us.setClave(getRs().getString(8));
				us.setSalt(getRs().getString(9));
				us.setFechaNacimiento(getRs().getInt(10));
				us.setFono(getRs().getString(11));
				us.setFono2(getRs().getString(12));
				us.setCelular(getRs().getString(13));
				us.setNextel(getRs().getString(14));
				us.setEmail(getRs().getString(15));
				us.setSexo(getRs().getString(16));
				us.setTipoCargo(getRs().getString(17));
				us.setDescipcion(getRs().getString(18));
				us.setActivo(getRs().getInt(19));
				us.setFecRegistro(getRs().getLong(20));
				us.setFecBaja(getRs().getLong(21));

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return us;
	}

	@Override
	public ArrayList<Usuario> getRegistros(String[] campos, String[] columnaId, Object[] id) {
		ArrayList<Usuario> rgs = new ArrayList<Usuario>();
		try {

			if (id != null) {
				this.setNumRegistros(this.getNumeroRegistros(Usuario.nt, Usuario.COLUMNA_ACTIVO, Usuario.COLUMNA_ACTIVO,
						id));
			} else {
				this.setNumRegistros(this.getNumeroRegistros(Usuario.nt, Usuario.COLUMNA_ACTIVO));
				if (getRs().isClosed()) {
					System.out.println("resultset esta cerrado...");
				}
			}
			setRs(this.getRegistros(Usuario.nt, campos, columnaId, id));
			if (this.getNumRegistros() < getFinalPag()) {
				setFinalPag(this.getNumRegistros());
			}
			if (getFinalPag() > inicioPag) {
				inicioPag -= (getFinalPag() - inicioPag) - 1;
			}
			Usuario us = null;
			while (getRs().next()) {
				us = new Usuario();
				us.setPrimaryKey(getRs().getInt(1));
				us.setIdu(getRs().getInt(1));
				us.setDni(getRs().getString(2));
				us.setCodigo(getRs().getString(3));
				us.setLogin(getRs().getString(4));
				us.setNombre(getRs().getString(5));
				us.setApellidos(getRs().getString(6));
				us.setClave(getRs().getString(8));
				us.setSalt(getRs().getString(9));
				us.setFechaNacimiento(getRs().getInt(10));
				us.setFono(getRs().getString(11));
				us.setFono2(getRs().getString(12));
				us.setCelular(getRs().getString(13));
				us.setNextel(getRs().getString(14));
				us.setEmail(getRs().getString(15));
				us.setSexo(getRs().getString(16));
				us.setTipoCargo(getRs().getString(17));
				us.setDescipcion(getRs().getString(18));
				us.setActivo(getRs().getInt(19));
				us.setFecRegistro(getRs().getLong(20));
				us.setFecBaja(getRs().getLong(21));
				rgs.add(us);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rgs;
	}

	public Usuario getUltimoUsuario() {
		Usuario us2 = null;
		setRs(this.getUltimoRegistro(Usuario.nt, Usuario.COLUMNA_PK));
		try {

			while (getRs().next()) {
				us2 = new Usuario();
				us2.setPrimaryKey(getRs().getInt(1));
				us2.setIdu(getRs().getInt(1));
				us2.setDni(getRs().getString(2));
				us2.setCodigo(getRs().getString(3));
				us2.setLogin(getRs().getString(4));
				us2.setNombre(getRs().getString(5));
				us2.setApellidos(getRs().getString(6));
				us2.setClave(getRs().getString(8));
				us2.setSalt(getRs().getString(9));
				us2.setFechaNacimiento(getRs().getInt(10));
				us2.setFono(getRs().getString(11));
				us2.setFono2(getRs().getString(12));
				us2.setCelular(getRs().getString(13));
				us2.setNextel(getRs().getString(14));
				us2.setEmail(getRs().getString(15));
				us2.setSexo(getRs().getString(16));
				us2.setTipoCargo(getRs().getString(17));
				us2.setDescipcion(getRs().getString(18));
				us2.setActivo(getRs().getInt(19));
				us2.setFecRegistro(getRs().getLong(20));
				us2.setFecBaja(getRs().getLong(21));

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return us2;
	}

	public ArrayList<Usuario> getRegistrosMn(String[] campos, String[] columnaId, Object[] id) {
		ArrayList<Usuario> rgs = new ArrayList<Usuario>();
		try {

			if (id != null) {
				this.setNumRegistros(this.getNumeroRegistros(Usuario.nt, Usuario.COLUMNA_ACTIVO, Usuario.COLUMNA_ACTIVO,
						id));
			} else {
				this.setNumRegistros(this.getNumeroRegistros(Usuario.nt, Usuario.COLUMNA_ACTIVO));
				if (getRs().isClosed()) {
					System.out.println("resultset esta cerrado...");
				}
			}
			setRs(this.getRegistrosMn(Usuario.nt, campos, columnaId, id));
			if (this.getNumRegistros() < getFinalPag()) {
				setFinalPag(this.getNumRegistros());
			}
			if (getFinalPag() > inicioPag) {
				inicioPag -= (getFinalPag() - inicioPag) - 1;
			}
			Usuario us = null;
			while (getRs().next()) {
				us = new Usuario();
				us.setPrimaryKey(getRs().getInt(1));
				us.setIdu(getRs().getInt(1));
				us.setDni(getRs().getString(2));
				us.setCodigo(getRs().getString(3));
				us.setLogin(getRs().getString(4));
				us.setNombre(getRs().getString(5));
				us.setApellidos(getRs().getString(6));
				us.setClave(getRs().getString(8));
				us.setSalt(getRs().getString(9));
				us.setFechaNacimiento(getRs().getInt(10));
				us.setFono(getRs().getString(11));
				us.setFono2(getRs().getString(12));
				us.setCelular(getRs().getString(13));
				us.setNextel(getRs().getString(14));
				us.setEmail(getRs().getString(15));
				us.setSexo(getRs().getString(16));
				us.setTipoCargo(getRs().getString(17));
				us.setDescipcion(getRs().getString(18));
				us.setActivo(getRs().getInt(19));
				us.setFecRegistro(getRs().getLong(20));
				us.setFecBaja(getRs().getLong(21));
				rgs.add(us);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rgs;
	}

	public boolean existeCodigo(String codigo) {
		return this.existe(Usuario.nt, "codigo", codigo);
	}

	public boolean existeDni(String dni) {
		return this.existe(Usuario.nt, "dni", dni);
	}

	public boolean existeLogin(String login) {
		return this.existe(Usuario.nt, "login", login);
	}

	public String getCodigoUsuario() {
		return this.getCodigo(Usuario.nt, "codigo");
	}

	public ImageIcon getFoto(Integer id) {
		InputStream archivo = this.getArchivo(Usuario.nt, "foto", Usuario.COLUMNA_PK, id);
		itmp = archivo;
		ImageIcon ii = null;
		if (archivo != null) {
			try {
				BufferedImage read = ImageIO.read(archivo);
				ii = new ImageIcon(read);
			} catch (IOException ex) {
				Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return ii;
	}

	public InputStream getDatoFoto() {

		return this.itmp;
	}

	public int getNumeroRegistros() {
		return this.getNumeroRegistros(Usuario.nt, Usuario.COLUMNA_PK);
	}

	public int activarUsuario(Usuario us) {
		return this.eliminacionTemporal(Usuario.nt, Usuario.COLUMNA_ACTIVO, Usuario.COLUMNA_PK, us.getPrimaryKey(), 1);
	}

}
