package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

import uz.cherevichenko_sergey.cafe_order_system.view.dto_view.AddDishToMenuDTO;

public class AddDishToMenuPresenterDto {
    private String action;
    private AddDishToMenuDTO addDishToMenuDTO;

    public AddDishToMenuPresenterDto(AddDishToMenuDTO addDishToMenuDTO) {
        this.action = "add_dish_to_menu";
        this.addDishToMenuDTO = addDishToMenuDTO;

    }

    public AddDishToMenuPresenterDto() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public AddDishToMenuDTO getAddDishToMenuDTO() {
        return addDishToMenuDTO;
    }

    public void setAddDishToMenuDTO(AddDishToMenuDTO addDishToMenuDTO) {
        this.addDishToMenuDTO = addDishToMenuDTO;
    }
}
