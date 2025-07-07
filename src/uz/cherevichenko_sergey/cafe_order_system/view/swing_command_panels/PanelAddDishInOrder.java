package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonListener;

import javax.swing.*;
import java.awt.*;

public class PanelAddDishInOrder extends JPanel {
    private final JTextField orderIndexField;
    private final JTextField dishIndexesField;
    private final JButton sendButton;
    private final JButton backButton;
    private final JLabel statusLabel;
    private final JTextArea messageArea;
    private final SendButtonListener listener;
    private final JLabel titleLabel;
    private final JLabel orderIndexLabel;
    private final JLabel dishIndexesLabel;

    public PanelAddDishInOrder(SendButtonListener listener) {
        this.listener = listener;
        setLayout(new BorderLayout());

        // Заголовок
        titleLabel = new JLabel("Добавить блюда в заказ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Форма
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Поле: индекс заказа
        gbc.gridx = 0;
        gbc.gridy = 0;
        orderIndexLabel = new JLabel("Индекс заказа:");
        formPanel.add(orderIndexLabel, gbc);

        gbc.gridx = 1;
        orderIndexField = new JTextField(15);
        formPanel.add(orderIndexField, gbc);

        // Поле: индексы блюд
        gbc.gridx = 0;
        gbc.gridy = 1;
        dishIndexesLabel = new JLabel("Индексы блюд (через запятую):");
        formPanel.add(dishIndexesLabel, gbc);

        gbc.gridx = 1;
        dishIndexesField = new JTextField(30);
        formPanel.add(dishIndexesField, gbc);

        // Кнопки
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        sendButton = new JButton("Отправить");
        backButton = new JButton("Назад");
        buttonPanel.add(backButton);
        buttonPanel.add(sendButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        // Статус
        gbc.gridy = 3;
        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        formPanel.add(statusLabel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Сообщения
        messageArea = new JTextArea(8, 50);
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(messageArea);
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

        showDishInput(false); // по умолчанию скрыт
    }

    // Геттеры и сеттеры
    public String getOrderIndex() {
        return orderIndexField.getText();
    }

    public void setOrderIndex(String value) {
        orderIndexField.setText(value);
    }

    public String getDishIndexes() {
        return dishIndexesField.getText();
    }

    public void setDishIndexes(String value) {
        dishIndexesField.setText(value);
    }

    public void setStatus(String text, Color color) {
        statusLabel.setText(text);
        statusLabel.setForeground(color);
    }

    public void setMessage(String msg) {
        messageArea.setText(msg);
    }

    public JTextArea getMessageArea() {
        return messageArea;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public boolean isDishInputVisible() {
        return dishIndexesLabel.isVisible() && dishIndexesField.isVisible();
    }

    public void showDishInput(boolean visible) {
        dishIndexesLabel.setVisible(visible);
        dishIndexesField.setVisible(visible);
    }

    public void clearFields() {
        orderIndexField.setText("");
        dishIndexesField.setText("");
        messageArea.setText("");
        statusLabel.setText("");
        showDishInput(false);
    }
}
