package juego;

import java.util.ArrayList;

public class Alfil extends Pieza implements Movimientos {
	public Alfil(String posicion, String color, int direccion) {
		super("Alfil", posicion, color, direccion);
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

	    // Comprobar si el Rey está en alguna de las diagonales del Alfil
	    if (Math.abs(filaRey - fila) == Math.abs(columnaRey - columna)) {
	        int direccionFila = (filaRey > fila) ? 1 : -1; // Determina la dirección de la fila
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

	        return true; // Jaque, no hay obstáculos en la diagonal
	    }

	    return false; // El Rey no está en una diagonal atacable
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
	    // Array para las direcciones diagonales [fila, columna]
	    int[][] direcciones = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

	    for (int[] direccion : direcciones) {
	        int nuevaFila = fila;
	        int nuevaColumna = columna;
	        // Explorar cada dirección
	        while (true) {
	            nuevaFila += direccion[0];
	            nuevaColumna += direccion[1];
	            // Verificar si está dentro de los límites del tablero
	            if (nuevaFila < 0 || nuevaFila > 7 || nuevaColumna < 0 || nuevaColumna > 7) {
	                break;
	            }
                // Verificar si hay otra pieza en la casilla
	            boolean ocupada = false;
	            for (Pieza p : piezas) {
	                if (p.fila == nuevaFila && p.columna == nuevaColumna) {
	                    ocupada = true;
	                    // Verificar si es una pieza del oponente
	                    if (!p.color.equals(this.color)) {
	                        System.out.println((char) ('a' + nuevaColumna) + "" + (8 - nuevaFila));
	                    }
	                    break;
	                }
	            }
	            // Si la casilla está ocupada, no se puede seguir en esa dirección
	            if (ocupada) {
	                break;
	            }
	            // Añadir la posición válida
	            System.out.println((char) ('a' + nuevaColumna) + "" + (8 - nuevaFila));
	        }
	    }
	}

}
