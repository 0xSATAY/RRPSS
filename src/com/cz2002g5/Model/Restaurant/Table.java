package com.cz2002g5.Model.Restaurant;

import com.cz2002g5.Model.Reservation.Reservation;

import java.util.ArrayList;

/**
 * The type Table.
 */
public class Table {
    /**
     * The Id.
     */
Integer id, /**
     * The Seating capacity.
     */
seatingCapacity;
    /**
     * The Reservations.
     */
ArrayList<Reservation> reservations = new ArrayList<>();
    /**
     * The Occupied.
     */
Boolean occupied = false;



    /**
     * Instantiates a new Table.
     *
     * @param id the id
     * @param seatingCapacity the seating capacity
     */
public Table(Integer id, Integer seatingCapacity) {
        this.id = id;
        this.seatingCapacity = seatingCapacity;
    }

    /**
     * Gets table id.
     *
     * @return the table id
     */
public Integer getTableID() {
        return this.id;
    }


    /**
     * Gets reservations.
     *
     * @return the reservations
     */
public ArrayList<Reservation> getReservations() {
        return this.reservations;
    }

    /**
     * Add reservation.
     *
     * @param reservation the reservation
     */
public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    /**
     * Gets seating capacity.
     *
     * @return the seating capacity
     */
public Integer getSeatingCapacity() {
        return this.seatingCapacity;
    }

    /**
     * Is occupied boolean.
     *
     * @return the boolean
     */
public Boolean isOccupied() {
        return occupied;
    }

    /**
     * Sets occupied.
     *
     * @param occupied the occupied
     */
public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }
}

