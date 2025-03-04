public class Ejercicio3 {
	// Calculo Concurrente
    public static void main(String[] args) {
        double[] numeros = {16, 25, -9, 36, 49};

        for (double numero : numeros) {
            Thread hilo = new Thread(new Calculo(numero));
            hilo.start();
        }
    }
}

class Calculo implements Runnable {
    private double numero;

    public Calculo(double numero) {
        this.numero = numero;
    }

    @Override
    public void run() {
        try {
            if (numero < 0) {
                throw new IllegalArgumentException("No se puede calcular la raíz cuadrada de un número negativo.");
            }
            double resultado = Math.sqrt(numero);
            System.out.println("La raíz cuadrada de " + numero + " es: " + resultado);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
