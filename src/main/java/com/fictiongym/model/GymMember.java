package com.fictiongym.model;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
public class GymMember {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String defaultId;

    @Column(unique = true)
    @NotEmpty(message = "Please provide an RSA ID number or any passport number.")
    private String memberIdentifier;

    @NotEmpty(message = "Please provide a name")
    private String firstName;

    @NotEmpty(message = "Please enter your surname")
    private String lastName;

    @NotEmpty(message = "Please provide your cellphone number")
    private String cellphone;

    @Column(unique = true)
    @NotEmpty(message = "Please provide an email address")
    private String emailAddress;

    @NotEmpty(message = "Please enter your physical address")
    private String physicalAddress;

    @NotEmpty(message = "Please enter a date of birth")
    private String dateOfBirth;

    @NotEmpty(message = "Please choose a gender")
    private String gender;

    private boolean isRsaCitizen;

    private String username;

    private String password;

    @Transient
    private MultipartFile memberProfilePicture;

    private boolean isProfileSet = false;

    private String imagePath = "male_default";


    @Column(name="registrationDate", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable=false)
    private Calendar registrationDate;

    private Date lastLoginDate;

    private Calendar lastUpdateDate;

    public MultipartFile getMemberProfilePicture() {
        return memberProfilePicture;
    }

    public Calendar getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Calendar lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public boolean getIsProfileSet() {
        return isProfileSet;
    }

    public void setIsProfileSet(boolean profileSet) {
        isProfileSet = profileSet;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setMemberProfilePicture(MultipartFile memberProfilePicture) {
        this.memberProfilePicture = memberProfilePicture;
    }

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

    public boolean getIsRsaCitizen() {
        return isRsaCitizen;
    }

    public void setIsRsaCitizen(boolean rsaCitizen) {
        isRsaCitizen = rsaCitizen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Calendar registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
