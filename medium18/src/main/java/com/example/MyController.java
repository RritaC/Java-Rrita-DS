package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MyController {

    @FXML
    private TextField textField;

    @FXML
    private Label label;

    @FXML
    private void onButtonClick() {
        // Set the label text to the text entered in the TextField
        String inputText = textField.getText();
        if (inputText.isEmpty()) {
            label.setText("Please enter some text.");
        } else {
            label.setText(inputText);
        }
    }
}
