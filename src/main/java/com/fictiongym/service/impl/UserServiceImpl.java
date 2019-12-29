package com.fictiongym.service.impl;

import com.fictiongym.dao.UserDao;
import com.fictiongym.model.Users;
import com.fictiongym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void deleteUserByMemberId(String memberId) {
        userDao.deleteUserByStaffMemberId(memberId);
    }


    @Override
    public void addNewUser(Users user) {
        userDao.addNewUser(user);
    }

    @Override
    public Users getUserByUsernameAndPassword(String username, String password) {
        return userDao.getUserByUsernameAndPassword( username, password);
    }

    @Override
    public void updateUser(Users user) {
        userDao.updateUser(user);
    }
}
