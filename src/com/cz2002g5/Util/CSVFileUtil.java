package com.cz2002g5.Util;

import com.cz2002g5.Model.Menu.ItemType;
import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Menu.PromotionalSet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/** The utility class for all CSV CRUD operations. */
public class CSVFileUtil {

  /**
   * Read file given a file name and returns the contents in an ArrayList.
   *
   * @param filename the filename of the file
   * @return the array list of the contents
   */
  public static ArrayList<String> readFile(String filename) {
    try {
      BufferedReader csvReader =
          new BufferedReader(new FileReader("src/com/cz2002g5/Util/" + filename));
      String row;
      ArrayList<String> rows = new ArrayList<>();
      while ((row = csvReader.readLine()) != null) {
        rows.add(row);
      }
      return rows;
    } catch (Exception e) {
      System.out.println("There was an error.");
      return null;
    }
  }

  /**
   * Generates menu item list from file.
   *
   * @return the array list of menu items
   */
  public static ArrayList<MenuItem> generateMenuItemListFromFile() {
    try {
      BufferedReader csvReader =
          new BufferedReader(new FileReader("src/com/cz2002g5/Util/menu.txt"));
      String row;
      ArrayList<MenuItem> menuItems = new ArrayList<>();
      while ((row = csvReader.readLine()) != null) {
        String[] data = row.split(";");
        MenuItem item =
            new MenuItem(data[0], ItemType.valueOf(data[1]), data[2], Double.parseDouble(data[3]));
        menuItems.add(item);
      }
      csvReader.close();
      return menuItems;
    } catch (Exception e) {
      System.out.println("There was an error.");
      return null;
    }
  }

  /**
   * Gets menu item by name.
   *
   * @param name the name of the item
   * @return the menu item of the name. If item doesn't exist, returns null
   */
  public static MenuItem getItemByName(String name) {
    try {
      ArrayList<MenuItem> menuItems = generateMenuItemListFromFile();
      assert menuItems != null;
      for (MenuItem item : menuItems) {
        if (item.getName().equals(name)) {
          return item;
        }
      }
      return null;
    } catch (Exception e) {
      System.out.println("There was an error.");
      return null;
    }
  }

  /**
   * Reads the CSV file that contains the menu items.
   *
   * @return the contents of the menu file
   */
  public static ArrayList<String> readMenuFile() {
    return readFile("menu.txt");
  }

  /**
   * Reads the CSV file that contains the promotional sets.
   *
   * @return the contents of the promotional sets file.
   */
  public static ArrayList<String> readPromoMenuFile() {
    return readFile("promomenu.txt");
  }

  /**
   * Writes a new item to the menu CSV file.
   *
   * @param item the menu item that is to be written to the menu CSV file.
   */
  public static void addItemToMenu(MenuItem item) {
    try {
      String itemString =
          item.getName()
              + ";"
              + item.getType().toString()
              + ";"
              + item.getDescription()
              + ";"
              + item.getPrice().toString()
              + "\n";
      BufferedWriter writer =
          new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/menu.txt", true));
      writer.write(itemString);
      writer.close();
    } catch (Exception e) {
      System.out.println("There was an error.");
    }
  }

  /**
   * Update menu item of the menu CSV file by index.
   *
   * @param itemIndex the item index
   * @param item the menu item that is to be updated
   */
  public static void updateMenuByIndex(int itemIndex, MenuItem item) {
    try {
      BufferedReader csvReader =
          new BufferedReader(new FileReader("src/com/cz2002g5/Util/menu.txt"));
      String row;
      String itemString =
          item.getName()
              + ";"
              + item.getType().toString()
              + ";"
              + item.getDescription()
              + ";"
              + item.getPrice().toString();
      ArrayList<String> rows = new ArrayList<>();
      while ((row = csvReader.readLine()) != null) {
        rows.add(row);
      }
      rows.set(itemIndex, itemString);
      BufferedWriter writer =
          new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/menu.txt", false));
      writer.write("");
      writer.close();
      writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/menu.txt", true));
      for (String r : rows) {
        writer.write(r);
        writer.newLine();
      }
      writer.close();
    } catch (Exception e) {
      System.out.println("There was an error.");
    }
  }

  /**
   * Remove menu item from the menu CSV file by index.
   *
   * @param index the index of the item that is to be removed from the menu CSV file
   */
  public static void removeMenuItemByIndex(int index) {
    try {
      ArrayList<String> fileContent = readMenuFile();
      fileContent.remove(index);
      BufferedWriter writer =
          new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/menu.txt", false));
      writer.write("");
      writer.close();
      writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/menu.txt", true));
      for (String r : fileContent) {
        writer.write(r);
        writer.newLine();
      }
      writer.close();
    } catch (Exception e) {
      System.out.println("There was an error.");
    }
  }

