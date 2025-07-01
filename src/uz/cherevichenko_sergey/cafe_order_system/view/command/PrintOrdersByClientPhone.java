package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class PrintOrdersByClientPhone extends Command {
    public PrintOrdersByClientPhone(ConsoleUI consoleUI){
        super("Показать список заказов клиента(по номеру телефона)", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().printOrderByClientPhone();
    }
}
