package com.cz2002g5.Model.Menu;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Promotional set.
 */
public class PromotionalSet extends MenuItem {
    /**
     * The constant count.
     */
public static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private final ArrayList<MenuItem> setItems = new ArrayList<>();
    private Double price;

    /**
     * Instantiates a new Promotional set.
     *
     * @param name the name
     * @param type the type
     * @param description the description
     * @param price the price
     */
public PromotionalSet(String name, ItemType type, String description, Double price) {
        super(name, type, description, price);
    }

    /**
     * Instantiates a new Promotional set.
     *
     * @param name the name
     */
public PromotionalSet(String name) {
        super(name, null, null, null);
        this.id = count.incrementAndGet();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
public int getId() {
        return id;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Add set item.
     *
     * @param item the item
     */
public void addSetItem(MenuItem item) {
        if (!this.setItems.contains(item)) setItems.add(item);
        else System.out.println("Item already exists in the set of items!");
    }

    /**
     * Gets set items.
     *
     * @return the set items
     */
public ArrayList<MenuItem> getSetItems() {
        return this.setItems;
    }

}
