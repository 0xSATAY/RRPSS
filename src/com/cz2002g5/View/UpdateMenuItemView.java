package com.cz2002g5.View;

public class UpdateMenuItemView implements View {

  public void showUpdateNameView(String name) {
    System.out.println("New name for " + name + " (or press Enter to keep prev name):");
  }

  public void showUpdateTypeView(String name) {
    System.out.println("New type for " + name + "\n1. MAINS\n2. DRINKS\n3. DESSERT");
  }

  public void showUpdateDescView(String name) {
    System.out.println(
        "New description for " + name + " (or press Enter to keep prev description):");
  }

  public void showUpdatePriceView(String name) {
    System.out.println("New price for " + name + ":");
  }

  @Override
  public void display() {
    System.out.println("---Updating Item---");
  }

  @Override
  public void displayCustomView(String menu) {
    this.display();
    System.out.println(menu);
    System.out.println("Enter the item you wish to edit. To cancel enter 0:");
  }
}
