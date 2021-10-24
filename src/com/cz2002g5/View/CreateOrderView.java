package com.cz2002g5.View;

/**
 * The type Create order view.
 */
public class CreateOrderView implements View {
    @Override
    public void display() {
        System.out.println("Create Order");
    }

    @Override
    public void displayCustomView(String s) {
        System.out.println(s);
        System.out.println("Select an item from the menu:");
    }

    /**
     * Show menu.
     *
     * @param menuString the menu string
     */
public void showMenu(String menuString) {
        this.displayCustomView(menuString);
    }

    /**
     * Show employee id view.
     */
public void showEmployeeIDView() {
        System.out.println("Enter your employee ID:");
    }

    /**
     * Show table number view.
     */
public void showTableNumberView() {
        System.out.println("Enter the table number:");
    }

    /**
     * Show num of customers view.
     */
public void showNumOfCustomersView() {
        System.out.println("Enter the number of customers:");
    }
}
