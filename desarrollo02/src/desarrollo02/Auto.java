package desarrollo02;

import javax.swing.JOptionPane;

public class Auto {
	private String marca;
	private String modelo;
	private double precio;
	private String color;
	private int año;
	private int nro_puertas;

	// CONSTRUCTOR POR DEFECTO
	public Auto() {
		llenar();
		mostrar();
	}

	public void llenar() {
		String c1 = JOptionPane.showInputDialog(null, "ingrese marca: ");
        marca = c1;
        c1 = JOptionPane.showInputDialog(null,"ingrese el modelo: ");
        modelo = c1;
        c1 = JOptionPane.showInputDialog(null,"ingrese el precio: ");
        precio = Double.parseDouble(c1); //convierte a double
        c1 = JOptionPane.showInputDialog(null,"ingrese el color: ");
        color = c1;
        c1 = JOptionPane.showInputDialog(null,"ingrese el año: ");
        año = Integer.parseInt(c1);
        c1 = JOptionPane.showInputDialog(null,"ingrese el numero de puertas: ");
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
		Auto[] autos = new Auto[3];
		autos[0] = new Auto();
		autos[1] = new Auto();
		autos[2] = new Auto();
		
		double maximo = Double.MIN_VALUE;
		for(int i=0; i<3; i++) {
			Auto a = autos[i];
			if(a.precio>maximo) {
				maximo = a.precio;
			}
		}
		
		String marca = JOptionPane.showInputDialog(null, "ingrese marca: ");
		boolean encontrado = false;
		for(int i=0; i<3; i++) {
			Auto a = autos[i];
			if(a.marca.equals(marca)) {
				encontrado = true;
			}
		}
		
		if (encontrado) {
			JOptionPane.showMessageDialog(null, "Marca encontrada!");
		} else {
			JOptionPane.showMessageDialog(null, "Marca no encontrada!");
		}
		
		
	}

}
