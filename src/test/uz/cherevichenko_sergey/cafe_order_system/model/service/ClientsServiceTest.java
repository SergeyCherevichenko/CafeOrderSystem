package uz.cherevichenko_sergey.cafe_order_system.model.service;

import org.junit.jupiter.api.Test;
import uz.cherevichenko_sergey.cafe_order_system.model.client.Client;

import static org.junit.jupiter.api.Assertions.*;

class ClientsServiceTest {

    @Test
    void addClient() {
        ClientsService clientsService = new ClientsService();
        boolean result = clientsService.addClient("Sergey", "+998909190458",
                "cherevichenkosn@gmail.com","Bradley-10121981");
        assertTrue(result);
    }


    @Test
    void findClientByName() {
        ClientsService clientsService = new ClientsService();
        clientsService.addClient("Sergey", "+998909190458",
                "cherevichenkosn@gmail.com","Bradley-10121981");
        Client client = clientsService.findClientByName("Sergey");
        assertEquals("Sergey", client.getName());
        assertTrue(client.isAdmin());
        assertNotEquals("Elena", client.getName());
        Client client1 = clientsService.findClientByPhoneNumber("+998909190458");
        assertEquals("Sergey", client1.getName());
    }

    @Test
    void findClientByPhoneNumber() {
        ClientsService clientsService = new ClientsService();
        clientsService.addClient("Elena", "+998333559055",
                "cherevichenkosn@gmail.com","123");
        Client client1 = clientsService.findClientByPhoneNumber("+998333559055");
        assertEquals("Elena", client1.getName());

    }

    @Test
    void getClientInfo() {
        ClientsService clientsService = new ClientsService();
        assertEquals(" Клиент не найден.", clientsService.getClientInfo(null));

    }

}