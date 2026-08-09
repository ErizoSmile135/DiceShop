package com.dice.shop.entity.good;

public enum ProductCategory {

    SET("Набор кубиков"),
    SINGLE("Одиночный кубик");

    private final String description;

    ProductCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}