package com.cz2002g5.Model.Order;

import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Menu.PromotionalSet;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/** The Order model for customer orders. */
public class Order {
  private final int employeeID, numOfCustomers;
  private final ArrayList<MenuItem> orderItems = new ArrayList<>();
  private final ArrayList<PromotionalSet> promotionalSets = new ArrayList<>();
  private final int tableNumber;

  /**
   * Instantiates a new Order.
   *
   * @param employeeID The employee ID of the employee that took the order.
   * @param tableNumber The table number of the customer.
   * @param numOfCustomers The number of customers.
   */
  public Order(int employeeID, int tableNumber, int numOfCustomers) {
    this.employeeID = employeeID;
    this.tableNumber = tableNumber;
    this.numOfCustomers = numOfCustomers;
  }

  /**
   * Adds a new menu item to the order.
   *
   * @param item The new item that is to be added to the order.
   */
  public void addItem(MenuItem item) {
    orderItems.add(item);
  }

  /**
   * Removes a menu item from the order.
   *
   * @param item The item that is to be removed from the order.
   */
  public void removeItem(MenuItem item) {
    orderItems.remove(item);
  }

  /**
   * Adds a new promotional set to the order.
   *
   * @param ps The new promotional set that is the be added to the order.
   */
  public void addPromotionalSets(PromotionalSet ps) {
    this.promotionalSets.add(ps);
  }

  /**
   * Getter method for retrieving all promotional sets in the order.
   *
   * @return The promotional sets in the order.
   */
  public ArrayList<PromotionalSet> getPromotionalSets() {
    return this.promotionalSets;
  }

  /**
   * Removes a promotional set from the order.
   *
   * @param ps The promotional set that is to be removed from the order.
   */
  public void removePromotionalSet(PromotionalSet ps) {
    this.promotionalSets.remove(ps);
  }

  /**
   * Removes a menu item from the order.
   *
   * @param index The index of the menu item in the order that is to be removed.
   */
  public void removeItem(int index) {
    orderItems.remove(index);
  }

  /**
   * Getter method that retrieves all menu items in the order.
   *
   * @return All order items in the order.
   */
  public ArrayList<MenuItem> getOrderItems() {
    return this.orderItems;
  }

  /**
   * Getter method to retrieve total price of the order.
   *
   * @return The total price of the order.
   */
  public double getTotalPrice() {
    double totalCost = 0;
    for (MenuItem item : this.orderItems) {
      totalCost += item.getPrice();
    }
    for (PromotionalSet promotionalSet : this.promotionalSets) {
      totalCost += promotionalSet.getPrice();
    }
    return totalCost;
  }

  /**
   * Getter method that retrieves the table number that the order belongs to.
   *
   * @return The table number that the order belongs to.
   */
  public int getTableNumber() {
    return this.tableNumber;
  }

  /**
   * Getter method to retrieve the number of customers of a table for the order.
   *
   * @return the num of customers of a table for the order.
   */
  public int getNumOfCustomers() {
    return this.numOfCustomers;
  }

  /**
   * Getter method that retrieves the employee ID that created this order.
   *
   * @return the employee ID that created this order.
   */
  public int getEmployeeID() {
    return this.employeeID;
  }

  /**
   * Getter method to retrieve the total size of the order. (Total size of ala carte menu items and
   * promotional set items)
   *
   * @return the total order size.
   */
  public int getTotalOrderSize() {
    return this.orderItems.size() + this.promotionalSets.size();
  }

  /**
   * Getter method that retrieves all the items of the order. (Both ala carte and promotional set
   * items)
   *
   * @return All item orders.
   */
  public ArrayList<MenuItem> getAllItemOrders() {
    ArrayList<MenuItem> all = new ArrayList<>();
    all.addAll(this.orderItems);
    all.addAll(this.promotionalSets);
    return all;
  }

  /**
   * Prints the invoice of the menu.
   *
   * @param isMember A flag to determine if the paying customer is a member of the restaurant.
   */
  public void printInvoice(boolean isMember) {
    ArrayList<MenuItem> all = this.getAllItemOrders();
    System.out.println("--------------0xCAFEBABE  Cafe--------------");
    System.out.println("            21 Lien Ying Chow Dr            ");
    System.out.println("                   #01-01                   ");
    System.out.println("--------------------------------------------");
    System.out.printf("Table: %01d\n", this.tableNumber);
    System.out.printf("Date: %s\n", LocalDate.now());
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
    System.out.printf("Time: %s\n", LocalTime.now().format(dtf));
    System.out.println("--------------------------------------------");
    for (MenuItem item : all) {
      int numSpaces =
          44
              - item.getName().length()
              - NumberFormat.getCurrencyInstance().format(item.getPrice()).length();
      String repeated = new String(new char[numSpaces]).replace("\0", " ");
      String itemString =
          item.getName() + repeated + NumberFormat.getCurrencyInstance().format(item.getPrice());
      System.out.println(itemString);
    }
    System.out.println("--------------------------------------------");
    double total = this.getTotalPrice();
    System.out.println("Subtotal: " + NumberFormat.getCurrencyInstance().format(total));
    if (isMember) {
      total *= 0.9;
      System.out.println(
          "10% discount for members: -"
              + NumberFormat.getCurrencyInstance().format(this.getTotalPrice() - total));
    }
    System.out.println("10% Service charge: " + NumberFormat.getCurrencyInstance().format(total * 0.1));
    System.out.println("7% GST: " + NumberFormat.getCurrencyInstance().format(total * 0.07));
    System.out.println("TOTAL: " + NumberFormat.getCurrencyInstance().format(total * 1.1 * 1.07));
    System.out.println("--------------------------------------------");
  }
}
