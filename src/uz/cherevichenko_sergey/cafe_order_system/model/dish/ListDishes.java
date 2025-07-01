package uz.cherevichenko_sergey.cafe_order_system.model.dish;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class ListDishes {

    private List<Dish> dishes;


    public ListDishes(){
        this.dishes = new ArrayList<>();

    }
    public void addDish(Dish dish){
        dishes.add(dish);
    }
    public List<Dish> getAllDishes(){
        return dishes;
    }
    @JsonIgnore
    public int getSize(){
        return dishes.size();
    }
    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }


}
