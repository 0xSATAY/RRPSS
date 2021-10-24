package com.cz2002g5.Model.Restaurant;

import com.cz2002g5.Model.Reservation.Reservation;

import java.util.ArrayList;

/**
 * The type Restaurant.
 */
public class Restaurant {
    /**
     * The constant TOTAL_TABLES.
     */
public static final Integer TOTAL_TABLES = 20;

    private final ArrayList<Table> tables;

    /**
     * Instantiates a new Restaurant.
     */
public Restaurant() {
        tables = new ArrayList<>();
        for (int i=0;i<TOTAL_TABLES;i++) {
            this.tables.add(new Table(i,((i+4)/4)*2));
        }
    }

    /**
     * Gets tables.
     *
     * @return the tables
     */
public ArrayList<Table> getTables() {
        return this.tables;
    }

    /**
     * Gets reservations.
     *
     * @return the reservations
     */
public ArrayList<Reservation> getReservations() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        for (Table t : this.tables) {
            reservations.addAll(t.getReservations());
        }
        return reservations;
    }

    /**
     * Assign table for reservation int.
     *
     * @param reservation the reservation
     * @param checkAvail the check avail
     * @return the int
     */
public int assignTableForReservation(Reservation reservation, boolean checkAvail) {
        boolean reservationFound = false;
        for (int i=0;i<this.tables.size();i++) {
            if (this.tables.get(i).getSeatingCapacity() >= reservation.getPax()) { // if capacity of table is greater than number of pax
                for (Reservation r : this.tables.get(i).getReservations()) { // find reservations for table.
                    if (r.getTime().toString().equals(reservation.getTime().toString()) && r.getDate().toString().equals(reservation.getDate().toString())) {
                        reservationFound = true;
                        break;
                    }
                }
                if (reservationFound) {
                    reservationFound = false;// if reservation is found, move on to next iteration
                    continue;
                }
                if (!checkAvail) { // if reservation is not found return data accordingly
                    this.tables.get(i).addReservation(reservation);
                }
                return i;
            }

        }
        return -1;
    }
}

