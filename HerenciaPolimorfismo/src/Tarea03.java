import java.util.Random;

abstract class Animal {
    protected int edad;

    public Animal() {
        Random rand = new Random();
        this.edad = rand.nextInt(10); // Edad aleatoria entre 0 y 9 años
    }

    public abstract String getCategoriaEdad();

    public int getEdad() {
        return edad;
    }
}

class Elefante extends Animal {
    public Elefante() {
        super();
    }

    @Override
    public String getCategoriaEdad() {
        if (edad <= 1) {
            return "0-1 año";
        } else if (edad > 1 && edad < 3) {
            return "1-3 años";
        } else {
            return "3 o más años";
        }
    }
}

class Jirafa extends Animal {
    public Jirafa() {
        super();
    }

    @Override
    public String getCategoriaEdad() {
        if (edad <= 1) {
            return "0-1 año";
        } else if (edad > 1 && edad < 3) {
            return "1-3 años";
        } else {
            return "3 o más años";
        }
    }
}

class Chimpanze extends Animal {
    public Chimpanze() {
        super();
    }

    @Override
    public String getCategoriaEdad() {
        if (edad <= 1) {
            return "0-1 año";
        } else if (edad > 1 && edad < 3) {
            return "1-3 años";
        } else {
            return "3 o más años";
        }
    }
}

public class Tarea03 {
    public static void main(String[] args) {
        int totalElefantes = 20;
        int totalJirafas = 15;
        int totalChimpances = 40;

        Animal[] animales = new Animal[totalElefantes + totalJirafas + totalChimpances];
        for (int i = 0; i < totalElefantes; i++) {
            animales[i] = new Elefante();
        }
        for (int i = totalElefantes; i < totalElefantes + totalJirafas; i++) {
            animales[i] = new Jirafa();
        }
        for (int i = totalElefantes + totalJirafas; i < totalElefantes + totalJirafas + totalChimpances; i++) {
            animales[i] = new Chimpanze();
        }

        int contador0a1 = 0;
        int contador1a3 = 0;
        int contador3omas = 0;

        for (Animal animal : animales) {
            String categoria = animal.getCategoriaEdad();
            switch (categoria) {
                case "0-1 año":
                    contador0a1++;
                    break;
                case "1-3 años":
                    contador1a3++;
                    break;
                case "3 o más años":
                    contador3omas++;
                    break;
            }
        }

        int totalAnimales = totalElefantes + totalJirafas + totalChimpances;
        double porcentaje0a1 = (double) contador0a1 / totalAnimales * 100;
        double porcentaje1a3 = (double) contador1a3 / totalAnimales * 100;
        double porcentaje3omas = (double) contador3omas / totalAnimales * 100;

        System.out.println("Porcentaje de animales por categoría de edad:");
        System.out.println("0-1 año: " + porcentaje0a1 + "%");
        System.out.println("1-3 años: " + porcentaje1a3 + "%");
        System.out.println("3 o más años: " + porcentaje3omas + "%");
    }
}

