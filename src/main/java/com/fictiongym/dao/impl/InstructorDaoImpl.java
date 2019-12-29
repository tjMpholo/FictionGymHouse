package com.fictiongym.dao.impl;

import com.fictiongym.dao.InstructorDao;
import com.fictiongym.model.Instructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InstructorDaoImpl implements InstructorDao {


    @Override
    public List<Instructor> getInstructorBookings(String staffMemberId,String gymMemberId, Date startDate, Date endDate) {
        List<Instructor> list = new ArrayList<>();

        Instructor instructor = new Instructor();

        instructor.setInstructorId(1);
        instructor.setGymMemberId("11");
        instructor.setStaffMemberId("22");
        instructor.setSessionComment("Great session");
        instructor.setSessionDate(new GregorianCalendar(2019, Calendar.FEBRUARY, 29,10,00).getTime());

        Instructor instructor2 = new Instructor();

        instructor2.setInstructorId(2);
        instructor2.setGymMemberId("11");
        instructor2.setStaffMemberId("22");
        instructor2.setSessionComment("Great session");
        instructor2.setSessionDate(new GregorianCalendar(2019, Calendar.FEBRUARY, 28,11,00).getTime());

        Instructor instructor3 = new Instructor();

        instructor3.setInstructorId(3);
        instructor3.setGymMemberId("11");
        instructor3.setStaffMemberId("22");
        instructor3.setSessionComment("Great session");
        instructor3.setSessionDate(new GregorianCalendar(2019, Calendar.FEBRUARY, 27,8,00).getTime());

        Instructor instructor4 = new Instructor();

        instructor4.setInstructorId(2);
        instructor4.setGymMemberId("11");
        instructor4.setStaffMemberId("22");
        instructor4.setSessionComment("Great session");
        instructor4.setSessionDate(new GregorianCalendar(2019, Calendar.FEBRUARY, 25,14,00).getTime());

        Instructor instructor5 = new Instructor();

        instructor5.setInstructorId(2);
        instructor5.setGymMemberId("11");
        instructor5.setStaffMemberId("22");
        instructor5.setSessionComment("Great session");
        instructor5.setSessionDate(new GregorianCalendar(2019, Calendar.FEBRUARY, 23,17,00).getTime());

        list.add(instructor);
        list.add(instructor2);
        list.add(instructor3);
        list.add(instructor4);
        list.add(instructor5);

        return null;
    }

    @Override
    public Instructor getInstructorByStaffId(String staffMemberId) {
        return null;
    }

    @Override
    public Instructor getInstructorByInstructorId(String instructorId) {
        Instructor instructor = new Instructor();

        instructor.setInstructorId(1);
        instructor.setGymMemberId("11");
        instructor.setStaffMemberId("22");
        instructor.setSessionComment("Great session");
        instructor.setSessionDate(new GregorianCalendar(2019, Calendar.FEBRUARY, 29,12,00).getTime());
        return instructor;
    }


}
