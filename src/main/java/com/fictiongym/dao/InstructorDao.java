package com.fictiongym.dao;

import com.fictiongym.model.Instructor;

import java.util.Date;
import java.util.List;

public interface InstructorDao {


    List<Instructor> getInstructorBookings(String staffMemberId,String gymMemberId, Date startDate, Date endDate);

    Instructor getInstructorByStaffId(String staffMemberId);

    Instructor getInstructorByInstructorId(String instructorId);
}
