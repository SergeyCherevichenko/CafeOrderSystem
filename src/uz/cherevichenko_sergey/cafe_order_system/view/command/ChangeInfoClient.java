package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class ChangeInfoClient extends Command{
    public ChangeInfoClient(ConsoleUI consoleUI){
        super("Изменить информацию о себе",consoleUI);

    }

    @Override
    public void execute() {
        getConsoleUI().changeInfoClient();
    }
}
