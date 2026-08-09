package com.dice.shop.controller;

import com.dice.shop.entity.DiceType;
import com.dice.shop.entity.Good;
import com.dice.shop.entity.ProductCategory;
import com.dice.shop.entity.Material;
import com.dice.shop.service.GoodService;
import java.util.Scanner;

public class GoodController {
    private final GoodService goodService;
    private final Scanner scanner = new Scanner(System.in);

    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    public void showAll() {
        System.out.println("\n=== Список товаров ===");

        goodService.findAll()
                .forEach(System.out::println);
    }

    public void add() {
        System.out.println("\n=== Добавление товара ===");

        System.out.print("Название: ");
        String name = scanner.nextLine();

        System.out.print("Код товара: ");
        String code = scanner.nextLine();

        System.out.print("Бренд: ");
        String brand = scanner.nextLine();

        System.out.print("Цена: ");
        double price = Double.parseDouble(scanner.nextLine());

        Good good = new Good();

        good.setName(name);
        good.setCode(code);
        good.setBrand(brand);
        good.setPrice(price);
        good.setMaterial(selectMaterial());

        ProductCategory category = selectCategory();
        good.setCategory(category);

        if(category == ProductCategory.SINGLE){
            good.setDiceType(selectDiceType());
        } else if(category == ProductCategory.SET){
            good.setDiceType(DiceType.SET);
        }

        goodService.add(good);

        System.out.println("Товар добавлен");
    }

    public void delete() {
        System.out.println("\n=== Удаление товара ===");

        System.out.print("ID товара: ");

        Long id = Long.parseLong(scanner.nextLine());
        goodService.delete(id);

        System.out.println("Товар удален");
    }

    private ProductCategory selectCategory() {
        System.out.println("\nВыберите категорию:");
        System.out.println("1. Набор кубиков");
        System.out.println("2. Одиночный кубик");

        System.out.print("\nВаш выбор: ");
        String choice = scanner.nextLine();

        //switch expression... Можно возвращать значение сразу
        return switch (choice) {
            case "1" -> ProductCategory.SET;
            case "2" -> ProductCategory.SINGLE;
            default -> throw new IllegalArgumentException(
                    "Неизвестная категория"
            );
        };
    }

    private Material selectMaterial() {
        System.out.println("\nВыберите материал:");
        System.out.println("1. " + Material.PLASTIC);
        System.out.println("2. " + Material.METAL);
        System.out.println("3. " + Material.RESIN);
        System.out.println("4. " + Material.WOOD);
        System.out.println("5. " + Material.STONE);

        System.out.print("\nВаш выбор: ");
        String choice = scanner.nextLine();

        return switch (choice) {
            case "1" -> Material.PLASTIC;
            case "2" -> Material.METAL;
            case "3" -> Material.RESIN;
            case "4" -> Material.WOOD;
            case "5" -> Material.STONE;
            default -> throw new IllegalArgumentException(
                    "Неизвестный материал"
            );
        };
    }

    public void update() {
        System.out.println("\n=== Изменение товара ===");

        System.out.print("ID товара: ");
        Long id = Long.parseLong(scanner.nextLine());
        Good good = goodService.findById(id);

        if (good == null) {
            System.out.println("Товар не найден");
            return;
        }

        System.out.println("Текущий товар:");
        System.out.println(good);

        System.out.print("Новое название: ");
        String name = scanner.nextLine();

        System.out.print("Новый код: ");
        String code = scanner.nextLine();

        System.out.print("Новый бренд: ");
        String brand = scanner.nextLine();

        System.out.print("Новая цена: ");
        double price = Double.parseDouble(scanner.nextLine());

        good.setName(name);
        good.setCode(code);
        good.setBrand(brand);
        good.setPrice(price);
        good.setCategory(selectCategory());
        good.setMaterial(selectMaterial());

        goodService.update(good);

        System.out.println("Товар обновлен");
    }

    public void filter() {
        System.out.println("\n=== Фильтр товаров ===");

        System.out.println("1. По категории");
        System.out.println("2. По цене");
        System.out.println("3. Категория + цена");
        System.out.println("4. Сортировка по цене");

        System.out.print("\nВаш выбор: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                filterByCategory();
                break;
            case "2":
                filterByPrice();
                break;
            case "3":
                filterByCategoryAndPrice();
                break;
            case "4":
                sortByPrice();
                break;
            default:
                System.out.println("Неверный выбор");
        }
    }

    private void filterByCategory() {
        ProductCategory category = selectCategory();

        goodService.findByCategory(category)
                .forEach(System.out::println);
    }

    private void filterByPrice() {
        System.out.print("Минимальная цена: ");
        double min = Double.parseDouble(scanner.nextLine());

        System.out.print("Максимальная цена: ");
        double max = Double.parseDouble(scanner.nextLine());

        goodService.findByPriceRange(min, max)
                .forEach(System.out::println);
    }

    private void filterByCategoryAndPrice() {
        ProductCategory category = selectCategory();

        System.out.print("Минимальная цена: ");
        double min = Double.parseDouble(scanner.nextLine());

        System.out.print("Максимальная цена: ");
        double max = Double.parseDouble(scanner.nextLine());

        goodService.findByCategoryAndPrice(
                category,
                min,
                max
        ).forEach(System.out::println);
    }

    private void sortByPrice() {
        System.out.println("1. По возрастанию");
        System.out.println("2. По убыванию");

        System.out.print("\nВаш выбор: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                goodService.sortByPriceAscending()
                        .forEach(System.out::println);
                break;
            case "2":
                goodService.sortByPriceDescending()
                        .forEach(System.out::println);
                break;
            default:
                System.out.println("Неверный выбор");
        }
    }

    private DiceType selectDiceType() {
        System.out.println("Выберите тип кубика:");

        System.out.println("1. " + DiceType.D4);
        System.out.println("2. " + DiceType.D6);
        System.out.println("3. " + DiceType.D8);
        System.out.println("4. " + DiceType.D10);
        System.out.println("5. " + DiceType.D12);
        System.out.println("6. " + DiceType.D20);
        System.out.println("7. " + DiceType.D100);

        System.out.print("\nВаш выбор: ");
        String choice = scanner.nextLine();

        return switch(choice){
            case "1" -> DiceType.D4;
            case "2" -> DiceType.D6;
            case "3" -> DiceType.D8;
            case "4" -> DiceType.D10;
            case "5" -> DiceType.D12;
            case "6" -> DiceType.D20;
            case "7" -> DiceType.D100;
            default -> throw new IllegalArgumentException(
                    "Неизвестный тип кубика"
            );
        };
    }
}