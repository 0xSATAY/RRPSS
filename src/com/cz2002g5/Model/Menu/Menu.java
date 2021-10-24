package com.cz2002g5.Model.Menu;

import java.util.ArrayList;

public class Menu {
  private ArrayList<MenuItem> menuItems;

  public Menu(ArrayList<MenuItem> menuItems) {
    this.menuItems = menuItems;
  }

  public ArrayList<MenuItem> getMenuItems() {
    return this.menuItems;
  }

  public void setMenuItems(ArrayList<MenuItem> menuItems) {
    this.menuItems = menuItems;
  }
}
