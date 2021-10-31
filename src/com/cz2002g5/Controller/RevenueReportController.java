package com.cz2002g5.Controller;

import com.cz2002g5.Model.Menu.MenuItem;
import com.cz2002g5.Model.Menu.PromotionalSet;
import com.cz2002g5.Model.Order.Order;
import com.cz2002g5.Util.CSVFileUtil;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class RevenueReportController {

  public void generateRevenueReport() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter start date (DD/MM/YYYY):");
    LocalDate startDate, endDate;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    try {
      startDate = LocalDate.parse(sc.next(), dateFormatter);
    } catch (Exception e) {
      System.out.println("You have entered an invalid date!");
      return;
    }
    System.out.println("Enter end date (DD/MM/YYYY):");
    try {
      endDate = LocalDate.parse(sc.next(), dateFormatter);
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
      splitString[2] =
          NumberFormat.getCurrencyInstance().format(Double.parseDouble(splitString[2]));
      for (String s : splitString) {
        System.out.printf("%s ", s);
      }
      System.out.println();
    }
    System.out.println("Total revenue: " + NumberFormat.getCurrencyInstance().format(totalRevenue));
    System.out.println("--------------------------------------------");
  }

  public void addOrderItemsToRevenueReport(Order order, LocalDate date) {
    ArrayList<String> orderItems = new ArrayList<>();
    for (MenuItem mi : order.getAllItemOrders()) {
      String generatedString =
          date.toString() + ";" + mi.getName() + ";" + mi.getPrice().toString() + ";";
      if (!(mi instanceof PromotionalSet)) {
        generatedString += mi.getType();
      }
      orderItems.add(generatedString);
    }
    CSVFileUtil.addOrderItemsToRevenueReportCSV(orderItems);
  }
}
