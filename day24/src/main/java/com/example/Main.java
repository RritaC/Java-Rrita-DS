package com.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    TableView<Product> table;
    private final Font globalFont = Font.font("Arial", 16); // Define a global font

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
        button1.setFont(globalFont);
        Button button2 = new Button("Edit");
        button2.setFont(globalFont);
        Button button3 = new Button("View");
        button3.setFont(globalFont);
        topMenu.getChildren().addAll(button1, button2, button3);

        // Left Menu
        VBox leftMenu = new VBox(10);
        leftMenu.setPadding(new Insets(15, 12, 15, 12));
        leftMenu.setStyle("-fx-background-color: #20639B;");
        Button button4 = new Button("Sales");
        button4.setFont(globalFont);
        Button button5 = new Button("Marketing");
        button5.setFont(globalFont);
        Button button6 = new Button("Costs");
        button6.setFont(globalFont);
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
        productType.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;"); // Set font via CSS for ComboBox
        Label productTypeLabel = new Label("Product Type:");
        productTypeLabel.setFont(globalFont);
        grid.add(productTypeLabel, 0, 4);
        grid.add(productType, 1, 4);

        // Checkboxes
        CheckBox virtualCheckBox = new CheckBox("Virtual Product");
        virtualCheckBox.setFont(globalFont);
        grid.add(virtualCheckBox, 1, 5);
        CheckBox downloadableCheckBox = new CheckBox("Downloadable Product");
        downloadableCheckBox.setFont(globalFont);
        grid.add(downloadableCheckBox, 1, 6);

        // create the listview
        ListView<String> attributes = new ListView<>();
        attributes.getItems().addAll(
                "Color",
                "Size");

        attributes.setPrefSize(150, 200);
        attributes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        grid.add(attributes, 1, 7);

        // Error Labels (Initially Hidden)
        Label nameError = createErrorLabel(grid, 2, 0);
        Label categoryError = createErrorLabel(grid, 2, 1);
        Label priceError = createErrorLabel(grid, 2, 2);
        Label statusError = createErrorLabel(grid, 2, 3);

        // Save Button to Trigger Validation
        Button saveButton = new Button("Save");
        saveButton.setFont(globalFont);
        grid.add(saveButton, 1, 8);

        saveButton.setOnAction(e -> {
            validateInputs(productNameInput, nameError, "[A-Za-z\\s]+");
            validateInputs(productCategoryInput, categoryError, "[A-Za-z\\s]+");
            validateInputs(productPriceInput, priceError, "\\d{0,7}(\\.\\d{0,4})?");
            validateInputs(productStatusInput, statusError, "[A-Za-z\\s]+");
        });

        // TableView for Products
        table = new TableView<>();
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;"); // Set font for TableColumn header

        TableColumn<Product, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setMinWidth(100);
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");

        TableColumn<Product, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");

        TableColumn<Product, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMinWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");

        TableColumn<Product, String> extraTypesColumn = new TableColumn<>("Extra Types");
        extraTypesColumn.setMinWidth(100);
        extraTypesColumn.setCellValueFactory(new PropertyValueFactory<>("extra types"));
        extraTypesColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");

        table.getColumns().addAll(nameColumn, categoryColumn, priceColumn, statusColumn, extraTypesColumn);

        VBox container = new VBox();
        container.getChildren().addAll(grid, table);

        // Layout with BorderPane
        BorderPane layout = new BorderPane();
        layout.setTop(topMenu);
        layout.setLeft(leftMenu);
        layout.setCenter(container);

        Scene scene = new Scene(layout, 900, 700);
        window.setScene(scene);
        window.show();
    }

    // Helper Method to Create Input Fields with Labels
    private TextField addLabelAndField(GridPane grid, String labelText, int row) {
        Label label = new Label(labelText);
        label.setFont(globalFont); // Apply the global font to each label
        TextField textField = new TextField();
        textField.setFont(globalFont); // Apply the global font to each text field
        grid.add(label, 0, row);
        grid.add(textField, 1, row);
        return textField;
    }

    // Helper Method to Create Error Labels
    private Label createErrorLabel(GridPane grid, int col, int row) {
        Label errorLabel = new Label("Invalid input!");
        errorLabel.setFont(globalFont); // Apply the global font to error labels
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
