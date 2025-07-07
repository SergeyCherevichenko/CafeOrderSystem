package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonCommandListener;

import javax.swing.*;
import java.awt.*;

public class PrintPanelMenuUser extends JPanel {
    private final JTextArea messageArea;
    private final SendButtonCommandListener listener;

    public PrintPanelMenuUser(SendButtonCommandListener listener) {
        this.listener = listener;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Заголовок
        JLabel menuUser = new JLabel("Меню пользователя");
        menuUser.setFont(new Font("Arial", Font.BOLD, 16));
        menuUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(menuUser);
        add(Box.createVerticalStrut(15));

        // Меню ресторана
        addButton("Показать меню", listener::onPrintMenuClicked);
        add(Box.createVerticalStrut(5));

        // Заказ
        addButton("Сделать заказ", listener::onCreateOrderClicked);
        addButton("Добавить блюдо в заказ", listener::onAddDishInOrderClicked);
        addButton("Удалить блюдо из заказа", listener::onRemoveDishFromOrderThisClicked);
        addButton("Удалить весь заказ", listener::onRemoveOrderThisClicked);
        addButton("Показать все заказы клиента", listener::onPrintOrderThisClientClicked);
        add(Box.createVerticalStrut(5));

        // Клиент
        addButton("Посмотреть информацию о себе", listener::onPrintInfoThisClientClicked);
        addButton("Изменить свои данные", listener::onChangeInfoClientClicked);
        add(Box.createVerticalStrut(5));

        // Навигация
        addButton("Вернуться", listener::onBackUserMenu);
        addButton("Выйти", listener::onFinishClicked);
        add(Box.createVerticalStrut(15));

        // Область вывода сообщений
        messageArea = new JTextArea(10, 40);
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результат выполнения"));
        add(scrollPane);
    }

    private void addButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.addActionListener(e -> {
            if (listener != null) action.run();
        });
        add(button);
    }

    public JTextArea getMessageArea() {
        return messageArea;
    }

    public void setMessageArea(String message) {
        this.messageArea.setText(message);
    }

}
