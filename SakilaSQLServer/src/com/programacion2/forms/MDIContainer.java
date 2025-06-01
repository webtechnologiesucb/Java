package com.programacion2.forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.programacion2.databases.Conexion;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MDIContainer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MDIContainer frame = new MDIContainer();
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
	public MDIContainer() {
		setTitle("Sakila App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());

		desktopPane = new JDesktopPane();
		getContentPane().add(desktopPane, BorderLayout.CENTER);

		// Add your menu items or buttons here
		// Agregar el menú "Registro"
		JMenuBar menuBar = new JMenuBar();
		JMenu registroMenu = new JMenu("Registro");
		JMenuItem actorItem = new JMenuItem("Actor");
		actorItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCrudActor frmActor;
				try {
					frmActor = new FrmCrudActor();
					desktopPane.add(frmActor);
					frmActor.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		JMenuItem filmItem = new JMenuItem("Film");
		JMenuItem categoryItem = new JMenuItem("Category");

		registroMenu.add(actorItem);
		registroMenu.add(filmItem);
		registroMenu.add(categoryItem);
		menuBar.add(registroMenu);
		setJMenuBar(menuBar);

		setVisible(true);
	}

}
