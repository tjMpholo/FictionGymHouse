package com.fictiongym.dao;

import com.fictiongym.model.StaffMember;
import com.fictiongym.model.Users;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserDao {

    void deleteUserByStaffMemberId(String memberId);

    void addNewUser(Users user);

    Users getUserByUsernameAndPassword(String username, String password);

    void updateUser(Users user);
}
