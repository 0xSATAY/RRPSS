package com.cz2002g5.Model.Restaurant;

public class Table {
    Integer id, seatingCapacity;
    Boolean booked;

    public Boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    Boolean occupied = false;
    public Table(Integer id, Integer seatingCapacity, Boolean booked) {
        this.id = id;
        this.seatingCapacity = seatingCapacity;
        this.booked = booked;
    }

    public Integer getTableID() {
        return this.id;
    }

    public Integer getSeatingCapacity() {
        return this.seatingCapacity;
    }

    public Boolean isBooked() {
        return this.booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}

