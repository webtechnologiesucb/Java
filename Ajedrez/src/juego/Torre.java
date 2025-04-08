package juego;

import java.util.ArrayList;

public class Torre extends Pieza implements Movimientos {
	public Torre(String posicion, String color, int direccion) {
		super("Torre", posicion, color, direccion);
	}
	
	@Override
	public boolean jaque(ArrayList<Pieza> piezas) {
	    // Encontrar la posición del Rey oponente
	    String posicionRey = "";
	    for (Pieza pieza : piezas) {
	        if (pieza.nombre.equals("Rey") && !pieza.color.equals(this.color) && pieza.estado) {
	            posicionRey = pieza.obtenerPosicion();
	            break;
	        }
	    }

	    // Extraer fila y columna del Rey oponente
	    int filaRey = 8 - Character.getNumericValue(posicionRey.charAt(1));
	    int columnaRey = posicionRey.charAt(0) - 'A';

	    // Comprobar movimientos horizontales y verticales
	    if (fila == filaRey) { // Misma fila
	        int direccion = (columnaRey > columna) ? 1 : -1; // Determinar dirección
	        for (int i = columna + direccion; i != columnaRey; i += direccion) {
	            if (hayObstaculo(fila, i, piezas)) {
	                return false; // Obstáculo bloqueando el camino
	            }
	        }
	        return true; // Jaque en línea horizontal
	    } else if (columna == columnaRey) { // Misma columna
	        int direccion = (filaRey > fila) ? 1 : -1; // Determinar dirección
	        for (int i = fila + direccion; i != filaRey; i += direccion) {
	            if (hayObstaculo(i, columna, piezas)) {
	                return false; // Obstáculo bloqueando el camino
	            }
	        }
	        return true; // Jaque en línea vertical
	    }

	    return false; // No hay jaque
	}
	
	private boolean hayObstaculo(int fila, int columna, ArrayList<Pieza> piezas) {
	    for (Pieza pieza : piezas) {
	        if (pieza.estado && pieza.fila == fila && pieza.columna == columna) {
	            return true; // Se encontró una pieza bloqueando el camino
	        }
	    }
	    return false; // No hay obstáculo
	}


	
	@Override
	public void mostrarPosiblesMovimientos(ArrayList<Pieza> piezas) {
		System.out.println("Movimientos posibles de torre en " + obtenerPosicion());
		// moverse en linea recta horizontal o vertical
		for(int i=1; i<8; i++) {
			//hacia arriba
			if(fila-i >=0)
				System.out.println((char) (columna +'A') +""+(8-(fila - i)));
			//hacia abajo
			if(fila+i <8)
				System.out.println((char) (columna +'A') +""+(8-(fila + i)));
			//hacia la izquierda
			if(columna-i>=0)
				System.out.println((char) ((columna - i) +'A') +""+(8-fila));
			//hacia la derecha
			if(columna+i<8)
				System.out.println((char) ((columna + i) +'A') +""+(8-fila));
		}
	}
}
