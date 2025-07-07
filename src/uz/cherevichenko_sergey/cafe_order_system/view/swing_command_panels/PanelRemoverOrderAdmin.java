package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonListener;

import javax.swing.*;
import java.awt.*;

public class PanelRemoverOrderAdmin extends JPanel {
    private final JLabel titleLabel;
    private final JLabel clientIndexLabel;
    private final JButton checkOrdersButton;
    private final JLabel orderIndexLabel;
    private final JButton sendButton;
    private final JButton backButton;
    private final JTextArea message;
    private final JLabel statusLabel;
    private final SendButtonListener listener;
    private final JTextField clientIndexField;
    private final JTextField orderIndexField;

    public PanelRemoverOrderAdmin(SendButtonListener listener) {
        this.listener = listener;
        setLayout(new BorderLayout());

        // Заголовок
        titleLabel = new JLabel("Удалить заказ клиента");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Ввод индекса клиента
        gbc.gridx = 0;
        gbc.gridy = 0;
        clientIndexLabel = new JLabel("Индекс клиента:");
        formPanel.add(clientIndexLabel, gbc);

        gbc.gridx = 1;
        clientIndexField = new JTextField(10);
        formPanel.add(clientIndexField, gbc);

        // Кнопка "Проверить заказы"
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        checkOrdersButton = new JButton("Проверить заказы");
        formPanel.add(checkOrdersButton, gbc);

        // Ввод индекса заказа (скрыто изначально)
        gbc.gridx = 0;
        gbc.gridy = 2;
        orderIndexLabel = new JLabel("Индекс заказа:");
        formPanel.add(orderIndexLabel, gbc);

        gbc.gridx = 1;
        orderIndexField = new JTextField(10);
        formPanel.add(orderIndexField, gbc);

        // Кнопки
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        sendButton = new JButton("Удалить");
        backButton = new JButton("Назад");
        buttonPanel.add(sendButton);
        buttonPanel.add(backButton);
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
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результат"));
        scrollPane.setPreferredSize(new Dimension(400, 150));
        add(scrollPane, BorderLayout.SOUTH);

        // Изначально скрываем ввод индекса заказа
        orderIndexLabel.setVisible(false);
        orderIndexField.setVisible(false);
        sendButton.setVisible(false);

        // Слушатели
        checkOrdersButton.addActionListener(e -> {
            if (listener != null) listener.onSendClicked(); // проверка заказов клиента
        });

        sendButton.addActionListener(e -> {
            if (listener != null) listener.onSendClicked(); // отправка удаления
        });

        backButton.addActionListener(e -> {
            if (listener != null) listener.onBackClicked();
        });
    }

    // Геттеры и сеттеры
    public String getClientIndex() {
        return clientIndexField.getText();
    }

    public String getOrderIndex() {
        return orderIndexField.getText();
    }

    public void showOrderFields(boolean show) {
        orderIndexLabel.setVisible(show);
        orderIndexField.setVisible(show);
        sendButton.setVisible(show);
    }

    public void setMessage(String msg) {
        message.setText(msg);
    }

    public void setStatus(String msg, Color color) {
        statusLabel.setText(msg);
        statusLabel.setForeground(color);
    }

    public JTextArea getMessageArea() {
        return message;
    }

    public boolean isOrderFieldVisible() {
        return orderIndexField.isVisible();
    }

    public void setClientIndexField(String clientIndexField) {
        this.clientIndexField.setText(clientIndexField);
    }

    public void setOrderIndexField(String orderIndexField) {
        this.orderIndexField.setText(orderIndexField);
    }

    public void resetPanel() {
        setClientIndexField("");
        setOrderIndexField("");
        showOrderFields(false);
        setStatus(" ", Color.BLACK);
        setMessage("");
    }
}
