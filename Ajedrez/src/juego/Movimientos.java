package juego;

import java.util.ArrayList;

public interface Movimientos {
	void mostrarPosiblesMovimientos(ArrayList<Pieza> piezas);
	boolean jaque(ArrayList<Pieza> piezas);
}
