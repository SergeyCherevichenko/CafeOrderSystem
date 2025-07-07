package uz.cherevichenko_sergey.cafe_order_system.view.dto_view;

import java.util.List;

public class AddDishInOrderAdminDTO {
    private int indexClient;
    private int orderIndex;
    private List<Integer> dishIndexes;

    public AddDishInOrderAdminDTO(int indexClient, int orderIndex, List<Integer> dishIndexes) {
        this.indexClient = indexClient;
        this.orderIndex = orderIndex;
        this.dishIndexes = dishIndexes;
    }

    public int getIndexClient() {
        return indexClient;
    }

    public void setIndexClient(int indexClient) {
        this.indexClient = indexClient;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public List<Integer> getDishIndexes() {
        return dishIndexes;
    }

    public void setDishIndexes(List<Integer> dishIndexes) {
        this.dishIndexes = dishIndexes;
    }
}
