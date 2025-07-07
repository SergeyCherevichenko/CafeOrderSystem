package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

public class PrintMenuPresenterDTO {
    private String action;

    public PrintMenuPresenterDTO() {
        this.action = "print_menu";
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
