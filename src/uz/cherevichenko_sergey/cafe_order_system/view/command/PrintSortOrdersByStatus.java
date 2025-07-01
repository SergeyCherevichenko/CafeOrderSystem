package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class PrintSortOrdersByStatus extends Command {
    public PrintSortOrdersByStatus(ConsoleUI consoleUI){
        super("Сортровать  заказы по статусу готовности", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().printSortOrdersByStatus();
    }
}
