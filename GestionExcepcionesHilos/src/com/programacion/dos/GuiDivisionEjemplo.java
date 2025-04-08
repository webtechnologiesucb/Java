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

public class GuiDivisionEjemplo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contenido;
	private JTextField txtNumero1;
	private JTextField txtNumero2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiDivisionEjemplo frame = new GuiDivisionEjemplo();
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
	public GuiDivisionEjemplo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 443);
		contenido = new JPanel();
		contenido.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contenido);
		contenido.setLayout(null);

		JLabel lblNewLabel = new JLabel("Division de dos numeros");
		lblNewLabel.setBounds(213, 53, 120, 13);
		contenido.add(lblNewLabel);

		JLabel lblNumero1 = new JLabel("Numero 1:");
		lblNumero1.setBounds(62, 96, 72, 13);
		contenido.add(lblNumero1);

		JLabel lblNumero2 = new JLabel("Numero 2:");
		lblNumero2.setBounds(62, 132, 72, 13);
		contenido.add(lblNumero2);

		txtNumero1 = new JTextField();
		txtNumero1.setBounds(144, 93, 96, 19);
		contenido.add(txtNumero1);
		txtNumero1.setColumns(10);

		txtNumero2 = new JTextField();
		txtNumero2.setBounds(144, 129, 96, 19);
		contenido.add(txtNumero2);
		txtNumero2.setColumns(10);

		JButton btnDividir = new JButton("Dividir");
		btnDividir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!txtNumero1.getText().equals("") && !txtNumero2.getText().equals("")) {
						int n1 = Integer.parseInt(txtNumero1.getText());
						int n2 = Integer.parseInt(txtNumero2.getText());
						int div = n1 / n2;
						// damos formato para mostrar el resultado
						String resultado = String.format("La division de: %d / %d = %d ", n1, n2, div);
						// imprime el mensaje en un cuadro de dialogo
						JOptionPane.showMessageDialog(contenido, resultado, "Divisi[on",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(contenido, "Revise si lleno los campos del formulario!",
								"Advertencia", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception exc) {
					if (!txtNumero2.getText().equals("0"))
						JOptionPane.showMessageDialog(contenido, "Revise sus datos ingresados!", "Error",
								JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(contenido, "No se puede dividir entre cero", "Error",
								JOptionPane.ERROR_MESSAGE);
					System.out.print(exc.getMessage());
					exc.printStackTrace();
				}

			}
		});
		btnDividir.setBounds(144, 170, 85, 21);
		contenido.add(btnDividir);
	}
}
