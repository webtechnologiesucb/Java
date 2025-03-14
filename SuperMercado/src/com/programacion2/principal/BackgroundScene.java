package com.programacion2.principal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BackgroundScene extends Application {

	@Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        dibujarArbolFractal(gc, 400, 550, -90, 10);

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Arbol Fractal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void dibujarArbolFractal(GraphicsContext gc, double x1, double y1, double angulo, int profundidad) {
        if (profundidad == 0) return;

        double longitudRama = profundidad * 10;
        double x2 = x1 + longitudRama * Math.cos(Math.toRadians(angulo));
        double y2 = y1 + longitudRama * Math.sin(Math.toRadians(angulo));

        gc.strokeLine(x1, y1, x2, y2);
        dibujarArbolFractal(gc, x2, y2, angulo - 30, profundidad - 1);
        dibujarArbolFractal(gc, x2, y2, angulo + 30, profundidad - 1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

