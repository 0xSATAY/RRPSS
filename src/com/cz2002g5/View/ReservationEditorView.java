package com.cz2002g5.View;

public class ReservationEditorView implements View {
  @Override
  public void display() {
    System.out.println(
        "------Reservation Editor------\n1. Check all reservations\n2. Remove reservation\n3. Cancel\nSelect your option:");
  }

  @Override
  public void displayCustomView(String s) {}
}
