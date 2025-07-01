package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class PrintInfoThisClient extends Command{

    public PrintInfoThisClient(ConsoleUI concoleUI){
        super("Посмотреть информацию о себе",concoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().printInfoThisClient();
    }
}
