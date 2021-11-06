package com.cz2002g5.Model.Menu;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/** The PromotionalSet model class for promotional set items. */
public class PromotionalSet extends MenuItem {

  /** The largest possible ID that can be used for a new promotional set*/
  public static final AtomicInteger count = new AtomicInteger(0);
  private final ArrayList<MenuItem> setItems = new ArrayList<>();
  private int id;

  /**
   * Instantiates a new Promotional set.
   *
   * @param name The name of the new promotional set.
   * @param type The type of the new promotional set.
   * @param description The description of the new promotional set.
   * @param price The price of the new promotional set.
   */
  public PromotionalSet(String name, ItemType type, String description, Double price) {
    super(name, type, description, price);
  }

  /**
   * Instantiates a new Promotional set.
   *
   * @param name The name of the new promotional set.
   */
  public PromotionalSet(String name) {
    super(name, ItemType.PROMO, "Promotional Item", 0.0);
    this.id = count.incrementAndGet();
  }

  /**
   * Getter method for ID of the promotional set.
   *
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Adds a new menu item to the promotional set.
   *
   * @param item The new item that is to be added into the promotional set.
   */
  public void addSetItem(MenuItem item) {
    if (!this.setItems.contains(item)) setItems.add(item);
    else System.out.println("Item already exists in the set of items!");
  }

  /**
   * Getter method for the items in the promotional set.
   *
   * @return The set items of the promotional set.
   */
  public ArrayList<MenuItem> getSetItems() {
    return this.setItems;
  }
}
