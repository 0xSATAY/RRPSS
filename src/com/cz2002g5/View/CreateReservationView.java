package com.cz2002g5.View;

/** Create reservation view. */
public class CreateReservationView implements View {
  @Override
  public void display() {
    System.out.println("------Creating Reservation------");
  }

  @Override
  public void displayCustomView(String s) {
    System.out.println(s);
  }

  /** Show add name view. */
  public void showAddNameView() {
    System.out.println("Enter the surname of reservee:");
  }

  /** Show add pax view. */
  public void showAddPaxView() {
    System.out.println("Enter the number of people:");
  }

  /** Show add contact number view. */
  public void showAddContactNumberView() {
    System.out.println("Enter the contact number of the reservee:");
  }

  /** Show add date view. */
  public void showAddDateView() {
    System.out.println("Enter the date of the reservation in the format DD/MM/YYYY:");
  }
}
