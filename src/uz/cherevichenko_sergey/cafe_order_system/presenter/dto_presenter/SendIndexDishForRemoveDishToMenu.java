package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

import uz.cherevichenko_sergey.cafe_order_system.view.dto_view.SendIndexDTO;

public class SendIndexDishForRemoveDishToMenu {
    private String action;
    private SendIndexDTO sendIndexDTO;

    public SendIndexDishForRemoveDishToMenu(SendIndexDTO sendIndexDTO) {
        this.action = "remove_dish_from_menu";
        this.sendIndexDTO = sendIndexDTO;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public SendIndexDTO getSendIndexDTO() {
        return sendIndexDTO;
    }

    public void setSendIndexDTO(SendIndexDTO sendIndexDTO) {
        this.sendIndexDTO = sendIndexDTO;
    }
}
