package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonListener;

import javax.swing.*;
import java.awt.*;

public class PanelCreateOrder extends JPanel {
    private final JLabel titleLabel;
    private final JLabel dishIndexesLabel;
    private final JTextField dishIndexesField;
    private final JTextArea messageArea;
    private final JLabel statusLabel;
    private final JButton sendButton;
    private final JButton backButton;
    private final SendButtonListener listener;

    public PanelCreateOrder(SendButtonListener listener) {
        this.listener = listener;
        setLayout(new BorderLayout());

        // Заголовок
        titleLabel = new JLabel("Создание заказа: выберите блюда");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Центральная форма
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        // Метка + поле ввода индексов
        dishIndexesLabel = new JLabel("Выберите индексы блюд (через запятую):");
        gbc.gridx = 0;
        gbc.gridy = y;
        formPanel.add(dishIndexesLabel, gbc);

        dishIndexesField = new JTextField(30);
        gbc.gridx = 1;
        formPanel.add(dishIndexesField, gbc);

        // Кнопки
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        sendButton = new JButton("Отправить");
        backButton = new JButton("Назад");
        buttonPanel.add(backButton);
        buttonPanel.add(sendButton);

        gbc.gridx = 0;
        gbc.gridy = ++y;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        // Статус
        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        gbc.gridy = ++y;
        formPanel.add(statusLabel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Сообщения от сервера (сюда сервер присылает список блюд и результат)
        messageArea = new JTextArea(10, 60);
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane messageScroll = new JScrollPane(messageArea);
        messageScroll.setBorder(BorderFactory.createTitledBorder("Результат выполнения"));
        add(messageScroll, BorderLayout.SOUTH);

        // Слушатели
        sendButton.addActionListener(e -> {
            if (listener != null) listener.onSendClicked();
        });

        backButton.addActionListener(e -> {
            if (listener != null) listener.onBackClicked();
        });
    }

    // Геттеры и сеттеры
    public String getDishIndexes() {
        return dishIndexesField.getText();
    }

    public void setDishIndexes(String indexes) {
        dishIndexesField.setText(indexes);
    }

    public void setMessage(String msg) {
        messageArea.setText(msg);
    }

    public void setStatus(String text, Color color) {
        statusLabel.setText(text);
        statusLabel.setForeground(color);
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JTextArea getMessageArea() {
        return messageArea;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public void clearInputFields() {
        dishIndexesField.setText("");
        messageArea.setText("");
        statusLabel.setText("");
    }
}
