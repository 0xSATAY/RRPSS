package com.cz2002g5.View;

/** Delete reservation view. */
public class DeleteReservationView implements View {
  @Override
  public void display() {
    System.out.println("------Deleting Reservation------");
  }

  @Override
  public void displayCustomView(String s) {
    System.out.println(s);
  }
}
