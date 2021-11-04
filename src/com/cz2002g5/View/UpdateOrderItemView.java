package com.cz2002g5.View;

/** Update order item view. */
public class UpdateOrderItemView implements View {
  @Override
  public void display() {
    System.out.println("-----Adding Item to order-----");
    System.out.println("Which order would you like to add items to? To cancel enter 0");
  }

  @Override
  public void displayCustomView(String s) {
    System.out.println(s);
  }
}
