package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

public class PrintAllOrdersPresenterDTO {
    private String action;

    public PrintAllOrdersPresenterDTO() {
        this.action = "print_all_orders";
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
