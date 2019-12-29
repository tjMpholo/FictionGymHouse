package com.fictiongym.dao.impl;

import com.fictiongym.dao.AuthorityDao;
import com.fictiongym.model.Authorities;
import com.fictiongym.model.Users;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;


@Repository
@Transactional
public class AuthorityDaoImpl implements AuthorityDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void deleteAuthorityByMemberId(String memberId) {
        Session session = sessionFactory.getCurrentSession();

        Query spSQLQuery = session.createSQLQuery("DELETE FROM authorities WHERE memberid = :param1");
        spSQLQuery.setString("param1",memberId);

        spSQLQuery.executeUpdate();
    }

    @Override
    public Authorities getAuthorityByMemberId(String memberId){
        Session session = sessionFactory.getCurrentSession();

        Authorities authority = (Authorities)session.get(Authorities.class,memberId);

        session.flush();

        return authority;
    }

    @Override
    public void addNewAuthority(Authorities authority) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(authority);
        session.flush();
    }

    @Override
    public Authorities getAuthorityByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        Query spSQLQuery = session.createSQLQuery("SELECT at.authoritiesid FROM authorities at WHERE at.username = :param1");
        //spSQLQuery.setString("param1",username);
        spSQLQuery.setString("param1",username);

        int authorityId = (int)spSQLQuery.uniqueResult();

        session.flush();

        Authorities authority = (Authorities) session.get(Authorities.class,authorityId);


        StringSelection stringSelection = new StringSelection(authority.getAuthority() + " ,,,");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        return authority;
    }

    @Override
    public void updateAuthority(Authorities authority) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(authority);
        session.flush();
    }
}
