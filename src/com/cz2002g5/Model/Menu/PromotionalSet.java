package com.cz2002g5.Model.Menu;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class PromotionalSet extends MenuItem {
    public static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private ArrayList<MenuItem> setItems = new ArrayList<>();
    private String name;
    private Double price;

    public PromotionalSet(String name, ItemType type, String description, Double price) {
        super(name, type, description, price);
    }

    public PromotionalSet(String name) {
        super(name, null, null, null);
        this.name = name;
        this.id = this.count.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void addSetItem(MenuItem item) {
        if (!this.setItems.contains(item)) setItems.add(item);
        else System.out.println("Item already exists in the set of items!");
    }

    public ArrayList<MenuItem> getSetItems() {
        return this.setItems;
    }

}
