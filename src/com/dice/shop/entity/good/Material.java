package com.dice.shop.entity.good;

public enum Material {

    PLASTIC("Пластик"),
    METAL("Металл"),
    RESIN("Смола"),
    STONE("Камень"),
    WOOD("Дерево");

    private final String description;

    Material(String description) {
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