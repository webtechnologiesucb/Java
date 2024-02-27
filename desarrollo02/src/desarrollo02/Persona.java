/**
 * Ejemplo completo de clase Persona
 */
package desarrollo02;

import java.util.Objects;

/**
 * Clase Persona de ejemplo
 */
public class Persona {
	private String nombre;
	private int edad;
	
	public Persona() {
		this.nombre = "";
		this.edad = 0;
	}
	
	public Persona(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", edad=" + edad + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(edad, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Persona other = (Persona) obj;
		return edad == other.edad && Objects.equals(nombre, other.nombre);
	}

	/**
	 * @param args Argumentos del metodo main
	 */
	public static void main(String[] args) {
        Persona p1 = new Persona();
        p1.setNombre("Carolina");
        p1.setEdad(22);
        System.out.println(p1.toString());
        System.out.println(p1.hashCode());
        
        Persona p2 = new Persona();
        p2.setNombre("Valentina");
        p2.setEdad(22);
        System.out.println(p2.toString());
        System.out.println(p2.hashCode());
        
        System.out.println(p1.equals(p2));
	}
}
