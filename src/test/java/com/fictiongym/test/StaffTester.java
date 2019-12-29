package com.fictiongym.test;

import com.fictiongym.dao.AuthorityDao;
import com.fictiongym.dao.GymMemberDao;
import com.fictiongym.dao.StaffMemberDao;
import com.fictiongym.dao.impl.StaffMemberImpl;
import com.fictiongym.model.Authorities;
import com.fictiongym.model.GymMember;
import com.fictiongym.model.StaffMember;
import com.fictiongym.service.AuthorityService;
import com.fictiongym.service.GymMemberService;
import com.fictiongym.service.StaffMemberService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
public class StaffTester {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GymMemberService gymMemberService;


    @Autowired
    private StaffMemberDao staffMemberDao;

    @Autowired
    private AuthorityDao authorityDao;

    @Autowired
    private StaffMemberService staffMemberService;

    @Autowired
    private AuthorityService authorityService;


    Session session;

    StaffMember staffMember;
    StaffMember staffMemberTest;
    GymMember gymMember;
    GymMember gymMemberTest;
    Authorities authority;

    @Before
    public void wireUpBeans(){
        //session = sessionFactory.getCurrentSession();

        staffMember = staffMemberService.getStaffMemberByRsaId("9606176298080");
        staffMemberTest = staffMemberService.getStaffMemberByEmailAddress("engelina@gmail.com");

        //authority = authorityDao.getAuthorityByUsername("T_Joseph_922");
        //newAuthority.setMemberId(gymMember.getMemberIdentifier());

        //gymMemberTest = gymMemberService.getGymMemberByEmailAddress("thato_m90@gmail.com");

        //session.flush();
    }

    @Test
    public void testStaffMemberID(){
        //gymMemberTest != null && !gymMemberTest.getMemberIdentifier().equals(gymMember.getMemberIdentifier())
        assertThat(staffMember.getRsaIdNumber(),is(staffMemberTest.getRsaIdNumber()));
    }


    @After
    public void endTest(){

    }
}
