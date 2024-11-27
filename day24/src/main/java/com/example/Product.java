package com.example;

import javafx.collections.ObservableList;

public class Product {
    private String name;
    private String category;
    private String price;
    private String status;
    private String productType;
    private String extraProductType;
    private ObservableList<String> attributes;


    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }

    public String getExtraProductType() { return extraProductType; }
    public void setExtraProductType(String extraProductType) { this.extraProductType = extraProductType; }

    public ObservableList<String> getAttributes() { return attributes; }
    public void setAttributes(ObservableList<String> attributes) { this.attributes = attributes; }

    
}
