package com.programacion.dos;

class MiHilo extends Thread {
    private String nombre;

    public MiHilo(String nombre) {
        this.nombre = nombre;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Hilo " + nombre + ": " + i);
            try {
                Thread.sleep(1000); // Hilo pausado durante 1 segundo
            } catch (InterruptedException e) {
                System.out.println("Hilo " + nombre + " interrumpido.");
            }
        }
        System.out.println("Hilo " + nombre + " terminado.");
    }
}

public class EjemploHilos {
    public static void main(String[] args) {
        System.out.println("Inicio del programa principal.");

        // Creación de dos instancias de MiHilo
        MiHilo hilo1 = new MiHilo("A");
        MiHilo hilo2 = new MiHilo("B");

        // Inicio de los hilos
        hilo1.start();
        hilo2.start();

        // Espera a que ambos hilos terminen antes de continuar con el programa principal
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            System.out.println("Hilo principal interrumpido.");
        }

        System.out.println("Fin del programa principal.");
    }
}

