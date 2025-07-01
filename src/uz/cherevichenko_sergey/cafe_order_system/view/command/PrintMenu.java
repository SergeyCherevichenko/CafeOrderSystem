package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class PrintMenu extends Command {
    public PrintMenu(ConsoleUI consoleUI){
        super("показать список всех блюд(меню)", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().printMenu();
    }
}
