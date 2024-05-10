package com.programacion.forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.programacion.databases.Conexion;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		setTitle("Registro de Actores");
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

		grdDatos = new JTable();
		grdDatos.setBounds(45, 141, 543, 290);
		contentPane.add(grdDatos);

		// carga de la base de datos

		Connection con = Conexion.getInstance().getConnection(); // rescatar conexion
		String consulta = "SELECT * FROM actor WHERE actor_id>?";
		PreparedStatement pst = con.prepareStatement(consulta); // Statement seguro
		pst.setString(1, "0"); // parametro tipo texto
		ResultSet rs = pst.executeQuery();
		DefaultTableModel dtmModelo;

		dtmModelo = new DefaultTableModel() {
			private static final long serialVersionUID = 8462952105265234581L;

			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false; // Con esto conseguimos que la tabla no se pueda editar
			}
		};

		dtmModelo.addColumn("ID");
		dtmModelo.addColumn("Nombre");
		dtmModelo.addColumn("Apellido");
		dtmModelo.addColumn("Fecha");

		while (rs.next()) {
			int actorId = rs.getInt("actor_id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String lastUpdate = rs.getString("last_update");

			dtmModelo.addRow(new Object[] { actorId, firstName, lastName, lastUpdate });
		}

		grdDatos.setModel(dtmModelo);
		grdDatos.updateUI();
		System.out.println(dtmModelo.getRowCount());

	}
}
