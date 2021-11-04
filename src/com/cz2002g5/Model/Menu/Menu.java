package com.cz2002g5.Model.Menu;

import java.util.ArrayList;

/**
 * The Menu model that represents the entire menu of the restaurant.
 */
public class Menu {
  private ArrayList<MenuItem> menuItems;

  /**
   * Instantiates a new Menu.
   *
   * @param menuItems The menu items that are to be part of the menu.
   */
public Menu(ArrayList<MenuItem> menuItems) {
    this.menuItems = menuItems;
  }

  /**
   * Retrieves the menu items of the menu.
   *
   * @return A list of all menu items from the menu.
   */
public ArrayList<MenuItem> getMenuItems() {
    return this.menuItems;
  }

  /**
   * Updates the menu items of the menu.
   *
   * @param menuItems The updated menu items that will be replacing the current menu items.
   */
public void setMenuItems(ArrayList<MenuItem> menuItems) {
    this.menuItems = menuItems;
  }
}
