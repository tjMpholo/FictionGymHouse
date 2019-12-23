package com.fictiongym.test;

import com.fictiongym.dao.impl.StaffMemberImpl;
import com.fictiongym.model.StaffMember;
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
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
public class StaffTester {

    @Autowired
    private SessionFactory sessionFactory;


    Session session;

    StaffMember staffMember;

    @Before
    public void wireUpBeans(){
        session = sessionFactory.getCurrentSession();
        staffMember = (StaffMember)session.get(StaffMember.class,"4028b8816efd3108016efd3641a50001");

        session.flush();

    }

    @Test
    public void testStaffMemberID(){

    }


    @After
    public void endTest(){

    }
}
