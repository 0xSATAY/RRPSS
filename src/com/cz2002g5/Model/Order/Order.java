package com.cz2002g5.Model.Order;

import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Menu.PromotionalSet;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {
    private final int employeeID, numOfCustomers;
    private int tableNumber;
    private final ArrayList<MenuItem> orderItems = new ArrayList<>();
    private final ArrayList<PromotionalSet> promotionalSets = new ArrayList<>();

    public Order(int employeeID, int tableNumber, int numOfCustomers) {
        this.employeeID = employeeID;
        this.tableNumber = tableNumber;
        this.numOfCustomers = numOfCustomers;
    }

    public void addItem(MenuItem item) {
        orderItems.add(item);
    }

    public void removeItem(MenuItem item) {
        orderItems.remove(item);
    }

    public void addPromotionalSets(PromotionalSet ps) {
        this.promotionalSets.add(ps);
    }

    public ArrayList<PromotionalSet> getPromotionalSets() {
        return this.promotionalSets;
    }

    public void removePromotionalSet(PromotionalSet ps) {
        this.promotionalSets.remove(ps);
    }

    public void removeItem(int index) {
        orderItems.remove(index);
    }

    public ArrayList<MenuItem> getOrderItems() {
        return this.orderItems;
    }

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

    public int getTableNumber() {
        return this.tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getNumOfCustomers() {
        return this.numOfCustomers;
    }

    public int getEmployeeID() {
        return this.employeeID;
    }

    public int getTotalOrderSize() {
        return this.orderItems.size() + this.promotionalSets.size();
    }

    public ArrayList<MenuItem> getAllItemOrders() {
        ArrayList<MenuItem> all = new ArrayList<>();
        all.addAll(this.orderItems);
        all.addAll(this.promotionalSets);
        return all;
    }

    public void printInvoice(boolean isMember) {
        ArrayList<MenuItem> all = this.getAllItemOrders();
        System.out.println("--------------0xCAFEBABE  Cafe--------------");
        System.out.println("            21 Lien Ying Chow Dr            ");
        System.out.println("                   #01-01                   ");
        System.out.println("--------------------------------------------");
        System.out.printf( "Table: %01d\n", this.tableNumber+1);
        System.out.printf( "Date: %s\n", LocalDate.now());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        System.out.printf( "Time: %s\n", LocalTime.now().format(dtf));
        System.out.println("--------------------------------------------");
        for (MenuItem item : all) {
            int numSpaces = 44-item.getName().length()-NumberFormat.getCurrencyInstance().format(item.getPrice()).length();
            String repeated = new String(new char[numSpaces]).replace("\0", " ");
            String itemString = item.getName() + repeated + NumberFormat.getCurrencyInstance().format(item.getPrice());
            System.out.println(itemString);
        }
        System.out.println("--------------------------------------------");
        double total = this.getTotalPrice();
        System.out.println("Subtotal: " + NumberFormat.getCurrencyInstance().format(total));
        if (isMember) {
            total *= 0.9;
            System.out.println("10% discount for members: -" + NumberFormat.getCurrencyInstance().format(this.getTotalPrice()-total));
        }
        System.out.println("7% GST: " + NumberFormat.getCurrencyInstance().format(total*0.07));
        System.out.println("TOTAL: " + NumberFormat.getCurrencyInstance().format(total*1.07));
        System.out.println("--------------------------------------------");
    }
}

