package com.cz2002g5.Model.Restaurant;

import com.cz2002g5.Model.Reservation.Reservation;

import java.util.ArrayList;

public class Table {
    Integer id, seatingCapacity;
    ArrayList<Reservation> reservations = new ArrayList<>();

    public Boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    Boolean occupied = false;
    public Table(Integer id, Integer seatingCapacity) {
        this.id = id;
        this.seatingCapacity = seatingCapacity;
    }

    public Integer getTableID() {
        return this.id;
    }


    public ArrayList<Reservation> getReservations() {
        return this.reservations;
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public Integer getSeatingCapacity() {
        return this.seatingCapacity;
    }


}

