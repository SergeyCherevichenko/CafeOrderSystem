package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

import uz.cherevichenko_sergey.cafe_order_system.view.dto_view.ChangeInfoClientDTO;

public class ChangeInfoClientPresenterDTO {
    private String action;
    private ChangeInfoClientDTO changeInfoClientDTO;

    public ChangeInfoClientPresenterDTO(ChangeInfoClientDTO changeInfoClientDTO) {
        this.action = "change_info_client";
        this.changeInfoClientDTO = changeInfoClientDTO;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ChangeInfoClientDTO getChangeInfoClientDTO() {
        return changeInfoClientDTO;
    }

    public void setChangeInfoClientDTO(ChangeInfoClientDTO changeInfoClientDTO) {
        this.changeInfoClientDTO = changeInfoClientDTO;
    }
}
