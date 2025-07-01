package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class PrintOrdersThisClient extends Command{
    public PrintOrdersThisClient(ConsoleUI consoleUI){
        super("Ваши заказы",consoleUI);

    }

    @Override
    public void execute() {
        getConsoleUI().printOrdersThisClient();
    }
}
