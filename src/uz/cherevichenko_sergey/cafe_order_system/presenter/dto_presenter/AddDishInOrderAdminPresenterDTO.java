package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

import uz.cherevichenko_sergey.cafe_order_system.view.dto_view.AddDishInOrderAdminDTO;

public class AddDishInOrderAdminPresenterDTO {
    private String action;
    private AddDishInOrderAdminDTO addDishInOrderAdminDTO;

    public AddDishInOrderAdminPresenterDTO(AddDishInOrderAdminDTO addDishInOrderAdminDTO) {
        this.action = "add_dish_in_order_admin";
        this.addDishInOrderAdminDTO = addDishInOrderAdminDTO;

    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public AddDishInOrderAdminDTO getAddDishInOrderAdminDTO() {
        return addDishInOrderAdminDTO;
    }

    public void setAddDishInOrderAdminDTO(AddDishInOrderAdminDTO addDishInOrderAdminDTO) {
        this.addDishInOrderAdminDTO = addDishInOrderAdminDTO;
    }
}
