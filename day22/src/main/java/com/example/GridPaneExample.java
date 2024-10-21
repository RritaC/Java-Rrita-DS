package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GridPaneExample extends Application {

    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        grid.add(new Label("Prodcut Name"), 1, 2);
        grid.add(new TextField(), 2, 2);

        Scene scene = new Scene(grid, 500, 500);
        stage.setScene(scene);
        stage.setTitle("GridExample");
        stage.show();
    }

}
