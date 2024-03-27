package com.programacion.dos;

import java.util.ArrayList;
import java.util.List;

class ProductoABC{
	private String nombre;
	private double precio;
	
	public ProductoABC(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public double getPrecio() {
		return this.precio;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}

public class ListaSample {
	public static void main(String...args) {
		List<ProductoABC> listado = new ArrayList<ProductoABC>();
		listado.add(new ProductoABC("Producto 1", 500.0));
		listado.add(new ProductoABC("Producto 2", 550.0));
		listado.add(new ProductoABC("Producto 3", 530.0));
		listado.add(new ProductoABC("Producto 4", 580.0));
		listado.add(new ProductoABC("Producto 5", 600.0));
		double suma = 0;
		double promedio = 0;
		for(ProductoABC p : listado) {
			System.out.print("Nombre: " + p.getNombre() + " ");
			System.out.println("Precio: " + p.getPrecio() + " ");
			suma += p.getPrecio();
		}
		promedio = suma / listado.size();
		System.out.println("Suma total: " + suma);
		System.out.println("Promedio: " + promedio);
	}
}
