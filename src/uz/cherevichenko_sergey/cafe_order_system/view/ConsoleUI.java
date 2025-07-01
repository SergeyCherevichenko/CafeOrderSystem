package uz.cherevichenko_sergey.cafe_order_system.view;

import uz.cherevichenko_sergey.cafe_order_system.presenter.Presenter;
import uz.cherevichenko_sergey.cafe_order_system.view.command.MainMenuAdmin;
import uz.cherevichenko_sergey.cafe_order_system.view.command.MainMenuUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements View {
    private final Scanner scan;
    private final Presenter presenter;
    private boolean work;
    private MainMenuUser menuUser;
    private MainMenuAdmin menuAdmin;
    private boolean isAdmin;
    private boolean isAuthorized;

    public ConsoleUI() {
        scan = new Scanner(System.in);
        presenter = new Presenter(this);
        work = true;
    }

    @Override
    public void start() {
        while (true) {
            System.out.println("Добро пожаловать! Выберите одну из операций:\n1. Регистрация\n2. Авторизация\n3. Выйти");
            int op = readInt("Введите номер операции: ");
            switch (op) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> {
                    finish();
                    return;
                }
                default -> System.out.println("Неверный выбор. Повторите попытку.");
            }
            if (isAuthorized) break;
        }

        while (work) {
            if (isAdmin) {
                printMenuAdmin();
                choiceMenuAdmin();
            } else {
                printMenuUser();
                choiceMenuUser();
            }
        }
    }

    private void register() {
        System.out.print("Введите имя: ");
        String name = scan.nextLine();
        System.out.print("Введите номер телефона: ");
        String phoneNumber = scan.nextLine();
        System.out.print("Введите email: ");
        String email = scan.nextLine();
        System.out.print("Введите пароль: ");
        String password = scan.nextLine();

        if (presenter.clientRegistry(name, phoneNumber, email, password)) {
            isAdmin = presenter.isAdmin();
            if (isAdmin) {
                menuAdmin = new MainMenuAdmin(this);
            } else {
                menuUser = new MainMenuUser(this);
            }
            isAuthorized = true;
        } else {
            System.out.println("Ошибка при регистрации. Возможно, клиент уже существует.");
        }
    }

    private void login() {
        System.out.print("Введите имя: ");
        String name = scan.nextLine();
        System.out.print("Введите email: ");
        String email = scan.nextLine();
        System.out.print("Введите пароль: ");
        String password = scan.nextLine();

        isAuthorized = presenter.clientLogin(name, email, password);
        if (isAuthorized) {
            isAdmin = presenter.isAdmin();
            if (isAdmin) {
                System.out.println(name + ", добро пожаловать в систему администратор!");
                menuAdmin = new MainMenuAdmin(this);
            } else {
                System.out.println(name + ", добро пожаловать в систему!");
                menuUser = new MainMenuUser(this);
            }
        } else {
            System.out.println("Вы не зарегистрированы в системе.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scan.hasNextInt()) {
                int value = scan.nextInt();
                scan.nextLine();
                return value;
            } else {
                System.out.println("Ошибка ввода. Введите число.");
                scan.nextLine();
            }
        }
    }

    private List<Integer> readDishIndexes() {
        List<Integer> dishIndexes = new ArrayList<>();
        while (true) {
            int dishIndex = readInt("Введите индекс блюда (или -1 для завершения): ");
            if (dishIndex == -1) break;
            dishIndexes.add(dishIndex);
        }
        return dishIndexes;
    }

    @Override
    public void printAnswer(String answer) {
        System.out.println(answer);
    }

    private void choiceMenuAdmin() {
        int choice = readInt("Выберите действие: ");
        if (choice > 0 && choice <= menuAdmin.size()) menuAdmin.execute(choice);
        else System.out.println("Операции с таким номером не существует!");
    }

    private void choiceMenuUser() {
        int choice = readInt("Выберите действие: ");
        if (choice > 0 && choice <= menuUser.size()) menuUser.execute(choice);
        else System.out.println("Операции с таким номером не существует!");
    }

    private void printMenuUser() {
        System.out.println(menuUser.menu());
    }

    private void printMenuAdmin() {
        System.out.println(menuAdmin.menu());
    }

    public void finish() {
        System.out.println("До новых встреч!");
        presenter.saveClientList();
        presenter.saveListDishes();
        work = false;
    }

    public void addClient() {
        System.out.println("введите имя: ");
        String name = scan.nextLine();
        System.out.println("введите номер телефона: ");
        String phoneNumber = scan.nextLine();
        System.out.println("введите email: ");
        String email = scan.nextLine();
        System.out.println("Введите пароль: ");
        String password = scan.nextLine();
        if (presenter.addClient(name, phoneNumber, email, password)) {
            printAnswer("Клиент успешно добавлен!");
        } else {
            printAnswer("Не возможго добавить клиента!");
        }
    }

    public void printMenu() {
        printAnswer(presenter.printMenu());
    }

    public void printAllOrders() {
        printAnswer(presenter.printAllOrders());
    }

    public void printInfoClient() {
        System.out.println("ведите номер телефона клиента");
        String phoneNumber = scan.nextLine();
        printAnswer(presenter.printInfoClient(phoneNumber));
    }

    public void printInfoThisClient() {
        printAnswer(presenter.printInfoThisClient());
    }

    public void printInfoAllClients() {
        printAnswer(presenter.printInfoAllClients());
    }

    public void printOrderByClientPhone() {
        scan.nextLine();
        System.out.println("Введите номер телефона клиента(посмотрите в списке: ");
        String listClient =presenter.printInfoAllClients();
        if ( listClient== null) {
            System.out.println("Нет зарегустрированных клиентов!");
        } else {
            printAnswer(listClient);
            String phoneNumber = scan.nextLine();
            String answ = presenter.printOrdersByClientPhone(phoneNumber);
            if (answ == null) {
                printAnswer("У клиента нет заказов!");
            } else {
                printAnswer(answ);
            }
        }
    }

    public void printOrdersThisClient() {
        printAnswer(presenter.printOrdersCurrentClient());
    }


    public void addDishInOrder() {
        String isHave = presenter.printOrdersCurrentClient();

        if (!isHave.equals("Пользователь не авторизован.") && !isHave.equals("У вас пока нет заказов.")) {
            System.out.println("Выберите из списка, в какой заказ добавить блюдо:");
            printOrdersThisClient();

            int order = -1;
            while (true) {
                System.out.print("Введите номер заказа: ");
                if (scan.hasNextInt()) {
                    order = scan.nextInt();
                    break;
                } else {
                    System.out.println("Вы ввели не число!");
                    scan.next(); // Съедаем некорректный ввод
                }
            }

            List<Integer> dish = new ArrayList<>();
            System.out.println("Введите индексы блюд для добавления в заказ (введите -1 для завершения):");
            printMenu();
            while (true) {
                if (scan.hasNextInt()) {
                    int dishIndex = scan.nextInt();
                    if (dishIndex == -1) break;
                    dish.add(dishIndex);
                } else {
                    System.out.println("Вы ввели не число!");
                    scan.next(); // Съедаем некорректный ввод
                }
            }

            System.out.println(presenter.addDishInOrder(order, dish)
                    ? "Успешно добавлено"
                    : "Не возможно добавить!");

        } else {
            printAnswer(isHave);
        }
    }


    public void addDishInOrderAdmin() {
        String isHave = presenter.printInfoAllClients();
        if (!isHave.equals("Клиенты отсутствуют.")) {
                printInfoAllClients();
                int client = -1;
                boolean isClient = true;
                while (isClient) {
                    System.out.print("Введите индекс клиента: ");
                    if (scan.hasNextInt()) {
                        client = scan.nextInt();
                        scan.nextLine();
                        isClient = false;
                    } else {
                        System.out.println("Вы ввели не число!");
                        scan.nextLine();
                    }
                }


            isHave = presenter.findOrderClientByIndex(client);
            if (!isHave.equals("Клиент с таким номером не найден.") && !isHave.equals("У клиента пока нет заказов.")) {
                System.out.println(presenter.printOrderByClientIndex(client));
                int order = -1;
                boolean isOrder = true;
                while (isOrder) {
                    System.out.print("Введите индекс заказа: ");
                    if (scan.hasNextInt()) {
                        order = scan.nextInt();
                        scan.nextLine();
                        isOrder = false;
                    } else {
                        System.out.println("Вы ввели не число!");
                        scan.nextLine();
                    }
                }

                List<Integer> dish = new ArrayList<>();
                printMenu();
                System.out.println("Введите индексы блюд для добавления (введите -1 для завершения):");
                while (true) {
                    if (scan.hasNextInt()) {
                        int dishIndex = scan.nextInt();
                        if (dishIndex == -1) break;
                        dish.add(dishIndex);
                    } else {
                        System.out.println("Вы ввели не число!");
                        scan.nextLine();
                    }
                }

                System.out.println(presenter.addDishInOrderAdmin(client, order, dish)
                        ? "Успешно добавлено"
                        : "Не возможно добавить!");
            } else printAnswer(isHave);
        } else {
            printAnswer(isHave);
        }
    }

    public void createOrder() {
        String isHave = presenter.printMenu();
        if (!isHave.equals("Нет блюд в меню!")) {
            printMenu();
            List<Integer> dish = new ArrayList<>();
            System.out.println("Введите индексы блюд для добавления (введите -1 для завершения):");
            while (true) {
                if (scan.hasNextInt()) {
                    int dishIndex = scan.nextInt();
                    if (dishIndex == -1) break;
                    dish.add(dishIndex);
                } else {
                    System.out.println("Вы ввели не число!");
                    scan.next();
                }
            }
            System.out.println(presenter.createOrder(dish)
                    ? "Успешно создан заказ"
                    : "Не возможно сделать заказ!");

        } else {
            printAnswer(isHave);
        }
    }

    public void createOrderAdmin() {
        scan.nextLine();
        System.out.println("Введите номер телефона клиента(посмотрите в списке");
        if(presenter.printInfoAllClients() == null){
            System.out.println("Нет зарегистрированных клиентов!");
        } else {
            printInfoAllClients();
            String phoneNumber = scan.nextLine();
            String isHave = presenter.printMenu();
            List<Integer> dish = new ArrayList<>();
            if (!isHave.equals("Нет блюд в меню!")) {
                printMenu();

                System.out.println("Введите индексы блюд для добавления (введите -1 для завершения):");
                while (true) {
                    if (scan.hasNextInt()) {
                        int dishIndex = scan.nextInt();
                        if (dishIndex == -1){
                            break;
                        }
                        dish.add(dishIndex);
                    } else {
                        System.out.println("Вы ввели не число!");
                        scan.nextLine();
                    }
                }

            }
            System.out.println(presenter.createOrderAdmin(phoneNumber, dish)
                    ? "Успешно создан заказ"
                    : "Не возможно сделать заказ!");
        }
    }

    public void addDishToMenu() {
        scan.nextLine();
        System.out.println("Введите название блюда");
        String nameDish = scan.nextLine();
        System.out.println("Введите расположение фото блюда");
        String pathImage = scan.nextLine();
        System.out.println("Введите стоимость блюда ");
        int cost = -1;
        boolean isCost = true;
        while (isCost) {
            if (scan.hasNextInt()) {
                cost = scan.nextInt();
                scan.nextLine();
                isCost = false;
            } else {
                System.out.println("Вы ввели не число!");
            }
        }
        System.out.println("Введите количество блюда ");
        int count = -1;
        boolean isCount = true;
        while (isCount) {
            if (scan.hasNextInt()) {
                count = scan.nextInt();
                scan.nextLine();
                isCount = false;
            } else {
                System.out.println("Вы ввели не число!");
            }
        }
        System.out.println(presenter.addDishToMenu(nameDish, pathImage, cost, count)
                ? "Успешно добавлено"
                : "Не возможно добавить!");

    }

    public void changeInfoClient() {
        scan.nextLine();
        System.out.println("Введите свой номер телефона");
        String phoneNumber = scan.nextLine();
        System.out.println("введите свой пароль");
        String password = scan.nextLine();
        System.out.println("Введите новое/старое имя ");
        String newName = scan.nextLine();
        System.out.println("Введи новый/старый  номер телефона");
        String newPhone = scan.nextLine();
        System.out.println("Введите новый/старый email");
        String newEmail = scan.nextLine();
        System.out.println("Введите новый пароль");
        String newPassword = scan.nextLine();
        System.out.println(presenter.changeInfoClient(phoneNumber, password, newName, newPhone, newEmail, newPassword)
                ? "Успешно обновлены"
                : "Не возможно обновить !");
    }

    public void changeStatusOrder() {
        String isHave = presenter.printInfoAllClients();
        if (!isHave.equals("Клиенты отсутствуют.")) {

            printInfoAllClients(); // Показ всех заказов

            int client = -1;
            while (true) {
                System.out.print("Введите индекс клиента: ");
                if (scan.hasNextInt()) {
                    client = scan.nextInt();
                    scan.nextLine();
                    break;
                } else {
                    System.out.println("Вы ввели не число!");
                    scan.next(); // сбрасываем некорректный ввод
                }
            }

            isHave = presenter.findOrderClientByIndex(client);
            if (!isHave.equals("Клиент с таким номером не найден.") && !isHave.equals("У клиента пока нет заказов.")) {
                printAnswer(presenter.printOrderByClientIndex(client));
                int order = -1;
                while (true) {
                    System.out.print("Введите индекс заказа: ");
                    if (scan.hasNextInt()) {
                        order = scan.nextInt();
                        scan.nextLine();
                        break;
                    } else {
                        System.out.println("Вы ввели не число!");
                        scan.nextLine();
                    }
                }

                printAnswer(presenter.printAllStatus());
                int status = -1;
                while (true) {
                    System.out.print("Выберите на какой статус изменть: ");
                    if (scan.hasNextInt()) {
                        status = scan.nextInt();
                        scan.nextLine();
                        break;
                    } else {
                        System.out.println("Вы ввели не число!");
                        scan.nextLine();
                    }
                }
                System.out.println(presenter.changeStatusOrder(client, order, status)
                        ? "Успешно изменен"
                        : "Не возможно изменить!");

            } else {
                printAnswer(isHave);
            }
        } else {
            System.out.println(isHave);
        }
    }

    public void deleteDishFromMenu() {
        String isHave = presenter.printMenu();
        if (!isHave.equals("Нет блюд в меню!")) {
            System.out.println("Выберите блюда из списка по номеру которое хотитет удалить");
            printMenu();
            boolean isDish = true;
            int dish = -1;
            while ((isDish)) {
                if (scan.hasNextInt()) {
                    dish = scan.nextInt();
                    isDish = false;
                } else {
                    System.out.println("вы ввели не число!");
                    scan.next();
                }
            }
            System.out.println(presenter.deleteDishFromMenu(dish)
                    ? "Успешно удалено"
                    : "Не возможно удалить!");

        }
    }

    public void removeDishFromOrderThis() {
        String isHave = presenter.printOrdersCurrentClient();
        if (!isHave.equals("Пользователь не авторизован.") && !isHave.equals("У вас пока нет заказов.")) {
            System.out.println("Выберите из списка, из какого заказа удалить блюдо:");
            printOrdersThisClient();
            int order = -1;
            while (true) {
                System.out.print("Введите номер заказа: ");
                if (scan.hasNextInt()) {
                    order = scan.nextInt();
                    scan.nextLine();
                    break;
                } else {
                    System.out.println("Вы ввели не число!");
                    scan.nextLine(); // Съедаем некорректный ввод
                }
            }
            String error = presenter.printAllDishInOrderThisClient(order);
            if(error == null){
                System.out.println("Нет такого заказа");
            } else {
                System.out.println(error);
                int dish = -1;
                System.out.println("Введите индекс блюда которое хотите убрать из заказа:");
                presenter.printAllDishInOrderThisClient(order);
                boolean isDish = true;
                while (isDish) {
                    if (scan.hasNextInt()) {
                        dish = scan.nextInt();
                        isDish = false;

                    } else {
                        System.out.println("Вы ввели не число!");
                        // Съедаем некорректный ввод
                        scan.nextLine();
                    }
                }

                System.out.println(presenter.removeDishFromOrderThis(order, dish)
                        ? "Успешно удалено"
                        : "Не возможно удалить!");
            }

        } else {
            printAnswer(isHave);
        }
    }

    public void removeDishFromOrderAdmin() {
        String isHave = presenter.printInfoAllClients();
        if (!isHave.equals("Клиенты отсутствуют.")) {

            printInfoAllClients(); // Показ всех заказов

            int client = -1;
            while (true) {
                System.out.print("Введите индекс клиента: ");
                if (scan.hasNextInt()) {
                    client = scan.nextInt();
                    break;
                } else {
                    System.out.println("Вы ввели не число!");
                    scan.next(); // сбрасываем некорректный ввод
                }
            }

            isHave = presenter.findOrderClientByIndex(client);
            if (!isHave.equals("Клиент с таким номером не найден.") && !isHave.equals("У клиента пока нет заказов.")) {
                printAnswer(presenter.printOrderByClientIndex(client));
                int order = -1;
                while (true) {
                    System.out.print("Введите индекс заказа: ");
                    if (scan.hasNextInt()) {
                        order = scan.nextInt();
                        break;
                    } else {
                        System.out.println("Вы ввели не число!");
                        scan.next();
                    }
                }

                int dish = -1;
                System.out.println("Введите индекс блюда которое хотите убрать из заказа:");
                String ans = presenter.getAllDishesInOrderByClientIndex(client, order);
                if (!ans.equals("Нет зарегистрированных клиентов") && !ans.equals("Клиент не найден") &&
                        !ans.equals("Заказ не найден")) {
                    printAnswer(ans);
                    boolean isDish = true;
                    while (isDish) {
                        if (scan.hasNextInt()) {
                            dish = scan.nextInt();
                            isDish = false;

                        } else {
                            System.out.println("Вы ввели не число!");
                            scan.nextLine();
                        }
                    }

                    System.out.println(presenter.removeDishFromOrderAdmin(client, order, dish)
                            ? "Успешно удалено"
                            : "Не возможно удалить!");
                } else {
                    printAnswer(ans);
                }
            } else {
                printAnswer(isHave);
            }
        } else {
            printAnswer(isHave);
        }
    }

    public void removeOrderThis() {
        String isHave = presenter.printOrdersCurrentClient();
        if (!isHave.equals("Пользователь не авторизован.") && !isHave.equals("У вас пока нет заказов.")) {
            System.out.println("Выберите из списка, какой заказа  хотите удалить :");
            printOrdersThisClient();

            int order = -1;
            while (true) {
                System.out.print("Введите номер заказа: ");
                if (scan.hasNextInt()) {
                    order = scan.nextInt();
                    scan.nextLine();
                    break;
                } else {
                    System.out.println("Вы ввели не число!");
                    scan.nextLine();// Съедаем некорректный ввод
                }
            }
            System.out.println(presenter.removeOrderThis(order)
                    ? "Успешно удалено"
                    : "Не возможно удалить!");

        } else {
            printAnswer(isHave);
        }
    }

    public void removeOrderAdmin() {
        String isHave = presenter.printInfoAllClients();
        if (!isHave.equals("Клиенты отсутствуют.")) {

            printInfoAllClients(); // Показ всех заказов

            int client = -1;
            while (true) {
                System.out.print("Введите индекс клиента: ");
                if (scan.hasNextInt()) {
                    client = scan.nextInt();
                    break;
                } else {
                    System.out.println("Вы ввели не число!");
                    scan.nextLine();
                }
            }

            isHave = presenter.findOrderClientByIndex(client);
            if (!isHave.equals("Клиент с таким номером не найден.") && !isHave.equals("У клиента пока нет заказов.")) {
                System.out.println("Выберите из списка, какой заказа  хотите удалить :");
                printAnswer(presenter.printOrderByClientIndex(client));
                int order = -1;
                while (true) {
                    System.out.print("Введите индекс заказа: ");
                    if (scan.hasNextInt()) {
                        order = scan.nextInt();

                        break;
                    } else {
                        System.out.println("Вы ввели не число!");
                        scan.nextLine();
                    }
                }


                System.out.println(presenter.removeOrderAdmin(client, order)
                        ? "Успешно удалено"
                        : "Не возможно удалить!");

            } else {
                printAnswer(isHave);
            }
        } else {
            printAnswer(isHave);
        }
    }
    public void printSortOrdersByDataTime(){
        printAnswer(presenter.sortOrdersByDataTime());

    }

    public void printSortOrdersByStatus(){
      printAnswer(presenter.sortOrdersByStatus());
    }



}
