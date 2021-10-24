package com.cz2002g5.Model.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The type Reservation.
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
     * @param name the name
     * @param contactNumber the contact number
     * @param pax the pax
     * @param date the date
     * @param time the time
     */
public Reservation(String name, String contactNumber, Integer pax, LocalDate date, LocalTime time) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.pax = pax;
        this.date = date;
        this.time = time;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
public String getName() {
        return this.name;
    }

    /**
     * Sets table.
     *
     * @param table the table
     */
public void setTable(int table) {
        this.table = table;
    }

    /**
     * Gets contact number.
     *
     * @return the contact number
     */
public String getContactNumber() {
        return this.contactNumber;
    }

    /**
     * Gets pax.
     *
     * @return the pax
     */
public Integer getPax() {
        return this.pax;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
public LocalDate getDate() {
        return this.date;
    }

    /**
     * Sets name.
     *
     * @param newName the new name
     */
public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Sets contact number.
     *
     * @param newContactNumber the new contact number
     */
public void setContactNumber(String newContactNumber) {
        this.contactNumber = newContactNumber;
    }

    /**
     * Sets pax.
     *
     * @param newPax the new pax
     */
public void setPax(Integer newPax) {
        this.pax = newPax;
    }

    /**
     * Sets date.
     *
     * @param newDate the new date
     */
public void setDate(LocalDate newDate) {
        this.date = newDate;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
public LocalTime getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Show info.
     */
public void showInfo() {
        System.out.println(this.date + " " + this.time + "\nName: " + this.name + "\nContact No.: " + this.contactNumber + "\nPax: " + this.pax);
    }

    /**
     * Gets table number.
     *
     * @return the table number
     */
public int getTableNumber() {
        return this.table;
    }
}
