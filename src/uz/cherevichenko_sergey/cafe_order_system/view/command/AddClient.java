package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class AddClient  extends Command {
    public AddClient(ConsoleUI consoleUI){
        super("Добавить клиента", consoleUI);
    }
    @Override
    public void execute() {
        getConsoleUI().addClient();

    }
}
