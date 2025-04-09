package com.programacion.dos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GuiEjemploExcepciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiEjemploExcepciones frame = new GuiEjemploExcepciones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiEjemploExcepciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("VALIDACIONES");
		lblTitulo.setBounds(188, 10, 77, 13);
		contentPane.add(lblTitulo);

		JLabel lblNumero = new JLabel("Ingrese un numero:");
		lblNumero.setBounds(24, 44, 125, 13);
		contentPane.add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				// Permitir dígitos, punto y borrado
				if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				}
				// Evitar más de un punto decimal
				if (c == '.' && txtNumero.getText().contains(".")) {
					e.consume();
				}
			}
		});
		txtNumero.setBounds(145, 41, 125, 19);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = txtNumero.getText().trim();
				if (texto.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Por favor ingrese un valor", "Validaciones",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				try {
					if (texto.contains(".")) {
						double numero = Double.parseDouble(texto);
						JOptionPane.showMessageDialog(contentPane, numero + " es un numero decimal", "Resultado",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						int numero = Integer.parseInt(texto);
						JOptionPane.showMessageDialog(contentPane, numero + " es un numero entero", "Resultado",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(contentPane, "No un numero valido", "Mensaje de error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIngresar.setBounds(145, 67, 85, 21);
		contentPane.add(btnIngresar);
	}

}
