package com.programacion.forms;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.programacion.databases.Conexion;
import com.programacion.models.Actor;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmCrudActor extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBusqueda;
	private JTable grdDatos;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudActor frame = new FrmCrudActor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public FrmCrudActor() throws SQLException {
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setTitle("CRUD de Actores");
		setBounds(10, 10, 400, 300);
		getContentPane().setLayout(new FlowLayout());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Registro de Actores");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(214, 11, 174, 23);
		contentPane.add(lblTitulo);

		JLabel lblBusqueda = new JLabel("Ingrese búsqueda");
		lblBusqueda.setBounds(45, 47, 94, 14);
		contentPane.add(lblBusqueda);

		txtBusqueda = new JTextField();
		lblBusqueda.setLabelFor(txtBusqueda);
		txtBusqueda.setBounds(149, 45, 340, 20);
		contentPane.add(txtBusqueda);
		txtBusqueda.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(499, 44, 89, 23);
		contentPane.add(btnBuscar);

		JButton btnNuevo = new JButton("Nuevo Registro");
		btnNuevo.setBounds(45, 99, 113, 23);
		contentPane.add(btnNuevo);

		JButton btnModificar = new JButton("Modificar Registro");
		btnModificar.setBounds(183, 99, 134, 23);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar Registro");
		btnEliminar.setBounds(342, 99, 128, 23);
		contentPane.add(btnEliminar);

		// Inicializar tabla
		String[] columnNames = { "Actor ID", "First Name", "Last Name", "Last Update" };
		model = new DefaultTableModel(columnNames, 0);
		grdDatos = new JTable(model);
		grdDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		grdDatos.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(grdDatos);
		scrollPane.setBounds(45, 158, 543, 290);
		contentPane.add(scrollPane);

		JButton btnGenerarReporte = new JButton("Ver Reporte");
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Conexión a la base de datos Sakila
					Connection con = Conexion.getInstance().getConnection();

					// Cargar el archivo .jasper
					JasperReport reporte = (JasperReport) JRLoader
							.loadObject(getClass().getResource("/reportes/ActorsList.jasper"));

					// Llenar y mostrar el reporte
					JasperPrint print = JasperFillManager.fillReport(reporte, null, con);
					JasperViewer.viewReport(print, false);

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al mostrar el reporte:\n" + ex.getMessage());
				}
			}
		});
		btnGenerarReporte.setBounds(481, 100, 107, 21);
		contentPane.add(btnGenerarReporte);

		// Cargar datos iniciales
		cargarDatos(0);

		// Acción de buscar
		btnBuscar.addActionListener(e -> {
			try {
				buscarActores(txtBusqueda.getText());
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});

		// Acción de nuevo
		btnNuevo.addActionListener(e -> {
			try {
				insertarActor();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});

		// Acción de modificar
		btnModificar.addActionListener(e -> {
			try {
				modificarActor();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});

		// Acción de eliminar
		btnEliminar.addActionListener(e -> {
			try {
				eliminarActor();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});
	}

	private void cargarDatos(int actorId) throws SQLException {
		List<Actor> lista = new ArrayList<Actor>();

		Connection con = Conexion.getInstance().getConnection();
		String consulta = "SELECT actor_id, first_name, last_name, last_update " + "FROM actor " + "WHERE actor_id>?";
		PreparedStatement pst = con.prepareStatement(consulta);
		pst.setInt(1, actorId);
		ResultSet rs = pst.executeQuery();
		System.out.println(pst.toString());

		while (rs.next()) {
			Actor a = new Actor();
			a.setActorId(rs.getInt("actor_id"));
			a.setFirstName(rs.getString("first_name"));
			a.setLastName(rs.getString("last_name"));
			a.setLastUpdate(rs.getDate("last_update"));
			lista.add(a);
		}

		// Limpiar el modelo
		model.setRowCount(0);

		// Agregar datos al modelo
		for (Actor actor : lista) {
			Object[] row = { actor.getActorId(), actor.getFirstName(), actor.getLastName(), actor.getLastUpdate() };
			model.addRow(row);
		}
	}

	private void buscarActores(String criterio) throws SQLException {
		List<Actor> lista = new ArrayList<Actor>();

		Connection con = Conexion.getInstance().getConnection();
		String consulta = "SELECT actor_id, first_name, last_name, last_update " + " FROM actor "
				+ " WHERE first_name LIKE ? OR last_name LIKE ? ";
		PreparedStatement pst = con.prepareStatement(consulta);
		pst.setString(1, "%" + criterio + "%");
		pst.setString(2, "%" + criterio + "%");
		ResultSet rs = pst.executeQuery();
		System.out.println(pst.toString());

		while (rs.next()) {
			Actor a = new Actor();
			a.setActorId(rs.getInt("actor_id"));
			a.setFirstName(rs.getString("first_name"));
			a.setLastName(rs.getString("last_name"));
			a.setLastUpdate(rs.getDate("last_update"));
			lista.add(a);
		}

		// Limpiar el modelo
		model.setRowCount(0);

		// Agregar datos al modelo
		for (Actor actor : lista) {
			Object[] row = { actor.getActorId(), actor.getFirstName(), actor.getLastName(), actor.getLastUpdate() };
			model.addRow(row);
		}
	}

	private void insertarActor() throws SQLException {
		String firstName = JOptionPane.showInputDialog("Ingrese el primer nombre:");
		String lastName = JOptionPane.showInputDialog("Ingrese el apellido:");
		if (firstName == null || lastName == null) {
			return;
		}

		Connection con = Conexion.getInstance().getConnection();
		String consulta = "INSERT INTO actor (first_name, last_name, last_update) VALUES (?, ?, NOW())";
		PreparedStatement pst = con.prepareStatement(consulta);
		pst.setString(1, firstName);
		pst.setString(2, lastName);
		int rowsAffected = pst.executeUpdate();
		System.out.println(pst.toString());

		if (rowsAffected > 0) {
			JOptionPane.showMessageDialog(this, "Actor insertado exitosamente.");
			cargarDatos(0);
		}
	}

	private void modificarActor() throws SQLException {
		int selectedRow = grdDatos.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Seleccione un registro para modificar.");
			return;
		}
		int actorId = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

		String firstName = JOptionPane.showInputDialog("Ingrese el nuevo primer nombre:");
		String lastName = JOptionPane.showInputDialog("Ingrese el nuevo apellido:");
		if (firstName == null || lastName == null) {
			return;
		}

		Connection con = Conexion.getInstance().getConnection();
		String consulta = "UPDATE actor SET first_name = ?, last_name = ?, last_update = NOW() "
				+ " WHERE actor_id = ?";
		PreparedStatement pst = con.prepareStatement(consulta);
		pst.setString(1, firstName);
		pst.setString(2, lastName);
		pst.setInt(3, actorId);
		int rowsAffected = pst.executeUpdate();
		System.out.println(pst.toString());

		if (rowsAffected > 0) {
			JOptionPane.showMessageDialog(this, "Actor modificado exitosamente.");
			cargarDatos(0);
		}
	}

	private void eliminarActor() throws SQLException {
		int selectedRow = grdDatos.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Seleccione un registro para eliminar.");
			return;
		}

		int actorId = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

		Connection con = Conexion.getInstance().getConnection();
		String consulta = "DELETE FROM actor WHERE actor_id = ?";
		PreparedStatement pst = con.prepareStatement(consulta);
		pst.setInt(1, actorId);
		int rowsAffected = pst.executeUpdate();
		System.out.println(pst.toString());
		if (rowsAffected > 0) {
			JOptionPane.showMessageDialog(this, "Actor eliminado exitosamente.");
			cargarDatos(0);
		}
	}
}
