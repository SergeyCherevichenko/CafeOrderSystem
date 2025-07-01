package uz.cherevichenko_sergey.cafe_order_system.model.client;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class ListClients {

    private List<Client> clients;

    public ListClients() {
        this.clients = new ArrayList<>();

    }


    public List<Client> getAllClients() {
        return clients;
    }
    public void addClient(Client client){
        clients.add(client);
    }
    @JsonIgnore
    public int getSize(){
        return clients.size();
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }


}
