package uz.cherevichenko_sergey.cafe_order_system.model.builder;

import uz.cherevichenko_sergey.cafe_order_system.model.dish.Dish;

public class DishBuilder {
    public Dish dishBuild(String name, String pathImage, int cost, int count){
        return new Dish(name, pathImage, cost, count);
    }
}
