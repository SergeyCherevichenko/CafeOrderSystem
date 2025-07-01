package uz.cherevichenko_sergey.cafe_order_system.model.dish;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Dish {

    private UUID idDish;
    private String nameDish;
    private String pathImage;
    private int cost;
    private int count;
    private LocalDateTime addToMenu;


    public Dish(String nameDish, String pathImage, int cost, int count) {
        this.idDish = UUID.randomUUID();
        this.nameDish = nameDish;
        this.pathImage = pathImage;
        this.cost = cost;
        this.count = count;
        addToMenu = LocalDateTime.now();

    }


    public Dish() {
    }

    public UUID getIdDish() {
        return idDish;
    }

    public void setIdDish(UUID idDish) {
        this.idDish = idDish;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
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

    public String getPathImage() {
        return pathImage;
    }

    public LocalDateTime getAddToMenu() {
        return addToMenu;
    }



    @Override
    public String toString() {
        return String.format("Блюдо: %-15s | Цена: %d | Кол-во: %d | Добавлено: %s",
                nameDish, cost, count, addToMenu.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
    }
}




