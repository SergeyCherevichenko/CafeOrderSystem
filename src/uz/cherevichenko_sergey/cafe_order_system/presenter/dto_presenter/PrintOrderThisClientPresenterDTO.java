package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

public class PrintOrderThisClientPresenterDTO {
    private String action;

    public PrintOrderThisClientPresenterDTO() {
        this.action = "print_order_this_client";
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
