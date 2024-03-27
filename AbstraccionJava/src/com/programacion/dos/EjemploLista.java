package com.programacion.dos;

import java.util.ArrayList;
import java.util.List;

class CocheX{
	private String placa;
	private double precio;
	private String marca;
	
	public CocheX(String placa, double precio, String marca){
		this.placa = placa;
		this.precio = precio;
		this.marca = marca;
	}
	
	public void mostrar() {
		System.out.println("Placa: " + this.getPlaca() +	" Precio: " + this.precio +
				" Marca: " + this.marca); 
	}
	
	public double getPrecio() { return this.precio; }

	public String getPlaca() { return placa;  }
}

public class EjemploLista {
	static List<CocheX> listado = new ArrayList<CocheX>();
	private static CocheX set;
	public static void main(String[] args) {
		double suma = 0;
		double promedio = 0;
		
		listado.add(new CocheX("564-GRT", 10000, "Toyota"));
		listado.add(new CocheX("456-GED", 15000, "Toyota"));
		listado.add(new CocheX("123-ABC", 18000, "Toyota"));
		listado.add(new CocheX("852-WER", 20000, "Toyota"));
		
		System.out.println("Lista actual");
		for(CocheX c: listado) {
			c.mostrar();
			suma += c.getPrecio();
		}
		promedio = (double) suma / listado.size(); // listado.size() cantidad de elementos
		System.out.println("Suma de precios: " + suma);
		System.out.println("Promedio de precios: " + promedio);
		
		// Modificar listado
		CocheX c1 = new CocheX("852-WER", 25000, "Toyota");
		for (int i = 0; i < listado.size(); i++) {
			if (listado.get(i).getPlaca() == c1.getPlaca()) {
				listado.set(i, c1); // actualice el registro
				break;
			}
		}
		
		System.out.println("Lista modificada");
		for(CocheX c: listado) {
			c.mostrar();
			suma += c.getPrecio();
		}
		
		// eliminar de lista
		String placa = "123-ABC";
		listado.removeIf(coche -> coche.getPlaca() == placa);
		System.out.println("Lista post eliminacion");
		for(CocheX c: listado) {
			c.mostrar();
			suma += c.getPrecio();
		}
	}
}
