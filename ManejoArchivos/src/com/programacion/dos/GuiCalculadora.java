package com.programacion.dos;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Calculadora básica con operaciones aritméticas.
 * @author Miguel Angel Pacheco Arteaga
 */
public class GuiCalculadora {

	private JFrame frame;
	private JTextField textField;
	private String operador = "";
	private double numero1 = 0;
	private boolean nuevaOperacion = true;

	/**
     * Método principal que lanza la aplicación.
     * @param args Argumentos de línea de comandos
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				GuiCalculadora window = new GuiCalculadora();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Crear la aplicacion
	 */
	public GuiCalculadora() {
		initialize();
	}

	/**
	 * Inicializar el contenido del frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Calculadora");
		frame.setBounds(100, 100, 300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField.setBounds(10, 10, 264, 50);
		textField.setHorizontalAlignment(JTextField.RIGHT);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		String[] botones = {
			"C", "←", "/", "*",
			"7", "8", "9", "-",
			"4", "5", "6", "+",
			"1", "2", "3", "=",
			"0", ".", "", ""
		};

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String btn = ((JButton) e.getSource()).getText();

				if (btn.matches("[0-9]")) {
					if (nuevaOperacion) {
						textField.setText(btn);
						nuevaOperacion = false;
					} else {
						textField.setText(textField.getText() + btn);
					}
				} else if (btn.equals(".")) {
					if (!textField.getText().contains(".")) {
						textField.setText(textField.getText() + ".");
					}
				} else if (btn.equals("C")) {
					textField.setText("");
					numero1 = 0;
					operador = "";
				} else if (btn.equals("←")) {
					String text = textField.getText();
					if (!text.isEmpty()) {
						textField.setText(text.substring(0, text.length() - 1));
					}
				} else if (btn.matches("[\\+\\-\\*/]")) {
					try {
						numero1 = Double.parseDouble(textField.getText());
						operador = btn;
						nuevaOperacion = true;
					} catch (NumberFormatException ex) {
						textField.setText("Error");
					}
				} else if (btn.equals("=")) {
					try {
						double numero2 = Double.parseDouble(textField.getText());
						double resultado = 0;
						switch (operador) {
							case "+": resultado = numero1 + numero2; break;
							case "-": resultado = numero1 - numero2; break;
							case "*": resultado = numero1 * numero2; break;
							case "/": resultado = numero2 != 0 ? numero1 / numero2 : 0; break;
						}
						textField.setText(String.valueOf(resultado));
						nuevaOperacion = true;
					} catch (NumberFormatException ex) {
						textField.setText("Error");
					}
				}
			}
		};

		int x = 10, y = 70;
		for (int i = 0; i < botones.length; i++) {
			String label = botones[i];
			if (!label.isEmpty()) {
				JButton btn = new JButton(label);
				btn.setFont(new Font("Tahoma", Font.BOLD, 18));
				btn.setBounds(x, y, 60, 40);
				btn.addActionListener(listener);
				frame.getContentPane().add(btn);
			}
			x += 70;
			if ((i + 1) % 4 == 0) {
				x = 10;
				y += 50;
			}
		}
	}
}
