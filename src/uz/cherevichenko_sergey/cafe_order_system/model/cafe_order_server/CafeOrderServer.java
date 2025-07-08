package uz.cherevichenko_sergey.cafe_order_system.model.cafe_order_server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import uz.cherevichenko_sergey.cafe_order_system.model.service.MenuService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Consumer;

public class CafeOrderServer {
    private final MenuService menuService;
    private ServerSocket serverSocket;
    private volatile boolean isRunning = false;
    private Consumer<String> logger = System.out::println;

    public CafeOrderServer() {
        this.menuService = new MenuService();
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(12345);
            serverSocket.setSoTimeout(1000); // обязательно!
            isRunning = true;
            log("Сервер запущен, IP-адрес: " + getLocalIpAddress() + ", порт: 12345");


            while (isRunning) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    log("Клиент подключён!");
                    new Thread(() -> handleClient(clientSocket)).start();
                } catch (SocketTimeoutException e) {
                    // ничего не пишем — просто ждём следующую проверку
                } catch (IOException e) {
                    log("Исключение в accept(): " + e.getMessage());
                    break;
                }
            }

            log("Сервер остановлен. Выход из основного цикла.");

        } catch (IOException e) {
            log("Ошибка при запуске сервера: " + e.getMessage());
        }
    }


    public void stopServer() {
        log(">>> Вызван stopServer()");
        isRunning = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                log("Закрываем сокет сервера...");
                serverSocket.close();
            }
        } catch (IOException e) {
            log("Ошибка при закрытии сервера: " + e.getMessage());
        }

        menuService.saveClientList();
        menuService.saveListDishes();

        log("Сервер остановлен (данные сохранены).");
    }


    private void handleClient(Socket clientSocket) {
        System.out.println("Обрабатываем клиента: " + clientSocket.getInetAddress());
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String json;
            while ((json = in.readLine()) != null) {
                log("Получено сообщение от клиента: " + json);
                String response = answerFromServerFunction(json);
                out.println(response);
                log("Ответ отправлен: " + response);
            }
        } catch (IOException e) {
            log("Клиент отключился или произошла ошибка: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                log("Соединение с клиентом закрыто.");
            } catch (IOException e) {
                log("Ошибка при закрытии соединения: " + e.getMessage());
            }
        }
    }

    public String answerFromServerFunction(String json) {
        if (json == null || json.isEmpty()) return "FAILURE: пустое сообщение";

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(json);
            String action = root.path("action").asText();

            switch (action) {
                case "client_registry":
                    JsonNode registryDTO = root.path("clientRegistryDTO");
                    return menuService.clientRegistry(
                            registryDTO.path("name").asText(),
                            registryDTO.path("phoneNumber").asText(),
                            registryDTO.path("email").asText(),
                            registryDTO.path("password").asText()
                    ) ? "SUCCESS" : "FAILURE";

                case "client_login":
                    JsonNode loginDTO = root.path("clientLoginDTO");
                    return menuService.clientLogin(
                            loginDTO.path("name").asText(),
                            loginDTO.path("email").asText(),
                            loginDTO.path("password").asText()
                    ) ? "SUCCESS" : "FAILURE";

                case "isAdmin":
                    return menuService.isAdmin() ? "SUCCESS" : "FAILURE";

                case "print_menu":
                    return menuService.getMenu();

                case "print_all_orders":
                    return menuService.getInfoAllOrders();

                case "print_info_this_client":
                    return menuService.getCurrentClientInfo();

                case "print_info_all_client":
                    return menuService.getInfoAllClients();

                case "print_order_by_client_phone":
                    return menuService.getOrderByClientPhone(root.path("phoneNumber").asText());

                case "print_order_this_client":
                    return menuService.getOrdersCurrentClient();

                case "add_dish_to_menu":
                    JsonNode addDishToMenuDTO = root.path("addDishToMenuDTO");
                    return menuService.addDish(
                            addDishToMenuDTO.path("nameDish").asText(),
                            addDishToMenuDTO.path("path").asText(),
                            addDishToMenuDTO.path("cost").asInt(),
                            addDishToMenuDTO.path("count").asInt()
                    ) ? "SUCCESS" : "FAILURE";

                case "save_list_dishes":
                    return menuService.saveListDishes() ? "SUCCESS" : "FAILURE";

                case "save_list_clients":
                    return menuService.saveClientList() ? "SUCCESS" : "FAILURE";

                case "remove_dish_from_menu":
                    JsonNode sendIndexDTO = root.path("sendIndexDTO");
                    return menuService.removeDishFromMenu(sendIndexDTO.path("index").asInt()
                    ) ? "SUCCESS" : "FAILURE";

                case "create_order_admin":
                    JsonNode dto = root.path("createOrderAdminDTO");
                    String phone = dto.path("phoneNumber").asText();

                    List<Integer> dishIndexes = new ArrayList<>();
                    JsonNode indexesNode = dto.path("dishIndexes");

                    if (indexesNode.isArray()) {
                        for (JsonNode node : indexesNode) {
                            dishIndexes.add(node.asInt());
                        }
                    }

                    return menuService.createOrderAdmin(phone, dishIndexes) ? "SUCCESS" : "FAILURE";

                case "print_order_by_client_index":
                    return menuService.getOrderByClientIndex(root.path("index").asInt());

                case "add_dish_in_order_admin":
                    JsonNode dt = root.path("addDishInOrderAdminDTO");
                    int indexClient = dt.path("indexClient").asInt();
                    int orderIndex = dt.path("orderIndex").asInt();
                    List<Integer> dishIndex = new ArrayList<>();
                    JsonNode indexNode = dt.path("dishIndexes");

                    if (indexNode.isArray()) {
                        for (JsonNode node : indexNode) {
                            dishIndex.add(node.asInt());
                        }
                    }

                    return menuService.addDishInOrderAdmin(indexClient, orderIndex, dishIndex) ? "SUCCESS" : "FAILURE";

                case "remove_order_admin":
                    return menuService.removeOrderByIndexAdmin(root.path("indexClient").asInt(),
                            root.path("indexOrder").asInt()) ? "SUCCESS" : "FAILURE";

                case "print_all_dishes_in_order":
                    return menuService.getAllDishesInOrderByClientIndex(root.path("indexClient").asInt(),
                            root.path("indexOrder").asInt());

                case "remove_dish_from_order_admin":
                    return menuService.removeDishFromOrderByIndexesAdmin(root.path("indexClient").asInt(),
                            root.path("indexOrder").asInt(), root.path("indexDish").asInt()) ? "SUCCESS" : "FAILURE";

                case "sort_orders_by_data_time_admin":
                    return menuService.getAllOrdersSortedByAddDate();

                case "sort_orders_status_admin":
                    return menuService.getAllOrdersSortedByStatus();

                case "change_info_client":
                    JsonNode dto1 = root.path("changeInfoClientDTO");
                    String email = dto1.path("email").asText();
                    String password = dto1.path("password").asText();
                    String newName = dto1.path("newName").asText();
                    String newPhoneNumber = dto1.path("newPhoneNumber").asText();
                    String newEmail = dto1.path("newEmail").asText();
                    String newPassword = dto1.path("newPassword").asText();

                    return menuService.changeInfoClient(email, password, newName, newPhoneNumber, newEmail, newPassword
                    ) ? "SUCCESS" : "FAILURE";

                case "print_all_status":
                    return menuService.getAllStatus();

                case "change_status_order":
                    return menuService.changeStatusOrder(root.path("indexClient").asInt(),
                            root.path("indexOrder").asInt(), root.path("indexStatus").asInt()
                    ) ? "SUCCESS" : "FAILURE";

                case "create_order":
                    JsonNode dishesNode = root.get("dishes");
                    List<Integer> dishes = new ArrayList<>();
                    if (dishesNode != null && dishesNode.isArray()) {
                        for (JsonNode node : dishesNode) {
                            dishes.add(node.asInt());
                        }
                    }
                    return menuService.createOrder(dishes) ? "SUCCESS" : "FAILURE";

                case "add_dish_in_order":
                    int indexOrder = root.path("orderNumber").asInt();
                    JsonNode dishesNode1 = root.get("dishes");
                    List<Integer> dishes1 = new ArrayList<>();
                    if (dishesNode1 != null && dishesNode1.isArray()) {
                        for (JsonNode node : dishesNode1) {
                            dishes1.add(node.asInt());
                        }
                    }
                    return menuService.addDishInOrder(indexOrder, dishes1) ? "SUCCESS" : "FAILURE";

                case "remove_order_this":
                    return menuService.removeOrderByIndex(root.path("index").asInt()) ? "SUCCESS" : "FAILURE";

                case "print_all_dishes_in_order_this_client":
                    return menuService.getDishFromOrderThisClient(root.path("index").asInt());

                case "remove_dish_from_order_this":
                    return menuService.removeDishFromOrderByIndexes(root.path("indexOrder").asInt(),
                            root.path("indexDish").asInt()) ? "SUCCESS" : "FAILURE";


                default:
                    return "FAILURE: неизвестное действие";
            }
        } catch (Exception e) {
            System.out.println("Ошибка обработки JSON: " + e.getMessage());
            return "FAILURE: ошибка обработки JSON";
        }
    }

    public void setLogger(Consumer<String> logger) {
        this.logger = logger;
    }

    private void log(String msg) {
        if (logger != null) logger.accept(msg);
    }


    private String getLocalIpAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                if (!iface.isUp() || iface.isLoopback() || iface.isVirtual()) continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr.isLoopbackAddress()) continue;
                    if (addr instanceof Inet4Address) return addr.getHostAddress();
                }
            }
        } catch (Exception e) {
            return "Не удалось определить IP: " + e.getMessage();
        }
        return "IP не найден";
    }




}
