package uz.cherevichenko_sergey.cafe_order_system.model.interfaces;

public interface DataStore<T> {
    void save(T data); // Принимает данные явно
    T read();         // Возвращает новые данные
}
