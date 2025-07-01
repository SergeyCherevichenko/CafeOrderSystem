package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class DeleteDishFromMenu extends  Command{
    public DeleteDishFromMenu(ConsoleUI consoleUI){
        super("удалить блюдо из меню",consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().deleteDishFromMenu();
    }
}
