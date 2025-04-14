package com.programacion.dos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GuiGestionArchivosBinarios extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtEntero, txtDecimal, txtCadena;
	private JButton btnGuardar, btnCargar;
	private String nombreArchivo = "datos.bin";

	public GuiGestionArchivosBinarios() {
		setTitle("Gestión de Archivos Binarios");
		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel label = new JLabel("Entero:");
		label.setBounds(0, 1, 193, 40);
		getContentPane().add(label);
		txtEntero = new JTextField();
		txtEntero.setBounds(193, 1, 193, 40);
		getContentPane().add(txtEntero);

		JLabel label_1 = new JLabel("Decimal:");
		label_1.setBounds(0, 41, 193, 40);
		getContentPane().add(label_1);
		txtDecimal = new JTextField();
		txtDecimal.setBounds(193, 41, 193, 40);
		getContentPane().add(txtDecimal);

		JLabel label_2 = new JLabel("Cadena:");
		label_2.setBounds(0, 81, 193, 40);
		getContentPane().add(label_2);
		txtCadena = new JTextField();
		txtCadena.setBounds(193, 81, 193, 40);
		getContentPane().add(txtCadena);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(0, 121, 193, 40);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				escribirArchivoBinario();
			}
		});
		getContentPane().add(btnGuardar);

		btnCargar = new JButton("Cargar");
		btnCargar.setBounds(193, 121, 193, 40);
		btnCargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leerArchivoBinario();
			}
		});
		getContentPane().add(btnCargar);

		setVisible(true);
	}

	public void escribirArchivoBinario() {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombreArchivo))) {
			dos.writeInt(Integer.parseInt(txtEntero.getText()));
			dos.writeDouble(Double.parseDouble(txtDecimal.getText()));
			dos.writeUTF(txtCadena.getText());
			JOptionPane.showMessageDialog(this, "Archivo guardado exitosamente.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error al escribir en el archivo: " + e.getMessage());
		}
	}

	public void leerArchivoBinario() {
		try (DataInputStream dis = new DataInputStream(new FileInputStream(nombreArchivo))) {
			txtEntero.setText(String.valueOf(dis.readInt()));
			txtDecimal.setText(String.valueOf(dis.readDouble()));
			txtCadena.setText(dis.readUTF());
			JOptionPane.showMessageDialog(this, "Datos cargados exitosamente.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		new GuiGestionArchivosBinarios();
	}
}