public class Ejercicio2 {
	//Hilos Impresion
    public static void main(String[] args) {
        Thread hilo1 = new Thread(new Mensaje("Hilo 1"));
        Thread hilo2 = new Thread(new Mensaje("Hilo 2"));

        hilo1.start();
        hilo2.start();
    }
}

class Mensaje implements Runnable {
    private String mensaje;

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(mensaje + " - Mensaje " + (i + 1));
            try {
                Thread.sleep(500); // Pausa de 500 milisegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
