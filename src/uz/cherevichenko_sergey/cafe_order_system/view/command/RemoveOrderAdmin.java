package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class RemoveOrderAdmin extends Command{
    public RemoveOrderAdmin(ConsoleUI consoleUI){
        super("удалить заказ клиента", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().removeOrderAdmin();
    }
}
