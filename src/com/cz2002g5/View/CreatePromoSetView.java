package com.cz2002g5.View;

/**
 * The type Create promo set view.
 */
public class CreatePromoSetView implements View {
  @Override
  public void display() {
    System.out.println("---Creating Promo Set---");
  }

  @Override
  public void displayCustomView(String s) {
    System.out.println(s);
  }

  /**
   * Show add item view.
   */
public void showAddItemView() {
    System.out.println("Select item to be added into the promo set. To cancel enter 0:");
  }

  /**
   * Show add price view.
   */
public void showAddPriceView() {
    System.out.println("Enter price of promo set:");
  }

  /**
   * Show add name view.
   */
public void showAddNameView() {
    System.out.println("Enter the name of your promo set:");
  }
}
