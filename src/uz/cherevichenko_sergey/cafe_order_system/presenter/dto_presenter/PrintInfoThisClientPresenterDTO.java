package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

public class PrintInfoThisClientPresenterDTO {
    private String action;

    public PrintInfoThisClientPresenterDTO() {
        this.action = "print_info_this_client";
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
