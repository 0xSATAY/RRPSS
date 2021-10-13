package com.cz2002g5.Model.Menu;

public class MenuItem {
    private String name, description;
    private ItemType type;
    private Double price;

    public MenuItem(String name, ItemType type, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
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

}
