package com.cz2002g5.View;

/**
 * The type Menu items view.
 */
public class MenuItemsView implements View{
    @Override
    public void display() {
        System.out.println("Menu");
    }

    public void displayCustomView(String generatedMenu) {
        System.out.println(generatedMenu);
    }
}
