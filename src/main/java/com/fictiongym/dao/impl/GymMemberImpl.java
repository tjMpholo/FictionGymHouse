package com.fictiongym.dao.impl;

import com.fictiongym.dao.GymMemberDao;
import com.fictiongym.model.GymMember;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

        session.saveOrUpdate(member);
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
    public GymMember getGymMemberById(String memberId) {
        Session session = sessionFactory.getCurrentSession();

        GymMember member = (GymMember)session.get(GymMember.class,memberId);

        session.flush();

        return member;
    }

    @Override
    public void deleteMember(String memberId) {
        Session session = sessionFactory.getCurrentSession();

        session.delete(getGymMemberById(memberId));

        session.flush();
    }
}
