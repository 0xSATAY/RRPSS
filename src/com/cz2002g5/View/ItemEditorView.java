package com.cz2002g5.View;

public class ItemEditorView implements View{
    @Override
    public void display() {
        System.out.println("---Editing Item---\n1. Create item\n2. Update item\n3. Remove item\n4. Cancel\nSelect your action:");
    }

    @Override
    public void displayCustomView(String s) {

    }
}
