package uz.cherevichenko_sergey.cafe_order_system.view.listeners;

public interface SendButtonCommandListener {
    void onPrintMenuClicked();

    void onCreateOrderClicked();

    void onAddDishInOrderClicked();

    void onPrintOrderThisClientClicked();

    void onRemoveDishFromOrderThisClicked();

    void onRemoveOrderThisClicked();

    void onPrintInfoThisClientClicked();

    void onChangeInfoClientClicked();

    void onFinishClicked();

    void onBackUserMenu();
}