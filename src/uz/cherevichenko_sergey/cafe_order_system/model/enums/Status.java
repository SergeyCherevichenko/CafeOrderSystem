package uz.cherevichenko_sergey.cafe_order_system.model.enums;


import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Status {
    NEW_ORDER,
    ACCEPTED,
    BEING_PREPARED,
    READY,
    ISSUED;

    @Override
    public String toString() {
        return switch (this) {
            case NEW_ORDER -> "Новый заказ";
            case ACCEPTED -> "Принят";
            case BEING_PREPARED -> "Готовится";
            case READY -> "Готов";
            case ISSUED -> "Выдан";
        };
    }
}

