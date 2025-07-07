package uz.cherevichenko_sergey.cafe_order_system.model.server_gui;

import uz.cherevichenko_sergey.cafe_order_system.model.cafe_order_server.CafeOrderServer;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerWindow extends JFrame {

    private final JTextArea logArea;
    private final JButton startButton;
    private final JButton stopButton;
    private final ExecutorService executor;
    private CafeOrderServer server;

    public ServerWindow() {
        super("Сервер Кафе");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Логи сервера"));

        startButton = new JButton("Запустить сервер");
        stopButton = new JButton("Остановить сервер");
        stopButton.setEnabled(false); // пока нет реализации остановки

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        executor = Executors.newSingleThreadExecutor();

        startButton.addActionListener(e -> startServer());
        stopButton.addActionListener(e -> stopServer());
    }

    private void startServer() {
        startButton.setEnabled(false);
        stopButton.setEnabled(true);

        new Thread(() -> {
            try {
                server = new CafeOrderServer();
                server.setLogger(this::log);
                server.startServer();
            } catch (Exception e) {
                log("Ошибка запуска сервера: " + e.getMessage());
            }
        }).start();
    }


    private void log(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }

    private void stopServer() {
        System.out.println(">>> Кнопка Остановить нажата");
        log(">>> Кнопка 'Остановить сервер' нажата");

        stopButton.setEnabled(false);
        startButton.setEnabled(true);

        if (server != null) {
            new Thread(() -> {
                server.stopServer();
                log("Сервер остановлен.");
            }).start();
        } else {
            log("Сервер не был запущен.");
        }
    }

}

