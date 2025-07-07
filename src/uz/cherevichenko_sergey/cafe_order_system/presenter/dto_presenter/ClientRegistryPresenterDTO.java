package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

import uz.cherevichenko_sergey.cafe_order_system.view.dto_view.ClientRegistryDTO;

public class ClientRegistryPresenterDTO {
    private String action;
    private ClientRegistryDTO clientRegistryDTO;

    public ClientRegistryPresenterDTO() {
    }

    public ClientRegistryPresenterDTO(ClientRegistryDTO clientRegistryDTO) {
        action = "client_registry";
        this.clientRegistryDTO = clientRegistryDTO;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ClientRegistryDTO getClientRegistryDTO() {
        return clientRegistryDTO;
    }

    public void setClientRegistryDTO(ClientRegistryDTO clientRegistryDTO) {
        this.clientRegistryDTO = clientRegistryDTO;
    }
}
