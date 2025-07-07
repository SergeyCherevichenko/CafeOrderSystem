package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonListener;

import javax.swing.*;
import java.awt.*;

public class PanelCreateOrderAdmin extends JPanel {
    private final JTextField indexField;
    private final JTextField dishIndexesField;
    private final JButton sendButton;
    private final JButton backButton;
    private final JLabel statusLabel;
    private final SendButtonListener listener;
    private final JTextArea message;
    private final JLabel titleLabel;
    private final JLabel indexLabel;
    private final JLabel dishIndexesLabel;

    public PanelCreateOrderAdmin(SendButtonListener listener) {
        this.listener = listener;
        setLayout(new BorderLayout());

        // Заголовок
        titleLabel = new JLabel("Добавить заказ клиенту по номеру телефона");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Форма
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Поле: номер заказа
        gbc.gridx = 0;
        gbc.gridy = 0;
        indexLabel = new JLabel("Номер телефона клиента:");
        formPanel.add(indexLabel, gbc);

        gbc.gridx = 1;
        indexField = new JTextField(15);
        formPanel.add(indexField, gbc);

        // Поле: индексы блюд
        gbc.gridx = 0;
        gbc.gridy = 1;
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
    }

    // Геттеры и сеттеры
    public JButton getSend() {
        return sendButton;
    }

    public String getIndex() {
        return indexField.getText();
    }

    public void setIndex(String index) {
        this.indexField.setText(index);
    }

    public String getDishIndexes() {
        return dishIndexesField.getText();
    }

    public void setDishIndexes(String indexes) {
        this.dishIndexesField.setText(indexes);
    }

    public JLabel getAction() {
        return titleLabel;
    }

    public void setAction(String action) {
        this.titleLabel.setText(action);
    }

    public JTextArea getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message.setText(msg);
    }

    public void setIndexLabelText(String labelText) {
        this.indexLabel.setText(labelText);
    }

    public void setDishIndexesLabelText(String labelText) {
        this.dishIndexesLabel.setText(labelText);
    }

    public void setStatus(String text, Color color) {
        this.statusLabel.setText(text);
        this.statusLabel.setForeground(color);
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }
}
