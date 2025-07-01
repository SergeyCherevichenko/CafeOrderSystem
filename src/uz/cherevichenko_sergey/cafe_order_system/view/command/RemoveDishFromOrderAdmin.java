package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

import java.io.Console;

public class RemoveDishFromOrderAdmin extends Command{
    public RemoveDishFromOrderAdmin(ConsoleUI consoleUi){
        super("Удалить блюдо из заказа клиента ", consoleUi);
    }

    @Override
    public void execute() {
        getConsoleUI().removeDishFromOrderAdmin();
    }
}
