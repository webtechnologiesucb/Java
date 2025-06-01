package com.programacion2.forms;

import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar;
    private int progress = 0;
    private Timer timer;

    public SplashScreen() {
        setUndecorated(true);
        setSize(500, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel lblTitulo = new JLabel("Sakila App");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBounds(140, 50, 300, 30);
        getContentPane().add(lblTitulo);

        JLabel lblCargando = new JLabel("Cargando...");
        lblCargando.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCargando.setBounds(210, 200, 100, 20);
        getContentPane().add(lblCargando);

        progressBar = new JProgressBar();
        progressBar.setBounds(100, 230, 300, 20);
        progressBar.setStringPainted(true);
        getContentPane().add(progressBar);

        iniciarCarga();
    }

    private void iniciarCarga() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                progress++;
                progressBar.setValue(progress);

                if (progress >= 100) {
                    timer.cancel();
                    abrirMDI();
                }
            }
        }, 0, 30); // velocidad del progreso
    }

    private void abrirMDI() {
        dispose(); // cerrar splash
        LoginFrame frm = new LoginFrame();
        frm.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SplashScreen frame = new SplashScreen();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
