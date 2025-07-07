package uz.cherevichenko_sergey.cafe_order_system.view.swing_command_panels;

import uz.cherevichenko_sergey.cafe_order_system.view.listeners.PanelStartMenuListeners;
import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonCommandAdminListener;
import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonCommandListener;
import uz.cherevichenko_sergey.cafe_order_system.view.listeners.SendButtonListener;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public static final String PANEL_ADD_CLIENT = "panel_add_client";
    public static final String PANEL_ADD_DISH_TO_MENU = "panel_add_dish_to_menu";
    public static final String PANEL_ADD_INDEX_FOR_DISH_REMOVE_TO_MENU = "panel_add_index_for_dish_remove_to_menu";
    public static final String PANEL_CHANGE_INFO_CLIENT = "panel_change_info_client";
    public static final String PANEL_CLIENT_LOGIN = "panel_client_login";
    public static final String PANEL_CLIENT_REGISTRY = "panel_client_registry";
    public static final String PANEL_START_MENU = "panel_start_menu";
    public static final String PANEL_MENU_USER = "panel_menu_user";
    public static final String PANEL_MENU_ADMIN = "panel_menu_admin";
    public static final String PANEL_INFO_CLIENT = "panel_info_client";
    public static final String PANEL_CREATE_ORDER_ADMIN = "panel_create_order_admin";
    public static final String PANEL_ADD_DISH_IN_ORDER_ADMIN = "panel_add_dish_in_order_admin";
    public static final String PANEL_REMOVE_ORDER_ADMIN = "panel_remove_order_admin";
    public static final String PANEL_REMOVE_DISH_FROM_ORDER_ADMIN = "panel_remove_dish_from_order_admin";
    public static final String PANEL_CHANGE_STATUS_ORDER = "panel_change_status_order";
    public static final String PANEL_CREATE_ORDER = "panel_create_order";
    public static final String PANEL_ADD_DISH_IN_ORDER = "panel_add_dish_in_order";
    public static final String PANEL_REMOVE_ORDER = "panel_remove_order";
    public static final String PANEL_REMOVE_DISH_FROM_ORDER = "panel_remove_dish_from_order";
    private final PanelAddClient panelAddClient;
    private final PanelAddDishToMenu panelAddDishToMenu;
    private final PanelAddIndexForDishRemoveToMenu panelAddIndex;
    private final PanelChangeInfoClient changeInfoClient;
    private final PanelClientLogin panelClientLogin;
    private final PanelClientRegistre panelClientRegistre;
    private final PanelPrintInfoClient panelPrintInfoClient;
    private final PanelStartMenu panelStartMenu;
    private final PrintPanelMenuUser printPanelMenuUser;
    private final PrintPanelMenuAdmin printPanelMenuAdmin;
    private final PanelCreateOrderAdmin panelCreateOrderAdmin;
    private final PanelAddDishInOrderAdmin panelAddDishInOrderAdmin;
    private final PanelRemoverOrderAdmin panelRemoverOrderAdmin;
    private final PanelRemoveDishFromOrderAdmin panelRemoveDishFromOrderAdmin;
    private final PanelChangeStatusOrder panelChangeStatusOrder;
    private final PanelCreateOrder panelCreateOrder;
    private final PanelAddDishInOrder panelAddDishInOrder;
    private final PanelRemoveOrder panelRemoveOrder;
    private final PanelRemoveDishFromOrder panelRemoveDishFromOrder;
    private String currentPanel;

    public MainPanel(PanelStartMenuListeners panelStartMenuListeners,
                     SendButtonCommandAdminListener sendButtonCommandAdminListener,
                     SendButtonListener sendButtonListener, SendButtonCommandListener sendButtonCommandListener) {
        super(new CardLayout());
        this.panelStartMenu = new PanelStartMenu(panelStartMenuListeners);
        this.panelClientLogin = new PanelClientLogin(sendButtonListener);
        this.panelClientRegistre = new PanelClientRegistre(sendButtonListener);
        this.printPanelMenuUser = new PrintPanelMenuUser(sendButtonCommandListener);
        this.printPanelMenuAdmin = new PrintPanelMenuAdmin(sendButtonCommandAdminListener);
        this.panelAddClient = new PanelAddClient(sendButtonListener);
        this.panelAddDishToMenu = new PanelAddDishToMenu(sendButtonListener);
        this.panelAddIndex = new PanelAddIndexForDishRemoveToMenu(sendButtonListener);
        this.changeInfoClient = new PanelChangeInfoClient(sendButtonListener);
        this.panelPrintInfoClient = new PanelPrintInfoClient(sendButtonListener);
        this.panelCreateOrderAdmin = new PanelCreateOrderAdmin(sendButtonListener);
        this.panelAddDishInOrderAdmin = new PanelAddDishInOrderAdmin(sendButtonListener);
        this.panelRemoverOrderAdmin = new PanelRemoverOrderAdmin(sendButtonListener);
        this.panelRemoveDishFromOrderAdmin = new PanelRemoveDishFromOrderAdmin(sendButtonListener);
        this.panelChangeStatusOrder = new PanelChangeStatusOrder(sendButtonListener);
        this.panelCreateOrder = new PanelCreateOrder(sendButtonListener);
        this.panelAddDishInOrder = new PanelAddDishInOrder(sendButtonListener);
        this.panelRemoveOrder = new PanelRemoveOrder(sendButtonListener);
        this.panelRemoveDishFromOrder = new PanelRemoveDishFromOrder(sendButtonListener);
        this.add(panelStartMenu, PANEL_START_MENU);
        this.add(panelClientRegistre, PANEL_CLIENT_REGISTRY);
        this.add(panelClientLogin, PANEL_CLIENT_LOGIN);
        this.add(printPanelMenuUser, PANEL_MENU_USER);
        this.add(printPanelMenuAdmin, PANEL_MENU_ADMIN);
        this.add(panelAddClient, PANEL_ADD_CLIENT);
        this.add(panelAddDishToMenu, PANEL_ADD_DISH_TO_MENU);
        this.add(panelAddIndex, PANEL_ADD_INDEX_FOR_DISH_REMOVE_TO_MENU);
        this.add(changeInfoClient, PANEL_CHANGE_INFO_CLIENT);
        this.add(panelPrintInfoClient, PANEL_INFO_CLIENT);
        this.add(panelCreateOrderAdmin, PANEL_CREATE_ORDER_ADMIN);
        this.add(panelAddDishInOrderAdmin, PANEL_ADD_DISH_IN_ORDER_ADMIN);
        this.add(panelRemoverOrderAdmin, PANEL_REMOVE_ORDER_ADMIN);
        this.add(panelRemoveDishFromOrderAdmin, PANEL_REMOVE_DISH_FROM_ORDER_ADMIN);
        this.add(panelChangeStatusOrder, PANEL_CHANGE_STATUS_ORDER);
        this.add(panelCreateOrder, PANEL_CREATE_ORDER);
        this.add(panelAddDishInOrder, PANEL_ADD_DISH_IN_ORDER);
        this.add(panelRemoveOrder, PANEL_REMOVE_ORDER);
        this.add(panelRemoveDishFromOrder, PANEL_REMOVE_DISH_FROM_ORDER);

    }

    public void showPanel(String panelName) {
        ((CardLayout) getLayout()).show(this, panelName);
        currentPanel = panelName;
    }

    public PanelAddClient getPanelAddClient() {
        return panelAddClient;
    }

    public PanelAddDishToMenu getPanelAddDishToMenu() {
        return panelAddDishToMenu;
    }

    public PanelAddIndexForDishRemoveToMenu getPanelAddIndexForDishRemoveToMenu() {
        return panelAddIndex;
    }

    public PanelChangeInfoClient getChangeInfoClient() {
        return changeInfoClient;
    }

    public PanelClientLogin getPanelClientLogin() {
        return panelClientLogin;
    }

    public PanelClientRegistre getPanelClientRegistre() {
        return panelClientRegistre;
    }

    public PanelPrintInfoClient getPanelPrintInfoClient() {
        return panelPrintInfoClient;
    }

    public PanelStartMenu getPanelStartMenu() {
        return panelStartMenu;
    }

    public PrintPanelMenuUser getPrintPanelMenuUser() {
        return printPanelMenuUser;
    }

    public PrintPanelMenuAdmin getPrintPanelMenuAdmin() {
        return printPanelMenuAdmin;
    }

    public String getCurrentPanel() {
        return currentPanel;
    }

    public PanelCreateOrderAdmin getPanelCreateOrderAdmin() {
        return panelCreateOrderAdmin;
    }

    public PanelAddDishInOrderAdmin getPanelAddDishInOrderAdmin() {
        return panelAddDishInOrderAdmin;
    }

    public PanelRemoverOrderAdmin getPanelRemoverOrderAdmin() {
        return panelRemoverOrderAdmin;
    }

    public PanelRemoveDishFromOrderAdmin getPanelRemoveDishFromOrderAdmin() {
        return panelRemoveDishFromOrderAdmin;
    }

    public PanelChangeStatusOrder getPanelChangeStatusOrder() {
        return panelChangeStatusOrder;
    }

    public PanelCreateOrder getPanelCreateOrder() {
        return panelCreateOrder;
    }

    public PanelAddDishInOrder getPanelAddDishInOrder() {
        return panelAddDishInOrder;
    }

    public PanelRemoveOrder getPanelRemoveOrder() {
        return panelRemoveOrder;
    }

    public PanelRemoveDishFromOrder getPanelRemoveDishFromOrder() {
        return panelRemoveDishFromOrder;
    }
}
