package com.cz2002g5.View;

/**
 * The type Delete menu item view.
 */
public class DeleteMenuItemView implements View {
  @Override
  public void display() {
    System.out.println("---Deleting item---");
  }

  @Override
  public void displayCustomView(String s) {
    System.out.println(s);
  }

  /**
   * Show delete item view.
   *
   * @param menu the menu
   */
public void showDeleteItemView(String menu) {
    System.out.println(menu);
    System.out.println("Select the item you wish to delete. To cancel enter 0:");
  }
}
