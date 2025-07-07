package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

public class IsAdminPresenterDTO {
    private String action;

    public IsAdminPresenterDTO(){
        this.action = "isAdmin";
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
