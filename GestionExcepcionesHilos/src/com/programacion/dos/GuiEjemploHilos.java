package com.programacion.dos;

import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class Hilo1 extends Thread {
	private String nombre;
	private DefaultListModel<String> modelo;

	public Hilo1(String nombre, DefaultListModel<String> modelo) {
		this.nombre = nombre;
		this.modelo = modelo;
	}

	public void run() {
		for (int i = 1; i <= 10; i++) {
			final String mensaje = "Hilo " + nombre + ": " + i;
			SwingUtilities.invokeLater(() -> modelo.addElement(mensaje));

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				SwingUtilities.invokeLater(() -> modelo.addElement("Hilo " + nombre + " interrumpido."));
			}
		}
		SwingUtilities.invokeLater(() -> modelo.addElement("Hilo " + nombre + " terminado."));
	}
}

public class GuiEjemploHilos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> lstResultado;
	private DefaultListModel<String> modeloLista;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				GuiEjemploHilos frame = new GuiEjemploHilos();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public GuiEjemploHilos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 514);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("EJEMPLO DE HILOS");
		lblTitulo.setBounds(301, 22, 135, 13);
		contentPane.add(lblTitulo);

		modeloLista = new DefaultListModel<>();
		lstResultado = new JList<>(modeloLista);

		// Agregar JScrollPane para el scroll en lstResultado
		JScrollPane scrollPane = new JScrollPane(lstResultado);
		scrollPane.setBounds(196, 76, 309, 375);
		contentPane.add(scrollPane);

		JButton btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloLista.clear();
				modeloLista.addElement("Inicio del programa...");

				Hilo1 hilo1 = new Hilo1("A", modeloLista);
				Hilo1 hilo2 = new Hilo1("B", modeloLista);

				hilo1.start();
				hilo2.start();

				new Thread(() -> {
					try {
						hilo1.join();
						hilo2.join();
						SwingUtilities.invokeLater(() -> modeloLista.addElement("Fin del programa."));
					} catch (InterruptedException ex) {
						SwingUtilities.invokeLater(() -> modeloLista.addElement("Hilo principal interrumpido."));
					}
				}).start();
			}
		});

		btnEjecutar.setBounds(305, 45, 85, 21);
		contentPane.add(btnEjecutar);
	}
}