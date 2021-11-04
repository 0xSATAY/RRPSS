package com.cz2002g5.Controller;

import com.cz2002g5.Model.Menu.ItemType;
import com.cz2002g5.Model.Menu.Menu;
import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Util.CSVFileUtil;
import com.cz2002g5.View.*;

import java.util.Scanner;

/** The controller class for creating, deleting and updating menu items. */
public class MenuItemController implements MenuEditController {

  @Override
  public void selectAction(RRPSS pos) {
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
  public void createItem(RRPSS pos) {
    Scanner sc = new Scanner(System.in);
    RRPSS.updateView(pos, new CreateMenuItemView());
    RRPSS.showView(pos);
    ((CreateMenuItemView) RRPSS.getCurrentView(pos)).showAddNameView();
    String name = sc.nextLine();
    ItemType type;
    while (true) {
      ((CreateMenuItemView) RRPSS.getCurrentView(pos)).showAddTypeView();
      while (!sc.hasNextInt()) {
        System.out.println("You have inputted a non-numerical value!");
        ((CreateMenuItemView) RRPSS.getCurrentView(pos)).showAddTypeView();
        sc.next();
      }
      int typeSelection = sc.nextInt();
      if (typeSelection > 0 && typeSelection <= ItemType.values().length) {
        type = ItemType.values()[typeSelection - 1];
        break;
      } else {
        System.out.println("You have entered an invalid type!");
      }
    }
    ((CreateMenuItemView) RRPSS.getCurrentView(pos)).showAddPriceView();
    while (!sc.hasNextDouble()) {
      System.out.println("You have inputted a non-numerical value!");
      ((CreateMenuItemView) RRPSS.getCurrentView(pos)).showAddPriceView();
      sc.next();
    }
    Double price = sc.nextDouble();
    ((CreateMenuItemView) RRPSS.getCurrentView(pos)).showAddDescView();
    sc.nextLine();
    String desc = sc.nextLine();
    MenuItem newItem = new MenuItem(name, type, desc, price);
    CSVFileUtil.addItemToMenu(newItem);
    pos.reloadMenu();
    System.out.println(name + " has been added to the menu.");
  }

  @Override
  public void updateItem(RRPSS pos) {
    while (true) {
      Scanner sc = new Scanner(System.in);
      RRPSS.updateView(pos, new UpdateMenuItemView());
      RRPSS.showView(pos, pos.generateMenuString(false));
      while (!sc.hasNextInt()) {
        System.out.println("You have inputted a non-numerical value!\nSelect your action:");
        sc.next();
      }
      int selection = sc.nextInt() - 1;
      if (selection == -1) {
        return;
      }
      Menu menu = RRPSS.getMenu(pos);
      if (selection >= 0 && selection < menu.getMenuItems().size()) {
        MenuItem itemSelected = menu.getMenuItems().get(selection);
        ((UpdateMenuItemView) RRPSS.getCurrentView(pos)).showUpdateNameView(itemSelected.getName());
        sc = new Scanner(System.in);
        String name = sc.nextLine();
        if (name.isEmpty()) {
          name = itemSelected.getName();
        }
        ItemType type;
        while (true) {
          ((UpdateMenuItemView) RRPSS.getCurrentView(pos)).showUpdateTypeView(name);
          while (!sc.hasNextInt()) {
            System.out.println("You have inputted a non-numerical value!");
            ((CreateMenuItemView) RRPSS.getCurrentView(pos)).showAddTypeView();
            sc.next();
          }
          int typeSelection = sc.nextInt();
          if (typeSelection == 0) {
            type = itemSelected.getType();
            break;
          }
          if (typeSelection > 0 && typeSelection <= ItemType.values().length) {
            type = ItemType.values()[typeSelection - 1];
            break;
          } else {
            System.out.println("You have entered an invalid type!");
          }
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
        ((UpdateMenuItemView) RRPSS.getCurrentView(pos)).showUpdateDescView(name);
        sc.nextLine();
        String desc = sc.nextLine();
        if (desc.isEmpty()) {
          desc = itemSelected.getDescription();
        }
        MenuItem menuItem = new MenuItem(name, type, desc, price);
        CSVFileUtil.updateMenuByIndex(selection, menuItem);
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
      RRPSS.updateView(pos, new DeleteMenuItemView());
      RRPSS.showView(pos);
      ((DeleteMenuItemView) RRPSS.getCurrentView(pos))
          .showDeleteItemView(pos.generateMenuString(false));
      while (!sc.hasNextInt()) {
        System.out.println("You have inputted a non-numerical value!");
        System.out.println("Select the item you wish to delete:");
        sc.next();
      }
      int selection = sc.nextInt();
      if (selection == 0) {
        return;
      }
      Menu menu = RRPSS.getMenu(pos);
      if (selection > 0 && selection <= menu.getMenuItems().size()) {
        MenuItem item = menu.getMenuItems().get(selection - 1);
        CSVFileUtil.removeMenuItemByIndex(selection - 1);
        System.out.println(item.getName() + " has been removed from the menu.");
        return;
      } else {
        System.out.println("You have entered and invalid option!");
      }
    }
  }

  /**
   * Displays all menu items (ala carte and promotional sets).
   *
   * @param pos Reference to the instance of RRPSS.
   */
  public void showAllMenuItems(RRPSS pos) {
    MenuItemsView miv = new MenuItemsView();
    RRPSS.updateView(pos, miv);
    System.out.println("Ala Carte Items:");
    RRPSS.getCurrentView(pos).displayCustomView(pos.generateMenuString(true));
    System.out.println("Promo Sets");
    RRPSS.getCurrentView(pos).displayCustomView(pos.generatePromoMenuString(true));
  }
}
