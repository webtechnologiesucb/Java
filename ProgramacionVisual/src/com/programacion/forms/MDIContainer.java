package com.programacion.forms;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
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
		setTitle("MDI Container Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);

		desktopPane = new JDesktopPane();
		setContentPane(desktopPane);

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
					frmActor.setVisible(true); // Mostrar el MDI Child
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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
