package com.dice.shop.entity.good;

import java.io.Serializable;
import java.util.Objects;

public class Good implements Serializable {

    private Long id;

    private String name;

    // Артикул товара
    private String code;

    // Производитель
    private String brand;

    // Набор или отдельный кубик
    private ProductCategory category;

    // d4, d6, d20 и т.д.
    private DiceType diceType;

    // Пластик, металл и т.д.
    private Material material;

    private double price;

    // Возрастное ограничение
    private int ageRestriction;


    public Good() {
    }

    public Good(Long id, String name, String code, String brand,
                ProductCategory category, DiceType diceType,
                Material material, double price, int ageRestriction) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.brand = brand;
        this.category = category;
        this.diceType = diceType;
        this.material = material;
        this.price = price;
        this.ageRestriction = ageRestriction;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }


    public DiceType getDiceType() {
        return diceType;
    }

    public void setDiceType(DiceType diceType) {
        this.diceType = diceType;
    }


    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }


    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", brand='" + brand + '\'' +
                ", category=" + category +
                ", diceType=" + diceType +
                ", material=" + material +
                ", price=" + price +
                ", ageRestriction=" + ageRestriction +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Good good)) return false;
        return Objects.equals(id, good.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}