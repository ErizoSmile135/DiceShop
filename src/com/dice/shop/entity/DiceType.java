package com.dice.shop.entity;

public enum DiceType {

    D4("Кубик D4 (четырехгранник)"),
    D6("Кубик D6 (шестигранник)"),
    D8("Кубик D8 (восьмигранник)"),
    D10("Кубик D10 (десятигранник)"),
    D12("Кубик D12 (двенадцатигранник)"),
    D20("Кубик D20 (двадцатигранник)"),
    D100("Кубик D100 (процентник)"),
    SET("Набор из нескольких кубиков");

    private final String description;

    DiceType(String description) {
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