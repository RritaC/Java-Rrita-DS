package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MyController {

    @FXML
    private Label label;

    @FXML
    private void onButtonClick() {
        label.setText("Button clicked");
    }
}
