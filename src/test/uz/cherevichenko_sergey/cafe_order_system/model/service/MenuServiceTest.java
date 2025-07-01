package uz.cherevichenko_sergey.cafe_order_system.model.service;

import org.junit.jupiter.api.Test;
import uz.cherevichenko_sergey.cafe_order_system.model.client.Client;

import static org.junit.jupiter.api.Assertions.*;

class MenuServiceTest {

    @Test
    void clientLogin() {
        MenuService menuService = new MenuService();
        menuService.addClient("Sergey","12345",
                "sergey@gmail.com","321");
        boolean isClient = menuService.clientLogin("Sergey","sergey@gmail.com","321");
        assertTrue(isClient);
        isClient = menuService.clientLogin("123","123","123");
        assertFalse(isClient);
    }

    @Test
    void isAdmin() {
        MenuService menuService = new MenuService();
        menuService.addClient("Sergey","12345",
                "sergey@gmail.com","321");
        boolean isClient = menuService.clientLogin("Sergey","sergey@gmail.com","321");
        assertTrue(isClient);
        Client client  =  menuService.getCurrentClient();
        assertTrue(client.isAdmin());
    }

    @Test
    void clientRegistryWorksCorrectly() {
        MenuService menuService = new MenuService(); // Создаётся с пустым списком

        // Первый вызов - добавление нового клиента
        boolean firstTry = menuService.clientRegistry("Elena", "055", "elena@example.com", "123");
        assertFalse(firstTry); // Ожидаем true — клиент успешно добавлен

        // Второй вызов - тот же клиент
        boolean secondTry = menuService.clientRegistry("123", "123", "elena@example.com", "123");
        assertTrue(secondTry); // Ожидаем false — клиент уже есть
    }

}