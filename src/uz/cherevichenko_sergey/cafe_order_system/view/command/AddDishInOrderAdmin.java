package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class AddDishInOrderAdmin extends Command{
    public AddDishInOrderAdmin(ConsoleUI consoleUI){
        super("Добавить блюдо в заказ(админ)", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().addDishInOrderAdmin();
    }
}
