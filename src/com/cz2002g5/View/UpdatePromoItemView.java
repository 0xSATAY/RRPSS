package com.cz2002g5.View;

/**
 * The type Update promo item view.
 */
public class UpdatePromoItemView extends UpdateMenuItemView {
    @Override
    public void display() {
        System.out.println("------Update promo item------");
    }

    @Override
    public void displayCustomView(String s) {
        this.display();
        System.out.println(s);
        System.out.println("Enter the item you wish to edit. To cancel enter 0:");
    }
}
