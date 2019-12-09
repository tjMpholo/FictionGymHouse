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
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
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
    public String addStaffMemberPost(@Valid @ModelAttribute("staffMember") StaffMember staffMember, BindingResult result, HttpServletRequest request){
        if (result.hasErrors()){
            return "addNewStaffMember";
        }

        staffMemberDao.addStaffMember(staffMember);

        MultipartFile staffMemberPicture = staffMember.getStaffMemberPicture();

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + staffMember.getStaffMemberId() + ".jpg");

        if (staffMemberPicture != null && !staffMemberPicture.isEmpty()){
            try {
                staffMemberPicture.transferTo(new File(path.toString()));
            }
            catch (Exception ex){
                throw new RuntimeException("Image path invalid " + ex.getMessage());
            }
        }
        return "redirect:/gymstaff";
    }

    @RequestMapping(value = "/gym_members/addNewGymMember", method = RequestMethod.POST)
    public String addNewGymMemberPost(@Valid @ModelAttribute("gymMember") GymMember gymMember,BindingResult result, HttpServletRequest request ){
        if (result.hasErrors()){
            return "add_new_gym_member";
        }

        gymMemberDao.addNewGymMember(gymMember);

        return "redirect:/gym_members";
    }

    @RequestMapping("/gymstaff/edit_StaffMemberDetail/{staffMemberId}")
    public String editStaffMemberDetail(@PathVariable String staffMemberId, Model model){
        StaffMember staffMember = staffMemberDao.getStaffMemberByStaffId(staffMemberId);

        model.addAttribute("staffMember",staffMember);

        return "edit_StaffMemberDetail";
    }

    @RequestMapping(value = "/gymstaff/edit_StaffMemberDetail", method = RequestMethod.POST)
    public String editStaffMemberDetail(@Valid @ModelAttribute("staffMember") StaffMember staffMember, BindingResult result, HttpServletRequest request){
        if (result.hasErrors()){
            return "edit_StaffMemberDetail";
        }

        MultipartFile staffMemberImage = staffMember.getStaffMemberPicture();

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + staffMember.getStaffMemberId() + ".jpg");

        if (staffMemberImage != null && !staffMemberImage.isEmpty()){
            try {
                staffMemberImage.transferTo(new File(path.toString()));
            }
            catch (Exception ex){
                throw new RuntimeException("Image path invalid " + ex.getMessage());
            }
            staffMemberDao.updateStaffMember(staffMember);
        }
        return "redirect:/gymstaff";
    }

    @RequestMapping("/gymstaff/delete_StaffDetail/{staffMemberId}")
    public String deleteStaffMember(@PathVariable String staffMemberId,  Model model, HttpServletRequest request){
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + staffMemberId + ".jpg");

        if (Files.exists(path)){
            try {
                Files.delete(path);
            }
            catch (Exception ex){
                throw new RuntimeException("Could not delete Image: " + ex.getMessage());
            }
        }

        staffMemberDao.deleteStaffMember(staffMemberId);
        return "redirect:/gymstaff";
    }

    @RequestMapping("/gym_members")
    public String gymMembersHome(){
        return "gym_members";
    }

    @RequestMapping("/gym_members/addNewGymMember")
    public String addNewGymMember(Model model){
        GymMember gymMember = new GymMember();

        model.addAttribute("gymMember",gymMember);

        return "add_new_gym_member";
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
