package com.fictiongym.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Users implements Serializable {
    private static final long serialVersionUID = 8107730013306219616L;
    @Id
    @GeneratedValue
    private int usersId;

    private String username;
    private String password;
    private boolean enabled;

    private String staffMemberId;

    public String getStaffMemberId() {
        return staffMemberId;
    }

    public void setStaffMemberId(String staffMemberId) {
        this.staffMemberId = staffMemberId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return usersId;
    }

    public void setUserId(int userId) {
        this.usersId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
