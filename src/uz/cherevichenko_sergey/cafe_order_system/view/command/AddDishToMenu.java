package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class AddDishToMenu extends Command {

    public AddDishToMenu(ConsoleUI consoleUI){
        super("Добавить блюдо в меню", consoleUI);
    }
    @Override
    public void execute() {
        getConsoleUI().addDishToMenu();
       
    }
}
