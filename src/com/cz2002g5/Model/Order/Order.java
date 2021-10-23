package com.cz2002g5.Model.Order;

import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Menu.PromotionalSet;

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
        for (int i=0;i<this.orderItems.size();i++) {
            MenuItem item = this.orderItems.get(i);
            totalCost += item.getPrice();
        }
        for (int i=0;i<this.promotionalSets.size();i++) {
            totalCost += this.promotionalSets.get(i).getPrice();
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
}

