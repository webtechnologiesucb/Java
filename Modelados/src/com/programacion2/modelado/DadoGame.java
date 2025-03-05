package com.programacion2.modelado;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;

public class DadoGame extends Application {
    // Generador de números aleatorios
    private final Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        // Etiquetas para mostrar los valores de los dados
        Label lblDado1 = new Label("Dado 1: ?");
        Label lblDado2 = new Label("Dado 2: ?");
        Label lblResultado = new Label("¡Bienvenido al juego de dados!");

        // Botón para lanzar los dados
        Button btnLanzar = new Button("Lanzar Dados");
        btnLanzar.setOnAction(event -> {
            int dado1 = lanzar();
            int dado2 = lanzar();
            int sum = dado1 + dado2;

            // Actualizar los valores de los dados
            lblDado1.setText("Dado 1: " + dado1);
            lblDado2.setText("Dado 2: " + dado2);

            // Verificar si la suma es 7
            if (sum == 7) {
                lblResultado.setText("¡Felicidades! Has ganado. 🎉");
            } else {
                lblResultado.setText("La suma es " + sum + ". Intenta de nuevo.");
            }
        });

        // Diseño de la interfaz
        VBox raiz = new VBox(10, lblDado1, lblDado2, btnLanzar, lblResultado);
        raiz.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-font-size: 14;");

        // Crear la escena
        Scene escena = new Scene(raiz, 300, 200);

        primaryStage.setTitle("Juego de Dados - ¡Gana con 7!");
        primaryStage.setScene(escena);
        primaryStage.show();
    }

    // Método para lanzar un dado (número entre 1 y 6)
    private int lanzar() {
        return random.nextInt(6) + 1;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
