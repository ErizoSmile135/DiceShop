package com.dice.shop.service;

import com.dice.shop.entity.Role;
import com.dice.shop.entity.User;
import com.dice.shop.repository.UserRepository;
import java.util.List;
import com.dice.shop.exception.UserAlreadyExistsException;
import com.dice.shop.exception.UserNotFoundException;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new UserAlreadyExistsException(
                    "Пользователь с таким логином уже существует"
            );
        }

        user.setId(generateId());
        user.setRole(Role.CLIENT);
        userRepository.save(user);
    }

    public void registerAdmin(User user) {
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new UserAlreadyExistsException(
                    "Пользователь с таким логином уже существует"
            );
        }

        user.setId(generateId());
        user.setRole(Role.ADMIN);

        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void update(User user) {
        if (userRepository.findById(user.getId()) == null) {
            throw new UserNotFoundException(
                    "Пользователь с id " + user.getId() + " не найден"
            );
        }
        userRepository.update(user);
    }

    //не применил
    /*public void delete(Long id) {
        if (userRepository.findById(id) == null) {
            throw new UserNotFoundException(
                    "Пользователь с id " + id + " не найден"
            );
        }
        userRepository.delete(id);
    }*/

    private Long generateId() {
        return userRepository.findAll()
                .stream()
                .mapToLong(User::getId)
                .max()
                .orElse(0) + 1;
    }
}