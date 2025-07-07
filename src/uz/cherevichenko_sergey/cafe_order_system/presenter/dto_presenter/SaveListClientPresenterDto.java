package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

public class SaveListClientPresenterDto {
    private String action;

    public SaveListClientPresenterDto() {
        this.action = "save_list_clients";
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
