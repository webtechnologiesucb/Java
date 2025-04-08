package juego;

import java.util.ArrayList;

public class Rey extends Pieza implements Movimientos {
	public Rey(String posicion, String color, int direccion) {
		super("Rey", posicion, color, direccion);
	}
	// Ctrl + espacio
	@Override // sobreescritura
	public boolean jaque(ArrayList<Pieza> piezas) {
		// Encontrar la posición actual del Rey
	    String posicionRey = this.obtenerPosicion();

	    // Iterar sobre todas las piezas del oponente
	    for (Pieza pieza : piezas) {
	        // Verificar si la pieza es del color opuesto
	        if (!pieza.color.equals(this.color) && pieza.estado) {
	            // Obtener los posibles movimientos de la pieza
	            ArrayList<String> movimientos = pieza.generarPosiblesMovimientos(piezas);

	            // Si el Rey está dentro de los movimientos posibles, está en jaque
	            if (movimientos.contains(posicionRey)) {
	                return true; // El Rey está en jaque
	            }
	        }
	    }
	    return false; // No hay jaque
	}

	
	@Override
	public void mostrarPosiblesMovimientos(ArrayList<Pieza> piezas) {
		System.out.println("Movimientos posibles del Rey en " + obtenerPosicion());
		// logica para moverse a una casilla adyacente
		for(int i=-1; i<=1; i++) {
			for(int j=-1; j<=1; j++) {
				if(i==0 && j==0) continue; // la posicion actual no cuenta
				int nuevaFila = fila + i;
				int nuevaCol = columna + j;
				if(nuevaFila>=0 && nuevaFila<8  && nuevaCol>=0 && nuevaCol < 8) {
					System.out.println((char) (nuevaCol + 'A') +""+ (8-nuevaFila));
				}
			}
		}		
	}
}
