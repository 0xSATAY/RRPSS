package com.cz2002g5.Util;

import com.cz2002g5.Model.Menu.ItemType;
import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Menu.PromotionalSet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class CSVFileUtil {

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

  public static ArrayList<String> readMenuFile() {
    return readFile("menu.txt");
  }

  public static ArrayList<String> readPromoMenuFile() {
    return readFile("promomenu.txt");
  }

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

  public static void addItemToPromoMenu(PromotionalSet item) {
    try {
      StringBuilder setItems = new StringBuilder();
      for (MenuItem mi : item.getSetItems()) {
        setItems.append(mi.getName());
        setItems.append(",");
      }
      setItems = new StringBuilder(setItems.substring(0, setItems.length() - 1));
      String promoString = item.getName() + ";" + item.getPrice() + ";" + setItems;
      BufferedWriter writer;
      writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/promomenu.txt", true));
      writer.write(promoString);
      writer.close();
    } catch (Exception e) {
      System.out.println("There was an error.");
    }
  }

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

  public static ArrayList<String> readRevenueReport() {
    return readFile("revenuereport.txt");
  }
}
