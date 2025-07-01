package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class ChangeStatusOrder extends Command {
    public ChangeStatusOrder(ConsoleUI consoleUI){
        super("Изменить статус заказа", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().changeStatusOrder();
    }
}
