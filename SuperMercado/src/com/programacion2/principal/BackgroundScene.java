package com.programacion2.principal;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BackgroundScene extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        // Crear el carrito (rectángulo)
        Rectangle carrito = new Rectangle(50, 30, Color.BLUE);
        carrito.setX(50);
        carrito.setY(100);

        // Crear la persona (círculo)
        Circle persona = new Circle(15, Color.RED);
        persona.setCenterX(100);
        persona.setCenterY(200);

        pane.getChildren().addAll(carrito, persona);

        // Animación para el carrito
        Timeline animacionCarrito = new Timeline(
                new KeyFrame(Duration.millis(50), e -> {
                    carrito.setX(carrito.getX() + 2); // Movimiento hacia la derecha
                    if (carrito.getX() > pane.getWidth()) {
                        carrito.setX(0); // Regresar al inicio cuando sale del borde
                    }
                })
        );
        animacionCarrito.setCycleCount(Timeline.INDEFINITE);
        animacionCarrito.play();

        // Animación para la persona
        Timeline animacionPersona = new Timeline(
                new KeyFrame(Duration.millis(100), e -> {
                    persona.setCenterY(persona.getCenterY() - 1); // Movimiento hacia arriba
                    if (persona.getCenterY() < 0) {
                        persona.setCenterY(pane.getHeight()); // Regresar al inicio cuando sale del borde
                    }
                })
        );
        animacionPersona.setCycleCount(Timeline.INDEFINITE);
        animacionPersona.play();

        Scene scene = new Scene(pane, 800, 400);
        primaryStage.setTitle("Supermercado Animado");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
