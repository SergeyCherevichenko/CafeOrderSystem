package uz.cherevichenko_sergey.cafe_order_system;

import uz.cherevichenko_sergey.cafe_order_system.view.View;
import uz.cherevichenko_sergey.cafe_order_system.view.swing_ui.SwingUI;
import uz.cherevichenko_sergey.cafe_order_system.presenter.presenter_socket.PresenterSocket;

import javax.swing.*;

public class MainGui {
    public static void main(String[] args) {
        int attempts = 3;

        for (int i = 1; i <= attempts; i++) {
            try {
                String ip = JOptionPane.showInputDialog(null, "Введите IP сервера:", "Подключение к серверу", JOptionPane.QUESTION_MESSAGE);
                if (ip == null) {
                    exitGracefully();
                    return;
                }
                if (ip.isEmpty()) ip = "127.0.0.1";

                String portStr = JOptionPane.showInputDialog(null, "Введите порт:", "Подключение к серверу", JOptionPane.QUESTION_MESSAGE);
                if (portStr == null) {
                    exitGracefully();
                    return;
                }
                if (portStr.isEmpty()) portStr = "12345";

                int port = Integer.parseInt(portStr);

                // 👉 Проверка доступности сервера
                if (!PresenterSocket.checkServerAvailable(ip, port)) {
                    JOptionPane.showMessageDialog(null,
                            "Попытка " + i + " из " + attempts + " не удалась:\nСервер недоступен по адресу " + ip + ":" + port,
                            "Ошибка подключения",
                            JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                // ✅ Если сервер доступен — запускаем GUI
                View view = new SwingUI(ip, port);
                view.start();
                return;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Попытка " + i + " из " + attempts + " не удалась:\n" + e.getMessage(),
                        "Ошибка подключения",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

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
