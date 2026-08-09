package com.dice.shop.repository;
import com.dice.shop.entity.Good;
import com.dice.shop.entity.ProductCategory;

import java.util.List;

public interface GoodRepository {

    void save(Good good);

    List<Good> findAll();

    Good findById(Long id);

    List<Good> findByCategory(ProductCategory category);

    void update(Good good);

    void delete(Long id);
}