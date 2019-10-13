package com.fictiongym.controller;


import com.fictiongym.dao.StaffMemberDao;
import com.fictiongym.model.StaffMember;
import com.sun.javafx.scene.layout.region.SliceSequenceConverter;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private StaffMemberDao staffMemberDao;

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/gymstaff")
    public String gymStaff(Model model){
        List<StaffMember> staffMembers = staffMemberDao.getAllStaffMembers();

        model.addAttribute("staffMembers",staffMembers);
        return "gymstaff";
    }

    @RequestMapping("/gymstaff/staff_detailed/{staffMemberId}")
    public String viewIndividualStaffDetail(@PathVariable String staffMemberId, Model model ) throws Exception{
        StaffMember staffMember = staffMemberDao.getStaffMemberByStaffId(staffMemberId);

        model.addAttribute("staffMember",staffMember);
        return "staff_detailed";
    }

    @RequestMapping("/gymstaff/addNewStaffMember")
    public String addNewStaffMember(Model model){
        StaffMember staffMember = new StaffMember();



        model.addAttribute("staffMember",staffMember);

        return "addNewStaffMember";
    }

    @RequestMapping(value = "/gymstaff/addNewStaffMember", method = RequestMethod.POST)
    public String addStaffMemberPost(@ModelAttribute("staffMember") StaffMember staffMember, BindingResult result, HttpServletRequest request){
        staffMemberDao.addStaffMember(staffMember);
        return "redirect:/gymstaff";
    }

    @RequestMapping("/gymstaff/edit_StaffMemberDetail/{staffMemberId}")
    public String editStaffMemberDetail(@PathVariable String staffMemberId, Model model){
        StaffMember staffMember = staffMemberDao.getStaffMemberByStaffId(staffMemberId);

        model.addAttribute("staffMember",staffMember);

        return "edit_StaffMemberDetail";
    }

    @RequestMapping(value = "/gymstaff/edit_StaffMemberDetail", method = RequestMethod.POST)
    public String editStaffMemberDetail(@ModelAttribute("staffMember") StaffMember staffMember, BindingResult bindingResult, HttpServletRequest servletRequest){
        staffMemberDao.updateStaffMember(staffMember);

        return "redirect:/gymstaff";
    }

    @RequestMapping("/gymstaff/delete_StaffDetail/{staffMemberId}")
    public String deleteStaffMember(@PathVariable String staffMemberId,  Model model){
        staffMemberDao.deleteStaffMember(staffMemberId);
        return "redirect:/gymstaff";
    }
}
