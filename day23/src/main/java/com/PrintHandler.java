package com;

import javafx.collections.ObservableList;

public class PrintHandler {
    public static void printOutValues() {
        System.out.println("Test");

        String productType = UIComponents.productComboBox.getValue();

        String extraTypes = "";
        if (UIComponents.virtualCheckBox.isSelected()) {
            extraTypes += "Virtual";
        }

        if (UIComponents.downloadableCheckBox.isSelected()) {
            extraTypes += "Download";
        }

        ObservableList<String> selectedAttributes = UIComponents.attributeListView.getSelectionModel()
                .getSelectedItems();

        String attributes = String.join(",", selectedAttributes);

        System.out.println("Product Type: " + productType);
        System.out.println("Extra Type: " + extraTypes);
        System.out.println("Attributes: " + attributes);

    }
}
