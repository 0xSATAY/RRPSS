package com.cz2002g5.View;

public class OrderEditorView implements View{
    @Override
    public void display() {
        System.out.println("What would you like to do?\n1. Add item to order\n2. Remove item from order\nTo cancel enter 0");
    }

    @Override
    public void displayCustomView(String s) {

    }
}
