package com.cz2002g5.View;

public class DeletePromoItemView implements View {
  @Override
  public void display() {
    System.out.println("-------Delete Promo Item-------");
  }

  @Override
  public void displayCustomView(String s) {
    System.out.println(s);
    System.out.println("Select the item you wish to delete. To cancel enter 0:");
  }
}
