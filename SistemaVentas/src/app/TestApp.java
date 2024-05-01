package app;

import autorizacion.DbAutorizacionAdministrador;
import config.AppConfig;
import modelbd.Usuario;
import ventanas.paneles.PanelAutorizacion;
import javax.swing.JFrame;

/**
 *
 * @author Código Lite
 */
public class TestApp {
    public static void main(String[] arg)
    { 
        DbAutorizacionAdministrador auth = new DbAutorizacionAdministrador();
        Usuario usr = new Usuario();       
        AppConfig.setUsuario((Usuario)usr.getUltimo(27));
        JFrame vnt = new JFrame("Test Permisos");
        vnt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vnt.add(new PanelAutorizacion());
        vnt.setVisible(true);
        vnt.setSize(420, 380);
    }
}
