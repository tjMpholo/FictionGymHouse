package com.fictiongym.dao.impl;

import com.fictiongym.dao.StaffMemberDao;
import com.fictiongym.helper.MemberHelper;
import com.fictiongym.model.Authorities;
import com.fictiongym.model.GymMember;
import com.fictiongym.model.StaffMember;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Repository
@Transactional
public class StaffMemberImpl implements StaffMemberDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addStaffMember(StaffMember staffMember) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(staffMember);
        session.flush();
    }

    public void updateStaffMember(StaffMember staffMember) {
        Session session = sessionFactory.getCurrentSession();

        staffMember.setLastUpdateDate(Calendar.getInstance());
        session.saveOrUpdate(staffMember);
        session.flush();
    }

    public StaffMember getStaffMemberByStaffId(String staffMemberId) {
        Session session = sessionFactory.getCurrentSession();

        StaffMember staffMember = (StaffMember)session.get(StaffMember.class,staffMemberId);
        return staffMember;
    }

    public List<StaffMember> getAllStaffMembers() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from StaffMember");

        List<StaffMember> staffMembers = query.list();

        session.flush();

        return staffMembers;
    }

    public void deleteStaffMember(String staffMemberId) {
        Session session = sessionFactory.getCurrentSession();

        session.delete(getStaffMemberByStaffId(staffMemberId));

        session.flush();
    }

    @Override
    public String getStaffMemberRoleCode(String roleDesc) {
        return MemberHelper.getStaffMemberRoleCode(roleDesc);
    }

    public StaffMember getStaffMemberByUsernameAndPassword(String username, String password){
        Session session = sessionFactory.getCurrentSession();

        try {
            Query spSQLQuery = session.createSQLQuery("SELECT st.staffmemberid FROM staffmember st WHERE st.username = :param1 and st.password = :param2");
            spSQLQuery.setString("param1",username);
            spSQLQuery.setString("param2",password);

            String staffId = (String)spSQLQuery.uniqueResult();

            session.flush();

            StaffMember staffMember = (StaffMember)session.get(StaffMember.class,staffId);

            session.flush();

            return staffMember;
        }
        catch (Exception ex){
            return new StaffMember();
        }
    }

    @Override
    public StaffMember getStaffMemberByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        try {
            Query spSQLQuery = session.createSQLQuery("SELECT st.staffmemberid FROM staffmember st WHERE st.username = :param1");
            spSQLQuery.setString("param1",username);

            String staffId = (String)spSQLQuery.uniqueResult();

            session.flush();

            StaffMember staffMember = (StaffMember)session.get(StaffMember.class,staffId);

            session.flush();

            return staffMember;
        }
        catch (Exception ex){
            return new StaffMember();
        }
    }

    @Override
    public StaffMember getStaffMemberByRsaId(String memberIdNo) {
        Session session = sessionFactory.getCurrentSession();

        try {
            Query spSQLQuery = session.createSQLQuery("SELECT stm.staffmemberid FROM staffmember stm WHERE stm.rsaidnumber = :param1");
            spSQLQuery.setString("param1",memberIdNo);

            String defaultId = (String)spSQLQuery.uniqueResult();

            session.flush();

            StaffMember staffMember = (StaffMember)session.get(StaffMember.class,defaultId);

            session.flush();

            return staffMember;
        }
        catch (Exception ex){
            return null;
        }
    }

    public StaffMember getStaffMemberByEmailAddress(String emailAddress) {
        Session session = sessionFactory.getCurrentSession();

        try {
            Query spSQLQuery = session.createSQLQuery("SELECT stm.staffmemberid FROM staffmember stm WHERE stm.emailaddress = :param1");
            spSQLQuery.setString("param1",emailAddress);

            String defaultId = (String)spSQLQuery.uniqueResult();

            session.flush();

            StaffMember staffMember = (StaffMember)session.get(StaffMember.class,defaultId);



            session.flush();

            return staffMember;
        }
        catch (Exception ex){
            return null;
        }
    }
}
