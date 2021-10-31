package com.cz2002g5.Controller;

import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Menu.PromotionalSet;
import com.cz2002g5.Util.CSVFileUtil;
import com.cz2002g5.View.*;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class PromotionEditController implements MenuEditController {

  @Override
  public void selectAction(RRPSS pos) {
    while (true) {
      Scanner sc = new Scanner(System.in);
      RRPSS.updateView(pos, new PromotionEditorView());
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
  public void createItem(RRPSS pos) {
    Scanner sc = new Scanner(System.in);
    RRPSS.updateView(pos, new CreatePromoSetView());
    RRPSS.showView(pos);
    ((CreatePromoSetView) RRPSS.getCurrentView(pos)).showAddNameView();
    String name = sc.nextLine();
    PromotionalSet promotionalSet = new PromotionalSet(name);
    System.out.println(pos.generateMenuString(false));
    ((CreatePromoSetView) RRPSS.getCurrentView(pos)).showAddItemView();
    int itemSelection = sc.nextInt();
    while (true) {
      if (itemSelection < 1 || itemSelection > RRPSS.getMenu(pos).getMenuItems().size()) {
        System.out.println("You have entered an invalid option!");
        return;
      }
      itemSelection--;
      MenuItem item = RRPSS.getMenu(pos).getMenuItems().get(itemSelection);
      promotionalSet.addSetItem(item);
      System.out.println(
          item.getName()
              + " has been added. To enter another item, enter its corresponding number. If you are done, enter '.'");
      if (sc.hasNextInt()) {
        itemSelection = sc.nextInt();
      } else {
        sc.next();
        System.out.println("Items in the promo set:");
        for (int i = 0; i < promotionalSet.getSetItems().size(); i++) {
          MenuItem setItem = promotionalSet.getSetItems().get(i);
          System.out.println(
              i
                  + 1
                  + ". "
                  + setItem.getName()
                  + " - "
                  + NumberFormat.getCurrencyInstance().format(setItem.getPrice()));
        }
        ((CreatePromoSetView) RRPSS.getCurrentView(pos)).showAddPriceView();
        while (!sc.hasNextDouble()) {
          System.out.println("You have inputted a non-numerical value!");
          ((CreateMenuItemView) RRPSS.getCurrentView(pos)).showAddPriceView();
          sc.next();
        }
        Double price = sc.nextDouble();
        promotionalSet.setPrice(price);
        pos.addPromoSet(promotionalSet);
        CSVFileUtil.addItemToPromoMenu(promotionalSet);
        System.out.println(
            "Price of promo set: "
                + NumberFormat.getCurrencyInstance().format(promotionalSet.getPrice())
                + "\n");
        return;
      }
    }
  }

  @Override
  public void updateItem(RRPSS pos) {
    while (true) {
      RRPSS.updateView(pos, new UpdatePromoItemView());
      RRPSS.showView(pos, pos.generatePromoMenuString(false));
      Scanner sc = new Scanner(System.in);
      while (!sc.hasNextInt()) {
        System.out.println("You have inputted a non-numerical value!\nSelect your action:");
        sc.next();
      }
      int selection = sc.nextInt() - 1;
      if (selection == -1) return;
      ArrayList<PromotionalSet> promotionalSets = RRPSS.getPromotionalSets(pos);
      if (selection >= 0 && selection < promotionalSets.size()) {
        PromotionalSet itemSelected = promotionalSets.get(selection);
        ((UpdatePromoItemView) RRPSS.getCurrentView(pos))
            .showUpdateNameView(itemSelected.getName());
        sc = new Scanner(System.in);
        String name = sc.nextLine();
        if (name.isEmpty()) {
          name = itemSelected.getName();
        }
        ((UpdateMenuItemView) RRPSS.getCurrentView(pos)).showUpdatePriceView(name);
        while (!sc.hasNextDouble()) {
          System.out.println("You have inputted a non-numerical value!");
          ((CreateMenuItemView) RRPSS.getCurrentView(pos)).showAddPriceView();
          sc.next();
        }
        Double price = sc.nextDouble();
        if (price == 0.0) {
          price = itemSelected.getPrice();
        }
        itemSelected.setName(name);
        itemSelected.setPrice(price);
        CSVFileUtil.updatePromoMenuByIndex(selection, itemSelected);
        pos.reloadMenu();
        System.out.println(name + " has been updated.");
        return;
      } else {
        System.out.println("You have entered an invalid action!");
      }
    }
  }

  @Override
  public void removeItem(RRPSS pos) {
    while (true) {
      Scanner sc = new Scanner(System.in);
      RRPSS.updateView(pos, new DeletePromoItemView());
      RRPSS.showView(pos);
      RRPSS.getCurrentView(pos).displayCustomView(pos.generatePromoMenuString(false));
      while (!sc.hasNextInt()) {
        System.out.println("You have inputted a non-numerical value!");
        System.out.println("Select the item you wish to delete:");
        sc.next();
      }
      int selection = sc.nextInt();
      if (selection == 0) return;
      ArrayList<PromotionalSet> promotionalSets = RRPSS.getPromotionalSets(pos);
      if (selection > 0 && selection <= promotionalSets.size()) {
        PromotionalSet item = promotionalSets.get(selection - 1);
        CSVFileUtil.removePromoItemByIndex(selection - 1);
        System.out.println(item.getName() + " has been removed from the promo menu.");
        return;
      } else {
        System.out.println("You have entered and invalid option!");
      }
    }
  }
}
