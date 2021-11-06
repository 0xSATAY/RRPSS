package com.cz2002g5.Model.Menu;

/** The MenuItem model for menu items. */
public class MenuItem {
  private final String description;
  private final ItemType type;
  private String name;
  private Double price;
  private int itemID;

  /**
   * Instantiates a new Menu item.
   *
   * @param name Name of the menu item.
   * @param type Type of course of the menu item.
   * @param description Description of the menu item.
   * @param price Price of the menu item.
   */
  public MenuItem(String name, ItemType type, String description, Double price) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.type = type;
  }

  /**
   * Getter method for ID of the menu item.
   *
   * @return the item id
   */
  public int getItemID() {
    return itemID;
  }

  /**
   * Setter method for ID of the menu item.
   *
   * @param itemID ID of the menu item.
   */
  public void setItemID(int itemID) {
    this.itemID = itemID;
  }

  /**
   * Getter method for name of the menu item.
   *
   * @return Name of the menu item.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Setter method for the name of the menu item.
   *
   * @param name The new name of the menu item.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter method for description of the menu item.
   *
   * @return The description of the menu item.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Getter method for price of the menu item.
   *
   * @return The price of the menu item.
   */
  public Double getPrice() {
    return this.price;
  }

  /**
   * Setter method for the price of the menu item.
   *
   * @param newPrice The new price of the menu item.
   */
  public void setPrice(Double newPrice) {
    this.price = newPrice;
  }

  /**
   * Getter method for type of course of the menu item.
   *
   * @return The type of course of the menu item.
   */
  public ItemType getType() {
    return this.type;
  }
}
