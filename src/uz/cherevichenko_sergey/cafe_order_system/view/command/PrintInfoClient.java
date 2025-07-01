package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

public class PrintInfoClient extends Command {
    public PrintInfoClient(ConsoleUI consoleUI){
        super("Показать информацию о клиенте(по номеру телефона)", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().printInfoClient();
    }
}
