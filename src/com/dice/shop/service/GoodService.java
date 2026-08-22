package com.dice.shop.service;

import com.dice.shop.entity.Good;
import com.dice.shop.entity.ProductCategory;
import com.dice.shop.repository.GoodRepository;
import java.util.List;
import com.dice.shop.exception.GoodNotFoundException;
import java.util.Comparator;

public class GoodService {

    private final GoodRepository goodRepository;

    public GoodService(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

    public void add(Good good) {
        good.setId(generateId());
        goodRepository.save(good);
    }

    public List<Good> findAll() {
        return goodRepository.findAll();
    }

    public Good findById(Long id) {
        return goodRepository.findById(id);
    }

    public List<Good> findByCategory(ProductCategory category) {
        return goodRepository.findByCategory(category);
    }

    public void update(Good good) {
        if (goodRepository.findById(good.getId()) == null) {
            throw new GoodNotFoundException(
                    "Товар с id " + good.getId() + " не найден"
            );
        }
        goodRepository.update(good);
    }

    public void delete(Long id) {
        if (goodRepository.findById(id) == null) {
            throw new GoodNotFoundException(
                    "Товар с id " + id + " не найден"
            );
        }
        goodRepository.delete(id);
    }

    private Long generateId() {
        return goodRepository.findAll()
                .stream()
                .mapToLong(Good::getId)
                .max()
                .orElse(0) + 1;
    }

    public List<Good> findByPriceRange(double minPrice, double maxPrice) {
        return findAll()
                .stream()
                .filter(good ->
                        good.getPrice() >= minPrice &&
                                good.getPrice() <= maxPrice)
                .toList();
    }

    public List<Good> findByCategoryAndPrice(ProductCategory category, double minPrice, double maxPrice) {
        return findAll()
                .stream()
                .filter(good ->
                        good.getCategory() == category &&
                                good.getPrice() >= minPrice &&
                                good.getPrice() <= maxPrice)
                .sorted(Comparator.comparing(Good::getPrice))       //наверное имеется в виду полноценная кнопка для сортировки... Но я в фильтре ее добавил. Как пример?
                .toList();
    }

    public List<Good> sortByPriceAscending() {
        return findAll()
                .stream()
                .sorted(Comparator.comparing(Good::getPrice))
                .toList();
    }

    public List<Good> sortByPriceDescending() {
        return findAll()
                .stream()
                .sorted(
                        Comparator.comparing(Good::getPrice)
                                .reversed()
                )
                .toList();
    }
}