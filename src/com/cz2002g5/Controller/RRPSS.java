package com.cz2002g5.Controller;

import com.cz2002g5.Model.Menu.Menu;
import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Menu.PromotionalSet;
import com.cz2002g5.Model.Order.Order;
import com.cz2002g5.Model.Restaurant.Restaurant;
import com.cz2002g5.Util.CSVFileUtil;
import com.cz2002g5.View.View;
import com.cz2002g5.View.WelcomeView;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Rrpss.
 */
public class RRPSS {
    private final Restaurant restaurant;
    private final ArrayList<Order> orders = new ArrayList<>();
    private final Menu menu;
    private ArrayList<PromotionalSet> promotionalSets;
    private View view;

    /**
     * Instantiates a new Rrpss.
     *
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
public RRPSS() throws IOException {
        this.restaurant = new Restaurant();
        this.menu = new Menu(CSVFileUtil.generateMenuItemListFromFile());
        this.promotionalSets = CSVFileUtil.generatePromoMenuItemListFromFile();
    }

    /**
     * Update view.
     *
     * @param pos The instance of the main RRPSS controller
     * @param view the view
     */
public static void updateView(RRPSS pos, View view) {
        pos.view = view;
    }

    /**
     * Show view.
     *
     * @param pos The instance of the main RRPSS controller
     */
public static void showView(RRPSS pos) {
        getCurrentView(pos).display();
    }

    /**
     * Show view.
     *
     * @param pos The instance of the main RRPSS controller
     * @param param the param
     */
public static void showView(RRPSS pos, String param) {
        getCurrentView(pos).displayCustomView(param);
    }

    /**
     * Gets current view.
     *
     * @param pos The instance of the main RRPSS controller
     * @return the current view
     */
public static View getCurrentView(RRPSS pos) {
        return pos.view;
    }

    /**
     * Gets menu.
     *
     * @param pos The instance of the main RRPSS controller
     * @return the menu
     */
public static Menu getMenu(RRPSS pos) {
        return pos.menu;
    }

    /**
     * Gets promotional sets.
     *
     * @param pos The instance of the main RRPSS controller
     * @return the promotional sets
     */
public static ArrayList<PromotionalSet> getPromotionalSets(RRPSS pos) {
        return pos.promotionalSets;
    }

    /**
     * Run.
     *
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
public void run() throws IOException {
        while (true) {
            RRPSS.updateView(this,new WelcomeView());
            getCurrentView(this).display();
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
                    getCurrentView(this).display();
                    break;
                case 2:
                    PromotionEditController pec = new PromotionEditController();
                    pec.selectAction(this);
                    this.view = new WelcomeView();
                    getCurrentView(this).display();
                    break;
                case 3:
                    OrderController oc = new OrderController();
                    oc.createOrder(this);
                    this.view = new WelcomeView();
                    getCurrentView(this).display();
                    break;
                case 4:
                    oc = new OrderController();
                    oc.viewAllOrders(this);
                    this.view = new WelcomeView();
                    getCurrentView(this).display();
                    break;
                case 5:
                    oc = new OrderController();
                    oc.selectAction(this);
                    this.view = new WelcomeView();
                    getCurrentView(this).display();
                    break;
                case 6:
                    ReservationController rc = new ReservationController();
                    rc.createReservation(this);
                    this.view = new WelcomeView();
                    getCurrentView(this).display();
                    break;
                case 7:
                    rc = new ReservationController();
                    rc.selectAction(this);
                    this.view = new WelcomeView();
                    getCurrentView(this).display();
                    break;
                case 8:
                    rc = new ReservationController();
                    rc.checkAvailability(this);
                    this.view = new WelcomeView();
                    getCurrentView(this).display();
                    break;
                case 9:
                    oc = new OrderController();
                    oc.generateInvoice(this);
                    this.view = new WelcomeView();
                    getCurrentView(this).display();
                    break;
                case 10:
                    RevenueReportController rrc = new RevenueReportController();
                    rrc.generateRevenueReport();
                    this.view = new WelcomeView();
                    getCurrentView(this).display();
                    break;
                default:
                    System.out.println("You have selected an invalid option!");
            }
        }
    }

    /**
     * Reload menu.
     *
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
public void reloadMenu() throws IOException {
        this.menu.setMenuItems(CSVFileUtil.generateMenuItemListFromFile());
        this.promotionalSets = CSVFileUtil.generatePromoMenuItemListFromFile();
    }

    /**
     * Add order.
     *
     * @param order the order
     */
public void addOrder(Order order) {
        this.orders.add(order);
    }

    /**
     * Gets orders.
     *
     * @return the orders
     */
public ArrayList<Order> getOrders() {
        return this.orders;
    }

    /**
     * Gets restaurant.
     *
     * @return the restaurant
     */
public Restaurant getRestaurant() {
        return this.restaurant;
    }

    /**
     * Add promo set.
     *
     * @param pi the pi
     */
public void addPromoSet(PromotionalSet pi) {
        this.promotionalSets.add(pi);
    }

    /**
     * Generate promo menu string string.
     *
     * @return the string
     */
public String generatePromoMenuString() {
        ArrayList<PromotionalSet> promotionalSets = new ArrayList<>();
        StringBuilder promoString = new StringBuilder();
        try {
            promotionalSets = CSVFileUtil.generatePromoMenuItemListFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i=0;i<promotionalSets.size();i++) {
            promoString.append(i + 1).append(". ").append(promotionalSets.get(i).getName()).append(" - ")
                    .append(NumberFormat.getCurrencyInstance().format(promotionalSets.get(i).getPrice())).append("\n");
        }
        return promoString.toString();
    }

    /**
     * Generate menu string string.
     *
     * @return the string
     */
public String generateMenuString() {
        ArrayList<MenuItem> menu = new ArrayList<>();
        StringBuilder menuString = new StringBuilder();
        try {
            menu = CSVFileUtil.generateMenuItemListFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i=0;i<menu.size();i++) {
            menuString.append(i + 1).append(". ").append(menu.get(i).getName()).append(" - ")
                    .append(NumberFormat.getCurrencyInstance().format(menu.get(i).getPrice())).append("\n");
        }
        return menuString.toString();
    }
}
