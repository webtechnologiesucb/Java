package com.programacion.dos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class GestionArchivosBinarios {
    public static void main(String[] args) {
        String nombreArchivo = "datos.bin";

        // Escribir en el archivo binario
        escribirArchivoBinario(nombreArchivo);

        // Leer el archivo binario
        leerArchivoBinario(nombreArchivo);
    }

    public static void escribirArchivoBinario(String nombreArchivo) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombreArchivo))) {
            dos.writeInt(42); // Escribir un entero
            dos.writeDouble(3.14); // Escribir un número de punto flotante de doble precisión
            dos.writeUTF("Hola, mundo!"); // Escribir una cadena en formato UTF-8
            System.out.println("Archivo binario creado y contenido escrito con éxito.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo binario: " + e.getMessage());
        }
    }

    public static void leerArchivoBinario(String nombreArchivo) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(nombreArchivo))) {
            int entero = dis.readInt(); // Leer un entero
            double decimal = dis.readDouble(); // Leer un número de punto flotante de doble precisión
            String cadena = dis.readUTF(); // Leer una cadena en formato UTF-8
            System.out.println("\nLeyendo contenido del archivo binario:");
            System.out.println("Entero: " + entero);
            System.out.println("Decimal: " + decimal);
            System.out.println("Cadena: " + cadena);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo binario: " + e.getMessage());
        }
    }
}
