package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonListener;

import javax.swing.*;
import java.awt.*;

public class PanelChangeStatusOrder extends JPanel {
    private final JLabel titleLabel;

    private final JLabel clientIndexLabel;
    private final JTextField clientIndexField;

    private final JLabel orderIndexLabel;
    private final JTextField orderIndexField;

    private final JLabel statusIndexLabel;
    private final JTextField statusIndexField;

    private final JLabel finalStatusLabel;

    private final JTextArea message;
    private final JLabel statusLabel;

    private final JButton sendButton;
    private final JButton backButton;

    private final SendButtonListener listener;

    public PanelChangeStatusOrder(SendButtonListener listener) {
        this.listener = listener;
        setLayout(new BorderLayout());

        // Заголовок
        titleLabel = new JLabel("Изменить статус заказа");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Центральная форма
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        clientIndexLabel = new JLabel("Индекс клиента:");
        clientIndexField = new JTextField(15);
        orderIndexLabel = new JLabel("Индекс заказа:");
        orderIndexField = new JTextField(15);
        statusIndexLabel = new JLabel("Новый индекс статуса:");
        statusIndexField = new JTextField(15);
        finalStatusLabel = new JLabel("Финальный статус будет отображен здесь.");

        int y = 0;
        addField(formPanel, gbc, clientIndexLabel, clientIndexField, y++);
        addField(formPanel, gbc, orderIndexLabel, orderIndexField, y++);
        addField(formPanel, gbc, statusIndexLabel, statusIndexField, y++);

        gbc.gridx = 0;
        gbc.gridy = y++;
        gbc.gridwidth = 2;
        finalStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(finalStatusLabel, gbc);

        // Кнопки
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        sendButton = new JButton("Отправить");
        backButton = new JButton("Назад");
        buttonPanel.add(sendButton);
        buttonPanel.add(backButton);

        gbc.gridy = y++;
        formPanel.add(buttonPanel, gbc);

        // Статус
        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        gbc.gridy = y++;
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
        scrollPane.setPreferredSize(new Dimension(400, 150));
        add(scrollPane, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> {
            if (listener != null) listener.onSendClicked();
        });

        backButton.addActionListener(e -> {
            if (listener != null) listener.onBackClicked();
        });

        showOrderInput(false);
        showStatusInput(false);
        showFinalStatus(false);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, JLabel label, JTextField field, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(field, gbc);
    }

    public String getClientIndex() {
        return clientIndexField.getText();
    }

    public String getOrderIndex() {
        return orderIndexField.getText();
    }

    public String getStatusIndex() {
        return statusIndexField.getText();
    }

    public void setStatus(String text, Color color) {
        this.statusLabel.setText(text);
        this.statusLabel.setForeground(color);
    }

    public void setMessage(String msg) {
        this.message.setText(msg);
    }

    public void setFinalStatus(String msg, Color color) {
        this.finalStatusLabel.setText(msg);
        this.finalStatusLabel.setForeground(color);
    }

    public void showOrderInput(boolean visible) {
        orderIndexLabel.setVisible(visible);
        orderIndexField.setVisible(visible);
    }

    public void showStatusInput(boolean visible) {
        statusIndexLabel.setVisible(visible);
        statusIndexField.setVisible(visible);
    }

    public void showFinalStatus(boolean visible) {
        finalStatusLabel.setVisible(visible);
    }

    public JButton getSend() {
        return sendButton;
    }

    public JButton getBack() {
        return backButton;
    }

    public JTextArea getMessageArea() {
        return message;
    }

    public boolean isOrderIndexEntered() {
        return !orderIndexField.getText().trim().isEmpty();
    }

    public boolean isStatusIndexEntered() {
        return !statusIndexField.getText().trim().isEmpty();
    }

}
