package com.fictiongym.dao.impl;

import com.fictiongym.dao.GymMemberDao;
import com.fictiongym.model.GymMember;
import com.fictiongym.model.StaffMember;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Calendar;
import java.util.List;

@Repository
@Transactional
public class GymMemberImpl implements GymMemberDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addNewGymMember(GymMember member) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(member);
        session.flush();
    }

    @Override
    public void updateGymMember(GymMember member) {
        Session session = sessionFactory.getCurrentSession();

        member.setLastUpdateDate(Calendar.getInstance());
        session.saveOrUpdate(member.getDefaultId(), member);
        session.flush();
    }

    @Override
    public List<GymMember> getAllGymMembers() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from GymMember");

        List<GymMember> memberList = query.list();

        session.flush();

        return memberList;
    }

    @Override
    public GymMember getGymMemberById(String defaultId) {
        Session session = sessionFactory.getCurrentSession();

        GymMember member = (GymMember)session.get(GymMember.class,defaultId);

        session.flush();

        return member;
    }

    @Override
    public void deleteMember(String memberId) {
        Session session = sessionFactory.getCurrentSession();

        session.delete(getGymMemberById(memberId));

        session.flush();
    }

    @Override
    public GymMember getGymMemberByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.getCurrentSession();


        StringSelection stringSelection = new StringSelection( " UID ");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        try {
            Query spSQLQuery = session.createSQLQuery("SELECT gm.defaultid FROM gymmember gm WHERE gm.username = :param1 and gm.password = :param2");
            spSQLQuery.setString("param1",username);
            spSQLQuery.setString("param2",password);

            String defaultId = (String)spSQLQuery.uniqueResult();

            session.flush();

            GymMember gymMember = (GymMember)session.get(GymMember.class,defaultId);

            session.flush();

            return gymMember;
        }
        catch (Exception ex){
            return new GymMember();
        }
    }

    @Override
    public GymMember getGymMemberByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        try {
            Query spSQLQuery = session.createSQLQuery("SELECT gm.defaultid FROM gymmember gm WHERE gm.username = :param1");
            spSQLQuery.setString("param1",username);

            String defaultId = (String)spSQLQuery.uniqueResult();

            session.flush();

            GymMember gymMember = (GymMember)session.get(GymMember.class,defaultId);

            session.flush();

            return gymMember;
        }
        catch (Exception ex){
            return new GymMember();
        }
    }

    @Override
    public GymMember getGymMemberByRsaId(String memberIdNo) {
        Session session = sessionFactory.getCurrentSession();

        try {
            Query spSQLQuery = session.createSQLQuery("SELECT gm.defaultid FROM gymmember gm WHERE gm.memberidentifier = :param1");
            spSQLQuery.setString("param1",memberIdNo);

            String defaultId = (String)spSQLQuery.uniqueResult();

            session.flush();

            GymMember gymMember = (GymMember)session.get(GymMember.class,defaultId);

            session.flush();

            return gymMember;
        }
        catch (Exception ex){
            return null;
        }
    }

    public GymMember getGymMemberByEmailAddress(String emailAddress) {
        Session session = sessionFactory.getCurrentSession();

        try {
            Query spSQLQuery = session.createSQLQuery("SELECT gm.defaultid FROM gymmember gm WHERE gm.emailaddress = :param1");
            spSQLQuery.setString("param1",emailAddress);

            String defaultId = (String)spSQLQuery.uniqueResult();

            GymMember gymMember = (GymMember)session.get(GymMember.class,defaultId);

            session.flush();

            return gymMember;
        }
        catch (Exception ex){
            return null;
        }
    }
}
