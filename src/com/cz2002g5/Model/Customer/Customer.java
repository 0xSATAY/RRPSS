package com.cz2002g5.Model.Customer;


import com.cz2002g5.Model.Misc.Gender;

public class Customer {
    private String name, contactNumber;
    private Gender gender;
    Boolean membership;

    public Customer(String name, Gender gender, String contactNumber, Boolean membership) {
        this.name = name;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.membership = membership;
    }

    public String getName() {
        return this.name;
    }

    public Gender getGender() {
        return this.gender;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public Boolean isMember() {
        return this.membership;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setContactNumber(String newContactNumber) {
        this.contactNumber = newContactNumber;
    }

    public void changeMembership() {
        this.membership = !this.membership;
    }
}

