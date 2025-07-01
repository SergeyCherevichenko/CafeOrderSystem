package uz.cherevichenko_sergey.cafe_order_system.model.service;

import uz.cherevichenko_sergey.cafe_order_system.model.dish.Dish;
import uz.cherevichenko_sergey.cafe_order_system.model.builder.DishBuilder;
import uz.cherevichenko_sergey.cafe_order_system.model.dish.ListDishes;

import java.util.Comparator;
import java.util.Iterator;
import java.util.UUID;

public class DishService {
    private DishBuilder dishBuilder;
    private ListDishes listDishes;

    public DishService(){
        dishBuilder = new DishBuilder();
        listDishes  = new ListDishes();
    }

    public boolean addDish(String name, String pathImage, int cost, int count){
        if (findDishByName(name) != null) return false; // уже есть
        listDishes.addDish(dishBuilder.dishBuild(name, pathImage, cost, count));
        return true;
    }
    public Dish findDishById(UUID id){
        for (Dish dish : listDishes.getAllDishes()){
            if(dish.getIdDish().equals(id)){
                return dish;
            }
        }
        return null;
    }
    public Dish findDishByIndex(int index) {
        if (index >= 0 && index < listDishes.getSize()) {
            return listDishes.getAllDishes().get(index);
        }
        return null;
    }


    public Dish findDishByName(String name){

        for (Dish dish : listDishes.getAllDishes()){
            if(dish.getNameDish().equals(name)){
                return dish;
            }
        }
        return null;
    }
    public boolean sortDishByName(){
        if(listDishes.getAllDishes().isEmpty()) return false;
        listDishes.getAllDishes().sort(Comparator.comparing(Dish::getNameDish));
        return true;
    }
    public boolean sortDishByCost(){
        if(listDishes.getAllDishes().isEmpty()) return false;
        listDishes.getAllDishes().sort(Comparator.comparing(Dish::getCost));
        return true;
    }
    public boolean removeDishByName(String name){
        Iterator<Dish> it = listDishes.getAllDishes().iterator();
        while (it.hasNext()) {
            if (it.next().getNameDish().equals(name)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public boolean sortDishByAddToMenuDateTime(){
        if(listDishes.getAllDishes().isEmpty()) return false;
        listDishes.getAllDishes().sort(Comparator.comparing(Dish::getAddToMenu));
        return true;
    }
    public boolean removeDishByID(UUID id){
        Iterator<Dish> it = listDishes.getAllDishes().iterator();
        while (it.hasNext()) {
            if (it.next().getIdDish().equals(id)) {
                it.remove();
                return true;
            }
        }
        return false;
    }
    public String getDishInfo(Dish dish) {
        if (dish == null) {
            return " Блюдо не найдено.";
        }
        return dish.toString();
    }

    public ListDishes getListDishes() {
        return listDishes;
    }

    public void setListDishes(ListDishes listDishes) {
        this.listDishes = listDishes;
    }
    public boolean removeDishByIndexFromListDish(int index){
        if(listDishes.getAllDishes().isEmpty()) return false;
        if(index < 0 || index >= listDishes.getSize()) return false;
        listDishes.getAllDishes().remove(index);
        return true;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Dish d : listDishes.getAllDishes()) {
            sb.append(index++).append(". ").append(d.toString()).append("\n");
        }
        return sb.toString();
    }

}
