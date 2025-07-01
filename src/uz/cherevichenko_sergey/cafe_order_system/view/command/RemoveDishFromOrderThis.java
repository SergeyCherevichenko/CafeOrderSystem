package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class RemoveDishFromOrderThis extends Command{
    public RemoveDishFromOrderThis(ConsoleUI consoleUI){
        super("Удалить блюдо из заказа", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().removeDishFromOrderThis();
    }
}
