package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonListener;

import javax.swing.*;
import java.awt.*;

public class PanelAddDishInOrderAdmin extends JPanel {
    private final JTextField clientIndexField;
    private final JTextField orderIndexField;
    private final JTextField dishIndexesField;
    private final JButton sendButton;
    private final JButton backButton;
    private final JLabel statusLabel;
    private final JTextArea message;
    private final SendButtonListener listener;
    private final JLabel titleLabel;
    private final JLabel clientIndexLabel;
    private final JLabel orderIndexLabel;
    private final JLabel dishIndexesLabel;

    public PanelAddDishInOrderAdmin(SendButtonListener listener) {
        this.listener = listener;
        setLayout(new BorderLayout());

        // Заголовок
        titleLabel = new JLabel("Добавить блюдо в заказ клиента");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Форма
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Поле: индекс клиента
        gbc.gridx = 0;
        gbc.gridy = 0;
        clientIndexLabel = new JLabel("Индекс клиента:");
        formPanel.add(clientIndexLabel, gbc);

        gbc.gridx = 1;
        clientIndexField = new JTextField(15);
        formPanel.add(clientIndexField, gbc);

        // Поле: индекс заказа
        gbc.gridx = 0;
        gbc.gridy = 1;
        orderIndexLabel = new JLabel("Индекс заказа:");
        formPanel.add(orderIndexLabel, gbc);

        gbc.gridx = 1;
        orderIndexField = new JTextField(15);
        formPanel.add(orderIndexField, gbc);

        // Поле: индексы блюд
        gbc.gridx = 0;
        gbc.gridy = 2;
        dishIndexesLabel = new JLabel("Индексы блюд (через запятую):");
        formPanel.add(dishIndexesLabel, gbc);

        gbc.gridx = 1;
        dishIndexesField = new JTextField(20);
        formPanel.add(dishIndexesField, gbc);

        // Кнопки
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        sendButton = new JButton("Отправить");
        backButton = new JButton("Назад");
        buttonPanel.add(sendButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        // Статус
        gbc.gridy = 4;
        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        formPanel.add(statusLabel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Сообщения
        message = new JTextArea(8, 50);
        message.setEditable(false);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        message.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(message);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результат выполнения"));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        add(scrollPane, BorderLayout.SOUTH);

        // Слушатели
        sendButton.addActionListener(e -> {
            if (listener != null) listener.onSendClicked();
        });

        backButton.addActionListener(e -> {
            if (listener != null) listener.onBackClicked();
        });
        showOrderInput(false);
        showDishInput(false);
    }

    // Геттеры и сеттеры
    public String getClientIndex() {
        return clientIndexField.getText();
    }

    public void setClientIndex(String value) {
        this.clientIndexField.setText(value);
    }

    public String getOrderIndex() {
        return orderIndexField.getText();
    }

    public void setOrderIndex(String value) {
        this.orderIndexField.setText(value);
    }

    public String getDishIndexes() {
        return dishIndexesField.getText();
    }

    public void setDishIndexes(String value) {
        this.dishIndexesField.setText(value);
    }

    public void setStatus(String text, Color color) {
        this.statusLabel.setText(text);
        this.statusLabel.setForeground(color);
    }

    public JTextArea getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message.setText(msg);
    }

    public JButton getSend() {
        return sendButton;
    }

    public JButton getBack() {
        return backButton;
    }

    public boolean isOrderInputVisible() {
        return orderIndexLabel.isVisible() && orderIndexField.isVisible();
    }

    public boolean isDishInputVisible() {
        return dishIndexesLabel.isVisible() && dishIndexesField.isVisible();
    }

    public void showOrderInput(boolean show) {
        orderIndexLabel.setVisible(show);
        orderIndexField.setVisible(show);
    }

    public void showDishInput(boolean show) {
        dishIndexesLabel.setVisible(show);
        dishIndexesField.setVisible(show);
    }
}
