package com.programacion.dos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GuiGestionArchivosTexto extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextArea txtContenido;
    private JButton btnGuardar, btnCargar;
    private String nombreArchivo = "ejemplo.txt";

    public GuiGestionArchivosTexto() {
        setTitle("Gestión de Archivos de Texto");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        txtContenido = new JTextArea();
        add(new JScrollPane(txtContenido), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                escribirArchivo();
            }
        });
        panelBotones.add(btnGuardar);

        btnCargar = new JButton("Cargar");
        btnCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leerArchivo();
            }
        });
        panelBotones.add(btnCargar);

        add(panelBotones, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void escribirArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(txtContenido.getText());
            JOptionPane.showMessageDialog(this, "Archivo guardado exitosamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public void leerArchivo() {
        File archivo = new File(nombreArchivo);
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            txtContenido.setText("");
            String linea;
            while ((linea = reader.readLine()) != null) {
                txtContenido.append(linea + "\n");
            }
            JOptionPane.showMessageDialog(this, "Archivo cargado exitosamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new GuiGestionArchivosTexto();
    }
}