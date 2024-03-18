package com.programacion.dos;

final class Coche {
    private String marca;

    public Coche(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }
}

/** no puede heredar porque esta declarada como final
class Demo extends Coche{
	
}
**/

public class EjemploFinal {
	public static void main(String... args) {
		Coche c = new Coche("Dodge");
		System.out.println("Marca del coche: " + c.getMarca());
	}
}
