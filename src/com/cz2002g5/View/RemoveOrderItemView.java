package com.cz2002g5.View;

public class RemoveOrderItemView implements View{

    @Override
    public void show() {
        System.out.println("-----Removing Item from order-----");
        System.out.println("Which order would you like to remove items from? To cancel enter 0");
    }

    @Override
    public void show(String s) {
        System.out.println(s);
    }
}
