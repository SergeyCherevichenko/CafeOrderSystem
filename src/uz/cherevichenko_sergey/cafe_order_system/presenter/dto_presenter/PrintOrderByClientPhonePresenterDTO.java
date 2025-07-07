package uz.cherevichenko_sergey.cafe_order_system.presenter.dto_presenter;

public class PrintOrderByClientPhonePresenterDTO {
    String action;
    String phoneNumber;

    public PrintOrderByClientPhonePresenterDTO(String phoneNumber) {
        this.action = "print_order_by_client_phone";
        this.phoneNumber = phoneNumber;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
