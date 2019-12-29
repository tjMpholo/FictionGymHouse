package com.fictiongym.dao.impl;

import com.fictiongym.dao.UserDao;
import com.fictiongym.model.StaffMember;
import com.fictiongym.model.Users;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;


@Repository
@Transactional
public class UserImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void deleteUserByStaffMemberId(String memberId) {
        Session session = sessionFactory.getCurrentSession();

        Query spSQLQuery = session.createSQLQuery("DELETE FROM users WHERE staffmemberid = :param1");
        spSQLQuery.setString("param1",memberId);

        spSQLQuery.executeUpdate();
    }

    @Override
    public void addNewUser(Users user) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(user);
        session.flush();
    }

    @Override
    public Users getUserByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.getCurrentSession();

        Query spSQLQuery = session.createSQLQuery("SELECT us.usersid FROM users us WHERE us.username = :param1 and us.password = :param2");
        spSQLQuery.setString("param1",username.trim());
        spSQLQuery.setString("param2",password.trim());

        int userId = (int)spSQLQuery.uniqueResult();

        session.flush();


        Users user = (Users) session.get(Users.class,userId);

        session.flush();

        return user;
    }

    @Override
    public void updateUser(Users user) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(user);
        session.flush();
    }
}
