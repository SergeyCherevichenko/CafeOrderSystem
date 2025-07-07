package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

import uz.cherevichenko_sergey.cafe_order_system.view.dto_view.CreateOrderAdminDTO;

public class CreateOrderAdminPresenterDTO {
    private String action;
    private CreateOrderAdminDTO createOrderAdminDTO;

    public CreateOrderAdminPresenterDTO(CreateOrderAdminDTO createOrderAdminDTO) {
        this.action = "create_order_admin";
        this.createOrderAdminDTO = createOrderAdminDTO;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public CreateOrderAdminDTO getCreateOrderAdminDTO() {
        return createOrderAdminDTO;
    }

    public void setCreateOrderAdminDTO(CreateOrderAdminDTO createOrderAdminDTO) {
        this.createOrderAdminDTO = createOrderAdminDTO;
    }
}
