package com.cz2002g5.Controller;

import com.cz2002g5.Model.Menu.Menu;
import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Order.Order;
import com.cz2002g5.Model.Restaurant.Restaurant;
import com.cz2002g5.View.CreateOrderView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderController {

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
                System.out.println(item.getName() + " - " + NumberFormat.getCurrencyInstance().format(item.getPrice()));
            }
            System.out.println("Total: " + NumberFormat.getCurrencyInstance().format(order.getTotalPrice()) + "\n");
        }
    }

    public void createOrder(RRPSS pos) {
        Scanner sc = new Scanner(System.in);
        CreateOrderView cov = new CreateOrderView();
        RRPSS.updateView(pos,cov);
        int employeeID;
        cov.employeeIDView();
        while (!sc.hasNextInt()) {
            System.out.println("You have inputted an invalid ID!\nEnter your employee ID:");
            sc.next();
        }
        employeeID = sc.nextInt();
        int tableNumber;
        cov.tableNumberView();
        while (!sc.hasNextInt()) {
            System.out.println("You have inputted an invalid table number!\nEnter the table number:");
            sc.next();
        }
        tableNumber = sc.nextInt();
        if (tableNumber<=0 || tableNumber>Restaurant.TOTAL_TABLES) {
            System.out.println("Please enter a valid table number (1-20)");
            return;
        }
        if (pos.getRestaurant().getTables().get(tableNumber-1).isOccupied()) {
            System.out.println("Table " + tableNumber + " is already occupied");
            return;
        }
        int numOfCustomers;
        cov.numOfCustomersView();
        while (!sc.hasNextInt()) {
            System.out.println("You have inputted an invalid number of customers!\nEnter the the number of customers:");
            sc.next();
        }
        numOfCustomers = sc.nextInt();
        if (numOfCustomers<=0 || numOfCustomers>10) {
            System.out.println("Please enter a valid number of customers.");
            return;
        }
        int input;
        RRPSS.showView(pos);
        ((CreateOrderView) RRPSS.getCurrentView(pos)).showMenu(pos.generateMenuString());
        while (!sc.hasNextInt()) {
            System.out.println("You have inputted a non-numerical value!\nSelect an item from the menu:");
            sc.next();
        }
        input = sc.nextInt()-1;
        Menu menu = RRPSS.getMenu(pos);
        Order order = new Order(employeeID,tableNumber,numOfCustomers);
        while (true) {
            if (input>=0 && input<menu.getMenuItems().size()) {
                order.addItem(menu.getMenuItems().get(input));
                System.out.println(menu.getMenuItems().get(input).getName() +
                        " has been added. To enter another item, enter its corresponding number. If you are done, enter '.'");
                if (sc.hasNextInt()) {
                    input = sc.nextInt()-1;
                } else {
                    System.out.println("Items in your order:");
                    for (int i=0;i<order.getOrderItems().size();i++) {
                        MenuItem item = order.getOrderItems().get(i);
                        System.out.println(i + 1 + ". " + item.getName() + " - " +
                                NumberFormat.getCurrencyInstance().format(item.getPrice()));
                    }
                    pos.addOrder(order);
                    pos.getRestaurant().getTables().get(tableNumber-1).setOccupied(true);
                    System.out.println("Total price of items: " +
                            NumberFormat.getCurrencyInstance().format(order.getTotalPrice()) + "\n");
                    return;
                }
            } else {
                System.out.println("You have entered an invalid option!\nSelect an item from the menu:");
                while (!sc.hasNextInt()) {
                    System.out.println("You have inputted a non-numerical value!\nSelect an item from the menu:");
                    sc.next();
                }
                input = sc.nextInt()-1;
            }
        }
    }
}
