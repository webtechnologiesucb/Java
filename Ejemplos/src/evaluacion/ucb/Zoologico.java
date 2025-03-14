package evaluacion.ucb;

// Clase base - superclase
class Animal {
	protected String nombre; // atributo nombre
	 
	public Animal(String nom) { // constructor
		this.nombre = nom;
	}
	
	//metodo hacerSonido
	public void hacerSonido() {
		System.out.println("Este animal emite un sonido...");
	}
}

// Lobo - Clase derivada - subclase
class Lobo extends Animal{
	public Lobo(String nom) { // constructor de Animal(nom)
		super(nom); 
	}
	
	//metodo hacerSonido @Override sobreescribir el metodo
	@Override
	public void hacerSonido() {
		System.out.println("Dice: au, au, au!");
	}
}

//Tigre - Clase derivada - subclase
class Tigre extends Animal{
	public Tigre(String nom) { // constructor de Animal(nom)
		super(nom); 
	}
	
	//metodo hacerSonido @Override
	@Override
	public void hacerSonido() {
		System.out.println("Dice: grr, grr, grr!");
	}
}

public class Zoologico {
	public static void main(String[] args) {
		Lobo lobo = new Lobo("Alfa");
		lobo.hacerSonido();
		Tigre tigre = new Tigre("X");
		tigre.hacerSonido();
	}
}