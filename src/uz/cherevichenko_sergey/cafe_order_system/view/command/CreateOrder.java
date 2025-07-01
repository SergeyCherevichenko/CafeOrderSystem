package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class CreateOrder extends Command {
    public CreateOrder(ConsoleUI consoleUI){
        super("Создать заказ",consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().createOrder();
    }
}
