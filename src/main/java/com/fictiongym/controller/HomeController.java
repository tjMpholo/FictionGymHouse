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

    @RequestMapping("/gymStaff")
    public String gymStaff(Model model){
        List<StaffMember> staffMembers = staffMemberDao.getAllStaffMembers();

        model.addAttribute("staffMembers",staffMembers);
        return "gym_staff";
    }

    @RequestMapping("/gymStaff/staffDetailed/{staffMemberId}")
    public String viewIndividualStaffDetail(@PathVariable String staffMemberId, Model model ) throws Exception{
        StaffMember staffMember = staffMemberDao.getStaffMemberByStaffId(staffMemberId);

        model.addAttribute("staffMember",staffMember);
        return "gym_staff_detailed";
    }

    @RequestMapping("/gymStaff/addNewStaffMember")
    public String addNewStaffMember(Model model){
        StaffMember staffMember = new StaffMember();
        model.addAttribute("staffMember",staffMember);

        return "add_new_staff_member";
    }

    @RequestMapping(value = "/gymStaff/addNewStaffMember", method = RequestMethod.POST)
    public String addStaffMemberPost(@Valid @ModelAttribute("staffMember") StaffMember staffMember, BindingResult result, HttpServletRequest request){
        if (result.hasErrors()){
            return "addNewStaffMember";
        }

        MultipartFile staffMemberPicture = staffMember.getStaffMemberPicture();

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + staffMember.getRsaIdNumber() + ".jpg");

        if (staffMemberPicture != null && !staffMemberPicture.isEmpty()){
            try {
                staffMemberPicture.transferTo(new File(path.toString()));
            }
            catch (Exception ex){
                throw new RuntimeException("Image path invalid " + ex.getMessage());
            }

            staffMember.setIsProfileSet(true);
            staffMember.setImagePath(staffMember.getRsaIdNumber());
        }
        else {
            staffMember.setIsProfileSet(false);
        }

        staffMemberDao.addStaffMember(staffMember);
        return "redirect:/gymStaff";
    }

    @RequestMapping(value = "/gym_members/addNewGymMember", method = RequestMethod.POST)
    public String addNewGymMemberPost(@Valid @ModelAttribute("gymMember") GymMember gymMember,BindingResult result, HttpServletRequest request ){
        if (result.hasErrors()){
            return "add_new_gym_member";
        }

        gymMemberDao.addNewGymMember(gymMember);

        return "redirect:/gym_members";
    }

    @RequestMapping("/gymStaff/editStaffMemberDetail/{staffMemberId}")
    public String editStaffMemberDetail(@PathVariable String staffMemberId, Model model){
        StaffMember staffMember = staffMemberDao.getStaffMemberByStaffId(staffMemberId);

        model.addAttribute("staffMember",staffMember);

        return "edit_staff_member";
    }

    @RequestMapping(value = "/gymStaff/editStaffMemberDetail", method = RequestMethod.POST)
    public String editStaffMemberDetail(@Valid @ModelAttribute("staffMember") StaffMember staffMember, BindingResult result, HttpServletRequest request){
        Path defaultPath = null;

        if (result.hasErrors()){
            return "edit_staff_member";
        }

        MultipartFile staffMemberImage = staffMember.getStaffMemberPicture();

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + staffMember.getRsaIdNumber() + ".jpg");

        if (Files.exists(path)){
            if (!(staffMemberImage != null && !staffMemberImage.isEmpty())){
                staffMember.setIsProfileSet(true);
                staffMember.setImagePath(staffMember.getRsaIdNumber());
            }
        }
        else{
            if (staffMemberImage != null && !staffMemberImage.isEmpty()){
                try {
                    staffMemberImage.transferTo(new File(path.toString()));
                    staffMember.setIsProfileSet(true);
                    staffMember.setImagePath(staffMember.getRsaIdNumber());
                }
                catch (Exception ex){
                    throw new RuntimeException("Image path invalid " + ex.getMessage());
                }
            }
        }

        staffMemberDao.updateStaffMember(staffMember);

        return "redirect:/gymStaff";
    }

    @RequestMapping("/gymStaff/deleteStaffDetail/{staffMemberId}")
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
        return "redirect:/gymStaff";
    }

    @RequestMapping("/gym_members")
    public String gymMembersHome(Model model){
        List<GymMember> gymMemberList = gymMemberDao.getAllGymMembers();

        model.addAttribute("memberList",gymMemberList);

        return "gym_members";
    }

    @RequestMapping("/gym_members/edit_gym_member_details/{defaultId}")
    public String editGymMemberDetails(@PathVariable String defaultId, Model model){
        GymMember gymMember = gymMemberDao.getGymMemberById(defaultId);

        model.addAttribute("gymMember",gymMember);

        return "edit_gym_member";
    }

    @RequestMapping(value = "/gym_members/update_gym_member", method = RequestMethod.POST)
    public String updateGymMemberDetails(@Valid @ModelAttribute("gymMember") GymMember gymMember,BindingResult result,HttpServletRequest request){
        if (result.hasErrors()){
            return "edit_gym_member";
        }

        gymMemberDao.updateGymMember(gymMember);

        return "redirect:/gym_members";
    }

    @RequestMapping("/gym_members/view_gym_member_details/{defaultId}")
    public String viewDetailGymMemberInfo(@PathVariable String defaultId,Model model){
        GymMember gymMember = gymMemberDao.getGymMemberById(defaultId);

        model.addAttribute("gymMember",gymMember);
        return "gym_member_detailed";
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
