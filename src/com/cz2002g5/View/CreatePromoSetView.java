package com.cz2002g5.View;

public class CreatePromoSetView implements View {
    @Override
    public void show() {
        System.out.println("---Creating Promo Set---");
    }

    @Override
    public void show(String s) {

    }

    public void showAddItemView() {
        System.out.println("Select item to be added into the promo set. To cancel enter 0:");
    }

    public void showAddPriceView() {
        System.out.println("Enter price of promo set:");
    }

    public void showAddNameView() {
        System.out.println("Enter the name of your promo set:");
    }
}
