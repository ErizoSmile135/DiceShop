package com.dice.shop.repository.impl;

import com.dice.shop.entity.User;
import com.dice.shop.repository.UserRepository;
import com.dice.shop.util.SerializationUtil;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static final String FILE_PATH = "resources/users.dat";

    @Override
    public void save(User user) {
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public void update(User user) {
    }

    @Override
    public void delete(Long id) {
    }
}