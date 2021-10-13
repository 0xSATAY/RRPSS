package com.cz2002g5.View;

import com.cz2002g5.Model.Menu.ItemType;

public class UpdateItemView implements View{
    public void updateNameView(String name) {
        System.out.println("New name for " + name + " (or press Enter to keep prev name):");
    }

    public void updateTypeView(String name) {
        System.out.println("New type for " + name + "\n1. MAINS\n2. DRINKS\n3. DESSERT");
    }

    public void updateDescView(String name) {
        System.out.println("New description for " + name + " (or press Enter to keep prev description):");
    }

    public void updatePriceView(String name) {
        System.out.println("New price for " + name + ":");
    }

    @Override
    public void show() {
        System.out.println("---Updating Item---");
    }

    @Override
    public void show(String menu) {
        this.show();
        System.out.println(menu);
        System.out.println("Enter the item you wish to edit:");
    }
}
