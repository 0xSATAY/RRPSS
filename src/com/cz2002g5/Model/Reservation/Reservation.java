package com.cz2002g5.Model.Reservation;

import java.util.Date;

public class Reservation {
    private Date dateTime;
    private Integer pax;
    private String name, contactNumber;

    public Reservation(String name, String contactNumber, Integer pax, Date dateTime) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.pax = pax;
        this.dateTime = dateTime;
    }

    public String getName() {
        return this.name;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public Integer getPax() {
        return this.pax;
    }

    public Date getDate() {
        return this.dateTime;
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

    public void setDate(Date newDate) {
        this.dateTime = newDate;
    }
}
