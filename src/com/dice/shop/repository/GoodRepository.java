package com.dice.shop.repository;
import com.dice.shop.entity.Good;
import java.util.List;

public interface GoodRepository {

    void save(Good good);

    List<Good> findAll();

    Good findById(Long id);

    List<Good> findByCategory(String category);

    void update(Good good);

    void delete(Long id);
}