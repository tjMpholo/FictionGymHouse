package com.fictiongym.controller;


import com.fictiongym.dao.InstructorDao;
import com.fictiongym.model.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller()
@RequestMapping("/Instructor")
public class InstructorController {

    @Autowired
    private InstructorDao instructorDao;

    @RequestMapping("/GetInstructorBookings")
    public String getInstructorBookings(@ModelAttribute("instructor") Instructor instructor, Model model){
        List<Instructor> list = instructorDao.getInstructorBookings("22",
                "11",
                new GregorianCalendar(2019, Calendar.FEBRUARY, 22).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 29).getTime());

        model.addAttribute("bookings",list);

        return "instructor_booking";
    }
}


