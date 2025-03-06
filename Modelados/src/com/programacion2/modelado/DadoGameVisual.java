package com.programacion2.modelado;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;

public class DadoGameVisual extends Application {
    // Generador de números aleatorios
    private final Random random = new Random();

    // Ruta a las imágenes de los dados
    private final String IMAGE_PATH = "/resources/images/";

    @Override
    public void start(Stage primaryStage) {
        // Imágenes iniciales de los dados
        ImageView vwDado1 = new ImageView();
        ImageView vwDado2 = new ImageView();
        vwDado1.setFitWidth(100); // Ajustar el tamaño de las imágenes
        vwDado1.setFitHeight(100);
        vwDado2.setFitWidth(100);
        vwDado2.setFitHeight(100);

        // Etiqueta para el resultado
        Label lblResultado = new Label("¡Lanza los dados!");

        // Botón para lanzar los dados
        Button btnLanzar = new Button("Lanzar Dados");
        btnLanzar.setOnAction(event -> {
            int dado1 = lanzar();
            int dado2 = lanzar();
            int suma = dado1 + dado2;

            Image imgDado1 = new Image(getClass().getResourceAsStream(IMAGE_PATH + "Dice-" + dado1 + ".png"));
            Image imgDado2 = new Image(getClass().getResourceAsStream(IMAGE_PATH + "Dice-" + dado2 + ".png"));

            if (imgDado1.isError() || imgDado2.isError()) {
                System.out.println("Error al cargar las imágenes de los dados.");
            } else {
                vwDado1.setImage(imgDado1);
                vwDado2.setImage(imgDado2);
            }

            // Verificar si ganas
            if (suma == 7) {
                lblResultado.setText("¡Felicidades! ¡Has ganado con una suma de 7!");
            } else {
                lblResultado.setText("La suma es " + suma + ". Intenta nuevamente.");
            }
        });

        // Diseño de la interfaz
        HBox bxDado = new HBox(10, vwDado1, vwDado2);
        bxDado.setStyle("-fx-alignment: center;");

        VBox root = new VBox(10, bxDado, btnLanzar, lblResultado);
        root.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-font-size: 14;");

        // Crear la escena
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Juego de Dados con Imágenes");
        primaryStage.setScene(scene);
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
