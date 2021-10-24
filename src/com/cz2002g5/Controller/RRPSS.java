package com.cz2002g5.Controller;

import com.cz2002g5.Model.Menu.Menu;
import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Menu.PromotionalSet;
import com.cz2002g5.Model.Order.Order;
import com.cz2002g5.Model.Reservation.Reservation;
import com.cz2002g5.Model.Restaurant.Restaurant;
import com.cz2002g5.Model.Restaurant.Table;
import com.cz2002g5.Util.CSVFileUtil;
import com.cz2002g5.View.View;
import com.cz2002g5.View.WelcomeView;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RRPSS {
  private final Restaurant restaurant;
  private final ArrayList<Order> orders = new ArrayList<>();
  private final Menu menu;
  private ArrayList<PromotionalSet> promotionalSets;
  private View view;

  public RRPSS() {
    this.restaurant = new Restaurant();
    this.menu = new Menu(CSVFileUtil.generateMenuItemListFromFile());
    this.promotionalSets = CSVFileUtil.generatePromoMenuItemListFromFile();
    Runnable checkReservationRunnable = this::clearReservations;
    ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
    exec.scheduleAtFixedRate(checkReservationRunnable , 0, 1, TimeUnit.MINUTES);
  }

  public static void updateView(RRPSS pos, View view) {
    pos.view = view;
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

  private void clearReservations() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    for (Table t : this.restaurant.getTables()) {
      for (Reservation r : t.getReservations()) {
        String dateTimeString = r.getDate().toString() + " " + r.getTime().toString();
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, dtf);
        long minutes = ChronoUnit.MINUTES.between(dateTime, LocalDateTime.now());
        if (minutes > 15) {
          t.getReservations().remove(r);
        }
      }
    }
  }
  public void run() {
    while (true) {
      RRPSS.updateView(this, new WelcomeView());
      getCurrentView(this).display();
      this.selectMainViewOption();
    }
  }

  private void selectMainViewOption() {
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

  public String generatePromoMenuString() {
    StringBuilder promoString = new StringBuilder();
    ArrayList<PromotionalSet> promotionalSets = CSVFileUtil.generatePromoMenuItemListFromFile();
    if (promotionalSets != null) {
      for (int i = 0; i < promotionalSets.size(); i++) {
        promoString
            .append(i + 1)
            .append(". ")
            .append(promotionalSets.get(i).getName())
            .append(" - ")
            .append(NumberFormat.getCurrencyInstance().format(promotionalSets.get(i).getPrice()))
            .append("\n");
      }
    }
    return promoString.toString();
  }

  public String generateMenuString() {
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
            .append(NumberFormat.getCurrencyInstance().format(menu.get(i).getPrice()))
            .append("\n");
      }
    }
    return menuString.toString();
  }
}
