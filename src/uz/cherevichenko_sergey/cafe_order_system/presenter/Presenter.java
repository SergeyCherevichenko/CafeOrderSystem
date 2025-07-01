package uz.cherevichenko_sergey.cafe_order_system.presenter;

import uz.cherevichenko_sergey.cafe_order_system.model.service.MenuService;
import uz.cherevichenko_sergey.cafe_order_system.view.View;

import java.time.LocalDateTime;
import java.util.List;

public class Presenter {
    View view;
    MenuService menuService;

    public Presenter(View view){
        this.view = view;
        menuService= new MenuService();
    }

    public boolean clientLogin(String name,String email, String password){
        return menuService.clientLogin(name,email,password);
    }
    public boolean isAdmin(){
        return menuService.isAdmin();
    }
    public  boolean clientRegistry(String name, String phone, String email, String password){
        return menuService.clientRegistry(name, phone,email,password);
    }

    public boolean addClient(String name,String phone,String email, String password){
       return menuService.addClient(name,phone,email,password);
    }
    public boolean addDishInOrder(int indexOrder, List<Integer> dishIndexes){
        return menuService.addDishInOrder(indexOrder, dishIndexes);
    }
    public boolean addDishInOrderAdmin(int indexClient, int indexOrder, List<Integer> dishIndexes){
        return menuService.addDishInOrderAdmin(indexClient, indexOrder, dishIndexes);
    }
    public boolean addDishToMenu(String name, String pathImage, int cost, int count){
        return menuService.addDish(name, pathImage, cost, count);
    }
    public boolean changeInfoClient(String phoneNumber, String password, String newName, String newPhone,
                                    String newEmail, String newPassword){
        return menuService.changeInfoClient(phoneNumber,password,newName,newPhone,newEmail,newPassword);
    }
    public boolean changeStatusOrder(int indexClient, int indexOrder, int indexStatus){
        return menuService.changeStatusOrder(indexClient, indexOrder, indexStatus);
    }
    public boolean createOrder(List<Integer> dishIndexes){
        return menuService.createOrder(dishIndexes);
    }
    public boolean createOrderAdmin(String phoneNumber, List<Integer> dishIndexes){
        return menuService.createOrderAdmin(phoneNumber,dishIndexes);
    }
    public boolean deleteDishFromMenu(int index){
        return menuService.removeDishFromMenu(index);
    }
    public boolean removeDishFromOrderAdmin(int indexClient, int indexOrder, int indexDish){
        return  menuService.removeDishFromOrderByIndexesAdmin(indexClient,indexOrder,indexDish);
    }
    public boolean removeDishFromOrderThis(int indexOrder, int indexDish){
        return menuService.removeDishFromOrderByIndexes(indexOrder,indexDish);
    }
    public boolean removeOrderAdmin(int indexClient, int indexOrder){
        return  menuService.removeOrderByIndexAdmin(indexClient, indexOrder);
    }
    public boolean removeOrderThis(int indexOrder){
        return menuService.removeOrderByIndex(indexOrder);
    }
    public String sortOrdersByDataTime(){
        return  menuService.getAllOrdersSortedByAddDate();
    }
    public String sortOrdersByStatus(){
        return menuService.getAllOrdersSortedByStatus();
    }
    public boolean saveListDishes(){
        return  menuService.saveListDishes();
    }

    public boolean saveClientList(){
        return menuService.saveClientList();
    }

    public String printMenu(){
        return menuService.getMenu();
    }
    public String printAllOrders(){
        return menuService.getInfoAllOrders();
    }
    public String printInfoClient(String phoneNumber){
        return menuService.getInfoClient(phoneNumber);
    }
    public String printInfoThisClient(){
        return menuService.getCurrentClientInfo();
    }
    public String printInfoAllClients(){
        return menuService.getInfoAllClients();
    }
    public String printOrdersByClientPhone(String phoneNumber){
        return menuService.getOrderByClientPhone(phoneNumber);
    }
    public String printOrdersCurrentClient(){
        return menuService.getOrdersCurrentClient();
    }
    public String findOrderClientByIndex( int index){
         return menuService.getOrderByClientIndex( index);
    }
    public String printOrderByClientIndex(int index){
        return menuService.getOrderByClientIndex(index);
    }
    public String printAllStatus(){
        return  menuService.getAllStatus();
    }
    public String printAllDishInOrderThisClient(int indexOrder){
        return menuService.getDishFromOrderThisClient(indexOrder);
    }
    public String getAllDishesInOrderByClientIndex(int indexClient, int indexOrder){
       return menuService.getAllDishesInOrderByClientIndex(indexClient,indexOrder);
    }




}
