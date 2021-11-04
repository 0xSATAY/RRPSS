package com.cz2002g5.Model.Restaurant;

import com.cz2002g5.Model.Reservation.Reservation;

import java.util.ArrayList;

/** The Table model of tables in the restaurant. */
public class Table {

  /** The table's ID. */
  Integer id,
      /** The Seating capacity of the table. */
      seatingCapacity;

  /** The Reservations of the table. */
  ArrayList<Reservation> reservations = new ArrayList<>();

  /** The state of the table whether it is occupied. */
  Boolean occupied = false;

  /**
   * Instantiates a new Table.
   *
   * @param id the ID of the table.
   * @param seatingCapacity the seating capacity of the table.
   */
  public Table(Integer id, Integer seatingCapacity) {
    this.id = id;
    this.seatingCapacity = seatingCapacity;
  }

  /**
   * Getter method to retrieve the table's ID.
   *
   * @return the table id
   */
  public Integer getTableID() {
    return this.id;
  }

  /**
   * Getter method to retrieve all reservations of the table.
   *
   * @return the reservations of the table.
   */
  public ArrayList<Reservation> getReservations() {
    return this.reservations;
  }

  /**
   * Add reservation to the table.
   *
   * @param reservation the reservation that is to be added to this table.
   */
  public void addReservation(Reservation reservation) {
    this.reservations.add(reservation);
  }

  /**
   * Getter method that retrieves the seating capacity of the table.
   *
   * @return the seating capacity of the table.
   */
  public Integer getSeatingCapacity() {
    return this.seatingCapacity;
  }

  /**
   * Checks if the table is occupied.
   *
   * @return whether the table is currently occupied
   */
  public Boolean isOccupied() {
    return occupied;
  }

  /**
   * Sets the state of occupancy of the table.
   *
   * @param occupied the state of occupancy of the table.
   */
  public void setOccupied(Boolean occupied) {
    this.occupied = occupied;
  }
}
