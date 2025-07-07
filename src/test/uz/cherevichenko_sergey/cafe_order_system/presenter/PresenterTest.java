package uz.cherevichenko_sergey.cafe_order_system.presenter;

import org.junit.jupiter.api.Test;
import uz.cherevichenko_sergey.cafe_order_system.view.ConsoleUI;
import uz.cherevichenko_sergey.cafe_order_system.view.View;
import static org.junit.jupiter.api.Assertions.*;

class PresenterTest {

    @Test
    void clientLogin() {
        View view = new ConsoleUI();
        Presenter presenter = new Presenter(view);

        // Регистрация клиента перед логином
        assertTrue(presenter.clientRegistry("Sergey", "050", "sergey@gmail.com", "321"));

        // Теперь логин должен сработать
        assertTrue(presenter.clientLogin("Sergey","sergey@gmail.com","321"));
        assertFalse(presenter.clientLogin("123","123","123"));
    }


    @Test
    void isAdmin() {
        View view = new ConsoleUI();
        Presenter presenter = new Presenter(view);

        presenter.clientRegistry("Sergey", "050", "sergey@gmail.com", "321");
        presenter.clientLogin("Sergey", "sergey@gmail.com", "321");

        assertTrue(presenter.isAdmin());
    }

}