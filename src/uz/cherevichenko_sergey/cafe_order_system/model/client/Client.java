package uz.cherevichenko_sergey.cafe_order_system.model.client;

import uz.cherevichenko_sergey.cafe_order_system.model.order.Order;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class Client {

    private UUID idClient;
    private String name;
    private String  phoneNumber;
    private List<Order> orders;
    private boolean isAdmin;
    private String password;
    private String email;
    private LocalDateTime addSystem;

    public Client( String name, String  phoneNumber, String email, String password) {
        this.idClient =  UUID.randomUUID();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = DigestUtils.sha256Hex(password);
        this.orders = new ArrayList<>();
        this.isAdmin = false;
        addSystem = LocalDateTime.now();
    }
    public Client(){

    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | Зарегистрирован: %s",
                name,
                phoneNumber,
                email,
                addSystem.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
    }



    public UUID getIdClient() {
        return idClient;
    }
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setIdClient(UUID idClient) {
        this.idClient = idClient;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void addOrder(Order order) {
        this.orders.add(order);
    }
    public List<Order> getOrders() {
        return orders;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    public LocalDateTime getAddSystem() {
        return addSystem;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }



}
