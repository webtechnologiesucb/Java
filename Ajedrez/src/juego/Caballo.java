package juego;

import java.util.ArrayList;

public class Caballo extends Pieza implements Movimientos {
	public Caballo(String posicion, String color, int direccion) {
		super("Caballo", posicion, color, direccion);
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

	    // Coordenadas de los posibles movimientos del Caballo
	    int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
	    int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

	    // Verificar si alguna de las posiciones alcanzables por el Caballo coincide con la posición del Rey
	    for (int i = 0; i < 8; i++) {
	        int nuevaFila = fila + dx[i];
	        int nuevaColumna = columna + dy[i];

	        // Validar que la nueva posición esté dentro del tablero
	        if (nuevaFila >= 0 && nuevaFila < 8 && nuevaColumna >= 0 && nuevaColumna < 8) {
	            if (nuevaFila == filaRey && nuevaColumna == columnaRey) {
	                return true; // Jaque, el Rey está en una posición atacable
	            }
	        }
	    }

	    return false; // No hay jaque
	}

	
	
	@Override
	public void mostrarPosiblesMovimientos(ArrayList<Pieza> piezas) {
	    System.out.println("Movimientos posibles para el " + nombre + " " + color + ":");

	    // Array para los movimientos posibles del caballo [fila, columna]
	    int[][] movimientos = {
	        {-2, -1}, {-2, 1},   // Saltos hacia arriba (izquierda y derecha)
	        {2, -1}, {2, 1},     // Saltos hacia abajo (izquierda y derecha)
	        {-1, -2}, {1, -2},   // Saltos hacia la izquierda (arriba y abajo)
	        {-1, 2}, {1, 2}      // Saltos hacia la derecha (arriba y abajo)
	    };

	    for (int[] movimiento : movimientos) {
	        int nuevaFila = fila + movimiento[0];
	        int nuevaColumna = columna + movimiento[1];

	        // Verificar si está dentro de los límites del tablero
	        if (nuevaFila >= 0 && nuevaFila < 8 && nuevaColumna >= 0 && nuevaColumna < 8) {
	            boolean ocupada = false;

	            // Verificar si hay una pieza en la casilla destino
	            for (Pieza p : piezas) {
	                if (p.fila == nuevaFila && p.columna == nuevaColumna) {
	                    ocupada = true;
	                    // Si la casilla está ocupada por una pieza enemiga, puede capturarla
	                    if (!p.color.equals(this.color)) {
	                        System.out.println((char) ('a' + nuevaColumna) + "" + (8 - nuevaFila));
	                    }
	                    break;
	                }
	            }

	            // Si la casilla está libre, añadir como movimiento válido
	            if (!ocupada) {
	                System.out.println((char) ('a' + nuevaColumna) + "" + (8 - nuevaFila));
	            }
	        }
	    }
	}

}
