package com;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class UIComponent {
    static ComboBox<String> productComboBox = new ComboBox<>();
    static CheckBox virtualCheckBox = new CheckBox("Virtual CheckBox");
    static CheckBox downloadabledCheckBox = new CheckBox("Download CheckBox");
    static ListView<String> attributeListView = new ListView<>();
    static Button saveButton = new Button();

    public static void addComponents(GridPane grid) {
        productComboBox.getItems().addAll("Simple", "Grouped", "Affiliate", "Variable");
        productComboBox.setValue("Attributes");
        grid.add(productComboBox, 1, 1);

        grid.add(virtualCheckBox, 1, 2);
        grid.add(virtualCheckBox, 1, 3);

        ObservableList<String> attributes = FXCollections.observableArrayList(
                "Color", "Size", "Brand", "Type");
        attributeListView.setItems(attributes);
        attributeListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        grid.add(attributeListView, 1, 4);

    }
}
