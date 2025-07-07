package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonListener;

import javax.swing.*;
import java.awt.*;

public class PanelAddDishToMenu extends JPanel {
    private final JLabel titleLabel;
    private final JTextField nameDishField;
    private final JTextField pathDishField;
    private final JTextField costDishField;
    private final JTextField countDishField;
    private final JLabel statusAnswerLabel;
    private final JButton sendButton;
    private final JButton backButton;
    private final SendButtonListener listener;

    public PanelAddDishToMenu(SendButtonListener listener) {
        this.listener = listener;
        setLayout(new BorderLayout());

        // Заголовок
        titleLabel = new JLabel("Добавить новое блюдо в меню");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Панель формы
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        nameDishField = new JTextField(20);
        pathDishField = new JTextField(20);
        costDishField = new JTextField(20);
        countDishField = new JTextField(20);
        statusAnswerLabel = new JLabel();

        addField(formPanel, gbc, 0, "Название блюда:", nameDishField);
        addField(formPanel, gbc, 1, "Путь к картинке:", pathDishField);
        addField(formPanel, gbc, 2, "Цена:", costDishField);
        addField(formPanel, gbc, 3, "Количество:", countDishField);

        // Статус
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(statusAnswerLabel, gbc);

        // Кнопки
        sendButton = new JButton("Отправить");
        backButton = new JButton("Вернуться");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(backButton);
        buttonPanel.add(sendButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Слушатели
        sendButton.addActionListener(e -> {
            if (listener != null) listener.onSendClicked();
        });

        backButton.addActionListener(e -> {
            if (listener != null) listener.onBackClicked();
        });
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
    public String getNameDish() {
        return nameDishField.getText();
    }

    public String getPathDish() {
        return pathDishField.getText();
    }

    public String getCostDish() {
        return costDishField.getText();
    }

    public String getCountDish() {
        return countDishField.getText();
    }

    public JButton getSend() {
        return sendButton;
    }

    public JLabel getStatusAnswer() {
        return statusAnswerLabel;
    }

    public void setStatusAnswer(String statusAnswer) {
        this.statusAnswerLabel.setText(statusAnswer);
    }
}
