package com.fictiongym.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class StaffMember {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String staffMemberId;

    @NotEmpty(message = "Id number cannot be empty")
    private String rsaIdNumber;

    @NotEmpty(message = "Your first name must not be empty.")
    private String firstName;
    @NotEmpty(message = "Your last name must not be empty.")
    private String lastName;
    @NotEmpty(message = "Please add a work title for the employee.")
    private String workTitle;
    @NotEmpty(message = "Please add the section in which the employee works.")
    private String section;

    private boolean isProfileSet = false;

    private String imagePath = "male_default";

    private String username;

    private String password;

    @Column(name="registrationDate", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable=false)
    private Calendar registrationDate;

    private Date lastLoginDate;

    private Calendar lastUpdateDate;

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

    public Calendar getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Calendar lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean getIsProfileSet() {
        return isProfileSet;
    }

    public void setIsProfileSet(boolean profileSet) {
        isProfileSet = profileSet;
    }

    @Transient
    private MultipartFile staffMemberPicture;

    public MultipartFile getStaffMemberPicture() {
        return staffMemberPicture;
    }

    public void setStaffMemberPicture(MultipartFile staffMemberPicture) {
        this.staffMemberPicture = staffMemberPicture;
    }

    public String getStaffMemberId() {
        return staffMemberId;
    }

    public void setStaffMemberId(String staffMemberId) {
        this.staffMemberId = staffMemberId;
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

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public String getSection() {
        return section;
    }

    public String getRsaIdNumber() {
        return rsaIdNumber;
    }

    public void setRsaIdNumber(String rsaIdNumber) {
        this.rsaIdNumber = rsaIdNumber;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
