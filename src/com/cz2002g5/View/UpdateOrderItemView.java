package com.cz2002g5.View;

public class UpdateOrderItemView implements View {
    @Override
    public void show() {
        System.out.println("-----Adding Item to order-----");
        System.out.println("Which order would you like to add items to? To cancel enter 0");
    }

    @Override
    public void show(String s) {

    }
}
