package com.dice.shop.repository.impl;

import com.dice.shop.entity.User;
import com.dice.shop.repository.UserRepository;
import com.dice.shop.util.SerializationUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepositoryImpl implements UserRepository {

    private static final String FILE_PATH = "resources/users.dat";

    @Override
    public void save(User user) {
        List<User> users = findAll();
        users.add(user);
        SerializationUtil.serialize(users, FILE_PATH);
    }

    @Override
    public List<User> findAll() {
        List<User> users = SerializationUtil.deserialize(FILE_PATH);
        if (users == null) {
            return new ArrayList<>();
        }
        return users;
    }

    @Override
    public User findById(Long id) {
        return findAll()
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findByLogin(String login) {
        return findAll()
                .stream()
                .filter(user ->
                        Objects.equals(user.getLogin(), login))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(User user) {
        List<User> users = findAll();
        users.removeIf(existingUser ->
                existingUser.getId().equals(user.getId()));
        users.add(user);
        SerializationUtil.serialize(users, FILE_PATH);
    }

    @Override
    public void delete(Long id) {
        List<User> users = findAll();
        users.removeIf(user ->
                user.getId().equals(id));
        SerializationUtil.serialize(users, FILE_PATH);
    }
}