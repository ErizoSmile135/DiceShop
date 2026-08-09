package com.dice.shop.repository;
import com.dice.shop.entity.User;
import java.util.List;

public interface UserRepository {

    void save(User user);

    List<User> findAll();

    User findById(Long id);

    User findByLogin(String login);

    void update(User user);

    void delete(Long id);
}