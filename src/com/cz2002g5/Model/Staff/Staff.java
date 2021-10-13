package com.cz2002g5.Model.Staff;

import com.cz2002g5.Model.Misc.Gender;

public class Staff {
    private String name, employeeID;
    private Gender gender;
    private JobTitle jobTitle;

    public Staff(String name, String employeeID, Gender gender, JobTitle jobTitle) {
        this.name = name;
        this.employeeID = employeeID;
        this.gender = gender;
        this.jobTitle = jobTitle;
    }

    public String getName() {
        return this.name;
    }

    public String getEmployeeID() {
        return this.employeeID;
    }

    public Gender getGender() {
        return this.gender;
    }

    public JobTitle getJobTitle() {
        return this.jobTitle;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setGender(Gender newGender) {
        this.gender = newGender;
    }

    public void setJobTitle(JobTitle newJobTitle) {
        this.jobTitle = newJobTitle;
    }
}

