package com.cz2002g5.View;

public class CreateOrderView implements View {
    @Override
    public void show() {
        System.out.println("Create Order");
    }

    @Override
    public void show(String s) {
        System.out.println(s);
        System.out.println("Select an item from the menu:");
    }

    public void showMenu(String menuString) {
        this.show(menuString);
    }

    public void employeeIDView() {
        System.out.println("Enter your employee ID:");
    }

    public void tableNumberView() {
        System.out.println("Enter the table number:");
    }

    public void numOfCustomersView() {
        System.out.println("Enter the number of customers:");
    }
}
