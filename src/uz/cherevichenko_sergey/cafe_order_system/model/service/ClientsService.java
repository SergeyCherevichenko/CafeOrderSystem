package uz.cherevichenko_sergey.cafe_order_system.model.service;

import org.apache.commons.codec.digest.DigestUtils;
import uz.cherevichenko_sergey.cafe_order_system.model.builder.ClientBuilder;
import uz.cherevichenko_sergey.cafe_order_system.model.client.Client;
import uz.cherevichenko_sergey.cafe_order_system.model.client.ListClients;
import uz.cherevichenko_sergey.cafe_order_system.model.order.Order;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class ClientsService {
    private final ClientBuilder clientBuilder;
    private ListClients listClients;

    public ClientsService() {
        this.listClients = new ListClients();
        this.clientBuilder = new ClientBuilder();
    }

    public boolean addClient(String name, String phoneNumber,String email, String password) {
        if (findClientByPhoneNumber(phoneNumber) != null) return false; // уже есть
        listClients.addClient(clientBuilder.build(name, phoneNumber, email, password));
        return true;
    }

    public Client findClientById(UUID id) {
        for (Client client : listClients.getAllClients()) {
            if (client.getIdClient().equals(id)) {
                return client;
            }
        }
        return null;
    }

    public Client findClientByName(String name) {
        for (Client client : listClients.getAllClients()) {
            if (client.getName().equals(name)) {
                return client;
            }
        }
        return null;
    }

    public Client findClientByPhoneNumber(String phoneNumber) {
        for (Client client : listClients.getAllClients()) {
            if (client.getPhoneNumber().equals(phoneNumber)) {
                return client;
            }
        }
        return null;
    }

    public String getClientInfo(Client client) {
        if (client == null) {
            return " Клиент не найден.";
        }
        return client.toString();
    }


    public List<Order> findOrderByName(String name) {
        Client client = findClientByName(name);
        if(client.getOrders().isEmpty()) return null;
        else  return client.getOrders();

    }

    public boolean addOrderToClientByName(String name, Order order) {
        Client client = findClientByName(name);
        if (client != null) {
            client.addOrder(order);
            return true;
        }
        return false;
    }

    public boolean removeClientById(UUID id) {
        Iterator<Client> iterator = listClients.getAllClients().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getIdClient().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }


    public boolean existsByPhone(String phone) {
         Client client = findClientByPhoneNumber(phone);
         return client != null;

    }

    public Client login(String email, String rawPassword) {
        if (listClients.getAllClients().isEmpty()) return null;
        for (Client client : listClients.getAllClients()) {

            if (client.getEmail().equals(email) &&
                    DigestUtils.sha256Hex(rawPassword).equals(client.getPassword())) {

                return client;
            }
        }
        return null;
    }

    public boolean sortClientsByName() {
        if (listClients.getAllClients().isEmpty()) return false;

        listClients.getAllClients().sort(Comparator.comparing(Client::getName));
        return true;
    }

    public boolean sortClientByAddSystem(){
        if(listClients.getAllClients().isEmpty()) return  false;
        listClients.getAllClients().sort(Comparator.comparing(Client::getAddSystem ));
        return  true;
    }

    public void setListClients(ListClients listClients) {
        this.listClients = listClients;
    }

    public ListClients getListClients() {
        return listClients;
    }
    public Client findClientByIndex(int index){
        if(index < 0 || index >= listClients.getAllClients().size()) return null;
        return listClients.getAllClients().get(index);
    }
    public Order findOrderClientByIndex(Client client, int indexOrder){
        if(client == null) return null;
        if(client.getOrders().isEmpty()) return null;
        if(indexOrder < 0 || indexOrder >= client.getOrders().size()) return null;
        return client.getOrders().get(indexOrder);

    }

    public ClientBuilder getClientBuilder() {
        return clientBuilder;
    }
}
