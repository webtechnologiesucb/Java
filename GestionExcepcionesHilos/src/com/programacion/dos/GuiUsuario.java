package com.programacion.dos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class UsuarioModelo {
	private int id;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String fechaReg; // Para manejar fecha y hora formateada
	private String cuenta;
	private String contraseña;
	private boolean vigente;
}

// Renderizador para JCheckBox en JTable
class BooleanCellRenderer extends JCheckBox implements TableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setSelected(value != null && (Boolean) value);
		return this;
	}
}

public class GuiUsuario extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modeloTabla;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				GuiUsuario frame = new GuiUsuario();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@SuppressWarnings("serial")
	public GuiUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 760, 340);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// Primero crea el modelo de la tabla
		modeloTabla = new DefaultTableModel(new Object[] { "ID", "Nombres", "Apellido Paterno", "Apellido Materno",
				"Fecha Registro", "Cuenta", "Vigente" }, 0) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return (columnIndex == 6) ? Boolean.class : String.class;
			}
		};

		// Luego inicializa JTable con el modelo
		table = new JTable(modeloTabla);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Activa ajuste automático

		// Ajustar cada columna según su contenido
		for (int i = 0; i < table.getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(100); // Ancho inicial, puede ajustarse dinámicamente
		}

		scrollPane.setViewportView(table);

		// Ahora sí puedes modificar las columnas porque ya existe el modelo
		table.getColumnModel().getColumn(6).setCellRenderer(new BooleanCellRenderer());
		table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(new JCheckBox()));

		// Carga los datos después de inicializar correctamente la tabla
		cargarUsuarios();
	}

	private void cargarUsuarios() {
		List<UsuarioModelo> listado = new ArrayList<>();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		listado.add(new UsuarioModelo(1, "Miguel", "Pacheco", "Arteaga", formato.format(new Date()), "admin",
				"4dm1n123", true));
		listado.add(new UsuarioModelo(2, "Carolina", "Cespedes", "Hernandez", formato.format(new Date()), "ccespedes",
				"C4r1t02024", true));
		listado.add(new UsuarioModelo(3, "Daniela", "Toledo", "Abastoflor", formato.format(new Date()), "dtoledo",
				"D4n12024", false));

		for (UsuarioModelo usuario : listado) {
			modeloTabla.addRow(new Object[] { usuario.getId(), usuario.getNombres(), usuario.getApellidoPaterno(),
					usuario.getApellidoMaterno(), usuario.getFechaReg(), usuario.getCuenta(), usuario.isVigente() });
		}
	}
}