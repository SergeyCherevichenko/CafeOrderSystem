package uz.cherevichenko_sergey.cafe_order_system.presenter;

import org.junit.jupiter.api.Test;
import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;
import uz.cherevichenko_sergey.cafe_order_system.view.View;
import static org.junit.jupiter.api.Assertions.*;

class PresenterTest {

    @Test
    void clientLogin() {
        View view = new ConsoleUI();
        Presenter presenter  =new Presenter(view);
        assertTrue(presenter.clientLogin("Sergey","cherevichenkosn@gmail.com","Bradley-10121981"));
        assertFalse(presenter.clientLogin("123","123","123"));

    }

    @Test
    void isAdmin() {
        View view = new ConsoleUI();
        Presenter presenter  =new Presenter(view);
        assertTrue(presenter.clientLogin("Sergey","cherevichenkosn@gmail.com","Bradley-10121981"));
        assertTrue(presenter.isAdmin());

    }
}