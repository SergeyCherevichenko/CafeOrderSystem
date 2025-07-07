package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonCommandAdminListener;

import javax.swing.*;
import java.awt.*;

public class PrintPanelMenuAdmin extends JPanel {
    private final JTextArea message;
    private final SendButtonCommandAdminListener listener;

    public PrintPanelMenuAdmin(SendButtonCommandAdminListener listener) {
        this.listener = listener;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setAlignmentX(Component.LEFT_ALIGNMENT);

        // Заголовок
        JLabel menuAdmin = new JLabel("Меню администратора");
        menuAdmin.setFont(new Font("Arial", Font.BOLD, 18));
        menuAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(menuAdmin);
        add(Box.createVerticalStrut(15));

        // Кнопки меню
        addButton("Меню ресторана", listener::onPrintMenuClicked);
        addButton("Добавить блюдо в меню", listener::onAddDishToMenuClicked);
        addButton("Удалить блюдо из меню", listener::onDeleteDishFromMenuClicked);

        add(Box.createVerticalStrut(10));

        // Клиенты
        addButton("Показать информацию о всех клиентах", listener::onPrintInfoAllClientsClicked);
        addButton("Посмотреть информацию о себе", listener::onPrintInfoThisClientClicked);

        add(Box.createVerticalStrut(10));

        // Заказы
        addButton("Сделать заказ для клиента", listener::onCreateOrderAdminClicked);
        addButton("Добавить блюда в заказ", listener::onAddDishInOrderAdminClicked);
        addButton("Удалить заказ", listener::onRemoveOrderAdminClicked);
        addButton("Удалить блюдо из заказа клиента", listener::onRemoveDishFromOrderAdminClicked);
        addButton("Показать все заказы", listener::onPrintAllOrdersClicked);
        addButton("Показать заказы клиента по телефону", listener::onPrintOrdersByClientPhoneClicked);
        addButton("Изменить статус заказа", listener::onChangeStatusOrderClicked);
        addButton("Показать заказы отсортированные по дате/времени", listener::onPrintSortOrdersByDataTimeClicked);
        addButton("Показать заказы отсортированные по статусу", listener::onPrintSortOrdersByStatusClicked);

        add(Box.createVerticalStrut(10));

        // Выход
        addButton("Выйти", listener::onFinishClicked);

        add(Box.createVerticalStrut(15));

        // Вывод сообщений
        message = new JTextArea(8, 50);
        message.setEditable(false);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        message.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(message);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результат выполнения"));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));

        add(scrollPane);
    }

    private void addButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.addActionListener(e -> {
            if (listener != null) action.run();
        });
        add(button);
        add(Box.createVerticalStrut(5));
    }

    public JTextArea getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        message.setText(msg);
    }
}
