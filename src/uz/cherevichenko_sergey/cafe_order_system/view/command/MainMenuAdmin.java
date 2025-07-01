package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

import java.util.ArrayList;

public class MainMenuAdmin extends MainMenuUser{
    public MainMenuAdmin(ConsoleUI consoleUI){
        super(consoleUI);
        commands = new ArrayList<>();
        // Меню блюд
        commands.add(new PrintMenu(consoleUI));
        commands.add(new AddDishToMenu(consoleUI));
        commands.add(new DeleteDishFromMenu(consoleUI));

// Клиенты
        commands.add(new PrintInfoAllClients(consoleUI));
        commands.add(new PrintInfoThisClient(consoleUI));

// Заказы
        commands.add(new CreateOrderAdmin(consoleUI));
        commands.add(new AddDishInOrderAdmin(consoleUI));
        commands.add(new RemoveOrderAdmin(consoleUI));
        commands.add(new RemoveDishFromOrderAdmin(consoleUI));

// Просмотр заказов
        commands.add(new PrintAllOrders(consoleUI));
        commands.add(new PrintOrdersByClientPhone(consoleUI));

// Управление заказами
        commands.add(new ChangeStatusOrder(consoleUI));
        commands.add(new PrintSortOrdersByDataTime(consoleUI));
        commands.add(new PrintSortOrdersByStatus(consoleUI));
        commands.add(new Finish(consoleUI));


    }
}
