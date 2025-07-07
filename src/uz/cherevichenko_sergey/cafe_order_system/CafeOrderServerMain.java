package uz.cherevichenko_sergey.cafe_order_system;

import uz.cherevichenko_sergey.cafe_order_system.model.cafe_order_server.CafeOrderServer;

public class CafeOrderServerMain {
    public static void main(String[] args) {
        CafeOrderServer server = new CafeOrderServer();
        server.startServer();
    }
}
