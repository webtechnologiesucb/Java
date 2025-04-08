package juego;

import java.util.ArrayList;

public class Peon extends Pieza implements Movimientos {
	public Peon(String posicion, String color, int direccion) {
		super("Peon", posicion, color, direccion);
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

	    // Determinar las posiciones de ataque del Peón (diagonales hacia adelante)
	    int direccion = this.direccion; // 1 para Peones Blancos, -1 para Peones Negros
	    int nuevaFilaIzquierda = fila + direccion;
	    int nuevaColumnaIzquierda = columna - 1;
	    int nuevaFilaDerecha = fila + direccion;
	    int nuevaColumnaDerecha = columna + 1;

	    // Verificar si el Rey está en una de las posiciones de ataque
	    if ((nuevaFilaIzquierda == filaRey && nuevaColumnaIzquierda == columnaRey) ||
	        (nuevaFilaDerecha == filaRey && nuevaColumnaDerecha == columnaRey)) {
	        return true; // Jaque, el Rey está en una de las diagonales atacables
	    }

	    return false; // No hay jaque
	}

	
	
	@Override
	public void mostrarPosiblesMovimientos(ArrayList<Pieza> piezas) {
	    System.out.println("Movimientos posibles para el " + nombre + " " + color + ":");

	    // Movimiento hacia adelante (1 casilla)
	    int nuevaFila = fila + direccion;
	    if (nuevaFila >= 0 && nuevaFila < 8) {
	        boolean ocupada = false;
	        for (Pieza p : piezas) {
	            if (p.fila == nuevaFila && p.columna == columna) {
	                ocupada = true;
	                break;
	            }
	        }
	        if (!ocupada) {
	            System.out.println((char) ('a' + columna) + "" + (8 - nuevaFila));
	        }
	    }

	    // Movimiento inicial de 2 casillas
	    if ((direccion == 1 && fila == 1) || (direccion == -1 && fila == 6)) {
	        int dobleFila = fila + 2 * direccion;
	        boolean bloqueada = false;
	        for (Pieza p : piezas) {
	            if ((p.fila == fila + direccion && p.columna == columna) || 
	                (p.fila == dobleFila && p.columna == columna)) {
	                bloqueada = true;
	                break;
	            }
	        }
	        if (!bloqueada) {
	            System.out.println((char) ('a' + columna) + "" + (8 - dobleFila));
	        }
	    }

	    // Capturas diagonales
	    for (int desplazamientoCol : new int[]{-1, 1}) {
	        int nuevaColumna = columna + desplazamientoCol;
	        if (nuevaColumna >= 0 && nuevaColumna < 8) {
	            for (Pieza p : piezas) {
	                if (p.fila == nuevaFila && p.columna == nuevaColumna && !p.color.equals(this.color)) {
	                    System.out.println((char) ('a' + nuevaColumna) + "" + (8 - nuevaFila));
	                    break;
	                }
	            }
	        }
	    }
	}

}
