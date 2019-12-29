package com.fictiongym.service;

import com.fictiongym.model.StaffMember;
import com.fictiongym.model.Users;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserService {

    void deleteUserByMemberId(String memberId);

    void addNewUser(Users user);

    Users getUserByUsernameAndPassword(String username, String password);

    void updateUser(Users user);
}
