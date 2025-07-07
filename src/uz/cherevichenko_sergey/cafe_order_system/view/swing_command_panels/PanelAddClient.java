package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonListener;

import javax.swing.*;
import java.awt.*;

public class PanelAddClient extends JPanel {
    private final JLabel titleLabel;
    private final JTextField nameField;
    private final JTextField phoneNumberField;
    private final JTextField emailField;
    private final JTextField passwordField;
    private final JButton sendButton;
    private final SendButtonListener listener;

    public PanelAddClient(SendButtonListener listener) {
        this.listener = listener;

        setLayout(new BorderLayout());

        // Верхний заголовок
        titleLabel = new JLabel("Добавить клиента");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Центральная форма
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        nameField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JTextField(20);

        addField(formPanel, gbc, 0, "Имя:", nameField);
        addField(formPanel, gbc, 1, "Номер телефона:", phoneNumberField);
        addField(formPanel, gbc, 2, "Email:", emailField);
        addField(formPanel, gbc, 3, "Пароль:", passwordField);

        // Кнопка отправки
        sendButton = new JButton("Отправить");
        sendButton.addActionListener(e -> {
            if (listener != null) listener.onSendClicked();
        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(sendButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int y, String labelText, JTextField field) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
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

    public JButton getSend() {
        return sendButton;
    }
}
