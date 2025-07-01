package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class PrintInfoAllClients extends Command{
    public PrintInfoAllClients(ConsoleUI consoleUI){
        super("Показать информацию о всех клиентах",consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().printInfoAllClients();
    }
}
