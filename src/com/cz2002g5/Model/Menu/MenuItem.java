package com.cz2002g5.Model.Menu;

/**
 * The type Menu item.
 */
public class MenuItem {
    private String name;
    private final String description;
    private final ItemType type;
    private Double price;
    private int promotionID;

    /**
     * Gets item id.
     *
     * @return the item id
     */
public int getItemID() {
        return itemID;
    }

    /**
     * Sets item id.
     *
     * @param itemID the item id
     */
public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    private int itemID;

    /**
     * Instantiates a new Menu item.
     *
     * @param name the name
     * @param type the type
     * @param description the description
     * @param price the price
     */
public MenuItem(String name, ItemType type, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.promotionID = -1;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
public String getName() {
        return this.name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
public String getDescription() {
        return this.description;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
public Double getPrice() {
        return this.price;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
public ItemType getType() {
        return this.type;
    }

    /**
     * Sets price.
     *
     * @param newPrice the new price
     */
public void setPrice(Double newPrice) {
        this.price = newPrice;
    }

    /**
     * Gets promotion id.
     *
     * @return the promotion id
     */
public int getPromotionID() {
        return this.promotionID;
    }

    /**
     * Sets promotion id.
     *
     * @param id the id
     */
public void setPromotionID(int id) {
        this.promotionID = id;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
public void setName(String name) {
        this.name = name;
    }
}
