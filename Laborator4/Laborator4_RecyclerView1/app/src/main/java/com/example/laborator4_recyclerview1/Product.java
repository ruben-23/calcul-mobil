package com.example.laborator4_recyclerview1;

public class Product {
    private String name;
    private String details;
    private int imageResId;

    public Product(String name, String details, int imageResId) {
        this.name = name;
        this.details = details;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public String getDetails() { return details; }
    public int getImageResId() { return imageResId; }
}