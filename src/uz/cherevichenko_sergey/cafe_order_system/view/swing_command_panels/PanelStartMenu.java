package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.PanelStartMenuListeners;

import javax.swing.*;
import java.awt.*;

public class PanelStartMenu extends JPanel {

    private final JButton registry;
    private final JButton authorisation;
    private final JButton exit;
    private final JLabel serverStatusLabel;
    private final PanelStartMenuListeners listener;

    public PanelStartMenu(PanelStartMenuListeners listener) {
        this.listener = listener;

        setLayout(new GridBagLayout()); // Центрируем элементы по середине
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Отступы между компонентами
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Заголовок
        JLabel title = new JLabel("Добро пожаловать в CafeOrderSystem", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridy = 0;
        add(title, gbc);

        // Кнопка Регистрация
        registry = new JButton("Регистрация");
        styleButton(registry);
        gbc.gridy = 1;
        add(registry, gbc);

        // Кнопка Авторизация
        authorisation = new JButton("Авторизация");
        styleButton(authorisation);
        gbc.gridy = 2;
        add(authorisation, gbc);

        // Кнопка Выход
        exit = new JButton("Выход");
        styleButton(exit);
        gbc.gridy = 3;
        add(exit, gbc);

        // Индикатор статуса сервера
        serverStatusLabel = new JLabel("Проверка соединения с сервером...", SwingConstants.CENTER);
        serverStatusLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        serverStatusLabel.setForeground(Color.GRAY);
        gbc.gridy = 4;
        add(serverStatusLabel, gbc);

        // Обработчики
        registry.addActionListener(e -> {
            if (listener != null) listener.onRegisterClicked();
        });
        authorisation.addActionListener(e -> {
            if (listener != null) listener.onAuthorisationClicked();
        });
        exit.addActionListener(e -> {
            if (listener != null) listener.onExitClicked();
        });
    }

    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(200, 40));
        button.setFont(new Font("Arial", Font.PLAIN, 16));
    }

    public JButton getRegistry() {
        return registry;
    }

    public JButton getAuthorisation() {
        return authorisation;
    }

    public JButton getExit() {
        return exit;
    }

    public void updateServerStatus(boolean isConnected) {
        if (isConnected) {
            serverStatusLabel.setText("✅ Сервер доступен");
            serverStatusLabel.setForeground(new Color(0, 128, 0)); // зелёный
        } else {
            serverStatusLabel.setText("❌ Сервер недоступен");
            serverStatusLabel.setForeground(Color.RED);
        }
    }


}
