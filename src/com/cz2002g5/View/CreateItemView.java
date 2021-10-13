package com.cz2002g5.View;

public class CreateItemView implements View {

    @Override
    public void show() {
        System.out.println("---Creating Item---");
    }

    @Override
    public void show(String s) {

    }

    public void addNameView() {
        System.out.println("Name of item:");
    }

    public void addTypeView() {
        System.out.println("Type of item:\n1. MAINS\n2. DRINKS\n3. DESSERT");
    }

    public void addDescView() {
        System.out.println("Description of item:");
    }

    public void addPriceView() {
        System.out.println("Price of item:");
    }
}
