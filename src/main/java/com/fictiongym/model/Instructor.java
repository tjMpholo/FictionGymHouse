package com.fictiongym.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Instructor implements Serializable {
    private static final long serialVersionUID = 217183997429818397L;

    @Id
    @GeneratedValue
    private int instructorId;

    private String staffMemberId;

    private String gymMemberId;

    private Date sessionDate;

    private String sessionComment;

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getStaffMemberId() {
        return staffMemberId;
    }

    public void setStaffMemberId(String staffMemberId) {
        this.staffMemberId = staffMemberId;
    }

    public String getGymMemberId() {
        return gymMemberId;
    }

    public void setGymMemberId(String gymMemberId) {
        this.gymMemberId = gymMemberId;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getSessionComment() {
        return sessionComment;
    }

    public void setSessionComment(String sessionComment) {
        this.sessionComment = sessionComment;
    }
}
