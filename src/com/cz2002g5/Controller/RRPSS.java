package com.cz2002g5.Controller;

import com.cz2002g5.Model.Customer.Customer;
import com.cz2002g5.Model.Menu.Menu;
import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Order.Order;
import com.cz2002g5.Model.Restaurant.Restaurant;
import com.cz2002g5.Util.CSVFileUtil;
import com.cz2002g5.View.View;
import com.cz2002g5.View.WelcomeView;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class RRPSS {
    private final Restaurant restaurant;
    private final ArrayList<Customer> customerList = new ArrayList<>();
    private final Menu menu;
    private View view;
    private final ArrayList<Order> orders = new ArrayList<>();
    private PromotionEditController pe;

    public RRPSS() throws IOException {
        this.restaurant = new Restaurant();
        this.menu = new Menu(CSVFileUtil.generateMenuItemListFromFile());
    }

    public static void updateView(RRPSS pos, View view) {
        pos.view = view;
    }

    public static void showView(RRPSS pos) {
        getCurrentView(pos).show();
    }

    public static void showView(RRPSS pos, String param) {
        getCurrentView(pos).show(param);
    }

    public static View getCurrentView(RRPSS pos) {
        return pos.view;
    }

    public static Menu getMenu(RRPSS pos) {
        return pos.menu;
    }

    public void run() throws IOException {
        while (true) {
            RRPSS.updateView(this,new WelcomeView());
            getCurrentView(this).show();
            this.selectMainViewOption();
        }
    }

    private void selectMainViewOption() throws IOException {
        while (true) {
            System.out.println("Select an option:");
            Scanner sc = new Scanner(System.in);
            while (!sc.hasNextInt()) {
                System.out.println("You have inputted a non-numerical value!\nSelect an option:");
                sc.next();
            }
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    MenuItemController mic = new MenuItemController();
                    mic.selectAction(this);
                    this.view = new WelcomeView();
                    getCurrentView(this).show();
                    break;
                case 2: //TODO
                    break;
                case 3:
                    OrderController oc = new OrderController();
                    oc.createOrder(this);
                    this.view = new WelcomeView();
                    getCurrentView(this).show();
                    break;
                case 4:
                    oc = new OrderController();
                    oc.viewAllOrders(this);
                    this.view = new WelcomeView();
                    getCurrentView(this).show();
                    break;
                case 5: //TODO
                    this.view = new WelcomeView();
                    getCurrentView(this).show();
                    break;
                case 6: //TODO
                    this.view = new WelcomeView();
                    getCurrentView(this).show();
                    break;
                case 7: //TODO
                    this.view = new WelcomeView();
                    getCurrentView(this).show();
                    break;
                case 8: //TODO
                    this.view = new WelcomeView();
                    getCurrentView(this).show();
                    break;
                case 9: //TODO
                    this.view = new WelcomeView();
                    getCurrentView(this).show();
                    break;
                case 10: //TODO
                    this.view = new WelcomeView();
                    getCurrentView(this).show();
                    break;
                default:
                    System.out.println("You have selected an invalid option!");
            }
        }
    }

    public void reloadMenu() throws IOException {
        this.menu.setMenuItems(CSVFileUtil.generateMenuItemListFromFile());
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }


    public String generateMenuString() {
        ArrayList<MenuItem> menu = new ArrayList<>();
        StringBuilder menuString = new StringBuilder();
        try {
            menu = CSVFileUtil.generateMenuItemListFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i=0;i<menu.size();i++) {
            menuString.append(new StringBuilder().append(i + 1).append(". ").append(menu.get(i).getName())
                    .append(" - ").append(NumberFormat.getCurrencyInstance().format(menu.get(i).getPrice()))
                    .append("\n").toString());
        }
        return menuString.toString();
    }
}
