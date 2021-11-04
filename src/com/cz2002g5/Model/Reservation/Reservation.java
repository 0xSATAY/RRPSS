package com.cz2002g5.Model.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The Reservation model for reservations.
 */
public class Reservation {
  private LocalDate date;
  private LocalTime time;
  private Integer pax;
  private int table;
  private String name, contactNumber;

  /**
   * Instantiates a new Reservation.
   *
   * @param name the name of the reservee
   * @param contactNumber the contact number of the reservee
   * @param pax the number of customers for the reservation
   * @param date the date of the reservation
   * @param time the time of the reservation
   */
public Reservation(
      String name, String contactNumber, Integer pax, LocalDate date, LocalTime time) {
    this.name = name;
    this.contactNumber = contactNumber;
    this.pax = pax;
    this.date = date;
    this.time = time;
  }

  /**
   * Getter method that retrieves the name of the reservee.
   *
   * @return the name of the reservee
   */
public String getName() {
    return this.name;
  }

  /**
   * Sets table of the reservation.
   *
   * @param table the table that is being reserved for this reservation.
   */
public void setTable(int table) {
    this.table = table;
  }

  /**
   * Getter method to retrieve the contact number of the reservee.
   *
   * @return the contact number of the reservee
   */
public String getContactNumber() {
    return this.contactNumber;
  }

  /**
   * Getter method to retrieve the number of customers for the reservation.
   *
   * @return the number of customers for the reservation0
   */
public Integer getPax() {
    return this.pax;
  }

  /**
   * Getter method to retrieve the date of the reservation.
   *
   * @return the date of the reservation
   */
public LocalDate getDate() {
    return this.date;
  }

  /**
   * Sets the name of the reservee if there is a change in the reservee.
   *
   * @param newName the new name of the reservee.
   */
public void setName(String newName) {
    this.name = newName;
  }

  /**
   * Sets contact number of the reservee if there is a change in the reservee.
   *
   * @param newContactNumber the new contact number of the reservee.
   */
public void setContactNumber(String newContactNumber) {
    this.contactNumber = newContactNumber;
  }

  /**
   * Sets the number of customers for the reservation if there is a change in the number of customers.
   *
   * @param newPax the new number of customers for the reservation
   */
public void setPax(Integer newPax) {
    this.pax = newPax;
  }

  /**
   * Sets new date of the reservation if there is a change in date of the reservation.
   *
   * @param newDate the new date of the reservation
   */
public void setDate(LocalDate newDate) {
    this.date = newDate;
  }

  /**
   * Getter method that retrieves the time of the reservation.
   *
   * @return the time of the reservation
   */
public LocalTime getTime() {
    return time;
  }

  /**
   * Sets a new time of the reservation if there is a change in time of the reservation.
   *
   * @param time the new time of the reservation.
   */
public void setTime(LocalTime time) {
    this.time = time;
  }

  /**
   * Prints a nicely formatted information string about the reservation to the standard output.
   */
public void showInfo() {
    System.out.println(
        this.date
            + " "
            + this.time
            + "\nName: "
            + this.name
            + "\nContact No.: "
            + this.contactNumber
            + "\nPax: "
            + this.pax);
  }

  /**
   * Getter method that retrieves the table number of the reservation.
   *
   * @return the table number of the reservation
   */
public int getTableNumber() {
    return this.table;
  }
}
