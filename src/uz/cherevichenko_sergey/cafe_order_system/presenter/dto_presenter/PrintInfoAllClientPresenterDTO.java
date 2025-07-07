package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

public class PrintInfoAllClientPresenterDTO {
    private String action;

    public PrintInfoAllClientPresenterDTO() {
        this.action = "print_info_all_client";
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
