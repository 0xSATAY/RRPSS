package com.cz2002g5.View;

/** Create menu item view. */
public class CreateMenuItemView implements View {

  @Override
  public void display() {
    System.out.println("---Creating Item---");
  }

  @Override
  public void displayCustomView(String s) {
    System.out.println(s);
  }

  /** Show add name view. */
  public void showAddNameView() {
    System.out.println("Name of item:");
  }

  /** Show add type view. */
  public void showAddTypeView() {
    System.out.println("Type of item:\n1. MAINS\n2. DRINKS\n3. DESSERT");
  }

  /** Show add desc view. */
  public void showAddDescView() {
    System.out.println("Description of item:");
  }

  /** Show add price view. */
  public void showAddPriceView() {
    System.out.println("Price of item:");
  }
}
