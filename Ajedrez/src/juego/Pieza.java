package juego;

import java.util.ArrayList;

public abstract class Pieza {
	protected String nombre;
	protected String color; // blanco - negro
	protected int fila; // 0-7
	protected int columna; // 0-7
	protected int direccion; // 1 si avanza hacia las filas adelante
	protected boolean estado; // true en juego, false fuera del juego

	public Pieza(String nombre, String posicion, String color, int direccion) {
		this.nombre = nombre;
		this.color = color.toLowerCase(); // poner en minusculas
		this.direccion = direccion;
		this.estado = true; // por defecto
		// Ad4, De6
		// De => charAt(0) => 'D' => col = 68 - 65 = 3
		this.columna = posicion.charAt(0) - 65; // convertir columnas restando el valor ASCII de A
		this.fila = 8 - Character.getNumericValue(posicion.charAt(1)); // convertir fila
	}

	public String obtenerPosicion() {
		return "" + (char) (columna + 'A') + (8 - fila); // A5
	}

	@Override
	public String toString() {
		return nombre + " " + color + " en " + obtenerPosicion() + (estado ? " (en juego)" : " (fuera de juego)");
	}

	public ArrayList<String> generarPosiblesMovimientos(ArrayList<Pieza> piezas) {
		ArrayList<String> movimientos = new ArrayList<>();
		for (Pieza p : piezas) {
			switch (p.nombre) {
			case "Torre":
				// Movimiento horizontal y vertical
				for (int i = 1; i < 8; i++) {
					// Arriba
					if (fila - i >= 0)
						movimientos.add("" + (char) ('A' + columna) + (8 - (fila - i)));
					// Abajo
					if (fila + i < 8)
						movimientos.add("" + (char) ('A' + columna) + (8 - (fila + i)));
					// Izquierda
					if (columna - i >= 0)
						movimientos.add("" + (char) ('A' + (columna - i)) + (8 - fila));
					// Derecha
					if (columna + i < 8)
						movimientos.add("" + (char) ('A' + (columna + i)) + (8 - fila));
				}
				break;

			case "Alfil":
				// Movimiento diagonal
				for (int i = 1; i < 8; i++) {
					// Superior derecha
					if (fila - i >= 0 && columna + i < 8)
						movimientos.add("" + (char) ('A' + (columna + i)) + (8 - (fila - i)));
					// Superior izquierda
					if (fila - i >= 0 && columna - i >= 0)
						movimientos.add("" + (char) ('A' + (columna - i)) + (8 - (fila - i)));
					// Inferior derecha
					if (fila + i < 8 && columna + i < 8)
						movimientos.add("" + (char) ('A' + (columna + i)) + (8 - (fila + i)));
					// Inferior izquierda
					if (fila + i < 8 && columna - i >= 0)
						movimientos.add("" + (char) ('A' + (columna - i)) + (8 - (fila + i)));
				}
				break;

			case "Caballo":
				// Movimiento en "L"
				int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
				int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };
				for (int i = 0; i < 8; i++) {
					int nuevaFila = fila + dx[i];
					int nuevaColumna = columna + dy[i];
					if (nuevaFila >= 0 && nuevaFila < 8 && nuevaColumna >= 0 && nuevaColumna < 8) {
						movimientos.add("" + (char) ('A' + nuevaColumna) + (8 - nuevaFila));
					}
				}
				break;

			case "Dama":
				// Movimiento como Torre y Alfil combinados
				for (int i = 1; i < 8; i++) {
					// Movimientos de la Torre
					if (fila - i >= 0)
						movimientos.add("" + (char) ('A' + columna) + (8 - (fila - i)));
					if (fila + i < 8)
						movimientos.add("" + (char) ('A' + columna) + (8 - (fila + i)));
					if (columna - i >= 0)
						movimientos.add("" + (char) ('A' + (columna - i)) + (8 - fila));
					if (columna + i < 8)
						movimientos.add("" + (char) ('A' + (columna + i)) + (8 - fila));

					// Movimientos del Alfil
					if (fila - i >= 0 && columna + i < 8)
						movimientos.add("" + (char) ('A' + (columna + i)) + (8 - (fila - i)));
					if (fila - i >= 0 && columna - i >= 0)
						movimientos.add("" + (char) ('A' + (columna - i)) + (8 - (fila - i)));
					if (fila + i < 8 && columna + i < 8)
						movimientos.add("" + (char) ('A' + (columna + i)) + (8 - (fila + i)));
					if (fila + i < 8 && columna - i >= 0)
						movimientos.add("" + (char) ('A' + (columna - i)) + (8 - (fila + i)));
				}
				break;

			case "Rey":
				// Movimiento a una casilla adyacente
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						if (i == 0 && j == 0)
							continue; // No incluir posición actual
						int nuevaFila = fila + i;
						int nuevaColumna = columna + j;
						if (nuevaFila >= 0 && nuevaFila < 8 && nuevaColumna >= 0 && nuevaColumna < 8) {
							movimientos.add("" + (char) ('A' + nuevaColumna) + (8 - nuevaFila));
						}
					}
				}
				break;

			case "Peon":
				// Movimiento hacia adelante
				int nuevaFila = fila + direccion; // Dirección es 1 para Blancos y -1 para Negros
				if (nuevaFila >= 0 && nuevaFila < 8) {
					movimientos.add("" + (char) ('A' + columna) + (8 - nuevaFila));
					// Capturas diagonales
					if (columna - 1 >= 0)
						movimientos.add("" + (char) ('A' + (columna - 1)) + (8 - nuevaFila));
					if (columna + 1 < 8)
						movimientos.add("" + (char) ('A' + (columna + 1)) + (8 - nuevaFila));
				}
				break;

			default:
				System.out.println("Tipo de pieza no reconocido.");
			}
		}
		return movimientos;
	}

	//public abstract boolean jaque(ArrayList<Pieza> piezas);
}
