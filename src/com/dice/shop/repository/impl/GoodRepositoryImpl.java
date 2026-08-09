package com.dice.shop.repository.impl;

import com.dice.shop.entity.Good;
import com.dice.shop.repository.GoodRepository;
import java.util.List;

public class GoodRepositoryImpl implements GoodRepository {

    private static final String FILE_PATH = "resources/goods.dat";

    @Override
    public void save(Good good) {
    }

    @Override
    public List<Good> findAll() {
        return null;
    }

    @Override
    public Good findById(Long id) {
        return null;
    }

    @Override
    public List<Good> findByCategory(String category) { //ProductCategory category
        return null;
    }

    @Override
    public void update(Good good) {
    }

    @Override
    public void delete(Long id) {
    }
}