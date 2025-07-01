package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class Finish extends Command{
    public Finish(ConsoleUI consoleUI){
        super("Закончить работу приложения ", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().finish();
    }
}
