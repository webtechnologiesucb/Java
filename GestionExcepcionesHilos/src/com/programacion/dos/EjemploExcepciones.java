package com.programacion.dos;

import java.util.Scanner;

public class EjemploExcepciones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese un número:");

        try {
            // Intenta leer un número del usuario
            int numero = Integer.parseInt(scanner.nextLine());
            System.out.println("El número ingresado es: " + numero);
        } catch (NumberFormatException e) {
            // Captura la excepción si el usuario ingresa algo que no es un número
            System.out.println("Error: Ingrese un número válido.");
        } finally {
            // Se ejecuta siempre, independientemente de si ocurre una excepción o no
            scanner.close();
        }

        System.out.println("Fin del programa.");
    }
}

