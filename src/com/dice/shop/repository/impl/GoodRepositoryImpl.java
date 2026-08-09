package com.dice.shop.repository.impl;

import com.dice.shop.entity.good.Good;
import com.dice.shop.entity.good.ProductCategory;
import com.dice.shop.repository.GoodRepository;
import com.dice.shop.util.SerializationUtil;
import java.util.ArrayList;
import java.util.List;

public class GoodRepositoryImpl implements GoodRepository {

    private static final String FILE_PATH = "resources/goods.dat";

    @Override
    public void save(Good good) {
        List<Good> goods = findAll();
        goods.add(good);
        SerializationUtil.serialize(goods, FILE_PATH);
    }

    @Override
    public List<Good> findAll() {
        try {
            return (List<Good>) SerializationUtil.deserialize(FILE_PATH);
        } catch (RuntimeException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Good findById(Long id) {
        return findAll()
                .stream()
                .filter(good -> good.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Good> findByCategory(ProductCategory category) {
        return findAll()
                .stream()
                .filter(good ->
                        good.getCategory() == category)
                .toList();
    }

    @Override
    public void update(Good good) {
        List<Good> goods = findAll();
        goods.removeIf(existingGood ->
                existingGood.getId().equals(good.getId()));
        goods.add(good);
        SerializationUtil.serialize(goods, FILE_PATH);
    }

    @Override
    public void delete(Long id) {
        List<Good> goods = findAll();
        goods.removeIf(good ->
                good.getId().equals(id));
        SerializationUtil.serialize(goods, FILE_PATH);
    }
}