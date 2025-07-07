package uz.cherevichenko_sergey.cafe_order_system.view.dto_view;

import java.util.List;

public class CreateOrderAdminDTO {
    private String phoneNumber;
    private List<Integer> dishIndexes;

    public CreateOrderAdminDTO(String phoneNumber, List<Integer> dishIndexes) {
        this.phoneNumber = phoneNumber;
        this.dishIndexes = dishIndexes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Integer> getDishIndexes() {
        return dishIndexes;
    }

    public void setDishIndexes(List<Integer> dishIndexes) {
        this.dishIndexes = dishIndexes;
    }
}
