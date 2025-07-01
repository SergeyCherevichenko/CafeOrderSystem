package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class CreateOrderAdmin extends Command{
    public CreateOrderAdmin(ConsoleUI consoleUI){
        super("Добавить заказ клиенту ",consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().createOrderAdmin();
    }
}
