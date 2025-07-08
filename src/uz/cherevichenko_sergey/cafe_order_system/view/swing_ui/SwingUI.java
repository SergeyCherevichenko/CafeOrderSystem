package uz.cherevichenko_sergey.cafe_order_system.view.swing_ui;

import uz.cherevichenko_sergey.cafe_order_system.presenter.presenter_socket.PresenterSocket;
import uz.cherevichenko_sergey.cafe_order_system.view.View;
import uz.cherevichenko_sergey.cafe_order_system.view.dto_view.*;
import uz.cherevichenko_sergey.cafe_order_system.view.listeners.PanelStartMenuListeners;
import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonCommandAdminListener;
import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonCommandListener;
import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonListener;
import uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels.MainPanel;
import uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels.PanelStartMenu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class SwingUI extends JFrame implements View,
        PanelStartMenuListeners,
        SendButtonCommandAdminListener,
        SendButtonListener,
        SendButtonCommandListener {

    private final MainPanel mainPanel;
    private PresenterSocket presenter;
    private boolean work;
    private boolean isAdmin;
    private boolean isAuthorized;

    public SwingUI(String ip, int port) {
        try {
            presenter = new PresenterSocket(this,ip,port);
        } catch (Exception e) {
            System.out.println("Не возможно создать презентер: " + e.getMessage());
            presenter = null; // ← важно явно присвоить null
        }
        this.mainPanel = new MainPanel(this, this, this, this);
        isAuthorized = false;
        isAdmin = false;
        work = true;
    }
    public SwingUI() {
        this("localhost", 12345);
    }


    @Override
    public void printAnswer(String answer) {
        System.out.println(answer);
    }

    @Override
    public void start() {
        setTitle("Cafe Order System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        add(mainPanel);
        setVisible(true);

        mainPanel.showPanel(MainPanel.PANEL_START_MENU);

        // ✅ Сервер уже проверен в MainGui до создания этой View
        // Обновим статус на панели
        PanelStartMenu panel = mainPanel.getPanelStartMenu();
        if (panel != null) {
            panel.updateServerStatus(true); // всегда true, раз мы уже проверили
        }
    }




    @Override
    public void onRegisterClicked() {
        mainPanel.showPanel(MainPanel.PANEL_CLIENT_REGISTRY);

    }

    @Override
    public void onAuthorisationClicked() {
        mainPanel.showPanel(MainPanel.PANEL_CLIENT_LOGIN);
    }

    @Override
    public void onExitClicked() {
        Timer t = new Timer(1500, e -> {
            work = false;
            System.exit(0); // завершаем полностью
        });
        t.setRepeats(false);
        t.start();
    }


    @Override
    public void onSendClicked() {
        String activePanel = mainPanel.getCurrentPanel();
        switch (activePanel) {
            case MainPanel.PANEL_CLIENT_REGISTRY: {
                String name = mainPanel.getPanelClientRegistre().getName();
                String phone = mainPanel.getPanelClientRegistre().getPhoneNumber();
                String email = mainPanel.getPanelClientRegistre().getEmail();
                String password = mainPanel.getPanelClientRegistre().getPassword();
                ClientRegistryDTO clientRegistryDTO = new ClientRegistryDTO(name, phone, email, password);
                String ans = presenter.clientRegistry(clientRegistryDTO);
                if ("SUCCESS".equals(ans)) {
                    mainPanel.getPanelClientRegistre().setStatusAnswer("Успешная регистрация!");
                    mainPanel.getPanelClientRegistre().getStatusAnswer().setForeground(Color.GREEN);
                    Timer t = new Timer(1500, e -> mainPanel.showPanel(MainPanel.PANEL_CLIENT_LOGIN));
                    t.setRepeats(false);
                    t.start();
                } else {
                    mainPanel.getPanelClientRegistre().setStatusAnswer("Ошибка" + ans);
                    mainPanel.getPanelClientRegistre().getStatusAnswer().setForeground(Color.RED);
                    Timer t = new Timer(1500, e -> mainPanel.showPanel(MainPanel.PANEL_START_MENU));
                    t.setRepeats(false);
                    t.start();
                }
                break;
            }
            case MainPanel.PANEL_CLIENT_LOGIN: {
                String name = mainPanel.getPanelClientLogin().getName();
                String email = mainPanel.getPanelClientLogin().getEmail();
                String password = mainPanel.getPanelClientLogin().getPassword();
                ClientLoginDTO clientLoginDTO = new ClientLoginDTO(name, email, password);
                String ans = presenter.clientLogin(clientLoginDTO);
                if ("SUCCESS".equals(ans)) {
                    mainPanel.getPanelClientLogin().setStatusAnswer("Успешная авторизация!");
                    mainPanel.getPanelClientLogin().getStatusAnswer().setForeground(Color.GREEN);
                    Timer t = new Timer(1500, e -> mainPanel.showPanel(MainPanel.PANEL_CLIENT_LOGIN));
                    t.setRepeats(false);
                    t.start();
                    String adm = presenter.clientAdmin();
                    if ("SUCCESS".equals(adm)) {
                        mainPanel.getPanelClientLogin().setStatusAnswer("Успешная регистрация! Добро пожаловать Админ!");
                        mainPanel.getPanelClientLogin().getStatusAnswer().setForeground(Color.GREEN);
                        this.isAdmin = true;
                        t = new Timer(1500, e -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
                        t.setRepeats(false);
                        t.start();
                    } else {
                        mainPanel.getPanelClientLogin().setStatusAnswer("Успешная регистрация! Добро пожаловать " + name);
                        mainPanel.getPanelClientLogin().getStatusAnswer().setForeground(Color.GREEN);
                        this.isAuthorized = true;
                        t = new Timer(1500, e -> mainPanel.showPanel(MainPanel.PANEL_MENU_USER));
                        t.setRepeats(false);
                        t.start();
                    }
                } else {
                    mainPanel.getPanelClientLogin().setStatusAnswer("Oшибка" + ans);
                    mainPanel.getPanelClientLogin().getStatusAnswer().setForeground(Color.RED);
                    Timer t = new Timer(1500, e -> mainPanel.showPanel(MainPanel.PANEL_START_MENU));
                    t.setRepeats(false);
                    t.start();
                }
                break;
            }
            case MainPanel.PANEL_INFO_CLIENT: {
                String phoneNumber = mainPanel.getPanelPrintInfoClient().getPhoneNumberClient();
                String ans = presenter.printOrderByClientPhone(phoneNumber);
                mainPanel.getPanelPrintInfoClient().setAnswer(ans);
                break;
            }

            case MainPanel.PANEL_ADD_DISH_TO_MENU: {
                String nameDish = mainPanel.getPanelAddDishToMenu().getNameDish();
                String path = mainPanel.getPanelAddDishToMenu().getPathDish();
                boolean isCost = true;
                int cost = 0;
                while (isCost) {
                    try {
                        cost = Integer.parseInt(mainPanel.getPanelAddDishToMenu().getCostDish());
                        isCost = false;
                    } catch (Exception e) {
                        System.out.println("Вы ввели не число!");
                    }
                }
                boolean isCount = true;
                int count = 0;
                while (isCount) {
                    try {
                        count = Integer.parseInt(mainPanel.getPanelAddDishToMenu().getCountDish());
                        isCount = false;
                    } catch (Exception e) {
                        System.out.println("Вы ввели не число!");
                    }
                }
                AddDishToMenuDTO addDishToMenuDTO = new AddDishToMenuDTO(nameDish, path, cost, count);
                String ans = presenter.addDishToMenu(addDishToMenuDTO);
                if ("SUCCESS".equals(ans)) {
                    mainPanel.getPanelAddDishToMenu().setStatusAnswer("Успешно добавлено!");
                    mainPanel.getPanelAddDishToMenu().setForeground(Color.GREEN);
                    Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
                    t.setRepeats(false);
                    t.start();
                } else {
                    mainPanel.getPanelAddDishToMenu().setStatusAnswer("Не возможно добавить!" + ans);
                    mainPanel.getPanelAddDishToMenu().setForeground(Color.RED);
                    Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
                    t.setRepeats(false);
                    t.start();
                }
            }
            break;

            case MainPanel.PANEL_ADD_INDEX_FOR_DISH_REMOVE_TO_MENU:
                int index = 0;
                try {
                    index = Integer.parseInt(mainPanel.getPanelAddIndexForDishRemoveToMenu().getIndex());
                    SendIndexDTO sendIndexDTO = new SendIndexDTO(index);
                    String ans = presenter.sendIndexFromRemoveDishToMenu(sendIndexDTO);
                    if ("SUCCESS".equals(ans)) {
                        mainPanel.getPanelAddIndexForDishRemoveToMenu().setStatus("Успешно удалено", Color.GREEN);
                        Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
                        t.setRepeats(false);
                        t.start();
                    } else {
                        mainPanel.getPanelAddIndexForDishRemoveToMenu().setStatus("Ошибка " + ans, Color.RED);
                        Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
                        t.setRepeats(false);
                        t.start();
                    }
                } catch (Exception e) {
                    mainPanel.getPanelAddIndexForDishRemoveToMenu().setMessage("Вы ввели не число");
                    Timer t = new Timer(1500, o -> onDeleteDishFromMenuClicked());
                    t.setRepeats(false);
                    t.start();
                }

                break;

            case MainPanel.PANEL_CREATE_ORDER_ADMIN:
                String phoneNumber = mainPanel.getPanelCreateOrderAdmin().getIndex();
                String indexesDishes = mainPanel.getPanelCreateOrderAdmin().getDishIndexes();
                List<Integer> dishIndexes = new ArrayList<>();
                for (String s : indexesDishes.split(",")) {
                    try {
                        dishIndexes.add(Integer.parseInt(s.trim()));
                    } catch (NumberFormatException e) {
                        mainPanel.getPanelCreateOrderAdmin().setMessage("Неверный индекс: " + s);
                        Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_CREATE_ORDER_ADMIN));
                        t.setRepeats(false);
                        t.start();

                    }
                    CreateOrderAdminDTO createOrderAdminDTO = new CreateOrderAdminDTO(phoneNumber, dishIndexes);
                    String ans = presenter.createOrderAdmin(createOrderAdminDTO);
                    if ("SUCCESS".equals(ans)) {
                        mainPanel.getPanelCreateOrderAdmin().setStatus("Успешно  добавлен", Color.GREEN);
                        Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
                        t.setRepeats(false);
                        t.start();
                    } else mainPanel.getPanelCreateOrderAdmin().setStatus("Ошибка " + ans, Color.RED);
                    Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
                    t.setRepeats(false);
                    t.start();
                }
                break;

            case MainPanel.PANEL_ADD_DISH_IN_ORDER_ADMIN:
                String clientStr = mainPanel.getPanelAddDishInOrderAdmin().getClientIndex().trim();
                int clientIndex;
                try {
                    clientIndex = Integer.parseInt(clientStr);
                } catch (NumberFormatException e) {
                    mainPanel.getPanelAddDishInOrderAdmin().setMessage("Индекс клиента — не число");
                    break;
                }

                // Шаг 1 — только клиент
                if (!mainPanel.getPanelAddDishInOrderAdmin().isOrderInputVisible()) {
                    String orders = presenter.findOrderByClientIndex(clientIndex);
                    if ("У клиента пока нет заказов.".equals(orders) || "Клиент с таким номером не найден.".equals(orders)) {
                        mainPanel.getPanelAddDishInOrderAdmin().setMessage("Клиент не найден или нет заказов");
                        new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN)).start();
                        break;
                    }
                    mainPanel.getPanelAddDishInOrderAdmin().setMessage(orders);
                    mainPanel.getPanelAddDishInOrderAdmin().showOrderInput(true);
                    break;
                }

                // Шаг 2 — заказ уже введён
                if (!mainPanel.getPanelAddDishInOrderAdmin().isDishInputVisible()) {
                    String orderStr = mainPanel.getPanelAddDishInOrderAdmin().getOrderIndex().trim();
                    int orderIndex;
                    try {
                        orderIndex = Integer.parseInt(orderStr);
                    } catch (NumberFormatException e) {
                        mainPanel.getPanelAddDishInOrderAdmin().setMessage("Индекс заказа — не число");
                        break;
                    }

                    // Отобразить список блюд
                    String allDishes = presenter.printMenu();
                    mainPanel.getPanelAddDishInOrderAdmin().setMessage(allDishes);
                    mainPanel.getPanelAddDishInOrderAdmin().showDishInput(true);
                    break;
                }

                // Шаг 3 — добавляем блюда
                String orderStr = mainPanel.getPanelAddDishInOrderAdmin().getOrderIndex().trim();
                String dishStr = mainPanel.getPanelAddDishInOrderAdmin().getDishIndexes();
                int orderIndex;
                try {
                    orderIndex = Integer.parseInt(orderStr);
                } catch (NumberFormatException e) {
                    mainPanel.getPanelAddDishInOrderAdmin().setMessage("Индекс заказа — не число");
                    break;
                }

                List<Integer> dish1Indexes = new ArrayList<>();
                for (String s : dishStr.split(",")) {
                    try {
                        dish1Indexes.add(Integer.parseInt(s.trim()));
                    } catch (NumberFormatException e) {
                        mainPanel.getPanelAddDishInOrderAdmin().setMessage("Неверный индекс блюда: " + s.trim());
                        return;
                    }
                }

                AddDishInOrderAdminDTO dto = new AddDishInOrderAdminDTO(clientIndex, orderIndex, dish1Indexes);
                String result = presenter.addDishToOrderAdmin(dto);
                if ("SUCCESS".equals(result)) {
                    mainPanel.getPanelAddDishInOrderAdmin().setStatus("Блюда успешно добавлены", Color.GREEN);
                    Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
                    t.setRepeats(false);
                    t.start();

                } else {
                    mainPanel.getPanelAddDishInOrderAdmin().setStatus("Ошибка: " + result, Color.RED);
                    Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
                    t.setRepeats(false);
                    t.start();
                }

                // Сброс
                mainPanel.getPanelAddDishInOrderAdmin().setClientIndex("");
                mainPanel.getPanelAddDishInOrderAdmin().setOrderIndex("");
                mainPanel.getPanelAddDishInOrderAdmin().setDishIndexes("");
                mainPanel.getPanelAddDishInOrderAdmin().showOrderInput(false);
                mainPanel.getPanelAddDishInOrderAdmin().showDishInput(false);
                break;

            case MainPanel.PANEL_REMOVE_ORDER_ADMIN:
                String indClient = mainPanel.getPanelRemoverOrderAdmin().getClientIndex().trim();
                if (indClient.isEmpty()) break;
                int index1Client = -1;
                try {
                    index1Client = Integer.parseInt(indClient);
                } catch (NumberFormatException e) {
                    mainPanel.getPanelRemoverOrderAdmin().setMessage("Вы ввели не число");
                    Timer t = new Timer(1500, o -> onRemoveOrderAdminClicked());
                    t.setRepeats(false);
                    t.start();
                    break;
                }

                // Если поле OrderIndex ещё не видно — значит первый этап
                if (!mainPanel.getPanelRemoverOrderAdmin().isOrderFieldVisible()) {
                    String ans1 = presenter.findOrderByClientIndex(index1Client);
                    if ("Клиент с таким номером не найден.".equals(ans1) || "У клиента пока нет заказов.".equals(ans1)) {
                        mainPanel.getPanelRemoverOrderAdmin().setMessage("Клиент с таким номером не найден или У клиента пока нет заказов.");
                        mainPanel.getPanelRemoverOrderAdmin().resetPanel(); // <- сброс перед выходом
                        Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
                        t.setRepeats(false);
                        t.start();
                        break;
                    }
                    // Показать список заказов и поле для ввода orderIndex
                    mainPanel.getPanelRemoverOrderAdmin().setMessage(ans1);
                    mainPanel.getPanelRemoverOrderAdmin().showOrderFields(true);
                    break;
                }

                // === Второй этап: поле для orderIndex уже видно ===
                String indeOrder = mainPanel.getPanelRemoverOrderAdmin().getOrderIndex().trim();
                int index1Order = -1;
                try {
                    index1Order = Integer.parseInt(indeOrder);
                } catch (NumberFormatException e) {
                    mainPanel.getPanelRemoverOrderAdmin().setMessage("Вы ввели не число");
                    Timer t = new Timer(1500, o -> onRemoveOrderAdminClicked());
                    t.setRepeats(false);
                    t.start();
                    break;
                }

                String answer = presenter.removeOrderAdmin(index1Client, index1Order);
                if ("SUCCESS".equals(answer)) {
                    mainPanel.getPanelRemoverOrderAdmin().setStatus("Успешно удалено", Color.GREEN);
                } else {
                    mainPanel.getPanelRemoverOrderAdmin().setStatus("Ошибка " + answer, Color.RED);
                }
                mainPanel.getPanelRemoverOrderAdmin().resetPanel();
                Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
                t.setRepeats(false);
                t.start();

                break;

            case MainPanel.PANEL_REMOVE_DISH_FROM_ORDER_ADMIN:
                String index2Client = mainPanel.getPanelRemoveDishFromOrderAdmin().getClientIndex().trim();
                int index3Client = -1;
                try {
                    index3Client = Integer.parseInt(index2Client);
                } catch (NumberFormatException e) {
                    mainPanel.getPanelRemoveDishFromOrderAdmin().setMessage("Вы ввели не число");

                    break;
                }

                // === Шаг 1: только клиент ===
                if (!mainPanel.getPanelRemoveDishFromOrderAdmin().isOrderInputVisible()) {
                    String ans2 = presenter.findOrderByClientIndex(index3Client);
                    if ("Клиент с таким номером не найден.".equals(ans2) || "У клиента пока нет заказов.".equals(ans2)) {
                        mainPanel.getPanelRemoveDishFromOrderAdmin().setMessage("Клиент с таким номером не найден или У клиента пока нет заказов.");
                        mainPanel.getPanelRemoveDishFromOrderAdmin().showOrderInput(false);
                        mainPanel.getPanelRemoveDishFromOrderAdmin().showDishInput(false);
                        new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN)).start();
                        break;
                    }
                    mainPanel.getPanelRemoveDishFromOrderAdmin().setMessage(ans2);
                    mainPanel.getPanelRemoveDishFromOrderAdmin().showOrderInput(true);
                    break;
                }

                // === Шаг 2: заказ введён, но блюдо ещё нет ===
                if (!mainPanel.getPanelRemoveDishFromOrderAdmin().isDishInputVisible()) {
                    String order1Str = mainPanel.getPanelRemoveDishFromOrderAdmin().getOrderIndex().trim();
                    int order1Index = -1;
                    try {
                        order1Index = Integer.parseInt(order1Str);
                    } catch (NumberFormatException e) {
                        mainPanel.getPanelRemoveDishFromOrderAdmin().setMessage("Вы ввели не число");

                        break;
                    }

                    String dishes = presenter.getAllDishesInOrderByClientIndex(index3Client, order1Index);
                    if ("Нет зарегистрированных клиентов".equals(dishes) || "Клиент не найден".equals(dishes) || "Заказ не найден".equals(dishes)) {
                        mainPanel.getPanelRemoveDishFromOrderAdmin().setMessage("Ошибка: Клиент или заказ не найден.");
                        mainPanel.getPanelRemoveDishFromOrderAdmin().showOrderInput(false);
                        mainPanel.getPanelRemoveDishFromOrderAdmin().showDishInput(false);
                        new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN)).start();
                        break;
                    }

                    mainPanel.getPanelRemoveDishFromOrderAdmin().setMessage(dishes);
                    mainPanel.getPanelRemoveDishFromOrderAdmin().showDishInput(true);
                    break;
                }

                // === Шаг 3: блюдо тоже введено, удаляем ===
                String dish1Str = mainPanel.getPanelRemoveDishFromOrderAdmin().getDishIndex().trim();
                int dish1Index = -1;
                try {
                    dish1Index = Integer.parseInt(dish1Str);
                } catch (NumberFormatException e) {
                    mainPanel.getPanelRemoveDishFromOrderAdmin().setMessage("Вы ввели не число");

                    break;
                }

                String result1 = presenter.removeDishFromOrderAdmin(index3Client,
                        Integer.parseInt(mainPanel.getPanelRemoveDishFromOrderAdmin().getOrderIndex()), dish1Index);

                if ("SUCCESS".equals(result1)) {
                    mainPanel.getPanelRemoveDishFromOrderAdmin().setStatus("Успешно удалено", Color.GREEN);
                    Timer k = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
                    k.setRepeats(false);
                    k.start();
                } else {
                    mainPanel.getPanelRemoveDishFromOrderAdmin().setStatus("Ошибка: " + result1, Color.RED);
                    Timer k = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
                    k.setRepeats(false);
                    k.start();
                }

                // Очистка и сброс
                mainPanel.getPanelRemoveDishFromOrderAdmin().showOrderInput(false);
                mainPanel.getPanelRemoveDishFromOrderAdmin().showDishInput(false);
                mainPanel.getPanelRemoveDishFromOrderAdmin().setClientIndexField("");
                mainPanel.getPanelRemoveDishFromOrderAdmin().setOrderIndexField("");
                mainPanel.getPanelRemoveDishFromOrderAdmin().setDishIndexField("");
                break;

            case MainPanel.PANEL_CHANGE_INFO_CLIENT:
                String phoneNumber1 = mainPanel.getChangeInfoClient().getEmail();
                String password = mainPanel.getChangeInfoClient().getPassword();
                String newName = mainPanel.getChangeInfoClient().getNewName();
                String newPhoneNumber = mainPanel.getChangeInfoClient().getNewPhone();
                String newEmail = mainPanel.getChangeInfoClient().getNewEmail();
                String newPassword = mainPanel.getChangeInfoClient().getNewPassword();
                ChangeInfoClientDTO changeInfoClientDTO = new ChangeInfoClientDTO(phoneNumber1, password, newName, newPhoneNumber,
                        newEmail, newPassword);
                String answ = presenter.changeInfoClient(changeInfoClientDTO);
                if ("SUCCESS".equals(answ)) {
                    mainPanel.getChangeInfoClient().setStatusMessage("Успешно изменено", Color.GREEN);
                    if (isAdmin) {
                        Timer k = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
                        k.setRepeats(false);
                        k.start();
                    } else {
                        Timer k = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_USER));
                        k.setRepeats(false);
                        k.start();
                    }
                } else {
                    mainPanel.getChangeInfoClient().setStatusMessage("Ошибка " + answ, Color.RED);
                    if (isAdmin) {
                        Timer k = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
                        k.setRepeats(false);
                        k.start();
                    } else {
                        Timer k = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_USER));
                        k.setRepeats(false);
                        k.start();
                    }
                }
                break;

            case MainPanel.PANEL_CHANGE_STATUS_ORDER:
                String clientInd = mainPanel.getPanelChangeStatusOrder().getClientIndex();
                int clientInd1 = -1;
                int orderIndex2 = -1;

                // === Шаг 0: Проверка индекса клиента ===
                try {
                    clientInd1 = Integer.parseInt(clientInd);
                } catch (NumberFormatException e) {
                    mainPanel.getPanelChangeStatusOrder().setMessage("Вы ввели не число в поле 'Индекс клиента'");
                    break;
                }

                // === Шаг 1: Проверка — введён ли индекс заказа ===
                if (!mainPanel.getPanelChangeStatusOrder().isOrderIndexEntered()) {
                    String ans2 = presenter.findOrderByClientIndex(clientInd1);

                    if ("Клиент с таким номером не найден.".equals(ans2) ||
                            "У клиента пока нет заказов.".equals(ans2)) {
                        mainPanel.getPanelChangeStatusOrder().setMessage(ans2);
                        mainPanel.getPanelChangeStatusOrder().showOrderInput(false);
                        mainPanel.getPanelChangeStatusOrder().showStatusInput(false);
                        mainPanel.getPanelChangeStatusOrder().showFinalStatus(false);
                        new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN)).start();
                        break;
                    }

                    mainPanel.getPanelChangeStatusOrder().setMessage(ans2);
                    mainPanel.getPanelChangeStatusOrder().showOrderInput(true);
                    break;
                }

                // === Шаг 2: Проверка — введён ли индекс статуса ===
                if (!mainPanel.getPanelChangeStatusOrder().isStatusIndexEntered()) {
                    String orderIndexStr = mainPanel.getPanelChangeStatusOrder().getOrderIndex();

                    try {
                        orderIndex2 = Integer.parseInt(orderIndexStr);
                    } catch (NumberFormatException e) {
                        mainPanel.getPanelChangeStatusOrder().setMessage("Вы ввели не число в поле 'Индекс заказа'");
                        break;
                    }

                    String statuses = presenter.printAllStatus();
                    mainPanel.getPanelChangeStatusOrder().setMessage(statuses);
                    mainPanel.getPanelChangeStatusOrder().showStatusInput(true);
                    break;
                }

                // === Шаг 3: Все поля введены — меняем статус ===
                String orderIndexStr = mainPanel.getPanelChangeStatusOrder().getOrderIndex();
                try {
                    orderIndex2 = Integer.parseInt(orderIndexStr);
                } catch (NumberFormatException e) {
                    mainPanel.getPanelChangeStatusOrder().setMessage("Вы ввели не число в поле 'Индекс заказа'");
                    break;
                }

                String statusIndex = mainPanel.getPanelChangeStatusOrder().getStatusIndex();
                int indexStatus = -1;

                try {
                    indexStatus = Integer.parseInt(statusIndex);
                } catch (NumberFormatException e) {
                    mainPanel.getPanelChangeStatusOrder().setMessage("Вы ввели не число в поле 'Индекс статуса'");
                    break;
                }

                String result2 = presenter.changeStatusOrder(clientInd1, orderIndex2, indexStatus);

                if ("SUCCESS".equals(result2)) {
                    mainPanel.getPanelChangeStatusOrder().setFinalStatus("Успешно изменен", Color.GREEN);
                } else {
                    mainPanel.getPanelChangeStatusOrder().setFinalStatus("Не удалось изменить: " + result2, Color.RED);
                }

                mainPanel.getPanelChangeStatusOrder().showOrderInput(false);
                mainPanel.getPanelChangeStatusOrder().showStatusInput(false);
                mainPanel.getPanelChangeStatusOrder().showFinalStatus(true);

                new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN)).start();
                break;

            case MainPanel.PANEL_CREATE_ORDER:
                String dishIndexes2 = mainPanel.getPanelCreateOrder().getDishIndexes();
                List<Integer> dish1Indexes1 = new ArrayList<>();
                for (String s : dishIndexes2.split(",")) {
                    try {
                        dish1Indexes1.add(Integer.parseInt(s.trim()));
                    } catch (NumberFormatException e) {
                        mainPanel.getPanelCreateOrder().setMessage("Вы ввели не число: " + s.trim());
                        return;
                    }
                }
                String answer1 = presenter.createOrder(dish1Indexes1);
                if ("SUCCESS".equals(answer1)) {
                    mainPanel.getPanelCreateOrder().setStatus("Успешно создан", Color.GREEN);
                } else {
                    mainPanel.getPanelCreateOrder().setStatus("Не удалось создать", Color.RED);
                }
                Timer j = new Timer(1500, o -> {
                    mainPanel.getPanelCreateOrder().clearInputFields();  // 1. Очистка полей
                    mainPanel.showPanel(MainPanel.PANEL_MENU_USER); // 2. Переход
                });
                j.setRepeats(false);
                j.start();

                break;

            case MainPanel.PANEL_ADD_DISH_IN_ORDER: {
                var panel = mainPanel.getPanelAddDishInOrder();
                String orderNum = panel.getOrderIndex();

                // Если второй этап еще не начался (dishIndexes скрыт)
                if (!panel.isDishInputVisible()) {
                    if (orderNum == null || orderNum.isBlank()) {
                        panel.setStatus("Введите индекс заказа!", Color.RED);
                        System.out.println("1111");
                        break;
                    }

                    int orderNumber;
                    try {
                        orderNumber = Integer.parseInt(orderNum);
                    } catch (NumberFormatException e) {
                        panel.setStatus("Неверный формат числа", Color.RED);
                        System.out.println("22222");
                        break;
                    }

                    // Переход ко второму этапу — показать меню и поле индексов
                    panel.setMessage(presenter.printMenu());
                    panel.showDishInput(true);
                    break;
                }

                // Второй этап: обработка индексов блюд
                String dishIndexes1 = panel.getDishIndexes();
                List<Integer> dishIndexList = new ArrayList<>();

                for (String s : dishIndexes1.split(",")) {
                    try {
                        dishIndexList.add(Integer.parseInt(s.trim()));
                    } catch (NumberFormatException e) {
                        System.out.println("3333");
                        panel.setMessage("Неверный индекс блюда: " + s.trim());
                        break;
                    }
                }

                int orderNumber = Integer.parseInt(orderNum); // Уже проверено ранее
                String answer2 = presenter.addDishInOrder(orderNumber, dishIndexList);


                if ("SUCCESS".equals(answer2)) {
                    mainPanel.getPanelAddDishInOrder().setStatus("Блюда добавлены", Color.GREEN);

                } else {
                    mainPanel.getPanelAddDishInOrder().setStatus("Ошибка добавления", Color.RED);

                }
                Timer k = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_USER));
                k.setRepeats(false);
                k.start();
                Timer g = new Timer(1500, o -> mainPanel.getPanelAddDishInOrder().clearFields());
                g.setRepeats(false);
                g.start();
                break;
            }
            case MainPanel.PANEL_REMOVE_ORDER: {
                String orderRemove = mainPanel.getPanelRemoveOrder().getOrderIndex();
                int orderDel = -1;
                try {
                    orderDel = Integer.parseInt(orderRemove);
                } catch (NumberFormatException e) {
                    mainPanel.getPanelRemoveOrder().setMessage("Вы ввели не число");
                    break;
                }
                String removeOrder = presenter.removeOrderThis(orderDel);
                if ("SUCCESS".equals(removeOrder)) {
                    mainPanel.getPanelRemoveOrder().setStatus("Заказ удален", Color.GREEN);

                } else {
                    mainPanel.getPanelRemoveOrder().setStatus("Ошибка удаления", Color.RED);

                }
                Timer k = new Timer(1500, o -> {
                    mainPanel.getPanelRemoveOrder().clearFields();
                    mainPanel.showPanel(MainPanel.PANEL_MENU_USER);
                });
                k.setRepeats(false);
                k.start();

                break;
            }

            case MainPanel.PANEL_REMOVE_DISH_FROM_ORDER: {
                String clInd = mainPanel.getPanelRemoveDishFromOrder().getOrderIndex();
                int indCl = -1;
                try {
                    indCl = Integer.parseInt(clInd);
                } catch (NumberFormatException e) {
                    mainPanel.getPanelRemoveDishFromOrder().setMessage("Вы ввели не число");
                    break;
                }

                // показать список блюд из заказа
                mainPanel.getPanelRemoveDishFromOrder().setMessage(presenter.printAllDishesInOrderThisClient(indCl));

                // если поле для блюда ещё не показано — показать
                if (!mainPanel.getPanelRemoveDishFromOrder().isDishInputVisible()) {
                    mainPanel.getPanelRemoveDishFromOrder().showDishInput(true);
                    break;
                }

                // поле уже показано — пробуем удалить
                String inDish = mainPanel.getPanelRemoveDishFromOrder().getDishIndex();
                int dishIn = -1;
                try {
                    dishIn = Integer.parseInt(inDish);
                } catch (NumberFormatException e) {
                    mainPanel.getPanelRemoveDishFromOrder().setMessage("Вы ввели не число");
                    break;
                }

                String delDish = presenter.removeDishFromOrder(indCl, dishIn);
                if ("SUCCESS".equals(delDish)) {
                    mainPanel.getPanelRemoveDishFromOrder().setStatus("Блюдо удалено", Color.GREEN);
                } else {
                    mainPanel.getPanelRemoveDishFromOrder().setStatus("Ошибка удаления", Color.RED);
                }

                Timer k = new Timer(1500, o -> {
                    mainPanel.getPanelRemoveDishFromOrder().clearFields();
                    mainPanel.showPanel(MainPanel.PANEL_MENU_USER);
                });
                k.setRepeats(false);
                k.start();
                break;
            }


            default: {
                System.out.println("Неизвестная панель: " + activePanel);
            }

        }
    }

    @Override
    public void onPrintMenuClicked() {
        String ans = presenter.printMenu();
        System.out.println(ans);
        if (isAdmin) {
            mainPanel.getPrintPanelMenuAdmin().getMessage().setText(ans);
            mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN);
        } else {
            mainPanel.getPrintPanelMenuUser().getMessageArea().setText(ans);
            mainPanel.showPanel(MainPanel.PANEL_MENU_USER);
        }
    }

    @Override
    public void onCreateOrderClicked() {
        mainPanel.getPanelCreateOrder().setMessage(presenter.printMenu());
        mainPanel.showPanel(MainPanel.PANEL_CREATE_ORDER);
    }

    @Override
    public void onAddDishInOrderClicked() {
        mainPanel.showPanel(MainPanel.PANEL_ADD_DISH_IN_ORDER);
        mainPanel.getPanelAddDishInOrder().setMessage(presenter.printOrderThisClient());

    }

    @Override
    public void onPrintOrderThisClientClicked() {
        String ans = presenter.printOrderThisClient();
        mainPanel.getPrintPanelMenuUser().setMessageArea(ans);
        mainPanel.showPanel(MainPanel.PANEL_MENU_USER);

    }

    @Override
    public void onRemoveDishFromOrderThisClicked() {
        mainPanel.getPanelRemoveDishFromOrder().setMessage(presenter.printOrderThisClient());
        mainPanel.showPanel(MainPanel.PANEL_REMOVE_DISH_FROM_ORDER);
    }

    @Override
    public void onRemoveOrderThisClicked() {
        mainPanel.showPanel(MainPanel.PANEL_REMOVE_ORDER);
        mainPanel.getPanelRemoveOrder().setMessage(presenter.printOrderThisClient());
    }

    @Override
    public void onAddDishToMenuClicked() {
        mainPanel.showPanel(MainPanel.PANEL_ADD_DISH_TO_MENU);

    }

    @Override
    public void onDeleteDishFromMenuClicked() {
        mainPanel.showPanel(MainPanel.PANEL_ADD_INDEX_FOR_DISH_REMOVE_TO_MENU);
        mainPanel.getPanelAddIndexForDishRemoveToMenu().setMessage(presenter.printMenu());

    }

    @Override
    public void onBackUserMenu() {
        if (isAuthorized) mainPanel.showPanel(MainPanel.PANEL_MENU_USER);
        if (isAdmin) mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN);
    }

    @Override
    public void onPrintAllOrdersClicked() {
        String ans = presenter.printAllOrders();
        System.out.println(ans);
        mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN);
        mainPanel.getPrintPanelMenuAdmin().setMessage(ans);

    }

    @Override
    public void onPrintInfoThisClientClicked() {
        String ans = presenter.printInfoThisClient();
        if (isAdmin) mainPanel.getPrintPanelMenuAdmin().getMessage().setText(ans);
        else mainPanel.getPrintPanelMenuUser().getMessageArea().setText(ans);
    }

    @Override
    public void onChangeInfoClientClicked() {
        mainPanel.showPanel(MainPanel.PANEL_CHANGE_INFO_CLIENT);
    }

    @Override
    public void onCreateOrderAdminClicked() {
        mainPanel.showPanel(MainPanel.PANEL_CREATE_ORDER_ADMIN);
        String ans = presenter.printInfoAllClient();
        String dishes = presenter.printMenu();
        if ("Нет зарегистрированных клиентов!".equals(ans)) {
            mainPanel.getPanelCreateOrderAdmin().setMessage("Нет зарегистрированных клиентов!");
            Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
            t.setRepeats(false);
            t.start();
            return;
        }
        if ("Нет блюд в меню!".equals(dishes)) {
            mainPanel.getPanelCreateOrderAdmin().setMessage("Нет блюд в меню!");
            Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
            t.setRepeats(false);
            t.start();
            return;
        }
        mainPanel.getPanelCreateOrderAdmin().setMessage(ans + "\n\n" + dishes);

    }

    @Override
    public void onAddDishInOrderAdminClicked() {
        mainPanel.showPanel(MainPanel.PANEL_ADD_DISH_IN_ORDER_ADMIN);
        String ans = presenter.printInfoAllClient();
        String dishes = presenter.printMenu();
        if ("Нет зарегистрированных клиентов!".equals(ans)) {
            mainPanel.getPanelAddDishInOrderAdmin().setMessage("Нет зарегистрированных клиентов!");
            Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
            t.setRepeats(false);
            t.start();
            return;
        }
        if ("Нет блюд в меню!".equals(dishes)) {
            mainPanel.getPanelAddDishInOrderAdmin().setMessage("Нет блюд в меню!");
            Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
            t.setRepeats(false);
            t.start();
            return;
        }
        mainPanel.getPanelAddDishInOrderAdmin().setMessage(ans + "\n\n" + dishes);


    }

    @Override
    public void onRemoveOrderAdminClicked() {
        mainPanel.showPanel(MainPanel.PANEL_REMOVE_ORDER_ADMIN);
        String ans = presenter.printInfoAllClient();
        if ("Нет зарегистрированных клиентов!".equals(ans)) {
            mainPanel.getPanelRemoverOrderAdmin().setMessage("Нет зарегистрированных клиентов!");
            Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
            t.setRepeats(false);
            t.start();
            return;
        }
        mainPanel.getPanelRemoverOrderAdmin().setMessage(presenter.printInfoAllClient());

    }

    @Override
    public void onRemoveDishFromOrderAdminClicked() {
        mainPanel.showPanel(MainPanel.PANEL_REMOVE_DISH_FROM_ORDER_ADMIN);
        String ans = presenter.printInfoAllClient();
        if ("Нет зарегистрированных клиентов!".equals(ans)) {
            mainPanel.getPanelRemoveDishFromOrderAdmin().setMessage("Нет зарегистрированных клиентов!");
            Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
            t.setRepeats(false);
            t.start();
            return;
        }
        mainPanel.getPanelRemoveDishFromOrderAdmin().setMessage(presenter.printInfoAllClient());

    }

    @Override
    public void onPrintInfoAllClientsClicked() {
        String ans = presenter.printInfoAllClient();
        mainPanel.getPrintPanelMenuAdmin().getMessage().setText(ans);
    }

    @Override
    public void onBackClicked() {
        if (isAuthorized) mainPanel.showPanel(MainPanel.PANEL_MENU_USER);
        if (isAdmin) mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN);
    }

    @Override
    public void onPrintOrdersByClientPhoneClicked() {
        mainPanel.getPanelPrintInfoClient().setInfoClient("Посмотреть заказы клиента по номеру телефона");
        mainPanel.getPanelPrintInfoClient().setAnswer(presenter.printInfoAllClient());
        mainPanel.showPanel(MainPanel.PANEL_INFO_CLIENT);
    }

    @Override
    public void onChangeStatusOrderClicked() {
        mainPanel.showPanel(MainPanel.PANEL_CHANGE_STATUS_ORDER);
        String ans = presenter.printInfoAllClient();
        if ("Нет зарегистрированных клиентов!".equals(ans)) {
            mainPanel.getPanelChangeStatusOrder().setMessage("Нет зарегистрированных клиентов!");
            Timer t = new Timer(1500, o -> mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN));
            t.setRepeats(false);
            t.start();
            return;
        }
        mainPanel.getPanelChangeStatusOrder().setMessage(presenter.printInfoAllClient());
    }

    @Override
    public void onPrintSortOrdersByDataTimeClicked() {
        String ans = presenter.printSortOrdersByDataTime();
        mainPanel.getPrintPanelMenuAdmin().getMessage().setText(ans);

    }

    @Override
    public void onPrintSortOrdersByStatusClicked() {
        String ans = presenter.printSortOrdersByStatus();
        mainPanel.getPrintPanelMenuAdmin().getMessage().setText(ans);
    }

    @Override
    public void onFinishClicked() {
        if (isAuthorized) {
            mainPanel.getPrintPanelMenuUser().setMessageArea("До новыых встреч");
            mainPanel.showPanel(MainPanel.PANEL_MENU_USER);
            presenter.saveListDishes();
            presenter.saveListClients();
            Timer t = new Timer(1500, e -> work = false);
            t.setRepeats(false);
            t.start();
        } else {
            mainPanel.getPrintPanelMenuAdmin().setMessage("До новых встреч");
            mainPanel.showPanel(MainPanel.PANEL_MENU_ADMIN);
            presenter.saveListDishes();
            presenter.saveListClients();
            Timer t = new Timer(1500, e -> work = false);
            t.setRepeats(false);
            t.start();
        }
        Timer t = new Timer(1500, e -> {
            work = false;
            System.exit(0); // завершаем полностью
        });
        t.setRepeats(false);
        t.start();
    }
}









