package evaluacion.ucb;

// Polimorfismo
// clase base
class Vehiculo {
	public void acelerar() {
		System.out.println("El vehiculo esta acelerando ...");
	}
}
//clase derivada
class Auto extends Vehiculo{
	@Override
	public void acelerar() {
		System.out.println("El auto esta acelerando a 150 km/h ...");
	}
}

//clase derivada
class Moto extends Vehiculo{
	@Override
	public void acelerar() {
		System.out.println("La moto esta acelerando a 120 km/h ...");
	}
}


public class Carrera {

	public static void main(String[] args) { // aplicar el polimorfismo
		Vehiculo auto = new Auto(); // clase base = new clase derivada
		Vehiculo moto = new Moto(); 
		auto.acelerar();
		moto.acelerar();
	}
}
