package juego;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// crear las piezas con polimorfismo
		Pieza alfilBlanco = new Alfil("C1","blanco",1); // ClaseBase x = new ClaseDerivada(p1,p2);
		Pieza reyBlanco = new Rey("E1","blanco",1);
		Pieza torreNegra = new Torre("A8", "negro",-1);
		Pieza reyNegro = new Rey("E8","negro",-1);
		Pieza reinaBlanca = new Dama("D1","blanco",1);
		Pieza alfilNegro = new Alfil("G8", "Negro", -1);
		
		
		//matriz tablero
		String[][] tablero = new String[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				tablero[i][j] = "___";
			}
		}
		
		//desplegar las piezas en el tablero
		ArrayList<Pieza> piezas = new ArrayList<>();
		piezas.add(alfilBlanco);
		piezas.add(reyBlanco);
		piezas.add(torreNegra);
		piezas.add(reyNegro);
		piezas.add(reinaBlanca);
		piezas.add(alfilNegro);
		//substring(0,2) => Torre => To 
		//charAt(0) => negro => n 
		// Torre Negra => Ton 
		for(Pieza p : piezas) { // for para colecciones como ArrayList
			tablero[p.fila][p.columna] = p.nombre.substring(0,2) + p.color.charAt(0);
		}
		
		//imprimir tablero
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(tablero[i][j]);
			}
			System.out.println();
		}
	}

}
