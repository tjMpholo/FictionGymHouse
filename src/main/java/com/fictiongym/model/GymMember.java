package com.fictiongym.model;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class GymMember {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String defaultId;

    @NotEmpty(message = "Please provide an RSA ID number or any passport number.")
    private String memberIdentifier;

    @NotEmpty(message = "Please provide a name")
    private String firstName;

    @NotEmpty(message = "Please enter your surname")
    private String lastName;

    @NotEmpty(message = "Please provide your cellphone number")
    private String cellphone;

    @NotEmpty(message = "Please provide an email address")
    private String emailAddress;

    @NotEmpty(message = "Please enter your physical address")
    private String physicalAddress;

    @NotEmpty(message = "Please enter a date of birth")
    private String dateOfBirth;

    @NotEmpty(message = "Please choose a gender")
    private String gender;

    private boolean isRsaCitizen;

    public String getDefaultId() {
        return defaultId;
    }

    public void setDefaultId(String defaultId) {
        this.defaultId = defaultId;
    }

    public String getMemberIdentifier() {
        return memberIdentifier;
    }

    public void setMemberIdentifier(String memberIdentifier) {
        this.memberIdentifier = memberIdentifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isRsaCitizen() {
        return isRsaCitizen;
    }

    public void setRsaCitizen(boolean rsaCitizen) {
        isRsaCitizen = rsaCitizen;
    }
}
