import java.util.Random;

class Dado {
	public int lanzar() {
		Random rand = new Random();
		return rand.nextInt(6) + 1;
	}
}

class Juego {
	private Dado dado1;
	private Dado dado2;

	public Juego(Dado dado1, Dado dado2) {
		this.dado1 = dado1;
		this.dado2 = dado2;
	}

	public boolean jugar() {
		int suma = dado1.lanzar() + dado2.lanzar();
		return suma == 7;
	}
}

class Simulador {
	private int numJuegos;
	private int juegosGanados;
	private int juegosPerdidos;
	private Dado dado1;
	private Dado dado2;

	public Simulador(int numJuegos) {
		this.numJuegos = numJuegos;
		this.juegosGanados = 0;
		this.juegosPerdidos = 0;
		this.dado1 = new Dado();
		this.dado2 = new Dado();
	}

	public void simular() {
		for (int i = 0; i < numJuegos; i++) {
			Juego juego = new Juego(dado1, dado2);
			if (juego.jugar()) {
				juegosGanados++;
			} else {
				juegosPerdidos++;
			}
		}
	}

	public void mostrarResultados() {
		int totalJuegos = juegosGanados + juegosPerdidos;
		double porcentajeGanados = ((double) juegosGanados / totalJuegos) * 100;
		double porcentajePerdidos = ((double) juegosPerdidos / totalJuegos) * 100;
		System.out.println("Resultados de la simulación:");
		System.out
				.println("Partidas ganadas: " + juegosGanados + " (" + String.format("%.2f", porcentajeGanados) + "%)");
		System.out.println(
				"Partidas perdidas: " + juegosPerdidos + " (" + String.format("%.2f", porcentajePerdidos) + "%)");
	}
}

public class Tarea01 {
	public static void main(String[] args) {
		Simulador simulador = new Simulador(10);
		simulador.simular();
		simulador.mostrarResultados();
	}
}
