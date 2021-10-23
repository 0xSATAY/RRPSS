package com.cz2002g5.Model.Reservation;

import com.cz2002g5.Model.Restaurant.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Reservation {
    private LocalDate date;
    private LocalTime time;
    private Integer pax;
    private int table;
    private String name, contactNumber;

    public Reservation(String name, String contactNumber, Integer pax, LocalDate date, LocalTime time) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.pax = pax;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return this.name;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public Integer getPax() {
        return this.pax;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setContactNumber(String newContactNumber) {
        this.contactNumber = newContactNumber;
    }

    public void setPax(Integer newPax) {
        this.pax = newPax;
    }

    public void setDate(LocalDate newDate) {
        this.date = newDate;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void showInfo() {
        System.out.println(this.date + " " + this.time + "\nName: " + this.name + "\nContact No.: " + this.contactNumber + "\nPax: " + this.pax);
    }

    public int getTableNumber() {
        return this.table;
    }
}
