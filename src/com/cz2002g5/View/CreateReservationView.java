package com.cz2002g5.View;

public class CreateReservationView implements View {
  @Override
  public void display() {
    System.out.println("------Creating Reservation------");
  }

  @Override
  public void displayCustomView(String s) {}

  public void showAddNameView() {
    System.out.println("Enter the surname of reservee:");
  }

  public void showAddPaxView() {
    System.out.println("Enter the number of people:");
  }

  public void showAddContactNumberView() {
    System.out.println("Enter the contact number of the reservee:");
  }

  public void showAddDateView() {
    System.out.println("Enter the date of the reservation in the format DD/MM/YYYY:");
  }
}
