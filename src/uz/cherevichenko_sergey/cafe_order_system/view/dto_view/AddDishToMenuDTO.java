package uz.cherevichenko_sergey.cafe_order_system.view.dto_view;

public class AddDishToMenuDTO {
    private String nameDish;
    private String path;
    private int cost;
    private int count;

    public AddDishToMenuDTO(String nameDish, String path, int cost, int count) {
        this.nameDish = nameDish;
        this.path = path;
        this.cost = cost;
        this.count = count;
    }

    public AddDishToMenuDTO() {
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
