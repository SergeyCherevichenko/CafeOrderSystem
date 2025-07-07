package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

import uz.cherevichenko_sergey.cafe_order_system.view.dto_view.ClientLoginDTO;

public class ClientLoginPresenterDTO {
    private String action;
    private ClientLoginDTO clientLoginDTO;

    public ClientLoginPresenterDTO() {
    }

    public ClientLoginPresenterDTO(ClientLoginDTO clientLoginDTO) {
        this.action = "client_login";
        this.clientLoginDTO = clientLoginDTO;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ClientLoginDTO getClientLoginDTO() {
        return clientLoginDTO;
    }

    public void setClientLoginDTO(ClientLoginDTO clientLoginDTO) {
        this.clientLoginDTO = clientLoginDTO;
    }
}
