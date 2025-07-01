package uz.cherevichenko_sergey.cafe_order_system.model.service;

import org.junit.jupiter.api.Test;
import uz.cherevichenko_sergey.cafe_order_system.model.dish.Dish;

import static org.junit.jupiter.api.Assertions.*;

class DishServiceTest {

    @Test
    void addDish() {
        DishService dishService = new DishService();
        assertTrue(dishService.addDish("Plov","path//",50000,100));
    }

    @Test
    void findDishByIndex() {
        DishService dishService = new DishService();
        dishService.addDish("Plov","path//",50000,100);
        dishService.addDish("Somsa","path//",50000,100);
        Dish dish = dishService.findDishByIndex(0);
        assertEquals("Plov",dish.getNameDish());
        assertNull(dish = dishService.findDishByIndex(3));
    }

    @Test
    void findDishByName() {
        DishService dishService = new DishService();
        dishService.addDish("Plov","path//",50000,100);
        dishService.addDish("Somsa","path//",50000,100);
        Dish dish = dishService.findDishByName("Plov");
        assertEquals("Plov",dish.getNameDish());
        assertNull(dish = dishService.findDishByName("Narin"));
    }

    @Test
    void removeDishByName() {
        DishService dishService = new DishService();
        dishService.addDish("Plov","path//",50000,100);
        dishService.addDish("Somsa","path//",50000,100);
       boolean dish = false;
        assertTrue( dish = dishService.removeDishByName("Plov"));
        assertFalse(dish = dishService.removeDishByName("Narin"));
    }
}