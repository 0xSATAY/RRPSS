package com.cz2002g5.Controller;

import com.cz2002g5.Model.Menu.ItemType;
import com.cz2002g5.Model.Menu.Menu;
import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Util.CSVFileUtil;
import com.cz2002g5.View.CreateItemView;
import com.cz2002g5.View.DeleteItemView;
import com.cz2002g5.View.ItemEditorView;
import com.cz2002g5.View.UpdateItemView;

import java.io.IOException;
import java.util.Scanner;

public class ItemEditor implements MenuEditor{

    @Override
    public void selectAction(RRPSS pos) throws IOException {
        while (true) {
            Scanner sc = new Scanner(System.in);
            RRPSS.updateView(pos, new ItemEditorView());
            RRPSS.showView(pos);
            while (!sc.hasNextInt()) {
                System.out.println("You have inputted a non-numerical value!\nSelect your action:");
                sc.next();
            }
            int action = sc.nextInt();
            switch (action) {
                case 1:
                    this.createItem(pos);
                    return;
                case 2:
                    this.updateItem(pos);
                    return;
                case 3:
                    this.removeItem(pos);
                    return;
                case 4:
                    return;
                default:
                    System.out.println("You have selected an invalid action!");
            }
        }
    }

    @Override
    public void removeItem(RRPSS pos) throws IOException {
        while (true) {
            Scanner sc = new Scanner(System.in);
            RRPSS.updateView(pos, new DeleteItemView());
            RRPSS.showView(pos);
            ((DeleteItemView) RRPSS.getCurrentView(pos)).deleteItemView(pos.generateMenuString());
            while (!sc.hasNextInt()) {
                System.out.println("You have inputted a non-numerical value!");
                System.out.println("Select the item you wish to delete:");
                sc.next();
            }
            int selection = sc.nextInt();
            Menu menu = RRPSS.getMenu(pos);
            if (selection > 0 && selection <= menu.getMenuItems().size()) {
                MenuItem item = menu.getMenuItems().get(selection-1);
                CSVFileUtil.removeMenuItemByIndex(selection-1);
                System.out.println(item.getName() + " has been removed from the menu.");
                return;
            } else {
                System.out.println("You have entered and invalid option!");
            }
        }
    }

    @Override
    public void createItem(RRPSS pos) throws IOException {
        Scanner sc = new Scanner(System.in);
        RRPSS.updateView(pos,new CreateItemView());
        RRPSS.showView(pos);
        ((CreateItemView) RRPSS.getCurrentView(pos)).addNameView();
        String name = sc.nextLine();
        ItemType type;
        while (true) {
            ((CreateItemView) RRPSS.getCurrentView(pos)).addTypeView();
            while (!sc.hasNextInt()) {
                System.out.println("You have inputted a non-numerical value!");
                ((CreateItemView) RRPSS.getCurrentView(pos)).addTypeView();
                sc.next();
            }
            int typeSelection = sc.nextInt();
            if (typeSelection > 0 && typeSelection <= ItemType.values().length) {
                type = ItemType.values()[sc.nextInt()-1];
                break;
            } else {
                System.out.println("You have entered an invalid type!");
            }
        }
        ((CreateItemView) RRPSS.getCurrentView(pos)).addPriceView();
        while (!sc.hasNextDouble()) {
            System.out.println("You have inputted a non-numerical value!");
            ((CreateItemView) RRPSS.getCurrentView(pos)).addPriceView();
            sc.next();
        }
        Double price = sc.nextDouble();
        ((CreateItemView) RRPSS.getCurrentView(pos)).addDescView();
        sc.nextLine();
        String desc = sc.nextLine();
        MenuItem newItem = new MenuItem(name, type, desc, price);
        CSVFileUtil.addItemToMenu(newItem);
        pos.reloadMenu();
        System.out.println(name + " has been added to the menu.");
    }

    @Override
    public void updateItem(RRPSS pos) throws IOException {
        while (true) {
            Scanner sc = new Scanner(System.in);
            RRPSS.updateView(pos,new UpdateItemView());
            RRPSS.showView(pos,pos.generateMenuString());
            while (!sc.hasNextInt()) {
                System.out.println("You have inputted a non-numerical value!\nSelect your action:");
                sc.next();
            }
            int selection = sc.nextInt()-1;
            Menu menu = RRPSS.getMenu(pos);
            if (selection >= 0 && selection < menu.getMenuItems().size()) {
                MenuItem itemSelected = menu.getMenuItems().get(selection);
                ((UpdateItemView) RRPSS.getCurrentView(pos)).updateNameView(itemSelected.getName());
                sc = new Scanner(System.in);
                String name = sc.nextLine();
                if (name.isEmpty()) {
                    name = itemSelected.getName();
                }
                ItemType type;
                while (true) {
                    ((UpdateItemView) RRPSS.getCurrentView(pos)).updateTypeView(name);
                    while (!sc.hasNextInt()) {
                        System.out.println("You have inputted a non-numerical value!");
                        ((CreateItemView) RRPSS.getCurrentView(pos)).addTypeView();
                        sc.next();
                    }
                    int typeSelection = sc.nextInt();
                    if (typeSelection == 0) {
                        type = itemSelected.getType();
                        break;
                    }
                    if (typeSelection > 0 && typeSelection <= ItemType.values().length) {
                        type = ItemType.values()[typeSelection-1];
                        break;
                    } else {
                        System.out.println("You have entered an invalid type!");
                    }
                }
                ((UpdateItemView) RRPSS.getCurrentView(pos)).updatePriceView(name);
                while (!sc.hasNextDouble()) {
                    System.out.println("You have inputted a non-numerical value!");
                    ((CreateItemView) RRPSS.getCurrentView(pos)).addPriceView();
                    sc.next();
                }
                Double price = sc.nextDouble();
                if (price == 0.0) {
                    price = itemSelected.getPrice();
                }
                ((UpdateItemView) RRPSS.getCurrentView(pos)).updateDescView(name);
                sc.nextLine();
                String desc = sc.nextLine();
                if (desc.isEmpty()) {
                    desc = itemSelected.getDescription();
                }
                MenuItem menuItem = new MenuItem(name, type, desc, price);
                CSVFileUtil.updateMenuByIndex(RRPSS.getMenu(pos),selection,menuItem);
                pos.reloadMenu();
                System.out.println(name + " has been updated.");
                return;
            } else {
                System.out.println("You have entered an invalid action!");
            }
        }
    }
}
