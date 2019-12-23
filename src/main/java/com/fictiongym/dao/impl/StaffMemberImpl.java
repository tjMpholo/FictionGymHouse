package com.fictiongym.dao.impl;

import com.fictiongym.dao.StaffMemberDao;
import com.fictiongym.model.StaffMember;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Repository
@Transactional
public class StaffMemberImpl implements StaffMemberDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addStaffMember(StaffMember staffMember) {
        Session session = sessionFactory.getCurrentSession();
        staffMember.setUsername(staffMember.getFirstName().substring(0,1) + "_" + staffMember.getLastName());
        staffMember.setPassword("$!" + String.valueOf((int)(Math.random() * 10000)));
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
}
