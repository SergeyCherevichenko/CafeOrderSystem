package uz.cherevichenko_sergey.cafe_order_system.model.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import uz.cherevichenko_sergey.cafe_order_system.model.dish.Dish;
import uz.cherevichenko_sergey.cafe_order_system.model.enums.Status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private UUID id;
    private List<Dish> orderDishes;
    private int sum;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Status status;
    private LocalDateTime addOrder;
    private LocalDateTime changeOrderStatus;


    public Order() {
        this.id = UUID.randomUUID();
        this.orderDishes = new ArrayList<>();
        this.status = Status.NEW_ORDER;
        this.addOrder = LocalDateTime.now();

    }


    public void addDishInOrder(Dish dish) {
        this.orderDishes.add(dish);
        sum += dish.getCost();
    }

    public List<Dish> getOrderDishes() {
        return orderDishes;
    }

    public UUID getId() {
        return id;
    }

    public int getSum() {
        return sum;
    }

    public Status getStatus() {
        return status;
    }



    public LocalDateTime getChangeOrderStatus() {
        return changeOrderStatus;
    }

    public boolean setStatus(Status status) {
        this.status = status;
        this.changeOrderStatus = LocalDateTime.now();
        return true;
    }
    @JsonIgnore
    public boolean setStatus(int index){
        if (index < 0 || index >= Status.values().length) return false;
        this.status = Status.values()[index];
        this.changeOrderStatus = LocalDateTime.now();
        return true;
    }

    public LocalDateTime getAddOrder() {
        return addOrder;
    }
    @Override
    public String toString() {
        return String.format("Заказ #%s | Статус: %s | Сумма: %d | Дата: %s",
                id.toString().substring(0, 8),
                status.name(),
                sum,
                addOrder.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setOrderDishes(List<Dish> orderDishes) {
        this.orderDishes = orderDishes;
    }

}


