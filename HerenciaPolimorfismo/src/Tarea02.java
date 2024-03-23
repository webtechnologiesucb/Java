import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

abstract class Juego01 {
	protected List<Integer> secuencia;

	public Juego01() {
		secuencia = new ArrayList<>();
		for (int i = 1; i <= 9; i++) {
			secuencia.add(i);
		}
		Collections.shuffle(secuencia);
		secuencia = secuencia.subList(0, 4);
	}

	public abstract int obtenerFamas(List<Integer> intento);

	public abstract int obtenerToques(List<Integer> intento);

	public abstract boolean esVictoria(int famas);

	public abstract String mensajeVictoria();

	public List<Integer> getSecuencia() {
		return secuencia;
	}
}

class ToqueYFamaJuego extends Juego01 {
	public ToqueYFamaJuego() {
		super();
	}

	@Override
	public int obtenerFamas(List<Integer> intento) {
		int famas = 0;
		for (int i = 0; i < 4; i++) {
			if (secuencia.get(i).equals(intento.get(i))) {
				famas++;
			}
		}
		return famas;
	}

	@Override
	public int obtenerToques(List<Integer> intento) {
		int toques = 0;
		for (int num : intento) {
			if (secuencia.contains(num) && !(secuencia.indexOf(num) == intento.indexOf(num))) {
				toques++;
			}
		}
		return toques;
	}

	@Override
	public boolean esVictoria(int famas) {
		return famas == 4;
	}

	@Override
	public String mensajeVictoria() {
		return "¡Felicidades! Has adivinado la secuencia correctamente.";
	}
}

public class Tarea02 {
	public static void main(String[] args) {
		ToqueYFamaJuego juego = new ToqueYFamaJuego();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Bienvenido al juego Toque y Fama!");
		System.out.println("Intenta adivinar la secuencia de 4 números diferentes entre 1 y 9.");

		int intentos = 0;
		while (true) {
			intentos++;
			System.out.println("Intento #" + intentos + ":");
			List<Integer> intento = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
				System.out.print("Ingrese el número " + (i + 1) + ": ");
				int num = scanner.nextInt();
				intento.add(num);
			}

			int famas = juego.obtenerFamas(intento);
			int toques = juego.obtenerToques(intento);

			System.out.println("Famas: " + famas);
			System.out.println("Toques: " + toques);

			if (juego.esVictoria(famas)) {
				System.out.println(juego.mensajeVictoria());
				break;
			}
		}
		scanner.close();
	}
}