  /**
   * Generate promo menu item list from the promotional set CSV file.
   *
   * @return the array list of promotional sets
   */
  public static ArrayList<PromotionalSet> generatePromoMenuItemListFromFile() {
    try {
      BufferedReader csvReader =
          new BufferedReader(new FileReader("src/com/cz2002g5/Util/promomenu.txt"));
      String row;
      ArrayList<PromotionalSet> promotionalSets = new ArrayList<>();
      while ((row = csvReader.readLine()) != null) {
        String[] data = row.split(";");
        PromotionalSet promotionalSet = new PromotionalSet(data[0]);
        promotionalSet.setPrice(Double.parseDouble(data[1]));
        String[] items = data[2].split(",");
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        for (String i : items) {
          menuItems.add(getItemByName(i));
        }
        for (MenuItem menuItem : menuItems) {
          promotionalSet.addSetItem(menuItem);
        }
        promotionalSets.add(promotionalSet);
      }
      csvReader.close();
      return promotionalSets;
    } catch (Exception e) {
      System.out.println("There was an error.");
    }
    return null;
  }

  /**
   * Remove promo item from promomenu CSV file by index.
   *
   * @param index the index of the promotional set that is to be removed
   */
  public static void removePromoItemByIndex(int index) {
    try {
      ArrayList<String> fileContent = readPromoMenuFile();
      fileContent.remove(index);
      BufferedWriter writer =
          new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/promomenu.txt", false));
      writer.write("");
      writer.close();
      writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/promomenu.txt", true));
      for (String r : fileContent) {
        writer.write(r);
        writer.newLine();
      }
      writer.close();
    } catch (Exception e) {
      System.out.println("There was an error.");
    }
  }

  /**
   * Update promo menu item of promomenu CSV file by index.
   *
   * @param itemIndex the index of the item that is to be updated
   * @param item the promotional set item to update
   */
  public static void updatePromoMenuByIndex(int itemIndex, PromotionalSet item) {
    try {
      BufferedReader csvReader;
      csvReader = new BufferedReader(new FileReader("src/com/cz2002g5/Util/promomenu.txt"));
      String row;
      StringBuilder setItems = new StringBuilder();
      for (MenuItem mi : item.getSetItems()) {
        setItems.append(mi.getName());
        setItems.append(",");
      }
      setItems = new StringBuilder(setItems.substring(0, setItems.length() - 1));
      String promoString = item.getName() + ";" + item.getPrice() + ";" + setItems;
      ArrayList<String> rows = new ArrayList<>();
      while ((row = csvReader.readLine()) != null) {
        rows.add(row);
      }
      rows.set(itemIndex, promoString);
      BufferedWriter writer =
          new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/promomenu.txt", false));
      writer.write("");
      writer.close();
      writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/promomenu.txt", true));
      for (String r : rows) {
        writer.write(r);
        writer.newLine();
      }
      writer.close();
    } catch (Exception e) {
      System.out.println("There was an error.");
    }
  }

  /**
   * Add promo item to promo menu CSV file.
   *
   * @param item the promotional set item that is to be added
   */
  public static void addItemToPromoMenu(PromotionalSet item) {
    try {
      StringBuilder setItems = new StringBuilder();
      for (MenuItem mi : item.getSetItems()) {
        setItems.append(mi.getName());
        setItems.append(",");
      }
      setItems = new StringBuilder(setItems.substring(0, setItems.length() - 1));
      String promoString = item.getName() + ";" + item.getPrice() + ";" + setItems + "\n";
      BufferedWriter writer;
      writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/promomenu.txt", true));
      writer.write(promoString);
      writer.close();
    } catch (Exception e) {
      System.out.println("There was an error.");
    }
  }

  /**
   * Add order items to revenue report CSV.
   *
   * @param orderItems the order items that is to be added
   */
  public static void addOrderItemsToRevenueReportCSV(ArrayList<String> orderItems) {
    try {
      BufferedReader csvReader =
          new BufferedReader(new FileReader("src/com/cz2002g5/Util/revenuereport.txt"));
      String row;
      ArrayList<String> rows = new ArrayList<>();
      while ((row = csvReader.readLine()) != null) {
        rows.add(row);
      }
      rows.addAll(orderItems);
      BufferedWriter writer =
          new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/revenuereport.txt", true));
      for (String r : rows) {
        writer.write(r);
        writer.newLine();
      }
      writer.close();
    } catch (Exception e) {
      System.out.println("There was an error.");
    }
  }

  /**
   * Generate revenue report from the revenue report CSV file.
   *
   * @return the array list of strings generated from the CSV file
   */
  public static ArrayList<String> readRevenueReport() {
    return readFile("revenuereport.txt");
  }
}
