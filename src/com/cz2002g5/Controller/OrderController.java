package com.cz2002g5.Controller;

import com.cz2002g5.Model.Menu.Menu;
import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Menu.PromotionalSet;
import com.cz2002g5.Model.Order.Order;
import com.cz2002g5.Model.Restaurant.Restaurant;
import com.cz2002g5.Model.Restaurant.Table;
import com.cz2002g5.View.CreateOrderView;
import com.cz2002g5.View.OrderEditorView;
import com.cz2002g5.View.RemoveOrderItemView;
import com.cz2002g5.View.UpdateOrderItemView;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/** The controller class for all order related operations. */
public class OrderController {
  /**
   * Processes user actions.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  public void selectAction(RRPSS pos) {
    while (true) {
      Scanner sc = new Scanner(System.in);
      RRPSS.updateView(pos, new OrderEditorView());
      RRPSS.showView(pos);
      while (!sc.hasNextInt()) {
        System.out.println("You have inputted a non-numerical value!\nSelect your action:");
        sc.next();
      }
      int action = sc.nextInt();
      switch (action) {
        case 1:
          this.addItemToOrder(pos);
          return;
        case 2:
          this.removeItemFromOrder(pos);
          return;
        default:
          System.out.println("You have selected an invalid action!");
      }
    }
  }

  /**
   * Displays all the existing orders to the view.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  public void viewAllOrders(RRPSS pos) {
    ArrayList<Order> orders = pos.getOrders();
    if (orders.size() == 0) {
      System.out.println("There are no orders.");
      return;
    }
    for (Order order : orders) {
      System.out.println("--------------------------");
      System.out.println("Employee ID: " + order.getEmployeeID());
      System.out.println("Table Number: " + order.getTableNumber());
      System.out.println("Number of customers: " + order.getNumOfCustomers());
      for (MenuItem item : order.getOrderItems()) {
        System.out.println(
            item.getName() + " - " + NumberFormat.getCurrencyInstance().format(item.getPrice()));
      }
      for (PromotionalSet ps : order.getPromotionalSets()) {
        System.out.println(
            ps.getName() + " - " + NumberFormat.getCurrencyInstance().format(ps.getPrice()));
      }
      System.out.println(
          "Total: " + NumberFormat.getCurrencyInstance().format(order.getTotalPrice()) + "\n");
    }
  }

  /**
   * Creates a new order.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  public void createOrder(RRPSS pos) {
    Scanner sc = new Scanner(System.in);
    CreateOrderView cov = new CreateOrderView();
    RRPSS.updateView(pos, cov);
    int employeeID;
    cov.showEmployeeIDView();
    while (!sc.hasNextInt()) {
      System.out.println("You have inputted an invalid ID!\nEnter your employee ID:");
      sc.next();
    }
    employeeID = sc.nextInt();
    int tableNumber;
    cov.showTableNumberView();
    while (!sc.hasNextInt()) {
      System.out.println("You have inputted an invalid table number!\nEnter the table number:");
      sc.next();
    }
    tableNumber = sc.nextInt();
    if (tableNumber <= 0 || tableNumber > Restaurant.TOTAL_TABLES) {
      System.out.println("Please enter a valid table number (1-20)");
      return;
    }
    if (pos.getRestaurant().getTables().get(tableNumber - 1).isOccupied()) {
      System.out.println("Table " + tableNumber + " is already occupied");
      return;
    }
    int numOfCustomers;
    cov.showNumOfCustomersView();
    while (!sc.hasNextInt()) {
      System.out.println(
          "You have inputted an invalid number of customers!\nEnter the the number of customers:");
      sc.next();
    }
    numOfCustomers = sc.nextInt();
    if (numOfCustomers <= 0 || numOfCustomers > 10) {
      System.out.println("Please enter a valid number of customers.");
      return;
    }
    int input;
    RRPSS.showView(pos);
    ((CreateOrderView) RRPSS.getCurrentView(pos)).showMenu(pos.generateMenuString(false));
    System.out.println("If no item is selected, enter 0:");
    while (!sc.hasNextInt()) {
      System.out.println("You have inputted a non-numerical value!\nSelect an item from the menu:");
      sc.next();
    }
    input = sc.nextInt() - 1;
    Menu menu = RRPSS.getMenu(pos);
    Order order = new Order(employeeID, tableNumber, numOfCustomers);
    while (true) {
      if (input >= 0 && input < menu.getMenuItems().size()) {
        order.addItem(menu.getMenuItems().get(input));
        System.out.println(
            menu.getMenuItems().get(input).getName()
                + " has been added. To enter another item, enter its corresponding number. If you are done, enter '.'");
        if (sc.hasNextInt()) {
          input = sc.nextInt() - 1;
        } else {
          break;
        }
      } else if (input == -1) {
        break;
      } else {
        System.out.println("You have entered an invalid option!\nSelect an item from the menu:");
        while (!sc.hasNextInt()) {
          System.out.println(
              "You have inputted a non-numerical value!\nSelect an item from the menu:");
          sc.next();
        }
        input = sc.nextInt() - 1;
      }
    }
    System.out.println("---------------Promo sets---------------");
    ((CreateOrderView) RRPSS.getCurrentView(pos)).showMenu(pos.generatePromoMenuString(false));
    System.out.println("If no item is selected, enter 0:");
    while (!sc.hasNextInt()) {
      System.out.println("You have inputted a non-numerical value!\nSelect an item from the menu:");
      sc.next();
    }
    input = sc.nextInt() - 1;
    ArrayList<PromotionalSet> promotionalSets = RRPSS.getPromotionalSets(pos);
    boolean done = input == -1;
    if (done) {
      System.out.println("Items in your order:");
      int counter = 0;
      for (int i = 0; i < order.getOrderItems().size(); i++) {
        MenuItem item = order.getOrderItems().get(i);
        System.out.println(
            counter
                + 1
                + ". "
                + item.getName()
                + " - "
                + NumberFormat.getCurrencyInstance().format(item.getPrice()));
        counter++;
      }
      for (int i = 0; i < order.getPromotionalSets().size(); i++) {
        PromotionalSet promotionalSet = order.getPromotionalSets().get(i);
        System.out.println(
            counter
                + 1
                + ". "
                + promotionalSet.getName()
                + " - "
                + NumberFormat.getCurrencyInstance().format(promotionalSet.getPrice()));
        counter++;
      }
      if (order.getAllItemOrders().size() == 0) return;
      pos.addOrder(order);
      pos.getRestaurant().getTables().get(tableNumber - 1).setOccupied(true);
      System.out.println(
          "Total price of items: "
              + NumberFormat.getCurrencyInstance().format(order.getTotalPrice())
              + "\n");
      return;
    }
    while (true) {
      if (input >= 0 && input < promotionalSets.size()) {
        order.addPromotionalSets(promotionalSets.get(input));
        System.out.println(
            promotionalSets.get(input).getName()
                + " has been added. To enter another item, enter its corresponding number. If you are done, enter '.'");
        if (sc.hasNextInt()) {
          input = sc.nextInt() - 1;
        } else {
          System.out.println("Items in your order:");
          int counter = 0;
          for (int i = 0; i < order.getOrderItems().size(); i++) {
            MenuItem item = order.getOrderItems().get(i);
            System.out.println(
                counter
                    + 1
                    + ". "
                    + item.getName()
                    + " - "
                    + NumberFormat.getCurrencyInstance().format(item.getPrice()));
            counter++;
          }
          for (int i = 0; i < order.getPromotionalSets().size(); i++) {
            PromotionalSet promotionalSet = order.getPromotionalSets().get(i);
            System.out.println(
                counter
                    + 1
                    + ". "
                    + promotionalSet.getName()
                    + " - "
                    + NumberFormat.getCurrencyInstance().format(promotionalSet.getPrice()));
            counter++;
          }
          pos.addOrder(order);
          pos.getRestaurant().getTables().get(tableNumber - 1).setOccupied(true);
          System.out.println(
              "Total price of items: "
                  + NumberFormat.getCurrencyInstance().format(order.getTotalPrice())
                  + "\n");
          return;
        }
      } else {
        System.out.println("You have entered an invalid option!\nSelect an item from the menu:");
        while (!sc.hasNextInt()) {
          System.out.println(
              "You have inputted a non-numerical value!\nSelect an item from the menu:");
          sc.next();
        }
        input = sc.nextInt() - 1;
      }
    }
  }

  /**
   * Adds an item to an existing order.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  public void addItemToOrder(RRPSS pos) {
    Scanner sc = new Scanner(System.in);
    UpdateOrderItemView uoiv = new UpdateOrderItemView();
    RRPSS.updateView(pos, uoiv);
    RRPSS.showView(pos);
    int counter = 1;
    for (Order order : pos.getOrders()) {
      System.out.println("ORDER #" + counter + "-------------");
      System.out.println("Employee ID: " + order.getEmployeeID());
      System.out.println("Table Number: " + order.getTableNumber());
      System.out.println("Number of customers: " + order.getNumOfCustomers());
      for (MenuItem item : order.getOrderItems()) {
        System.out.println(
            item.getName() + " - " + NumberFormat.getCurrencyInstance().format(item.getPrice()));
      }
      System.out.println(
          "Total: " + NumberFormat.getCurrencyInstance().format(order.getTotalPrice()) + "\n");
      counter++;
    }
    if (pos.getOrders().size() == 0) {
      return;
    }
    System.out.println("Select the order you wish to add item to:");
    while (!sc.hasNextInt()) {
      System.out.println(
          "You have entered an invalid option!\nSelect the order you wish to add item to:");
      sc.next();
    }
    int orderSelection = sc.nextInt();
    if (orderSelection == 0) {
      return;
    }
    if (orderSelection < 1 || orderSelection > pos.getOrders().size()) {
      System.out.println("You have entered an invalid order!");
      return;
    }
    orderSelection--;
    Order orderSelected = pos.getOrders().get(orderSelection);
    System.out.println("Add item to order #" + (orderSelection + 1));
    ArrayList<MenuItem> menuItems = RRPSS.getMenu(pos).getMenuItems();
    ArrayList<PromotionalSet> promotionalSets = RRPSS.getPromotionalSets(pos);
    counter = 1;
    for (MenuItem item : menuItems) {
      System.out.println(
          counter
              + ": "
              + item.getName()
              + " - "
              + NumberFormat.getCurrencyInstance().format(item.getPrice()));
      counter++;
    }
    for (PromotionalSet ps : promotionalSets) {
      System.out.println(
          counter
              + ": "
              + ps.getName()
              + " - "
              + NumberFormat.getCurrencyInstance().format(ps.getPrice()));
      counter++;
    }
    System.out.println("Select the item you wish to add:");
    while (!sc.hasNextInt()) {
      System.out.println("You have entered an invalid option!\nSelect the item you wish to add:");
      sc.next();
    }
    int itemSelection = sc.nextInt();
    if (itemSelection < 1 || itemSelection > menuItems.size() + promotionalSets.size()) {
      System.out.println("You have entered an invalid item!");
      return;
    }
    itemSelection--;
    if (itemSelection >= menuItems.size()) {
      PromotionalSet psSelected = promotionalSets.get(itemSelection - menuItems.size());
      orderSelected.addPromotionalSets(psSelected);
      System.out.println("You have added " + psSelected.getName());
    } else {
      MenuItem menuItem = menuItems.get(itemSelection);
      orderSelected.addItem(menuItem);
      System.out.println("You have added " + menuItem.getName());
    }
    System.out.println("ORDER #" + (orderSelection + 1) + "-------------");
    for (MenuItem item : orderSelected.getAllItemOrders()) {
      System.out.println(
          item.getName() + " - " + NumberFormat.getCurrencyInstance().format(item.getPrice()));
    }
    System.out.println(
        "Total: "
            + NumberFormat.getCurrencyInstance().format(orderSelected.getTotalPrice())
            + "\n");
  }

  /**
   * Removes an item from an existing order.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  public void removeItemFromOrder(RRPSS pos) {
    Scanner sc = new Scanner(System.in);
    RemoveOrderItemView roiv = new RemoveOrderItemView();
    RRPSS.updateView(pos, roiv);
    RRPSS.showView(pos);
    int counter = 1;
    for (Order order : pos.getOrders()) {
      System.out.println("ORDER #" + counter + "-------------");
      System.out.println("Employee ID: " + order.getEmployeeID());
      System.out.println("Table Number: " + order.getTableNumber());
      System.out.println("Number of customers: " + order.getNumOfCustomers());
      for (MenuItem item : order.getOrderItems()) {
        System.out.println(
            item.getName() + " - " + NumberFormat.getCurrencyInstance().format(item.getPrice()));
        counter++;
      }
      for (PromotionalSet ps : order.getPromotionalSets()) {
        System.out.println(
            ps.getName() + " - " + NumberFormat.getCurrencyInstance().format(ps.getPrice()));
        counter++;
      }
      System.out.println(
          "Total: " + NumberFormat.getCurrencyInstance().format(order.getTotalPrice()) + "\n");
      counter = 1;
    }
    if (pos.getOrders().size() == 0) {
      return;
    }
    System.out.println("Select the order you wish to remove item from:");
    while (!sc.hasNextInt()) {
      System.out.println(
          "You have entered an invalid option!\nSelect the order you wish to remove item from:");
      sc.next();
    }
    int orderSelection = sc.nextInt();
    if (orderSelection == 0) {
      return;
    }
    if (orderSelection < 1 || orderSelection > pos.getOrders().size()) {
      System.out.println("You have entered an invalid order!");
      return;
    }
    orderSelection--;
    Order orderSelected = pos.getOrders().get(orderSelection);
    System.out.println("Remove item from order #" + (orderSelection + 1));
    for (MenuItem item : orderSelected.getOrderItems()) {
      System.out.println(
          counter
              + ": "
              + item.getName()
              + " - "
              + NumberFormat.getCurrencyInstance().format(item.getPrice()));
      counter++;
    }
    for (PromotionalSet ps : orderSelected.getPromotionalSets()) {
      System.out.println(
          counter
              + ": "
              + ps.getName()
              + " - "
              + NumberFormat.getCurrencyInstance().format(ps.getPrice()));
      counter++;
    }
    System.out.println("Select the item you wish to remove:");
    while (!sc.hasNextInt()) {
      System.out.println(
          "You have entered an invalid option!\nSelect the item you wish to remove:");
      sc.next();
    }
    int itemSelection = sc.nextInt();
    if (itemSelection < 1 || itemSelection > orderSelected.getTotalOrderSize()) {
      System.out.println("You have entered an invalid item!");
      return;
    }
    itemSelection--;
    MenuItem menuItem = orderSelected.getAllItemOrders().get(itemSelection);
    if (menuItem instanceof PromotionalSet) {
      orderSelected.removePromotionalSet((PromotionalSet) menuItem);
    } else {
      orderSelected.removeItem(menuItem);
    }
    System.out.println("You have removed " + menuItem.getName());
    if (orderSelected.getAllItemOrders().size() == 0) {
      pos.getOrders().remove(orderSelected);
      pos.getRestaurant().getTables().get(orderSelected.getTableNumber() - 1).setOccupied(false);
      return;
    }
    System.out.println("ORDER #" + (orderSelection + 1) + "-------------");
    for (MenuItem item : orderSelected.getAllItemOrders()) {
      System.out.println(
          item.getName() + " - " + NumberFormat.getCurrencyInstance().format(item.getPrice()));
    }
    System.out.println(
        "Total: "
            + NumberFormat.getCurrencyInstance().format(orderSelected.getTotalPrice())
            + "\n");
  }

  /**
   * Generates the invoice when customer pays the bill. Customer's table is set free and
   * all of the ordered items are added to the revenue report.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  public void generateInvoice(RRPSS pos) {
    Scanner sc = new Scanner(System.in);
    int counter = 1;
    for (Order order : pos.getOrders()) {
      System.out.println("ORDER #" + counter + "-------------");
      System.out.println("Employee ID: " + order.getEmployeeID());
      System.out.println("Table Number: " + order.getTableNumber());
      System.out.println("Number of customers: " + order.getNumOfCustomers());
      for (MenuItem item : order.getOrderItems()) {
        System.out.println(
            item.getName() + " - " + NumberFormat.getCurrencyInstance().format(item.getPrice()));
      }
      for (PromotionalSet ps : order.getPromotionalSets()) {
        System.out.println(
            ps.getName() + " - " + NumberFormat.getCurrencyInstance().format(ps.getPrice()));
      }
      System.out.println(
          "Total: " + NumberFormat.getCurrencyInstance().format(order.getTotalPrice()) + "\n");
      counter++;
    }
    if (pos.getOrders().size() == 0) {
      return;
    }
    System.out.println("Select the order you are billing. Enter 0 to cancel:");
    while (!sc.hasNextInt()) {
      System.out.println(
          "You have entered an invalid option!\nSelect the order you are billing. Enter 0 to cancel:");
      sc.next();
    }
    int orderSelection = sc.nextInt();
    if (orderSelection == 0) {
      return;
    }
    if (orderSelection < 1 || orderSelection > pos.getOrders().size()) {
      System.out.println("You have entered an invalid order!");
      return;
    }
    orderSelection--;
    Order orderSelected = pos.getOrders().get(orderSelection);
    System.out.println("Is customer a member? (Y/N):");
    String isMemberString = sc.next();
    System.out.println(isMemberString);
    boolean isMember = isMemberString.toUpperCase(Locale.ROOT).equals("Y");
    orderSelected.printInvoice(isMember);
    Table billingTable = pos.getRestaurant().getTables().get(orderSelected.getTableNumber() - 1);
    billingTable.setOccupied(false);
    System.out.println("Table " + billingTable.getTableID()+1 + " is vacated.");
    pos.getOrders().remove(orderSelected);
    RevenueReportController rrc = new RevenueReportController();
    rrc.addOrderItemsToRevenueReport(orderSelected, LocalDate.now());
  }
}
