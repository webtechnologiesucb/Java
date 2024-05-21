package com.programacion.forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.programacion.databases.Conexion;
import com.programacion.models.Actor;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListSelectionModel;

public class FrmCrudActor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBusqueda;
	private JTable grdDatos;

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
		setTitle("CRUD de Actores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Registro de Actores");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(214, 11, 174, 23);
		contentPane.add(lblTitulo);

		JLabel lblBusqueda = new JLabel("Ingrese búsqueda");
		lblBusqueda.setBounds(10, 48, 94, 14);
		contentPane.add(lblBusqueda);

		txtBusqueda = new JTextField();
		lblBusqueda.setLabelFor(txtBusqueda);
		txtBusqueda.setBounds(114, 45, 375, 20);
		contentPane.add(txtBusqueda);
		txtBusqueda.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(499, 44, 89, 23);
		contentPane.add(btnBuscar);

		JButton btnNuevo = new JButton("Nuevo Registro");
		btnNuevo.setBounds(45, 99, 113, 23);
		contentPane.add(btnNuevo);

		JButton btnModificar = new JButton("Modificar Registro");
		btnModificar.setBounds(254, 99, 134, 23);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar Registro");
		btnEliminar.setBounds(460, 99, 128, 23);
		contentPane.add(btnEliminar);

		// carga de la base de datos
		List<Actor> lista = new ArrayList<Actor>();

		Connection con = Conexion.getInstance().getConnection(); // rescatar conexion
		String consulta = "SELECT actor_id, first_name, last_name, last_update " + " FROM actor WHERE actor_id>?";
		PreparedStatement pst = con.prepareStatement(consulta); // Statement seguro
		pst.setString(1, "0"); // parametro tipo texto
		System.out.println(pst.toString());
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			Actor a = new Actor();
			a.setActorId(rs.getInt("actor_id"));
			a.setFirstName(rs.getString("first_name"));
			a.setLastName(rs.getString("last_name"));
			a.setLastUpdate(rs.getDate("last_update"));
			lista.add(a);
		}

		String[] columnNames = { "Actor ID", "First Name", "Last Name", "Last Update" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		for (Actor actor : lista) {
			Object[] row = { actor.getActorId(), actor.getFirstName(), actor.getLastName(), actor.getLastUpdate() };
			model.addRow(row);
		}

		grdDatos = new JTable();
		grdDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		grdDatos.setFillsViewportHeight(true);
		grdDatos.setBounds(45, 158, 543, 290);
		contentPane.add(grdDatos);

		// Envolver JTable en JScrollPane para mostrar encabezados
		JScrollPane scrollPane = new JScrollPane(grdDatos);
		scrollPane.setBounds(45, 158, 543, 290);
		contentPane.add(scrollPane);

		grdDatos.setModel(model);
		grdDatos.updateUI();

	}
}
