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

/**
 * The Restaurant Reservation and Point of Sale System class*/
public class RRPSS {
  private final Restaurant restaurant;
  private final ArrayList<Order> orders = new ArrayList<>();
  private final Menu menu;
  private ArrayList<PromotionalSet> promotionalSets;
  private View view;
  private final MenuItemController mic;
  private final PromotionEditController pec;
  private final OrderController oc;
  private final ReservationController rc;
  private final RevenueReportController rrc;

  /** Instantiates a new instance of RRPSS.
   * Initializes an instance of all controllers and menu.
   * */
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

    private void returnToWelcomeView() {
        this.view = new WelcomeView();
        getCurrentView(this).display();
    }

  /**
   * This method updates the current view that the user is in.
   *
   * @param pos Reference to the instance of RRPSS.
   * @param view The new view that is to be updated.
   */
  public static void updateView(RRPSS pos, View view) {
    pos.view = view;
  }

  /**
   * This method shows the default view of the current view of an instance of RRPSS.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  public static void showView(RRPSS pos) {
    getCurrentView(pos).display();
  }

  /**
   * This method shows the default view of the current view of an instance of RRPSS with an additional message.
   *
   * @param pos Reference to the instance of RRPSS.
   * @param param The custom message to be displayed
   */
  public static void showView(RRPSS pos, String param) {
    getCurrentView(pos).displayCustomView(param);
  }

  /**
   * This method returns the current view that the user is on for an instance of RRPSS.
   *
   * @param pos Reference to the instance of RRPSS.
   * @return the current view
   */
  public static View getCurrentView(RRPSS pos) {
    return pos.view;
  }

  /**
   * This method retrieves the menu of an instance of RRPSS.
   *
   * @param pos Reference to the instance of RRPSS.
   * @return the menu
   */
  public static Menu getMenu(RRPSS pos) {
    return pos.menu;
  }

  /**
   * This method retrieves the promotional sets of an instance of RRPSS.
   *
   * @param pos Reference to the instance of RRPSS.
   * @return the promotional sets
   */
  public static ArrayList<PromotionalSet> getPromotionalSets(RRPSS pos) {
    return pos.promotionalSets;
  }

  /** This method starts the execution of the current instance of RRPSS.
   * */
  public void run() {
    while (true) {
      this.selectMainViewOption();
    }
  }

  /** This method reloads the menu from storage.
   * */
  public void reloadMenu() {
    this.menu.setMenuItems(CSVFileUtil.generateMenuItemListFromFile());
    this.promotionalSets = CSVFileUtil.generatePromoMenuItemListFromFile();
  }

  /**
   * This method adds an order to all existing orders.
   *
   * @param order The created order
   */
  public void addOrder(Order order) {
    this.orders.add(order);
  }

  /**
   * This method retrieves all existing orders.
   *
   * @return The list of all existing orders.
   */
  public ArrayList<Order> getOrders() {
    return this.orders;
  }

  /**
   * This method retrieves the instance of the restaurant.
   *
   * @return The restaurant of the current RRPSS instance.
   */
  public Restaurant getRestaurant() {
    return this.restaurant;
  }

  /**
   * This method adds a promotional set to all existing promotional sets.
   *
   * @param pi The newly created promotional set
   */
  public void addPromoSet(PromotionalSet pi) {
    this.promotionalSets.add(pi);
  }

  /**
   * This method generates the string of all promotional sets concatenated.
   *
   * @param withDesc Checks if description is needed.
   * @return The string of all promotional sets concatenated.
   */
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
          itemString = itemString.substring(0, itemString.length() - 1);
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

  /**
   * This method generates the string of all menu items concatenated.
   *
   * @param withDesc Checks if description is needed.
   * @return The string of all menu items concatenated.
   */
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
