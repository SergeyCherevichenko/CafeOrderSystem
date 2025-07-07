package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonListener;

import javax.swing.*;
import java.awt.*;

public class PanelChangeInfoClient extends JPanel {
    private final JLabel titleLabel;
    private final JTextField emailField;
    private final JTextField passwordField;
    private final JTextField newNameField;
    private final JTextField newPhoneField;
    private final JTextField newEmailField;
    private final JTextField newPasswordField;
    private final JButton sendButton;
    private final JLabel statusLabel; // 🟡 Новый статус лейбл
    private final SendButtonListener listener;

    public PanelChangeInfoClient(SendButtonListener listener) {
        this.listener = listener;
        setLayout(new BorderLayout());

        // Заголовок
        titleLabel = new JLabel("Изменить информацию о себе");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Форма
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // Поля ввода
        emailField = new JTextField(15);
        passwordField = new JTextField(15);
        newNameField = new JTextField(15);
        newPhoneField = new JTextField(15);
        newEmailField = new JTextField(15);
        newPasswordField = new JTextField(15);
        sendButton = new JButton("Отправить");

        int y = 0;
        addLabeledField(formPanel, gbc, "Ваш номер телефона:", emailField, y++);
        addLabeledField(formPanel, gbc, "Ваш пароль:", passwordField, y++);
        addLabeledField(formPanel, gbc, "Новое имя:", newNameField, y++);
        addLabeledField(formPanel, gbc, "Новый телефон:", newPhoneField, y++);
        addLabeledField(formPanel, gbc, "Новый email:", newEmailField, y++);
        addLabeledField(formPanel, gbc, "Новый пароль:", newPasswordField, y++);

        // Кнопка
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(sendButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        // 🟢 Статус Label
        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setForeground(Color.BLUE);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(statusLabel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> {
            if (listener != null) listener.onSendClicked();
        });
    }

    private void addLabeledField(JPanel panel, GridBagConstraints gbc, String labelText, JTextField textField, int yPos) {
        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(textField, gbc);
    }

    // Геттеры
    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public String getNewName() {
        return newNameField.getText();
    }

    public String getNewPhone() {
        return newPhoneField.getText();
    }

    public String getNewEmail() {
        return newEmailField.getText();
    }

    public String getNewPassword() {
        return newPasswordField.getText();
    }

    public JButton getSend() {
        return sendButton;
    }


    public void setStatusMessage(String message, Color color) {
        statusLabel.setText(message);
        statusLabel.setForeground(color);
    }

    public void clearStatusMessage() {
        statusLabel.setText(" ");
    }
}
