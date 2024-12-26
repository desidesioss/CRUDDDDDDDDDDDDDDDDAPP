package org.example;

import com.crudapp.controller.EntityController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        EntityController controller = new EntityController();

        BorderPane root = new BorderPane();
        root.setTop(controller.createTopBar());
        root.setCenter(controller.createEntityListView());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("CRUD Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
