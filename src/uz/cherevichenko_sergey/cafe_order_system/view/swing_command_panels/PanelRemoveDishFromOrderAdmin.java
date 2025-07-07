package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonListener;

import javax.swing.*;
import java.awt.*;

public class PanelRemoveDishFromOrderAdmin extends JPanel {
    private final JLabel titleLabel;
    private final JLabel clientIndexLabel;
    private final JButton checkOrdersButton;
    private final JLabel orderIndexLabel;
    private final JButton checkDishesButton;
    private final JLabel dishIndexLabel;
    private final JButton removeDishButton;
    private final JButton backButton;
    private final JTextArea message;
    private final JLabel statusLabel;
    private final SendButtonListener listener;
    private final JTextField clientIndexField;
    private final JTextField orderIndexField;
    private final JTextField dishIndexField;

    public PanelRemoveDishFromOrderAdmin(SendButtonListener listener) {
        this.listener = listener;
        setLayout(new BorderLayout());

        titleLabel = new JLabel("Удалить блюдо из заказа клиента");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Индекс клиента
        gbc.gridx = 0;
        gbc.gridy = 0;
        clientIndexLabel = new JLabel("Индекс клиента:");
        formPanel.add(clientIndexLabel, gbc);

        gbc.gridx = 1;
        clientIndexField = new JTextField(10);
        formPanel.add(clientIndexField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        checkOrdersButton = new JButton("Проверить заказы");
        formPanel.add(checkOrdersButton, gbc);

        // Индекс заказа (второй шаг)
        gbc.gridy = 2;
        orderIndexLabel = new JLabel("Индекс заказа:");
        formPanel.add(orderIndexLabel, gbc);

        gbc.gridx = 1;
        orderIndexField = new JTextField(10);
        formPanel.add(orderIndexField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        checkDishesButton = new JButton("Показать блюда заказа");
        formPanel.add(checkDishesButton, gbc);

        // Индекс блюда (третий шаг)
        gbc.gridy = 4;
        gbc.gridx = 0;
        dishIndexLabel = new JLabel("Индекс блюда:");
        formPanel.add(dishIndexLabel, gbc);

        gbc.gridx = 1;
        dishIndexField = new JTextField(10);
        formPanel.add(dishIndexField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        removeDishButton = new JButton("Удалить блюдо");
        formPanel.add(removeDishButton, gbc);

        // Статус
        gbc.gridy = 6;
        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        formPanel.add(statusLabel, gbc);

        // Назад
        gbc.gridy = 7;
        backButton = new JButton("Назад");
        formPanel.add(backButton, gbc);

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

        // Изначально скрыты шаги 2 и 3
        showOrderInput(false);
        showDishInput(false);

        // Слушатели
        checkOrdersButton.addActionListener(e -> {
            if (listener != null) listener.onSendClicked();
        });

        checkDishesButton.addActionListener(e -> {
            if (listener != null) listener.onSendClicked();
        });

        removeDishButton.addActionListener(e -> {
            if (listener != null) listener.onSendClicked();
        });

        backButton.addActionListener(e -> {
            if (listener != null) listener.onBackClicked();
        });
    }

    public String getClientIndex() {
        return clientIndexField.getText();
    }

    public String getOrderIndex() {
        return orderIndexField.getText();
    }

    public String getDishIndex() {
        return dishIndexField.getText();
    }

    public void showOrderInput(boolean show) {
        orderIndexLabel.setVisible(show);
        orderIndexField.setVisible(show);
        checkDishesButton.setVisible(show);
    }

    public void showDishInput(boolean show) {
        dishIndexLabel.setVisible(show);
        dishIndexField.setVisible(show);
        removeDishButton.setVisible(show);
    }

    public void setMessage(String msg) {
        message.setText(msg);
    }

    public void setStatus(String msg, Color color) {
        statusLabel.setText(msg);
        statusLabel.setForeground(color);
    }

    public void clearFields() {
        clientIndexField.setText("");
        orderIndexField.setText("");
        dishIndexField.setText("");
    }

    public boolean isOrderInputVisible() {
        return orderIndexField.isVisible();
    }

    public boolean isDishInputVisible() {
        return dishIndexField.isVisible();
    }

    public void setClientIndexField(String clientIndexField) {
        this.clientIndexField.setText(clientIndexField);
    }

    public void setOrderIndexField(String orderIndexField) {
        this.orderIndexField.setText(orderIndexField);
    }

    public void setDishIndexField(String dishIndexField) {
        this.dishIndexField.setText(dishIndexField);
    }
}
