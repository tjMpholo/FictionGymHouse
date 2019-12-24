package com.fictiongym.controller;


import com.fictiongym.dao.GymMemberDao;
import com.fictiongym.dao.StaffMemberDao;
import com.fictiongym.model.GymMember;
import com.fictiongym.model.StaffMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private StaffMemberDao staffMemberDao;

    @Autowired
            private GymMemberDao gymMemberDao;


    Path path;

    @RequestMapping("/")
    public String home(){
        return "home";
    }


    /*
    protected Map workTitles(HttpServletRequest request) throws Exception{
        Map workTitles = new HashMap();

        Map<String,String> titles = new HashMap<String, String>();

        titles.put("1","Manager");
        titles.put("2","Sales Manager");
        titles.put("3","Instructor");
        titles.put("4","Sales Person");
        titles.put("5","General Worker");

        workTitles.put("workTitles", titles);
        return workTitles;
    }
    */

}
