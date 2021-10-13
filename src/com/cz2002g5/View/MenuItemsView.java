package com.cz2002g5.View;

import com.cz2002g5.Model.Menu.MenuItem;

import java.util.ArrayList;

public class MenuItemsView implements View{
    @Override
    public void show() {
        System.out.println("Menu");
    }

    public void show(String generatedMenu) {
        System.out.println(generatedMenu);
    }
}
