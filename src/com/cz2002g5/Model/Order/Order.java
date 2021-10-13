package com.cz2002g5.Model.Order;

import com.cz2002g5.Model.Menu.MenuItem;
import java.util.ArrayList;

public class Order {
    private final int employeeID, numOfCustomers;
    private int tableNumber;
    private final ArrayList<MenuItem> orderItems = new ArrayList<>();

    public Order(int employeeID, int tableNumber, int numOfCustomers) {
        this.employeeID = employeeID;
        this.tableNumber = tableNumber;
        this.numOfCustomers = numOfCustomers;
    }

    public void addItem(MenuItem item) {
        orderItems.add(item);
    }

    public ArrayList<MenuItem> getOrderItems() {
        return this.orderItems;
    }

    public double getTotalPrice() {
        double totalCost = 0;
        for (int i=0;i<orderItems.size();i++) {
            MenuItem item = orderItems.get(i);
            totalCost += item.getPrice();
        }
        return totalCost;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getNumOfCustomers() {
        return numOfCustomers;
    }

    public int getEmployeeID() {
        return employeeID;
    }
}

