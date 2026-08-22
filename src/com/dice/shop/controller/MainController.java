package com.dice.shop.controller;

import com.dice.shop.entity.Role;
import com.dice.shop.entity.User;
import com.dice.shop.service.AuthService;
import com.dice.shop.service.GoodService;
import com.dice.shop.service.UserService;
import java.util.Scanner;

public class MainController {
    private final UserController userController;
    private final GoodController goodController;
    private final AuthService authService;

    private final Scanner scanner = new Scanner(System.in);

    public MainController(
            UserService userService,
            GoodService goodService,
            AuthService authService
    ) {
        this.userController = new UserController(userService);
        this.goodController = new GoodController(goodService);
        this.authService = authService;
    }

    public void start() {
        while (true) {
            if (!authService.isAuthorized()) {
                showGuestMenu();
            } else {
                showUserMenu();
            }
        }
    }

    private void showGuestMenu() {
        System.out.println("\n=== Главное меню ===");
        System.out.println("1. Регистрация");
        System.out.println("2. Авторизация");
        System.out.println("0. Выход");

        System.out.print("\nВаш выбор: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                userController.register();
                break;
            case "2":
                userController.login(authService);
                break;
            case "#42_admin_init":
                userController.registerAdmin();
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Неверный ввод");
        }
    }

    private void showUserMenu() {
        User user = authService.getCurrentUser();

        if (user.getRole() == Role.ADMIN) {
            showAdminMenu();
        } else {
            showClientMenu();
        }
    }

    private void showClientMenu() {
        System.out.println("\n=== Меню ===");
        System.out.println("1. Просмотр товаров");
        System.out.println("2. Фильтр товаров");
        System.out.println("3. Профиль");
        System.out.println("0. Выход");

        System.out.print("\nВаш выбор: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                goodController.showAll();
                break;
            case "2":
                goodController.filter();
                break;
            case "3":
                userController.profile(
                        authService.getCurrentUser()
                );

                System.out.println("\n1. Редактировать профиль");
                System.out.println("0. Выход");

                System.out.print("\nВаш выбор: ");
                choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        userController.editProfile(
                                authService.getCurrentUser()
                        );
                        break;
                    case "0":
                        showUserMenu();
                        break;
                    default:
                        System.out.println("Неверный выбор");
                }
                break;
            case "0":
                authService.logout();
                break;
            default:
                System.out.println("Неверный выбор");
        }
    }

    private void showAdminMenu() {
        System.out.println("\n=== Меню администратора ===");
        System.out.println("1. Товары");
        System.out.println("2. Добавить товар");
        System.out.println("3. Изменить товар");
        System.out.println("4. Удалить товар");
        System.out.println("5. Пользователи");
        System.out.println("6. Профиль");
        System.out.println("0. Выход");

        System.out.print("\nВаш выбор: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                goodController.showAll();
                break;
            case "2":
                goodController.add();
                break;
            case "3":
                goodController.update();
                break;
            case "4":
                goodController.delete();
                break;
            case "5":
                userController.showAll();
                break;
            case "6":
                userController.profile(
                        authService.getCurrentUser()
                );

                System.out.println("\n1. Редактировать профиль");
                System.out.println("0. Выход");

                System.out.print("\nВаш выбор: ");
                choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        userController.editProfile(
                                authService.getCurrentUser()
                        );
                        break;
                    case "0":
                        showUserMenu();
                        break;
                    default:
                        System.out.println("Неверный выбор");
                }
                break;
            case "0":
                authService.logout();
                break;
            default:
                System.out.println("Неверный выбор");
        }
    }
}