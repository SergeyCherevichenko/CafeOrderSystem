package uz.cherevichenko_sergey.cafe_order_system.presenter.presenter_socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter.*;
import uz.cherevichenko_sergey.cafe_order_system.view.View;
import uz.cherevichenko_sergey.cafe_order_system.view.dto_view.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class PresenterSocket {
    private final View view;
    private final Socket clientSocket;
    private final ObjectMapper objectMapper;
    private final PrintWriter out;
    private final BufferedReader in;
    private  final String ip;
    private  final int port;

    // Конструктор по умолчанию — localhost
    public PresenterSocket(View view) throws IOException {
        this(view, "localhost", 12345);
    }

    // Конструктор с IP — порт по умолчанию
    public PresenterSocket(View view, String ip) throws IOException {
        this(view, ip, 12345);
    }

    // Центральный конструктор
    public PresenterSocket(View view, String ip, int port) throws IOException {
        this.view = view;
        this.ip = ip;
        this.port = port;
        this.clientSocket = new Socket(ip, port);
        this.objectMapper = new ObjectMapper();
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }



    public boolean sendCommandToServer(String message) {
        try {
            out.println(message); // автоматически добавляет \n
            return true;
        } catch (Exception e) {
            System.out.println("Не удалось отправить сообщение на сервер: " + e.getMessage());
            return false;
        }
    }

    public String getMessageFromServer() {
        try {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line).append("\n");
                if (!in.ready()) break;  // ждёт, пока все данные будут считаны
            }
            return sb.toString().trim(); // убираем лишний \n в конце
        } catch (Exception e) {
            System.out.println("Не удалось принять сообщение от сервера: " + e.getMessage());
            return null;
        }
    }


    public String clientRegistry(ClientRegistryDTO clientRegistryDTO) {
        try {
            ClientRegistryPresenterDTO dto = new ClientRegistryPresenterDTO(clientRegistryDTO);
            String json = objectMapper.writeValueAsString(dto);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String clientLogin(ClientLoginDTO clientLoginDTO) {
        try {
            ClientLoginPresenterDTO dto = new ClientLoginPresenterDTO(clientLoginDTO);
            String json = objectMapper.writeValueAsString(dto);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String clientAdmin() {
        try {
            IsAdminPresenterDTO dto = new IsAdminPresenterDTO();
            String json = objectMapper.writeValueAsString(dto);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String printMenu() {
        try {
            PrintMenuPresenterDTO dto = new PrintMenuPresenterDTO();
            String json = objectMapper.writeValueAsString(dto);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String printAllOrders() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.createObjectNode();

            node.put("action", "print_all_orders");
            String json = objectMapper.writeValueAsString(node);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public String printInfoThisClient() {
        try {
            PrintInfoThisClientPresenterDTO dto = new PrintInfoThisClientPresenterDTO();
            String json = objectMapper.writeValueAsString(dto);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String printInfoAllClient() {
        try {
            PrintInfoAllClientPresenterDTO dto = new PrintInfoAllClientPresenterDTO();
            String json = objectMapper.writeValueAsString(dto);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String printOrderByClientPhone(String phoneNumber) {
        try {
            PrintOrderByClientPhonePresenterDTO dto = new PrintOrderByClientPhonePresenterDTO(phoneNumber);
            String json = objectMapper.writeValueAsString(dto);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String printOrderThisClient() {
        try {
            PrintOrderThisClientPresenterDTO dto = new PrintOrderThisClientPresenterDTO();
            String json = objectMapper.writeValueAsString(dto);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String addDishToMenu(AddDishToMenuDTO dtoView) {
        try {
            AddDishToMenuPresenterDto dto = new AddDishToMenuPresenterDto(dtoView);
            String json = objectMapper.writeValueAsString(dto);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String saveListDishes() {
        try {
            SaveListDishesPresenterDTO saveListDishesPresenterDTO = new SaveListDishesPresenterDTO();
            String json = objectMapper.writeValueAsString(saveListDishesPresenterDTO);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String saveListClients() {
        try {
            SaveListClientPresenterDto saveListClientPresenterDto = new SaveListClientPresenterDto();
            String json = objectMapper.writeValueAsString(saveListClientPresenterDto);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String sendIndexFromRemoveDishToMenu(SendIndexDTO sendIndexDTO) {
        try {
            SendIndexDishForRemoveDishToMenu sendIndexDishForRemoveDishToMenu =
                    new SendIndexDishForRemoveDishToMenu(sendIndexDTO);
            String json = objectMapper.writeValueAsString(sendIndexDishForRemoveDishToMenu);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String findClientByPhoneNumber(String phoneNumber) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.createObjectNode();

            node.put("action", "print_order_by_client_phone");
            node.put("phoneNumber", phoneNumber);

            String json = objectMapper.writeValueAsString(node);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createOrderAdmin(CreateOrderAdminDTO createOrderAdminDTO) {
        try {
            CreateOrderAdminPresenterDTO createOrderAdminPresenterDTO = new CreateOrderAdminPresenterDTO(createOrderAdminDTO);
            String json = objectMapper.writeValueAsString(createOrderAdminPresenterDTO);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String findOrderByClientIndex(int index) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.createObjectNode();

            node.put("action", "print_order_by_client_index");
            node.put("index", index);

            String json = objectMapper.writeValueAsString(node);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String addDishToOrderAdmin(AddDishInOrderAdminDTO addDishInOrderAdminDTO) {
        try {
            AddDishInOrderAdminPresenterDTO addDishInOrderAdminPresenterDTO = new AddDishInOrderAdminPresenterDTO(addDishInOrderAdminDTO);
            String json = objectMapper.writeValueAsString(addDishInOrderAdminPresenterDTO);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String removeOrderAdmin(int indexClient, int indexOrder) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.createObjectNode();

            node.put("action", "remove_order_admin");
            node.put("indexClient", indexClient);
            node.put("indexOrder", indexOrder);

            String json = objectMapper.writeValueAsString(node);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getAllDishesInOrderByClientIndex(int indexClient, int indexOrder) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.createObjectNode();

            node.put("action", "print_all_dishes_in_order");
            node.put("indexClient", indexClient);
            node.put("indexOrder", indexOrder);

            String json = objectMapper.writeValueAsString(node);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String removeDishFromOrderAdmin(int indexClient, int indexOrder, int indexDish) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.createObjectNode();

            node.put("action", "remove_dish_from_order_admin");
            node.put("indexClient", indexClient);
            node.put("indexOrder", indexOrder);
            node.put("indexDish", indexDish);

            String json = objectMapper.writeValueAsString(node);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String printSortOrdersByDataTime() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.createObjectNode();

            node.put("action", "sort_orders_by_data_time_admin");
            String json = objectMapper.writeValueAsString(node);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String printSortOrdersByStatus() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.createObjectNode();

            node.put("action", "sort_orders_status_admin");
            String json = objectMapper.writeValueAsString(node);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String changeInfoClient(ChangeInfoClientDTO changeInfoClientDTO) {
        try {
            ChangeInfoClientPresenterDTO changeInfoClientPresenterDTO = new ChangeInfoClientPresenterDTO(changeInfoClientDTO);
            String json = objectMapper.writeValueAsString(changeInfoClientPresenterDTO);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String printAllStatus() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.createObjectNode();

            node.put("action", "print_all_status");
            String json = objectMapper.writeValueAsString(node);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String changeStatusOrder(int indexClient, int indexOrder, int indexStatus) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.createObjectNode();

            node.put("action", "change_status_order");
            node.put("indexClient", indexClient);
            node.put("indexOrder", indexOrder);
            node.put("indexStatus", indexStatus);

            String json = objectMapper.writeValueAsString(node);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createOrder(List<Integer> dishes) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.createObjectNode();

            node.put("action", "create_order");

            // Добавляем массив dishes
            ArrayNode dishesArray = objectMapper.createArrayNode();
            for (Integer dish : dishes) {
                dishesArray.add(dish);
            }
            node.set("dishes", dishesArray);

            String json = objectMapper.writeValueAsString(node);

            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String addDishInOrder(int orderNumber, List<Integer> dish1Indexes) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.createObjectNode();

            node.put("action", "add_dish_in_order");
            node.put("orderNumber", orderNumber);

            // Добавляем массив dishes
            ArrayNode dishesArray = objectMapper.createArrayNode();
            for (Integer dish : dish1Indexes) {
                dishesArray.add(dish);
            }
            node.set("dishes", dishesArray);

            String json = objectMapper.writeValueAsString(node);

            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String removeOrderThis(int index) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.createObjectNode();

            node.put("action", "remove_order_this");
            node.put("indexClient", index);


            String json = objectMapper.writeValueAsString(node);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public String printAllDishesInOrderThisClient(int index) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.createObjectNode();

            node.put("action", "print_all_dishes_in_order_this_client");
            node.put("index", index);


            String json = objectMapper.writeValueAsString(node);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String removeDishFromOrder(int indexOrder, int indexDish) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.createObjectNode();

            node.put("action", "remove_dish_from_order_this");
            node.put("indexOrder", indexOrder);
            node.put("indexDish", indexDish);


            String json = objectMapper.writeValueAsString(node);
            if (!sendCommandToServer(json)) return null;
            return getMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public static boolean checkServerAvailable(String ip, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(ip, port), 1000);
            return true;
        } catch (IOException e) {
            return false;
        }
    }



}
