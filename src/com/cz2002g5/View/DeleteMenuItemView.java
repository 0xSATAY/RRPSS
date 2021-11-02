package com.cz2002g5.View;

public class DeleteMenuItemView implements View {
  @Override
  public void display() {
    System.out.println("---Deleting item---");
  }

  @Override
  public void displayCustomView(String s) {
    System.out.println(s);
  }

  public void showDeleteItemView(String menu) {
    System.out.println(menu);
    System.out.println("Select the item you wish to delete. To cancel enter 0:");
  }
}
