package com.example;

import javafx.collections.ObservableList;

public class PrintHandler {
    public static void printOutValues() {
        // System.out.println("Test");
        String productType = UIComponents.productComboBox.getValue();

        String extraType = "";
        if (UIComponents.virtualCheckBox.isSelected()){
            extraType += "Virtual ";    
        }

        if (UIComponents.downloadableCheckBox.isSelected()){
            extraType += "Downloadable ";    
        }

        ObservableList<String> selectedAttributes = UIComponents.attributeListView.getSelectionModel().getSelectedItems();

        String attributes = String.join(", ", selectedAttributes);

        System.out.println("Product Type: " + productType);
        System.out.println("Extra Type: " + extraType);
        System.out.println("Attributes: " + attributes);
    }
}
