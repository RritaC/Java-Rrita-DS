package com.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Product Management");

        // Top Menu
        HBox topMenu = new HBox(10);
        topMenu.setPadding(new Insets(15, 12, 15, 12));
        topMenu.setStyle("-fx-background-color: #173F5F;");
        Button button1 = new Button("File");
        Button button2 = new Button("Edit");
        Button button3 = new Button("View");
        topMenu.getChildren().addAll(button1, button2, button3);

        // Left Menu
        VBox leftMenu = new VBox(10);
        leftMenu.setPadding(new Insets(15, 12, 15, 12));
        leftMenu.setStyle("-fx-background-color: #20639B;");
        Button button4 = new Button("Sales");
        Button button5 = new Button("Marketing");
        Button button6 = new Button("Costs");
        leftMenu.getChildren().addAll(button4, button5, button6);

        // GridPane for Input Fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // Input Fields and Labels
        TextField productNameInput = addLabelAndField(grid, "Product Name:", 0);
        TextField productCategoryInput = addLabelAndField(grid, "Product Category:", 1);
        TextField productPriceInput = addLabelAndField(grid, "Product Price:", 2);
        TextField productStatusInput = addLabelAndField(grid, "Product Status:", 3);

        // ComboBox for Product Type
        ComboBox<String> productType = new ComboBox<>(FXCollections.observableArrayList(
                "Simple product", "Grouped product", "Affiliate product", "Variable product"));
        productType.setPrefSize(150, 20);
        grid.add(new Label("Product Type:"), 0, 4);
        grid.add(productType, 1, 4);

        // Checkboxes
        CheckBox virtualCheckBox = new CheckBox("Virtual Product");
        grid.add(virtualCheckBox, 1, 5);
        CheckBox downloadableCheckBox = new CheckBox("Downloadable Product");
        grid.add(downloadableCheckBox, 1, 6);

        // Error Labels (Initially Hidden)
        Label nameError = createErrorLabel(grid, 2, 0);
        Label categoryError = createErrorLabel(grid, 2, 1);
        Label priceError = createErrorLabel(grid, 2, 2);
        Label statusError = createErrorLabel(grid, 2, 3);

        // Save Button to Trigger Validation
        Button saveButton = new Button("Save");
        grid.add(saveButton, 1, 7);

        saveButton.setOnAction(e -> {
            validateInputs(productNameInput, nameError, "[A-Za-z\\s]+");
            validateInputs(productCategoryInput, categoryError, "[A-Za-z\\s]+");
            validateInputs(productPriceInput, priceError, "\\d{0,7}(\\.\\d{0,4})?");
            validateInputs(productStatusInput, statusError, "[A-Za-z\\s]+");
        });

        // Layout with BorderPane
        BorderPane layout = new BorderPane();
        layout.setTop(topMenu);
        layout.setLeft(leftMenu);
        layout.setCenter(grid);

        Scene scene = new Scene(layout, 900, 700);
        window.setScene(scene);
        window.show();
    }

    // Helper Method to Create Input Fields with Labels
    private TextField addLabelAndField(GridPane grid, String labelText, int row) {
        Label label = new Label(labelText);
        TextField textField = new TextField();
        grid.add(label, 0, row);
        grid.add(textField, 1, row);
        return textField;
    }

    // Helper Method to Create Error Labels
    private Label createErrorLabel(GridPane grid, int col, int row) {
        Label errorLabel = new Label("Invalid input!");
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setVisible(false);
        grid.add(errorLabel, col, row);
        return errorLabel;
    }

    // Validation Method to Check Inputs with Regex
    private void validateInputs(TextField input, Label errorLabel, String regex) {
        if (!input.getText().matches(regex)) {
            errorLabel.setVisible(true); // Show error if input is invalid
        } else {
            errorLabel.setVisible(false); // Hide error if input is valid
            System.out.println(input.getText());
        }
    }
}
