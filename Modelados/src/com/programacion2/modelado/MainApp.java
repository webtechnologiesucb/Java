package com.programacion2.modelado;

import com.programacion2.dao.ActorDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.programacion2.modelo.Actor;
import java.sql.SQLException;

public class MainApp extends Application {
    private TableView<Actor> table;
    private ObservableList<Actor> actorList;
    private ActorDAO actorDAO;

    @Override
    public void start(Stage primaryStage) {
        actorDAO = new ActorDAO();
        table = new TableView<>();
        actorList = FXCollections.observableArrayList();

        // Configurar columnas de la tabla
        TableColumn<Actor, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getActorId()));

        TableColumn<Actor, String> colFirstName = new TableColumn<>("Nombre");
        colFirstName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFirstName()));

        TableColumn<Actor, String> colLastName = new TableColumn<>("Apellido");
        colLastName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLastName()));
        
        TableColumn<Actor, String> colLastUpdate = new TableColumn<>("Fecha Registro");
        colLastUpdate.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLastUpdate()));
        
        table.getColumns().addAll(colId, colFirstName, colLastName, colLastUpdate);

        // Cargar datos
        try {
            actorList.addAll(actorDAO.getAllActors());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table.setItems(actorList);

        // Layout
        VBox root = new VBox(table);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("CRUD Actores - Sakila");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}