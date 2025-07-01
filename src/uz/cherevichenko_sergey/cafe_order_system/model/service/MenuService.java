package uz.cherevichenko_sergey.cafe_order_system.model.service;

import org.apache.commons.codec.digest.DigestUtils;
import uz.cherevichenko_sergey.cafe_order_system.model.client.Client;
import uz.cherevichenko_sergey.cafe_order_system.model.enums.Status;
import uz.cherevichenko_sergey.cafe_order_system.model.storage.FileHandlerForListClients;
import uz.cherevichenko_sergey.cafe_order_system.model.client.ListClients;
import uz.cherevichenko_sergey.cafe_order_system.model.dish.Dish;
import uz.cherevichenko_sergey.cafe_order_system.model.storage.FileHandlerForListDishes;
import uz.cherevichenko_sergey.cafe_order_system.model.dish.ListDishes;
import uz.cherevichenko_sergey.cafe_order_system.model.order.Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MenuService {



    private ClientsService clientsService;
    private DishService dishService;
    private Order order;
    private FileHandlerForListClients fileHandlerForListClients;
    private FileHandlerForListDishes fileHandlerForListDishes;
    private Client currentClient;
    private List<Order> allOrders;

    public MenuService() {
        clientsService = new ClientsService();
        dishService = new DishService();
        this.fileHandlerForListClients = new FileHandlerForListClients();
        this.fileHandlerForListDishes = new FileHandlerForListDishes();
        ListDishes dishesFromFile = fileHandlerForListDishes.read();
        if (dishesFromFile == null || dishesFromFile.getAllDishes() == null) {
            dishService.setListDishes(new ListDishes());
        } else {
            dishService.setListDishes(dishesFromFile);
        }

        ListClients clientsFomFile = fileHandlerForListClients.read();
        if(clientsFomFile == null || clientsFomFile.getAllClients() == null ){
            clientsService.setListClients( new ListClients());
        } else {
            clientsService.setListClients(clientsFomFile);
        }
        this.currentClient = null;
        this.allOrders = new ArrayList<>();
    }
    public boolean clientLogin(String name,String email, String password){
        this.currentClient =  clientsService.login(email,password);
        return  currentClient !=null;

    }
    public boolean isAdmin(){
        return currentClient.isAdmin();
    }
    public boolean clientRegistry(String name, String phoneNumber, String email, String password) {
        boolean added = clientsService.addClient(name, phoneNumber, email, password);
        if (added) {
            this.currentClient = clientsService.findClientByPhoneNumber(phoneNumber);
            return true;
        }
        return false;
    }



    public boolean addClient(String name, String phoneNumber,String email, String password) {
         return clientsService.addClient(name,phoneNumber,email, password);
    }

    public boolean addDish(String name, String pathImage, int cost, int count) {
       return dishService.addDish(name,pathImage, cost,count);
    }


    public String getMenu() {
        if(dishService.getListDishes().getSize() == 0) {
            return "Нет блюд в меню!";
        }
        return dishService.toString();
    }

    public boolean createOrder(List<Integer> dishIndexes) {
        if (currentClient == null) return false;


        Order order = null;
        int addedCount = 0;

        for (int i : dishIndexes) {
            Dish d = dishService.findDishByIndex(i);
            if (d != null && d.getCount() > 0) {
                if (order == null) order = new Order();
                order.addDishInOrder(d);
                d.setCount(d.getCount() - 1);
                addedCount++;
            }
        }

        if (addedCount == 0) return false; // ничего не добавили

        currentClient.addOrder(order);
        return true;
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public boolean createOrderAdmin(String phoneNumber, List<Integer> dishIndexes) {
        Client client = clientsService.findClientByPhoneNumber(phoneNumber);
        if (client == null) return false;

        Order order = null;
        int addedCount = 0;

        for (int i : dishIndexes) {
            Dish d = dishService.findDishByIndex(i);
            if (d != null && d.getCount() > 0) {
                if (order == null) order = new Order();
                order.addDishInOrder(d);
                d.setCount(d.getCount() - 1);
                addedCount++;
            }
        }

        if (addedCount == 0) return false; // ничего не добавили

        client.addOrder(order);
        return true;
    }




    public boolean addDishInOrder(int indexOrder, List<Integer> dishIndexes) {
        if (currentClient == null) return false;
        if (dishService.getListDishes().getAllDishes().isEmpty()) return false;
        if (currentClient.getOrders().isEmpty()) return false;
        if (indexOrder < 0 || indexOrder >= currentClient.getOrders().size()) return false;

        Order order = currentClient.getOrders().get(indexOrder);
        int addedCount = 0;

        for (int i : dishIndexes) {
            Dish d = dishService.findDishByIndex(i);
            if (d != null && d.getCount() > 0) {
                order.addDishInOrder(d);
                d.setCount(d.getCount() - 1);
                addedCount++;
            }
        }

        return addedCount > 0;
    }


    public boolean addDishInOrderAdmin(int indexClient, int indexOrder, List<Integer> dishIndexes) {
        Client client = clientsService.findClientByIndex(indexClient); // уже вернёт null, если не найден
        if(client == null) return false;
        Order order = clientsService.findOrderClientByIndex(client, indexOrder);
        if(order == null) return false;
        if (dishService.getListDishes().getAllDishes().isEmpty()) return false;
        int addedCount = 0;
        for (int i : dishIndexes) {
            Dish d = dishService.findDishByIndex(i);
            if (d != null && d.getCount() > 0) {
                order.addDishInOrder(d);
                d.setCount(d.getCount() - 1);
                addedCount++;
            } else {
                return false;
            }
        }

        return addedCount > 0;
    }

    public String getOrderByClientPhone(String phoneNumber) {
        Client client = clientsService.findClientByPhoneNumber(phoneNumber);
        if (client == null) {
            return "Клиент с таким номером не найден.";
        }

        List<Order> clientOrder = clientsService.findOrderByName(client.getName());
        if (clientOrder == null || clientOrder.isEmpty()) {
            return "У клиента пока нет заказов.";
        }

        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Order order : clientOrder) {
            builder.append(i).append(". ").append(order.toString()).append("\n");
            i++;
        }

        return builder.toString();
    }
    public String getOrderByClientIndex(int index) {
        Client client = clientsService.findClientByIndex(index);
        if (client == null) {
            return "Клиент с таким номером не найден.";
        }

        List<Order> clientOrder = clientsService.findOrderByName(client.getName());
        if (clientOrder == null || clientOrder.isEmpty()) {
            return "У клиента пока нет заказов.";
        }

        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Order order : clientOrder) {
            builder.append(i).append(". ").append(order.toString()).append("\n");
            i++;
        }

        return builder.toString();
    }

    public String getCurrentClientInfo(){
        if(currentClient == null) return null;
        return currentClient.toString();
    }
    public String getOrdersCurrentClient() {
        if (currentClient == null) {
            return "Пользователь не авторизован.";
        }
        if (currentClient.getOrders().isEmpty()) {
            return "У вас пока нет заказов.";
        }

        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Order order : currentClient.getOrders()) {
            builder.append(i).append(". ").append(order.toString()).append("\n");
            i++;
        }

        return builder.toString();
    }




    public String getInfoClient(String phoneNumber) {
       Client client = clientsService.findClientByPhoneNumber(phoneNumber);
       if(client == null) return "Клиент не найден";
       return client.toString();
    }

    public boolean changeStatusOrder(int indexClient, int indexOrder, int indexStatus) {
        Client client = clientsService.findClientByIndex(indexClient);
        if(client == null) return false;
        Order order = clientsService.findOrderClientByIndex(client, indexOrder);
        if(order == null) return false;
        return order.setStatus(indexStatus);

        }
    public String getDishFromOrderThisClient(int indexOrder){
        int i = 0;
        if(currentClient.getOrders().isEmpty()) return null;
        if(indexOrder < 0 || indexOrder >= currentClient.getOrders().size()) return null;
        StringBuilder builder = new StringBuilder();
        for(Dish dish : currentClient.getOrders().get(indexOrder).getOrderDishes()){
            builder.append(i).append(". ").append(dish.toString()).append("\n");
            i++;
        }
        return builder.toString();
    }

    private List<Order> getAllOrders() {
        if (clientsService.getListClients().getAllClients().isEmpty()) return null;

        this.allOrders.clear(); // очищаем текущий список всех заказов

        for (Client client : clientsService.getListClients().getAllClients()) {
            if (!client.getOrders().isEmpty()) {
                // добавляем все заказы клиента
                this.allOrders.addAll(client.getOrders());
            }
        }
        return this.allOrders;
    }



    public String getInfoAllOrders() {
        getAllOrders(); // обновляем список

        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Order order : this.getAllOrders()) {
            builder.append(i).append(". ").append(order.toString()).append("\n");
            i++;
        }

        return builder.toString();
    }
    public boolean removeDishFromMenu(int index) {
        return dishService.removeDishByIndexFromListDish(index);
    }

    public boolean saveListDishes() {
            fileHandlerForListDishes.save(dishService.getListDishes());
            return true;

    }
    public boolean saveClientList(){
        fileHandlerForListClients.save(clientsService.getListClients());
        return true;
    }

    public boolean readListDishes() {
        ListDishes read = fileHandlerForListDishes.read();
        if (read == null) return false;
        dishService.setListDishes(read);
        return true;
    }

    public boolean readListClient(){
        ListClients read =  fileHandlerForListClients.read();
        if(read == null) return false;
        clientsService.setListClients(read);
        return true;
    }
    public boolean sortAllOrdersById(){
        getAllOrders();
        if(this.allOrders == null) return false;
        this.allOrders.sort(Comparator.comparing(Order::getId));
        return true;
    }
    public String getAllOrdersSortedByStatus() {
        List<Order> orders = getAllOrders();
        if (orders == null || orders.isEmpty()) return "Нет заказов.";

        orders.sort(Comparator.comparing(Order::getStatus));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < orders.size(); i++) {
            sb.append(i).append(". ").append(orders.get(i)).append("\n");
        }

        return sb.toString();
    }

    public String getAllOrdersSortedByAddDate(){
        List<Order> orders = getAllOrders();
        if (orders == null || orders.isEmpty()) return "Нет заказов.";

        orders.sort(Comparator.comparing(Order::getAddOrder));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < orders.size(); i++) {
            sb.append(i).append(". ").append(orders.get(i)).append("\n");
        }

        return sb.toString();
    }
    public boolean removeDishFromOrderByIndexes(int indexOrder, int indexDish){
        if(currentClient == null) return false;
        if(currentClient.getOrders().isEmpty()) return false;
        if(indexOrder < 0 || indexOrder >= currentClient.getOrders().size()) return false;
        Order order = currentClient.getOrders().get(indexOrder);
        if(!order.getStatus().equals(Status.NEW_ORDER)) return false;
        if(indexDish < 0 || indexDish >= order.getOrderDishes().size()) return false;
        int old = order.getOrderDishes().get(indexDish).getCount();
        order.getOrderDishes().get(indexDish).setCount(old + 1);
        order.setSum(order.getSum() - order.getOrderDishes().get(indexDish).getCost());
        order.getOrderDishes().remove(indexDish);
        return true;

    }
    public boolean removeDishFromOrderByIndexesAdmin(int indexClient, int indexOrder, int indexDish){
       Client client = clientsService.findClientByIndex(indexClient);
       if(client == null) return  false;
       Order order  = clientsService.findOrderClientByIndex(client, indexOrder);
       if(order == null) return false;
       if(!order.getStatus().equals(Status.NEW_ORDER)) return false;
       if(indexDish < 0 || indexDish >= order.getOrderDishes().size()) return false;
       int old = order.getOrderDishes().get(indexDish).getCount();
       order.setSum(order.getSum() - order.getOrderDishes().get(indexDish).getCost());
       order.getOrderDishes().get(indexDish).setCount(old + 1);
       order.getOrderDishes().remove(indexDish);
       return true;

    }

    public boolean removeOrderByIndex( int indexOrder ){
        if(currentClient == null) return false;
        if(indexOrder < 0 || indexOrder >= currentClient.getOrders().size()) return false;
        if(!currentClient.getOrders().get(indexOrder).getStatus().equals(Status.NEW_ORDER)) return false;
        for(Dish dish : currentClient.getOrders().get(indexOrder).getOrderDishes()){
            dish.setCount(dish.getCount() + 1);
        }
        currentClient.getOrders().remove(indexOrder);
        return true;
    }

    public boolean removeOrderByIndexAdmin( int indexClient, int indexOrder ){
        Client client = clientsService.findClientByIndex(indexClient);
        if(client == null) return false;
        if(client.getOrders().isEmpty()) return false;
        if(indexOrder < 0 || indexOrder >= client.getOrders().size()) return false;
        if(!client.getOrders().get(indexOrder).getStatus().equals(Status.NEW_ORDER)) return false;
        for(Dish dish : client.getOrders().get(indexOrder).getOrderDishes()){
            dish.setCount(dish.getCount() + 1);
        }
        client.getOrders().remove(indexOrder);
        return true;
    }
    public boolean changeInfoClient(String phoneNumber, String password, String newName, String newPhone,
                                    String newEmail, String newPassword) {
        if (currentClient.getPhoneNumber().equals(phoneNumber) &&
                currentClient.getPassword().equals(DigestUtils.sha256Hex(password))) {

            currentClient.setName(newName);
            currentClient.setEmail(newEmail);
            currentClient.setPhoneNumber(newPhone);
            currentClient.setPassword(DigestUtils.sha256Hex(newPassword)); // не забудь хешировать
            return true;
        }
        return false;
    }
    public String getInfoAllClients() {
        if (clientsService.getListClients().getSize() == 0) {
            return "Клиенты отсутствуют.";
        }

        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Client client : clientsService.getListClients().getAllClients()) {
            builder.append(i).append(". ").append(client.toString()).append("\n");
            i++;
        }

        return builder.toString();
    }
    public String getAllStatus(){
        StringBuilder builder = new StringBuilder();
        int i =0;
        for(Status status : Status.values()){
            builder.append(i).append(". ").append(status.toString()).append("\n");
            i++;
        }
        return builder.toString();
    }
    public String getAllDishesInOrderByClientIndex(int indexClient, int indexOrder) {
        if (clientsService.getListClients().getAllClients().isEmpty()) return "Нет зарегистрированных клиентов";
        Client client = clientsService.findClientByIndex(indexClient);
        if (client == null) return "Клиент не найден";
        else {
            Order order = clientsService.findOrderClientByIndex(client, indexOrder);
            if (order == null) return "Заказ не найден";
            else {
                StringBuilder builder = new StringBuilder();
                int i = 0;
                for (Dish dish : order.getOrderDishes()) {
                    builder.append(i).append(". ").append(dish).append("\n");
                    i++;
                }
                return builder.toString();

            }

        }
    }
    private  void setAllOrders(List<Order> orders){
        this.allOrders = orders;
    }






}






