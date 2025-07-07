package uz.cherevichenko_sergey.cafe_order_system.view.listeners;

public interface SendButtonCommandAdminListener {
    void onPrintMenuClicked();

    void onAddDishToMenuClicked();

    void onDeleteDishFromMenuClicked();

    void onPrintInfoAllClientsClicked();

    void onPrintInfoThisClientClicked();

    void onCreateOrderAdminClicked();

    void onAddDishInOrderAdminClicked();

    void onRemoveOrderAdminClicked();

    void onRemoveDishFromOrderAdminClicked();

    void onPrintAllOrdersClicked();

    void onPrintOrdersByClientPhoneClicked();

    void onChangeStatusOrderClicked();

    void onPrintSortOrdersByDataTimeClicked();

    void onPrintSortOrdersByStatusClicked();

    void onFinishClicked();

}
