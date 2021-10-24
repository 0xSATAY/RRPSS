package com.cz2002g5.Controller;

import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Order.Order;
import com.cz2002g5.Util.CSVFileUtil;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Revenue report controller.
 */
public class RevenueReportController {
    /**
     * Generate revenue report.
     *
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
public void generateRevenueReport() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter start date (DD/MM/YYYY):");
        LocalDate startDate, endDate;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            startDate = LocalDate.parse(sc.next(),dateFormatter);
        } catch (Exception e) {
            System.out.println("You have entered an invalid date!");
            return;
        }
        System.out.println("Enter end date (DD/MM/YYYY):");
        try {
            endDate = LocalDate.parse(sc.next(),dateFormatter);
        } catch (Exception e) {
            System.out.println("You have entered an invalid date!");
            return;
        }
        ArrayList<String> revenueReportFromCSV = CSVFileUtil.readRevenueReport();
        ArrayList<String> revenueReport = new ArrayList<>();
        DateTimeFormatter entryDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (String row : revenueReportFromCSV) {
            String[] entry = row.split(";");
            LocalDate entryDate = LocalDate.parse(entry[0], entryDateFormatter);
            if (!entryDate.isBefore(startDate) && !entryDate.isAfter(endDate)) {
                revenueReport.add(row);
            }
        }
        Double totalRevenue = 0.0;
        System.out.println("Revenue Report from " + startDate.toString() + " to " + endDate.toString());
        for (String row : revenueReport) {
            String[] splitString = row.split(";");
            totalRevenue += Double.parseDouble(splitString[2]);
            splitString[2] = NumberFormat.getCurrencyInstance().format(Double.parseDouble(splitString[2]));
            for (String s : splitString) {
                System.out.printf("%s ",s);
            }
            System.out.println();
        }
        System.out.println("Total revenue: " + NumberFormat.getCurrencyInstance().format(totalRevenue));
        System.out.println("--------------------------------------------");
    }

    /**
     * Add order items to revenue report.
     *
     * @param order the order
     * @param date the date
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
public void addOrderItemsToRevenueReport(Order order, LocalDate date) throws IOException {
        ArrayList<String> orderItems = new ArrayList<>();
        for (MenuItem mi : order.getAllItemOrders()) {
            String generatedString = date.toString() + ";" + mi.getName() + ";" + mi.getPrice().toString();
            orderItems.add(generatedString);
        }
        CSVFileUtil.addOrderItemsToRevenueReportCSV(orderItems);
    }
}
