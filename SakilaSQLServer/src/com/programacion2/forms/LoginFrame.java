package com.programacion2.forms;

import javax.swing.*;

import com.programacion2.databases.Conexion;

import java.awt.*;
import java.sql.*;

import at.favre.lib.crypto.bcrypt.BCrypt; // ✅ NUEVA LIBRERÍA

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    public LoginFrame() {
        setTitle("Iniciar Sesión");
        setSize(300, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 5, 5));

        add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        add(txtUsuario);

        add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        add(txtPassword);

        JButton btnLogin = new JButton("Entrar");
        add(btnLogin);

        btnLogin.addActionListener(e -> login());
    }

    private void login() {
        String usuario = txtUsuario.getText().trim();
        String passwordIngresado = new String(txtPassword.getPassword());

        if (usuario.isEmpty() || passwordIngresado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT password FROM staff WHERE username = ?")) {

            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashAlmacenado = rs.getString("password");

                if (hashAlmacenado == null || hashAlmacenado.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Contraseña no establecida. Contacte al administrador.");
                    return;
                }

                // ✅ Verifica la contraseña con la librería moderna
                BCrypt.Result resultado = BCrypt.verifyer().verify(passwordIngresado.toCharArray(), hashAlmacenado);

                if (resultado.verified) {
                    JOptionPane.showMessageDialog(this, "¡Acceso correcto!");
                    MDIContainer mdi = new MDIContainer();
                    mdi.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Contraseña incorrecta.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar: " + ex.getMessage());
        }
    }
}

