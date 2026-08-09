package com.dice.shop.controller;

import com.dice.shop.entity.User;
import com.dice.shop.service.AuthService;
import com.dice.shop.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserController {
    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void register() {
        System.out.println("\n=== Регистрация ===");

        System.out.print("Логин: ");
        String login = scanner.nextLine();

        System.out.print("Пароль: ");
        String password = scanner.nextLine();

        System.out.print("Имя: ");
        String firstName = scanner.nextLine();

        System.out.print("Фамилия: ");
        String lastName = scanner.nextLine();

        System.out.print("Дата рождения (yyyy-MM-dd): ");
        String dateInput = scanner.nextLine();
        LocalDate birthDate;
        try {
            //пустую или пробелы чек
            if (dateInput.isBlank()) {
                birthDate = LocalDate.now();
            } else {
                birthDate = LocalDate.parse(dateInput);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты. Установлена текущая дата.");
            birthDate = LocalDate.now();
        }

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setBirthDate(birthDate);

        userService.register(user);

        System.out.println("Пользователь зарегистрирован");
    }

    public void registerAdmin() {
        System.out.println("\n=== Создание администратора ===");

        System.out.print("Логин: ");
        String login = scanner.nextLine();

        System.out.print("Пароль: ");
        String password = scanner.nextLine();

        System.out.print("Имя: ");
        String firstName = scanner.nextLine();

        System.out.print("Фамилия: ");
        String lastName = scanner.nextLine();

        System.out.print("Дата рождения (yyyy-MM-dd): ");
        String dateInput = scanner.nextLine();
        LocalDate birthDate;
        try {
            //пустую или пробелы чек
            if (dateInput.isBlank()) {
                birthDate = LocalDate.now();
            } else {
                birthDate = LocalDate.parse(dateInput);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты. Установлена текущая дата.");
            birthDate = LocalDate.now();
        }

        User user = new User();

        user.setLogin(login);
        user.setPassword(password);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setBirthDate(birthDate);

        userService.registerAdmin(user);

        System.out.println("Администратор создан");
    }

    public void login(AuthService authService) {
        System.out.println("\n=== Авторизация ===");

        System.out.print("Логин: ");
        String login = scanner.nextLine();

        System.out.print("Пароль: ");
        String password = scanner.nextLine();

        boolean result =
                authService.login(login, password);

        if (result) {
            System.out.println("Вход выполнен");
        } else {
            System.out.println("Неверный логин или пароль");
        }
    }

    public void showAll() {
        System.out.println("\n=== Пользователи ===");

        userService.findAll()
                .forEach(System.out::println);
    }

    public void profile(User user) {
        System.out.println("\n=== Профиль ===");

        System.out.println(user);
    }

    public void editProfile(User user) {
        System.out.println("\n=== Редактирование профиля ===");

        System.out.print("Имя: ");
        String firstname = scanner.nextLine();

        System.out.print("Фамилия: ");
        String lastname = scanner.nextLine();

        System.out.print("Пароль: ");
        String password = scanner.nextLine();

        System.out.print("Дата рождения (yyyy-MM-dd): ");
        String dateInput = scanner.nextLine();
        LocalDate birthDate;
        try {
            //пустую или пробелы чек
            if (dateInput.isBlank()) {
                birthDate = LocalDate.now();
            } else {
                birthDate = LocalDate.parse(dateInput);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты. Установлена текущая дата.");
            birthDate = LocalDate.now();
        }

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPassword(password);
        user.setBirthDate(birthDate);

        userService.update(user);

        System.out.println("Данные обновлены");
    }
}