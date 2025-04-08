package desarrollo02;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class AutoLista {
    private String marca;
    private String modelo;
    private double precio;
    private String color;
    private int año;
    private int nro_puertas;

    // CONSTRUCTOR POR DEFECTO
    public AutoLista() {
        llenar();
        mostrar();
    }

    public void llenar() {
        String c1 = JOptionPane.showInputDialog(null, "Ingrese marca: ");
        marca = c1;
        c1 = JOptionPane.showInputDialog(null, "Ingrese el modelo: ");
        modelo = c1;
        c1 = JOptionPane.showInputDialog(null, "Ingrese el precio: ");
        precio = Double.parseDouble(c1); // Convierte a double
        c1 = JOptionPane.showInputDialog(null, "Ingrese el color: ");
        color = c1;
        c1 = JOptionPane.showInputDialog(null, "Ingrese el año: ");
        año = Integer.parseInt(c1);
        c1 = JOptionPane.showInputDialog(null, "Ingrese el número de puertas: ");
        nro_puertas = Integer.parseInt(c1);
    }

    public void mostrar() {
        System.out.println("MARCA: " + marca);
        System.out.println("MODELO: " + modelo);
        System.out.println("PRECIO: " + precio);
        System.out.println("COLOR: " + color);
        System.out.println("AÑO: " + año);
        System.out.println("NÚMERO DE PUERTAS: " + nro_puertas);
    }

    public static void main(String[] args) {
        ArrayList<AutoLista> autos = new ArrayList<>();

        // Añadir autos dinámicamente sin límite fijo
        for (int i = 0; i < 3; i++) {
            autos.add(new AutoLista());
        }

        double maximo = Double.MIN_VALUE;
        for (AutoLista a : autos) {
            if (a.precio > maximo) {
                maximo = a.precio;
            }
        }
        
        JOptionPane.showMessageDialog(null, "Precio mayor: " + maximo);

        String marca = JOptionPane.showInputDialog(null, "Ingrese marca a buscar: ");
        boolean encontrado = false;
        for (AutoLista a : autos) {
            if (a.marca.equals(marca)) {
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Marca encontrada!");
        } else {
            JOptionPane.showMessageDialog(null, "Marca no encontrada!");
        }
    }
}