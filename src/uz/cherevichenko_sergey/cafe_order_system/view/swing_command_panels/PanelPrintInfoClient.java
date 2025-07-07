package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonListener;

import javax.swing.*;
import java.awt.*;

public class PanelPrintInfoClient extends JPanel {
    private final JLabel infoClientLabel;
    private final JTextField phoneNumberField;
    private final JTextArea answerArea;
    private final JLabel statusLabel;
    private final JButton sendButton;
    private final JButton backButton;
    private final SendButtonListener listener;

    public PanelPrintInfoClient(SendButtonListener listener) {
        this.listener = listener;
        setLayout(new BorderLayout());

        // Заголовок
        infoClientLabel = new JLabel("Посмотреть информацию о клиенте");
        infoClientLabel.setFont(new Font("Arial", Font.BOLD, 18));
        infoClientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(infoClientLabel, BorderLayout.NORTH);

        // Центральная панель с формой
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        // Поле ввода номера телефона
        gbc.gridx = 0;
        gbc.gridy = y;
        formPanel.add(new JLabel("Номер телефона клиента:"), gbc);

        gbc.gridx = 1;
        phoneNumberField = new JTextField(25);
        phoneNumberField.setPreferredSize(new Dimension(250, 25));
        formPanel.add(phoneNumberField, gbc);

        // Статус
        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        statusLabel.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0;
        gbc.gridy = ++y;
        gbc.gridwidth = 2;
        formPanel.add(statusLabel, gbc);

        // Кнопки
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        sendButton = new JButton("Отправить");
        backButton = new JButton("Вернуться");
        buttonPanel.add(backButton);
        buttonPanel.add(sendButton);

        gbc.gridy = ++y;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Зона вывода информации
        answerArea = new JTextArea(12, 60);
        answerArea.setEditable(false);
        answerArea.setLineWrap(true);
        answerArea.setWrapStyleWord(true);
        answerArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(answerArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Информация о клиенте"));
        scrollPane.setPreferredSize(new Dimension(800, 200));

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
    public String getPhoneNumberClient() {
        return phoneNumberField.getText();
    }

    public void setAnswer(String answer) {
        this.answerArea.setText(answer);
    }

    public JTextArea getAnswerArea() {
        return answerArea;
    }

    public void setInfoClient(String text) {
        this.infoClientLabel.setText(text);
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public void setStatus(String statusText, Color color) {
        this.statusLabel.setText(statusText);
        this.statusLabel.setForeground(color);
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
