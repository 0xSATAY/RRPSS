package com.cz2002g5.Model.Menu;

public class MenuItem {
    private String name;
    private final String description;
    private final ItemType type;
    private Double price;
    private int promotionID;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    private int itemID;

    public MenuItem(String name, ItemType type, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.promotionID = -1;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Double getPrice() {
        return this.price;
    }

    public ItemType getType() {
        return this.type;
    }

    public void setPrice(Double newPrice) {
        this.price = newPrice;
    }

    public int getPromotionID() {
        return this.promotionID;
    }

    public void setPromotionID(int id) {
        this.promotionID = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
