package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

public class SaveListDishesPresenterDTO {
    private String action;

    public SaveListDishesPresenterDTO() {
        this.action = "save_list_dishes";
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
