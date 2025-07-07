package uz.cherevichenko_sergey.cafe_order_system;


import uz.cherevichenko_sergey.cafe_order_system.model.server_gui.ServerWindow;

public class CafeOrderServerGuiMain {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ServerWindow window = new ServerWindow();
            window.setVisible(true);
        });
    }
}

