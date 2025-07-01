package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class AddDishInOrder extends  Command{
    public  AddDishInOrder(ConsoleUI consoleUI){
        super("Добавить блюдо в заказ", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().addDishInOrder();
    }
}
