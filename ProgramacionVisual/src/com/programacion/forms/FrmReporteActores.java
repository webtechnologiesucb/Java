package com.programacion.forms;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.programacion.databases.Conexion;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.util.JRLoader;

public class FrmReporteActores extends JFrame {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FrmReporteActores frame = new FrmReporteActores();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmReporteActores() {
        setTitle("Reporte de Actores");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JButton btnVerReporte = new JButton("Ver Reporte ActorsList");
        btnVerReporte.setBounds(130, 100, 180, 30);
        getContentPane().add(btnVerReporte);

        btnVerReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarReporte();
            }
        });
    }

    private void mostrarReporte() {
        try {
            // Conexión a la base de datos Sakila
        	Connection con = Conexion.getInstance().getConnection();

            // Cargar el archivo .jasper
            JasperReport reporte = (JasperReport) JRLoader.loadObject(
                getClass().getResource("/reportes/ActorsList.jasper"));

            // Llenar y mostrar el reporte
            JasperPrint print = JasperFillManager.fillReport(reporte, null, con);
            JasperViewer.viewReport(print, false);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al mostrar el reporte:\n" + ex.getMessage());
        }
    }
}
