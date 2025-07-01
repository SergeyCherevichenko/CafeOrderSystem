import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;
import uz.cherevichenko_sergey.cafe_order_system.view.View;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        View view = new ConsoleUI();
        view.start();
    }
}

