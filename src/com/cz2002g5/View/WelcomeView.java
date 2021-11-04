package com.cz2002g5.View;

/** Welcome view. */
public class WelcomeView implements View {
  public void display() {
    System.out.println(
        "Welcome to the Restaurant Reservation and Point of Sales System!\n"
            + "What can I do for you today?");
    System.out.println(
        "1. Create/Update/Remove menu item\n"
            + "2. Create/Update/Remove promotion\n"
            + "3. Create order\n"
            + "4. View orders\n"
            + "5. Add/Remove order item/s to/from order\n"
            + "6. Create reservation booking\n"
            + "7. Check/Remove reservation booking\n"
            + "8. Check table availability\n"
            + "9. Print order invoice\n"
            + "10. Print sale revenue report by period\n"
            + "11. Print all menu items");
  }

  @Override
  public void displayCustomView(String s) {
    System.out.println(s);
  }
}
