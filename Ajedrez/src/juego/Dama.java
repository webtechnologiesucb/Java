package juego;

import java.util.ArrayList;

public class Dama extends Pieza implements Movimientos {
	public Dama(String posicion, String color, int direccion) {
		super("Dama", posicion, color, direccion);
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

	    // Comprobar movimientos horizontales y verticales (como Torre)
	    if (fila == filaRey) { // Misma fila
	        int direccion = (columnaRey > columna) ? 1 : -1; // Determinar dirección
	        for (int i = columna + direccion; i != columnaRey; i += direccion) {
	            if (hayObstaculo(fila, i, piezas)) {
	                return false; // Obstáculo bloqueando el camino
	            }
	        }
	        return true; // Jaque horizontal
	    } else if (columna == columnaRey) { // Misma columna
	        int direccion = (filaRey > fila) ? 1 : -1; // Determinar dirección
	        for (int i = fila + direccion; i != filaRey; i += direccion) {
	            if (hayObstaculo(i, columna, piezas)) {
	                return false; // Obstáculo bloqueando el camino
	            }
	        }
	        return true; // Jaque vertical
	    }

	    // Comprobar movimientos diagonales (como Alfil)
	    if (Math.abs(filaRey - fila) == Math.abs(columnaRey - columna)) {
	        int direccionFila = (filaRey > fila) ? 1 : -1; // Dirección de la fila
	        int direccionColumna = (columnaRey > columna) ? 1 : -1; // Dirección de la columna

	        int i = fila + direccionFila;
	        int j = columna + direccionColumna;

	        // Recorrer las casillas en la diagonal hacia el Rey
	        while (i != filaRey && j != columnaRey) {
	            if (hayObstaculo(i, j, piezas)) {
	                return false; // Obstáculo bloqueando el camino
	            }
	            i += direccionFila;
	            j += direccionColumna;
	        }
	        return true; // Jaque en la diagonal
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
	    System.out.println("Movimientos posibles para el " + nombre + " " + color + ":");

	    // Direcciones posibles para la reina (vertical, horizontal, diagonal)
	    int[][] direcciones = {
	        {-1, 0}, {1, 0},    // Vertical (arriba, abajo)
	        {0, -1}, {0, 1},    // Horizontal (izquierda, derecha)
	        {-1, -1}, {-1, 1},  // Diagonal superior (izquierda, derecha)
	        {1, -1}, {1, 1}     // Diagonal inferior (izquierda, derecha)
	    };

	    // Iterar sobre cada dirección
	    for (int[] direccion : direcciones) {
	        int filaActual = fila;
	        int columnaActual = columna;

	        // Explorar una dirección específica
	        while (true) {
	            filaActual += direccion[0];
	            columnaActual += direccion[1];

	            // Verificar límites del tablero
	            if (filaActual < 0 || filaActual > 7 || columnaActual < 0 || columnaActual > 7) {
	                break;
	            }

	            // Verificar si la casilla está ocupada
	            Pieza piezaEnCasilla = null;
	            for (Pieza p : piezas) {
	                if (p.fila == filaActual && p.columna == columnaActual) {
	                    piezaEnCasilla = p;
	                    break;
	                }
	            }

	            if (piezaEnCasilla != null) {
	                // Si es una pieza enemiga, se puede capturar
	                if (!piezaEnCasilla.color.equals(this.color)) {
	                    System.out.println((char) ('a' + columnaActual) + "" + (8 - filaActual));
	                }
	                // No se puede seguir explorando esta dirección
	                break;
	            }

	            // Si la casilla está vacía, es un movimiento válido
	            System.out.println((char) ('a' + columnaActual) + "" + (8 - filaActual));
	        }
	    }
	}
}
