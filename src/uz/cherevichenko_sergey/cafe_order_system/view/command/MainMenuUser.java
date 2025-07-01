package uz.cherevichenko_sergey.cafe_order_system.view.command;

import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;

import java.util.ArrayList;
import java.util.List;

public class MainMenuUser {
    protected List<Command> commands;

    public MainMenuUser(ConsoleUI consoleUI){
        commands = new ArrayList<>();
        commands.add(new PrintMenu(consoleUI));
        commands.add(new CreateOrder(consoleUI));
        commands.add(new AddDishInOrder(consoleUI));
        commands.add(new PrintOrdersThisClient(consoleUI));
        commands.add(new RemoveDishFromOrderThis(consoleUI));
        commands.add(new RemoveOrderThis(consoleUI));
        commands.add(new PrintInfoThisClient(consoleUI));
        commands.add(new ChangeInfoClient(consoleUI));
        commands.add(new Finish(consoleUI));

    }
    public String menu(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < commands.size(); i++) {
            stringBuilder.append(i+1)
                    .append(". ")
                    .append(commands.get(i).getDescription())
                    .append("\n");
        }
        return stringBuilder.toString();
    }
    public void execute(int choice){
        Command command = commands.get(choice-1);
        command.execute();
    }
    public int size(){
        return  commands.size();
    }
}
