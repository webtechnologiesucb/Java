package com.programacion.dos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestionArchivosTexto {
	public static void main(String[] args) {
		String nombreArchivo = "ejemplo.txt";

		// Escribir en el archivo
		escribirArchivo(nombreArchivo);

		// Leer el archivo
		leerArchivo(nombreArchivo);
	}

	public static void escribirArchivo(String nombreArchivo) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
			writer.write("Este es un ejemplo de escritura en un archivo de texto.\n");
			writer.write("Podemos escribir varias líneas también.\n");
			writer.write("¡Es muy fácil escribir en archivos de texto en Java!");
			System.out.println("Archivo creado y contenido escrito con éxito.");
		} catch (IOException e) {
			System.err.println("Error al escribir en el archivo: " + e.getMessage());
		}
	}

	public static void leerArchivo(String nombreArchivo) {
		File archivo = new File(nombreArchivo);

		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
			String linea;
			System.out.println("\nLeyendo contenido del archivo:\n");
			while ((linea = reader.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
		}
	}
}
