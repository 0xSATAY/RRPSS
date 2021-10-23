package com.cz2002g5.Util;

import com.cz2002g5.Model.Menu.ItemType;
import com.cz2002g5.Model.Menu.Menu;
import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Menu.PromotionalSet;

import java.io.*;
import java.util.ArrayList;

public class CSVFileUtil {
    public static ArrayList<MenuItem> generateMenuItemListFromFile() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/com/cz2002g5/Util/menu.txt"));
        String row;
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(";");
                MenuItem item = new MenuItem(data[0], ItemType.valueOf(data[1]),data[2],Double.parseDouble(data[3]));
                menuItems.add(item);
        }
        csvReader.close();
        return menuItems;
    }

    public static MenuItem getItemByName(String name) throws IOException {
        ArrayList<MenuItem> menuItems = generateMenuItemListFromFile();
        for (MenuItem item : menuItems) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public static ArrayList<String> readMenuFile() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/com/cz2002g5/Util/menu.txt"));
        String row;
        ArrayList<String> rows = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            rows.add(row);
        }
        return rows;
    }

    public static ArrayList<String> readPromoMenuFile() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/com/cz2002g5/Util/promomenu.txt"));
        String row;
        ArrayList<String> rows = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            rows.add(row);
        }
        return rows;
    }

    public static void addItemToMenu(MenuItem item) throws IOException {
        String itemString = item.getName() + ";" + item.getType().toString() + ";" + item.getDescription() + ";" + item.getPrice().toString() + "\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/menu.txt", true));
        writer.write(itemString);
        writer.close();
    }

    public static void updateMenuByIndex(int itemIndex, MenuItem item) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/com/cz2002g5/Util/menu.txt"));
        String row;
        String itemString = item.getName() + ";" + item.getType().toString() + ";" + item.getDescription() + ";" + item.getPrice().toString();
        ArrayList<String> rows = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            rows.add(row);
        }
        rows.set(itemIndex, itemString);
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/menu.txt", false));
        writer.write("");
        writer.close();
        writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/menu.txt", true));
        for (String r : rows) {
            writer.write(r);
            writer.newLine();
        }
        writer.close();
    }

    public static void removeMenuItemByIndex(int index) throws IOException {
        ArrayList<String> fileContent = readMenuFile();
        fileContent.remove(index);
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/menu.txt", false));
        writer.write("");
        writer.close();
        writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/menu.txt", true));
        for (String r : fileContent) {
            writer.write(r);
            writer.newLine();
        }
        writer.close();
    }

    public static ArrayList<PromotionalSet> generatePromoMenuItemListFromFile() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/com/cz2002g5/Util/promomenu.txt"));
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
    }

    public static void removePromoItemByIndex(int index) throws IOException {
        ArrayList<String> fileContent = readPromoMenuFile();
        fileContent.remove(index);
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/promomenu.txt", false));
        writer.write("");
        writer.close();
        writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/promomenu.txt", true));
        for (String r : fileContent) {
            writer.write(r);
            writer.newLine();
        }
        writer.close();
    }

    public static void updatePromoMenuByIndex(int itemIndex, PromotionalSet item) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/com/cz2002g5/Util/promomenu.txt"));
        String row;
        String setItems = "";
        for (MenuItem mi : item.getSetItems()) {
            setItems += mi.getName();
            setItems += ",";
        }
        setItems = setItems.substring(0,setItems.length()-1);
        String promoString = item.getName() + ";" + item.getPrice() + ";" + setItems;
        ArrayList<String> rows = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            rows.add(row);
        }
        rows.set(itemIndex, promoString);
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/promomenu.txt", false));
        writer.write("");
        writer.close();
        writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/promomenu.txt", true));
        for (String r : rows) {
            writer.write(r);
            writer.newLine();
        }
        writer.close();
    }

    public static void addItemToPromoMenu(PromotionalSet item) throws IOException {
        String setItems = "";
        for (MenuItem mi : item.getSetItems()) {
            setItems += mi.getName();
            setItems += ",";
        }
        setItems = setItems.substring(0,setItems.length()-1);
        String promoString = item.getName() + ";" + item.getPrice() + ";" + setItems;
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/promomenu.txt", true));
        writer.write(promoString);
        writer.close();
    }
}
