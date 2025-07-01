package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class PrintSortOrdersByDataTime extends Command {
    public PrintSortOrdersByDataTime(ConsoleUI consoleUI){
        super("Сортировать заказы по дате и времени", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().printSortOrdersByDataTime();
    }
}
