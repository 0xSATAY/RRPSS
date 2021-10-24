package com.cz2002g5.Model.Menu;

import java.util.ArrayList;

/**
 * The type Menu.
 */
public class Menu {
    private ArrayList<MenuItem> menuItems;
    /**
     * Instantiates a new Menu.
     *
     * @param menuItems the menu items
     */
public Menu(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    /**
     * Gets menu items.
     *
     * @return the menu items
     */
public ArrayList<MenuItem> getMenuItems() {
        return this.menuItems;
    }

    /**
     * Sets menu items.
     *
     * @param menuItems the menu items
     */
public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
