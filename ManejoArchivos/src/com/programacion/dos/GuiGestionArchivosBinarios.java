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
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Entero:"));
        txtEntero = new JTextField();
        add(txtEntero);

        add(new JLabel("Decimal:"));
        txtDecimal = new JTextField();
        add(txtDecimal);

        add(new JLabel("Cadena:"));
        txtCadena = new JTextField();
        add(txtCadena);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                escribirArchivoBinario();
            }
        });
        add(btnGuardar);

        btnCargar = new JButton("Cargar");
        btnCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leerArchivoBinario();
            }
        });
        add(btnCargar);
        
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