package com.cz2002g5.Controller;

import com.cz2002g5.Model.Menu.Menu;
import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Menu.PromotionalSet;
import com.cz2002g5.Model.Order.Order;
import com.cz2002g5.Model.Restaurant.Restaurant;
import com.cz2002g5.Util.CSVFileUtil;
import com.cz2002g5.View.View;
import com.cz2002g5.View.WelcomeView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RRPSS implements Runnable{
  private final Restaurant restaurant;
  private final ArrayList<Order> orders = new ArrayList<>();
  private final Menu menu;
  private ArrayList<PromotionalSet> promotionalSets;
  private View view;
  private MenuItemController mic;
  private PromotionEditController pec;
  private OrderController oc;
  private ReservationController rc;
  private RevenueReportController rrc;

  public RRPSS() {
    this.restaurant = new Restaurant();
    this.menu = new Menu(CSVFileUtil.generateMenuItemListFromFile());
    this.promotionalSets = CSVFileUtil.generatePromoMenuItemListFromFile();
    this.mic = new MenuItemController();
    this.pec = new PromotionEditController();
    this.oc = new OrderController();
    this.rc = new ReservationController(this);
    this.rrc = new RevenueReportController();
  }


  private void selectMainViewOption() {
    while (true) {
      returnToWelcomeView();
      System.out.println("Select an option:");
      Scanner sc = new Scanner(System.in);
      while (!sc.hasNextInt()) {
        System.out.println("You have inputted a non-numerical value!\nSelect an option:");
        sc.next();
      }
      int input = sc.nextInt();
      switch (input) {
        case 1:
          mic.selectAction(this);
          break;
        case 2:
          pec.selectAction(this);
          break;
        case 3:
          oc.createOrder(this);
          break;
        case 4:
          oc.viewAllOrders(this);
          break;
        case 5:
          oc.selectAction(this);
          break;
        case 6:
          rc.createReservation(this);
          break;
        case 7:
          rc.selectAction(this);
          break;
        case 8:
          rc.checkAvailability(this);
          break;
        case 9:
          oc.generateInvoice(this);
          break;
        case 10:
          rrc.generateRevenueReport();
          break;
        case 11:
          mic.showAllMenuItems(this);
          break;
        default:
          System.out.println("You have selected an invalid option!");
      }
    }
  }

  public static void updateView(RRPSS pos, View view) {
    pos.view = view;
  }

  private void returnToWelcomeView() {
    this.view = new WelcomeView();
    getCurrentView(this).display();
  }

  public static void showView(RRPSS pos) {
    getCurrentView(pos).display();
  }

  public static void showView(RRPSS pos, String param) {
    getCurrentView(pos).displayCustomView(param);
  }

  public static View getCurrentView(RRPSS pos) {
    return pos.view;
  }

  public static Menu getMenu(RRPSS pos) {
    return pos.menu;
  }

  public static ArrayList<PromotionalSet> getPromotionalSets(RRPSS pos) {
    return pos.promotionalSets;
  }


  public void run() {
    while (true) {
      this.selectMainViewOption();
    }
  }

  public void reloadMenu() {
    this.menu.setMenuItems(CSVFileUtil.generateMenuItemListFromFile());
    this.promotionalSets = CSVFileUtil.generatePromoMenuItemListFromFile();
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

  public void addPromoSet(PromotionalSet pi) {
    this.promotionalSets.add(pi);
  }

  public String generatePromoMenuString(boolean withDesc) {
    StringBuilder promoString = new StringBuilder();
    ArrayList<PromotionalSet> promotionalSets = CSVFileUtil.generatePromoMenuItemListFromFile();
    if (promotionalSets != null) {
      for (int i = 0; i < promotionalSets.size(); i++) {
        String itemString = "";
        if (withDesc) {
          ArrayList<PromotionalSet> setItemes = new ArrayList<>();
          for (MenuItem item : promotionalSets.get(i).getSetItems()) {
            itemString += item.getName() + ",";
          }
          itemString = itemString.substring(0,itemString.length()-1);
        }
        promoString
            .append(i + 1)
            .append(". ")
            .append(promotionalSets.get(i).getName())
            .append(" - ")
            .append(withDesc ? itemString + " - " : "")
            .append(NumberFormat.getCurrencyInstance().format(promotionalSets.get(i).getPrice()))
            .append("\n");
      }
    }
    return promoString.toString();
  }

  public String generateMenuString(boolean withDesc) {
    ArrayList<MenuItem> menu;
    StringBuilder menuString = new StringBuilder();
    menu = CSVFileUtil.generateMenuItemListFromFile();
    if (menu != null) {
      for (int i = 0; i < menu.size(); i++) {
        menuString
            .append(i + 1)
            .append(". ")
            .append(menu.get(i).getName())
            .append(" - ")
            .append(withDesc ? menu.get(i).getDescription() + " - " : "")
            .append(NumberFormat.getCurrencyInstance().format(menu.get(i).getPrice()))
            .append("\n");
      }
    }
    return menuString.toString();
  }
}
