package com.dice.shop.service;

import com.dice.shop.entity.User;

public class AuthService {
    private final UserService userService;
    private User currentUser;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public boolean login(String login, String password) {
        User user = userService.findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {      //пароли не хеширую, лень разбираться в безопасности в профилях
            currentUser = user;
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isAuthorized() {
        return currentUser != null;
    }
}