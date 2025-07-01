package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class RemoveOrderThis extends Command{

    public RemoveOrderThis(ConsoleUI consoleUI){
        super("Удалить всеь заказ", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().removeOrderThis();
    }
}
