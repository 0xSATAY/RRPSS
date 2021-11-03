package com.cz2002g5.Model.Order;

import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Menu.PromotionalSet;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The type Order.
 */
public class Order {
  private final int employeeID, numOfCustomers;
  private int tableNumber;
  private final ArrayList<MenuItem> orderItems = new ArrayList<>();
  private final ArrayList<PromotionalSet> promotionalSets = new ArrayList<>();

  /**
   * Instantiates a new Order.
   *
   * @param employeeID the employee id
   * @param tableNumber the table number
   * @param numOfCustomers the num of customers
   */
public Order(int employeeID, int tableNumber, int numOfCustomers) {
    this.employeeID = employeeID;
    this.tableNumber = tableNumber;
    this.numOfCustomers = numOfCustomers;
  }

  /**
   * Add item.
   *
   * @param item the item
   */
public void addItem(MenuItem item) {
    orderItems.add(item);
  }

  /**
   * Remove item.
   *
   * @param item the item
   */
public void removeItem(MenuItem item) {
    orderItems.remove(item);
  }

  /**
   * Add promotional sets.
   *
   * @param ps the ps
   */
public void addPromotionalSets(PromotionalSet ps) {
    this.promotionalSets.add(ps);
  }

  /**
   * Gets promotional sets.
   *
   * @return the promotional sets
   */
public ArrayList<PromotionalSet> getPromotionalSets() {
    return this.promotionalSets;
  }

  /**
   * Remove promotional set.
   *
   * @param ps the ps
   */
public void removePromotionalSet(PromotionalSet ps) {
    this.promotionalSets.remove(ps);
  }

  /**
   * Remove item.
   *
   * @param index the index
   */
public void removeItem(int index) {
    orderItems.remove(index);
  }

  /**
   * Gets order items.
   *
   * @return the order items
   */
public ArrayList<MenuItem> getOrderItems() {
    return this.orderItems;
  }

  /**
   * Gets total price.
   *
   * @return the total price
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
   * Gets table number.
   *
   * @return the table number
   */
public int getTableNumber() {
    return this.tableNumber;
  }

  /**
   * Sets table number.
   *
   * @param tableNumber the table number
   */
public void setTableNumber(int tableNumber) {
    this.tableNumber = tableNumber;
  }

  /**
   * Gets num of customers.
   *
   * @return the num of customers
   */
public int getNumOfCustomers() {
    return this.numOfCustomers;
  }

  /**
   * Gets employee id.
   *
   * @return the employee id
   */
public int getEmployeeID() {
    return this.employeeID;
  }

  /**
   * Gets total order size.
   *
   * @return the total order size
   */
public int getTotalOrderSize() {
    return this.orderItems.size() + this.promotionalSets.size();
  }

  /**
   * Gets all item orders.
   *
   * @return the all item orders
   */
public ArrayList<MenuItem> getAllItemOrders() {
    ArrayList<MenuItem> all = new ArrayList<>();
    all.addAll(this.orderItems);
    all.addAll(this.promotionalSets);
    return all;
  }

  /**
   * Print invoice.
   *
   * @param isMember the is member
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
    System.out.println("7% GST: " + NumberFormat.getCurrencyInstance().format(total * 0.07));
    System.out.println("TOTAL: " + NumberFormat.getCurrencyInstance().format(total * 1.07));
    System.out.println("--------------------------------------------");
  }
}
