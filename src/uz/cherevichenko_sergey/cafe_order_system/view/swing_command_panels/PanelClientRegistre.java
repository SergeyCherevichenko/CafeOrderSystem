package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonListener;

import javax.swing.*;
import java.awt.*;

public class PanelClientRegistre extends JPanel {
    private final JLabel titleLabel;
    private final JLabel statusAnswer;
    private final JTextField nameField;
    private final JTextField phoneNumberField;
    private final JTextField emailField;
    private final JTextField passwordField;
    private final JButton sendButton;
    private final SendButtonListener listener;

    public PanelClientRegistre(SendButtonListener listener) {
        this.listener = listener;

        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Заголовок
        titleLabel = new JLabel("Регистрация клиента");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Поля формы
        nameField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JTextField(20);

        addField(formPanel, gbc, 0, "Имя:", nameField);
        addField(formPanel, gbc, 1, "Телефон:", phoneNumberField);
        addField(formPanel, gbc, 2, "Email:", emailField);
        addField(formPanel, gbc, 3, "Пароль:", passwordField);

        // Статус и кнопка
        statusAnswer = new JLabel(" ");
        statusAnswer.setForeground(Color.GRAY);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(statusAnswer, gbc);

        sendButton = new JButton("Зарегистрироваться");
        sendButton.addActionListener(e -> {
            if (listener != null) listener.onSendClicked();
        });

        gbc.gridy = 5;
        formPanel.add(sendButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int y, String labelText, JTextField field) {
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    // Геттеры
    public String getName() {
        return nameField.getText();
    }

    public String getPhoneNumber() {
        return phoneNumberField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public JLabel getStatusAnswer() {
        return statusAnswer;
    }

    public void setStatusAnswer(String answer) {
        this.statusAnswer.setText(answer);
    }

    public JButton getSend() {
        return sendButton;
    }
}
