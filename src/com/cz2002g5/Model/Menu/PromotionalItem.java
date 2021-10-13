package com.cz2002g5.Model.Menu;

import java.util.ArrayList;

public class PromotionalItem extends MenuItem {
    private ArrayList<MenuItem> setItems;
    public PromotionalItem(String name, ItemType type, String description, Double price) {
        super(name, type, description, price);
    }

    public void addSetItem(MenuItem item) {
        if (!this.setItems.contains(item)) setItems.add(item);
        else System.out.println("Item already exists in the set of items!");
    }

    public ArrayList<MenuItem> getSetItems() {
        return this.setItems;
    }
}
