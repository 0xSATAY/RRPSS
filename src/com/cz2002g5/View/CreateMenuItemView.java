package com.cz2002g5.View;

public class CreateMenuItemView implements View {

  @Override
  public void display() {
    System.out.println("---Creating Item---");
  }

  @Override
  public void displayCustomView(String s) {
    System.out.println(s);
  }

  public void showAddNameView() {
    System.out.println("Name of item:");
  }

  public void showAddTypeView() {
    System.out.println("Type of item:\n1. MAINS\n2. DRINKS\n3. DESSERT");
  }

  public void showAddDescView() {
    System.out.println("Description of item:");
  }

  public void showAddPriceView() {
    System.out.println("Price of item:");
  }
}
