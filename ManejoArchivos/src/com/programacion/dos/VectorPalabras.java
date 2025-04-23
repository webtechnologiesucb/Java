package com.programacion.dos;

import javax.swing.JOptionPane;

public class VectorPalabras {
    private String[] nombres;

    public VectorPalabras(int tam) {
        nombres = new String[tam];
    }

    public void llenarVector() {
        for (int i = 0; i < nombres.length; i++) {
            nombres[i] = JOptionPane.showInputDialog("Ingrese el nombre " + (i + 1) + ":");
        }
    }

    public void mostrarVector() {
        StringBuilder listado = new StringBuilder("Nombres ingresados:\n");
        for (String nombre : nombres) {
            listado.append(nombre).append("\n");
        }
        JOptionPane.showMessageDialog(null, listado.toString());
    }

    public void buscarPalabra() {
        String busqueda = JOptionPane.showInputDialog("Ingrese una palabra a buscar:");
        boolean encontrado = false;
        StringBuilder nomEncontrado = new StringBuilder("Palabras encontradas:\n");
        for (String nombre : nombres) {
            if (nombre.equalsIgnoreCase(busqueda)) {
                nomEncontrado.append(nombre).append("\n");
                encontrado = true;
            }
        }

        if (encontrado) {
            JOptionPane.showMessageDialog(null, nomEncontrado.toString());
        } else {
            JOptionPane.showMessageDialog(null, "La palabra buscada no existe en el vector.");
        }
    }

    public void mostrarPalindromos() {
        StringBuilder palindromos = new StringBuilder("Nombres palíndromos:\n");
        for (String nombre : nombres) {
            if (esPalindromo(nombre)) {
                palindromos.append(nombre).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, palindromos.toString());
    }

    private boolean esPalindromo(String palabra) {
        int izq = 0;
        int der = palabra.length() - 1;
        while (izq < der) {
            if (palabra.charAt(izq) != palabra.charAt(der)) {
                return false;
            }
            izq++;
            der--;
        }
        return true;
    }

    public static void main(String[] args) {
        int tamaño = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de nombres:"));
        VectorPalabras array = new VectorPalabras(tamaño);
        array.llenarVector();
        array.mostrarVector();
        array.buscarPalabra();
        array.mostrarPalindromos();
    }
}