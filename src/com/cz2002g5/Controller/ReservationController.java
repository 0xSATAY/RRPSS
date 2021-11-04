package com.cz2002g5.Controller;

import com.cz2002g5.Model.Reservation.Reservation;
import com.cz2002g5.Model.Restaurant.Table;
import com.cz2002g5.View.CreateReservationView;
import com.cz2002g5.View.DeleteReservationView;
import com.cz2002g5.View.ReservationEditorView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** The controller class for all reservation related operations. */
public class ReservationController {

  /**
   * Timings in String to be converted into Time. Purpose of this is to reduce human error when
   * selecting time slots.
   */
  public static final String[] timings = {
    "11:00AM", "11:30AM", "12:00PM", "12:30PM", "01:00PM", "01:30PM", "06:00PM", "06:30PM",
    "07:00PM", "07:30PM", "08:00PM", "08:30PM"
  };

  /**
   * Instantiates a new Reservation controller. Runs a background thread that checks for expiring
   * reservations that runs every minute.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  public ReservationController(RRPSS pos) {
    this.initBackgroundReservationsChecker(pos);
  }

  private void initBackgroundReservationsChecker(RRPSS pos) {
    Runnable checkReservationRunnable = () -> this.clearReservations(pos);
    ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
    exec.scheduleAtFixedRate(checkReservationRunnable, 0, 1, TimeUnit.MINUTES);
  }

  private int clearReservations(RRPSS pos) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    int removedReservationsCount = 0;
    for (Table t : pos.getRestaurant().getTables()) {
      for (Reservation r : t.getReservations()) {
        String dateTimeString = r.getDate().toString() + " " + r.getTime().toString();
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, dtf);
        long minutes = ChronoUnit.MINUTES.between(dateTime, LocalDateTime.now());
        if (minutes > 15) {
          removedReservationsCount++;
          t.getReservations().remove(r);
        }
      }
    }
    return removedReservationsCount;
  }

  private void removeReservation(RRPSS pos) {
    Scanner sc = new Scanner(System.in);
    DeleteReservationView drv = new DeleteReservationView();
    RRPSS.updateView(pos, drv);
    drv.display();
    showAllReservations(pos);
    System.out.println("Select the reservation you would like to delete. Enter 0 to cancel:");
    while (!sc.hasNextInt()) {
      System.out.println(
              "You have inputted an invalid selection!\nSelect the reservation you would like to delete. Enter 0 to cancel:");
      sc.next();
    }
    int selection = sc.nextInt();
    if (selection == 0) {
      return;
    }
    if (selection < 1 || selection > pos.getRestaurant().getReservations().size()) {
      System.out.println("You have entered an invalid reservation!");
      return;
    }
    ArrayList<Reservation> reservations = pos.getRestaurant().getReservations();
    Reservation reservation = reservations.get(selection - 1);
    int table = reservation.getTableNumber();
    pos.getRestaurant().getTables().get(table).getReservations().remove(reservation);
    System.out.println("Reservation for " + reservation.getName() + " has been removed.");
  }

  private void showAllReservations(RRPSS pos) {
    System.out.println("------Showing All Reservations------");
    int i = 1;
    for (Reservation r : pos.getRestaurant().getReservations()) {
      System.out.println("Reservation #" + i);
      r.showInfo();
      System.out.println();
      i++;
    }
  }

  /**
   * Processes user actions.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  public void selectAction(RRPSS pos) {
    while (true) {
      Scanner sc = new Scanner(System.in);
      ReservationEditorView rev = new ReservationEditorView();
      RRPSS.updateView(pos, rev);
      rev.display();
      while (!sc.hasNextInt()) {
        System.out.println("You have inputted a non-numerical value!\nSelect your action:");
        sc.next();
      }
      int selection = sc.nextInt();
      switch (selection) {
        case 1:
          this.showAllReservations(pos);
          break;
        case 2:
          this.removeReservation(pos);
          break;
        case 3:
          return;
        default:
          System.out.println("You have entered and invalid option!");
      }
    }
  }

  /**
   * Creates a new reservation.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  public void createReservation(RRPSS pos) {
    LocalDate date;
    CreateReservationView crv = new CreateReservationView();
    RRPSS.updateView(pos, crv);
    crv.display();
    crv.showAddNameView();
    Scanner sc = new Scanner(System.in);
    String name = sc.nextLine();
    crv.showAddDateView();
    try {
      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      date = LocalDate.parse(sc.next(), dateFormatter);
      if (date.isBefore(LocalDateTime.now().toLocalDate())) {
        System.out.println("You cannot enter a date earlier than today!");
        return;
      }
    } catch (Exception e) {
      System.out.println("You have entered an invalid date!");
      return;
    }
    System.out.println("------------");
    for (int i = 1; i < timings.length; i++) {
      System.out.println(i + ". " + timings[i]);
    }
    System.out.println("Select a timing:");
    while (!sc.hasNextInt()) {
      System.out.println("You have inputted an invalid selection!\nSelect a timing:");
      sc.next();
    }
    int selectedTiming = sc.nextInt();
    if (selectedTiming < 1 || selectedTiming > timings.length) {
      System.out.println("You have selected an invalid option!");
      return;
    }
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma", Locale.ENGLISH);
    LocalTime time = LocalTime.parse(timings[selectedTiming], timeFormatter);
    crv.showAddContactNumberView();
    String contactNumber = sc.next();
    for (Reservation r : pos.getRestaurant().getReservations()) {
      if (r.getContactNumber().equals(contactNumber)) {
        System.out.println("This contact number has already been used in an existing reservation!");
        return;
      }
    }
    crv.showAddPaxView();
    while (!sc.hasNextInt()) {
      System.out.println("You have inputted an invalid entry!");
      crv.showAddPaxView();
      sc.next();
    }
    int pax = sc.nextInt();
    if (pax == 0) {
      System.out.println("Number of people has to be at least 1!");
      return;
    } else if (pax > 20) {
      System.out.println("Number of people cannot exceed 10!");
      return;
    }
    Reservation reservation = new Reservation(name, contactNumber, pax, date, time);
    int res = pos.getRestaurant().assignTableForReservation(reservation, false);
    if (res > -1) {
      reservation.setTable(res);
      System.out.println(
          "Reservation for table "
              + (res + 1)
              + " has been added for "
              + name
              + " at "
              + date
              + " "
              + time
              + " for "
              + pax
              + ".");
    } else {
      System.out.println(
          "Failed to reserve table as there are no more available tables on "
              + date
              + " at "
              + time
              + " for "
              + pax
              + "\n");
    }
  }

  /**
   * Checks for table availability based on the time, date, and table number that the user entered.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  public void checkAvailability(RRPSS pos) {
    LocalDate date;
    CreateReservationView crv = new CreateReservationView();
    RRPSS.updateView(pos, crv);
    Scanner sc = new Scanner(System.in);
    crv.showAddDateView();
    try {
      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      date = LocalDate.parse(sc.next(), dateFormatter);
      if (date.isBefore(LocalDateTime.now().toLocalDate())) {
        System.out.println("You cannot enter a date earlier than today!");
        return;
      }
    } catch (Exception e) {
      System.out.println("You have entered an invalid date!");
      return;
    }
    System.out.println("------------");
    for (int i = 1; i < timings.length; i++) {
      System.out.println(i + ". " + timings[i]);
    }
    System.out.println("Select a timing:");
    while (!sc.hasNextInt()) {
      System.out.println("You have inputted an invalid selection!\nSelect a timing:");
      sc.next();
    }
    int selectedTiming = sc.nextInt();
    if (selectedTiming < 1 || selectedTiming > timings.length) {
      System.out.println("You have selected an invalid option!");
      return;
    }
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma", Locale.ENGLISH);
    LocalTime time = LocalTime.parse(timings[selectedTiming], timeFormatter);
    crv.showAddPaxView();
    while (!sc.hasNextInt()) {
      System.out.println("You have inputted an invalid entry!");
      crv.showAddPaxView();
      sc.next();
    }
    int pax = sc.nextInt();
    if (pax == 0) {
      System.out.println("Number of people has to be at least 1!");
      return;
    } else if (pax > 20) {
      System.out.println("Number of people cannot exceed 10!");
      return;
    }
    Reservation reservation = new Reservation("", "", pax, date, time);
    int res = pos.getRestaurant().assignTableForReservation(reservation, true);
    if (res == -1) {
      System.out.println("Table not available for the specified date and time!\n");
    } else {
      System.out.println("Table available!\n");
    }
  }
}
