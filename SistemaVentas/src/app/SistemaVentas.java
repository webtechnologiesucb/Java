package app;

import java.awt.EventQueue;

import ventanas.DLogin;

/**
 *
 * @author Código Lite - https://codigolite.com
 */
public class SistemaVentas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DLogin dialog = new DLogin(new javax.swing.JFrame(), true);        
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
}
