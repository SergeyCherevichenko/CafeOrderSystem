package uz.cherevichenko_sergey.cafe_order_system;

import uz.cherevichenko_sergey.cafe_order_system.view.View;
import uz.cherevichenko_sergey.cafe_order_system.view.swing_ui.SwingUI;

import javax.swing.*;

public class MainGui {
    public static void main(String[] args) {
        int attempts = 3;
        for (int i = 1; i <= attempts; i++) {
            try {
                // Показываем окно ввода IP
                String ip = JOptionPane.showInputDialog(null, "Введите IP сервера:", "Подключение к серверу", JOptionPane.QUESTION_MESSAGE);
                if (ip == null) {
                    exitGracefully();
                    return;
                }
                if (ip.isEmpty()) ip = "127.0.0.1"; // по умолчанию

                // Показываем окно ввода порта
                String portStr = JOptionPane.showInputDialog(null, "Введите порт:", "Подключение к серверу", JOptionPane.QUESTION_MESSAGE);
                if (portStr == null) {
                    exitGracefully();
                    return;
                }
                if (portStr.isEmpty()) portStr = "12345"; // по умолчанию

                int port = Integer.parseInt(portStr);

                // Пробуем подключиться
                View view = new SwingUI(ip, port);
                view.start();
                return; // если успешно — выходим из main()
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Попытка " + i + " из " + attempts + " не удалась:\n" + e.getMessage(),
                        "Ошибка подключения",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        // После 3 неудачных попыток
        JOptionPane.showMessageDialog(null,
                "Не удалось подключиться к серверу после " + attempts + " попыток.\nПриложение будет закрыто.",
                "Ошибка",
                JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    private static void exitGracefully() {
        JOptionPane.showMessageDialog(null,
                "Приложение будет закрыто.",
                "Выход",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
