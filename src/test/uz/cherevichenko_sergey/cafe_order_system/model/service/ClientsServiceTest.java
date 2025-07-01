package uz.cherevichenko_sergey.cafe_order_system.model.service;

import org.junit.jupiter.api.Test;
import uz.cherevichenko_sergey.cafe_order_system.model.client.Client;

import static org.junit.jupiter.api.Assertions.*;

class ClientsServiceTest {

    @Test
    void addClient() {
        ClientsService clientsService = new ClientsService();
        boolean result = clientsService.addClient("Sergey", "12345",
                "sergey@gmail.com","321");
        assertTrue(result);
    }


    @Test
    void findClientByName() {
        ClientsService clientsService = new ClientsService();
        clientsService.addClient("Sergey", "12345",
                "sergey@gmail.com","321");
        Client client = clientsService.findClientByName("Sergey");
        assertEquals("Sergey", client.getName());
        assertTrue(client.isAdmin());
        assertNotEquals("Elena", client.getName());
        Client client1 = clientsService.findClientByPhoneNumber("12345");
        assertEquals("Sergey", client1.getName());
    }

    @Test
    void findClientByPhoneNumber() {
        ClientsService clientsService = new ClientsService();
        clientsService.addClient("Elena", "055",
                "elean@gmail.com","123");
        Client client1 = clientsService.findClientByPhoneNumber("055");
        assertEquals("Elena", client1.getName());

    }

    @Test
    void getClientInfo() {
        ClientsService clientsService = new ClientsService();
        assertEquals(" Клиент не найден.", clientsService.getClientInfo(null));

    }

}