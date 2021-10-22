package com.cz2002g5.View;

public class DeleteItemView implements View {
    @Override
    public void show() {
        System.out.println("---Deleting item---");
    }

    @Override
    public void show(String s) {

    }

    public void showDeleteItemView(String menu) {
        System.out.println(menu);
        System.out.println("Select the item you wish to delete:");
    }

}
