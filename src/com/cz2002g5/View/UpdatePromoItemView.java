package com.cz2002g5.View;

public class UpdatePromoItemView extends UpdateMenuItemView {
    @Override
    public void show() {
        System.out.println("------Update promo item------");
    }

    @Override
    public void show(String s) {
        this.show();
        System.out.println(s);
        System.out.println("Enter the item you wish to edit. To cancel enter 0:");
    }
}
