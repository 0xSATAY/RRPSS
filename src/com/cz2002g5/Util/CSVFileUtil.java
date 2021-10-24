package com.cz2002g5.Util;

import com.cz2002g5.Model.Menu.ItemType;
import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Menu.PromotionalSet;

import java.io.*;
import java.util.ArrayList;

/**
 * The type Csv file util.
 */
public class CSVFileUtil {

    /**
     * Read file array list.
     *
     * @param filename the filename
     * @return the array list
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
public static ArrayList<String> readFile(String filename) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/com/cz2002g5/Util/"+filename));
        String row;
        ArrayList<String> rows = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            rows.add(row);
        }
        return rows;
    }
    /**
     * Generate menu item list from file array list.
     *
     * @return the array list
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
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

    /**
     * Gets item by name.
     *
     * @param name the name
     * @return the item by name
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
public static MenuItem getItemByName(String name) throws IOException {
        ArrayList<MenuItem> menuItems = generateMenuItemListFromFile();
        for (MenuItem item : menuItems) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Read menu file array list.
     *
     * @return the array list
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
public static ArrayList<String> readMenuFile() throws IOException {
        return readFile("menu.txt");
    }

    /**
     * Read promo menu file array list.
     *
     * @return the array list
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
public static ArrayList<String> readPromoMenuFile() throws IOException {
        return readFile("promomenu.txt");
    }

    /**
     * Add item to menu.
     *
     * @param item the item
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
public static void addItemToMenu(MenuItem item) throws IOException {
        String itemString = item.getName() + ";" + item.getType().toString() + ";" + item.getDescription() + ";" + item.getPrice().toString() + "\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/menu.txt", true));
        writer.write(itemString);
        writer.close();
    }

    /**
     * Update menu by index.
     *
     * @param itemIndex the item index
     * @param item the item
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
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

    /**
     * Remove menu item by index.
     *
     * @param index the index
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
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

    /**
     * Generate promo menu item list from file array list.
     *
     * @return the array list
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
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

    /**
     * Remove promo item by index.
     *
     * @param index the index
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
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

    /**
     * Update promo menu by index.
     *
     * @param itemIndex the item index
     * @param item the item
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
public static void updatePromoMenuByIndex(int itemIndex, PromotionalSet item) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/com/cz2002g5/Util/promomenu.txt"));
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

    /**
     * Add item to promo menu.
     *
     * @param item the item
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
public static void addItemToPromoMenu(PromotionalSet item) throws IOException {
        StringBuilder setItems = new StringBuilder();
        for (MenuItem mi : item.getSetItems()) {
            setItems.append(mi.getName());
            setItems.append(",");
        }
        setItems = new StringBuilder(setItems.substring(0, setItems.length() - 1));
        String promoString = item.getName() + ";" + item.getPrice() + ";" + setItems;
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/promomenu.txt", true));
        writer.write(promoString);
        writer.close();
    }

    /**
     * Add order items to revenue report csv.
     *
     * @param orderItems the order items
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
public static void addOrderItemsToRevenueReportCSV(ArrayList<String> orderItems) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/com/cz2002g5/Util/revenuereport.txt"));
        String row;
        ArrayList<String> rows = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            rows.add(row);
        }
        rows.addAll(orderItems);
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/com/cz2002g5/Util/revenuereport.txt", true));
        for (String r : rows) {
            writer.write(r);
            writer.newLine();
        }
        writer.close();
    }

    /**
     * Read revenue report array list.
     *
     * @return the array list
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
public static ArrayList<String> readRevenueReport() throws IOException {
        return readFile("revenuereport.txt");
    }
}
