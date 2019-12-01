package com.fictiongym.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
public class StaffMember {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String staffMemberId;

    @NotEmpty(message = "Your first name must not be empty.")
    private String firstName;
    @NotEmpty(message = "Your last name must not be empty.")
    private String lastName;
    @NotEmpty(message = "Please add a work title for the employee.")
    private String workTitle;
    @NotEmpty(message = "Please add the section in which the employee works.")
    private String section;

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

    public void setSection(String section) {
        this.section = section;
    }
}
