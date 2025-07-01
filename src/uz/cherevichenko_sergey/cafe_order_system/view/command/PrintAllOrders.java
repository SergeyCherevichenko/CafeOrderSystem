package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class PrintAllOrders extends Command {
    public PrintAllOrders(ConsoleUI consoleUI) {
        super("Показать все заказы",consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().printAllOrders();
    }
}
