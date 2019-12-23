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

    @RequestMapping(value = "/gymMember/addNewGymMember", method = RequestMethod.POST)
    public String addNewGymMemberPost(@Valid @ModelAttribute("gymMember") GymMember gymMember,BindingResult result, HttpServletRequest request ){
        if (result.hasErrors()){
            return "gym_member_register";
        }


        MultipartFile memberPicture = gymMember.getMemberProfilePicture();

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + gymMember.getMemberIdentifier() + ".jpg");


        if (memberPicture != null && !memberPicture.isEmpty()){
            try {
                memberPicture.transferTo(new File(path.toString()));
            }
            catch (Exception ex){
                throw new RuntimeException("Image path invalid " + ex.getMessage());
            }

            gymMember.setIsProfileSet(true);
            gymMember.setImagePath(gymMember.getMemberIdentifier());
        }
        else {
            gymMember.setIsProfileSet(false);
        }

        gymMemberDao.addNewGymMember(gymMember);

        return "redirect:/gymMember";
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


        /*
        //Clipboard code
        StringSelection stringSelection = new StringSelection(path.toAbsolutePath().toString());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        */

        if (Files.exists(path)){
            if (!(staffMemberImage != null && !staffMemberImage.isEmpty())){
                staffMember.setIsProfileSet(true);
                staffMember.setImagePath(staffMember.getRsaIdNumber());
            }
            else{
                try {
                    Files.delete(path);

                    staffMemberImage.transferTo(new File(path.toString()));
                    staffMember.setIsProfileSet(true);
                    staffMember.setImagePath(staffMember.getRsaIdNumber());
                }
                catch (Exception ex){
                    throw new RuntimeException("Image path invalid " + ex.getMessage());
                }
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
        StaffMember staffMember = staffMemberDao.getStaffMemberByStaffId(staffMemberId);

        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + staffMember.getRsaIdNumber() + ".jpg");

        /*
        //Clipboard code
        StringSelection stringSelection = new StringSelection(path.toAbsolutePath().toString());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        */

        if (Files.exists(path)){
            try {
                Files.delete(path);
                staffMemberDao.deleteStaffMember(staffMemberId);
            }
            catch (Exception ex){
                throw new RuntimeException("Could not delete Image: " + ex.getMessage());
            }
        }


        return "redirect:/gymStaff";
    }

    @RequestMapping("/gymMember")
    public String gymMembersHome(Model model){
        List<GymMember> gymMemberList = gymMemberDao.getAllGymMembers();

        model.addAttribute("memberList",gymMemberList);

        return "gym_member";
    }

    @RequestMapping("/gymMember/editGymMemberDetail/{defaultId}")
    public String editGymMemberDetails(@PathVariable String defaultId, Model model){
        GymMember gymMember = gymMemberDao.getGymMemberById(defaultId);

        model.addAttribute("gymMember",gymMember);

        return "edit_gym_member";
    }

    @RequestMapping(value = "/gymMember/editStaffMemberDetail", method = RequestMethod.POST)
    public String updateGymMemberDetails(@Valid @ModelAttribute("gymMember") GymMember gymMember,BindingResult result,HttpServletRequest request){
        if (result.hasErrors()){
            return "edit_gym_member";
        }

        MultipartFile memberProfilePicture = gymMember.getMemberProfilePicture();

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + gymMember.getMemberIdentifier() + ".jpg");

        if (Files.exists(path)){
            if (!(memberProfilePicture != null && !memberProfilePicture.isEmpty())){
                gymMember.setIsProfileSet(true);
                gymMember.setImagePath(gymMember.getMemberIdentifier());
            }
            else{
                try {
                    Files.delete(path);

                    memberProfilePicture.transferTo(new File(path.toString()));
                    gymMember.setIsProfileSet(true);
                    gymMember.setImagePath(gymMember.getMemberIdentifier());
                }
                catch (Exception ex){
                    throw new RuntimeException("Image path invalid " + ex.getMessage());
                }
            }
        }
        else{
            if (memberProfilePicture != null && !memberProfilePicture.isEmpty()){
                try {
                    memberProfilePicture.transferTo(new File(path.toString()));
                    gymMember.setIsProfileSet(true);
                    gymMember.setImagePath(gymMember.getMemberIdentifier());
                }
                catch (Exception ex){
                    throw new RuntimeException("Image path invalid " + ex.getMessage());
                }
            }
        }

        gymMemberDao.updateGymMember(gymMember);

        return "redirect:/gymMember";
    }

    @RequestMapping("/gymMember/deleteMemberDetail/{gymMemberId}")
    public String deleteGymMember(@PathVariable String gymMemberId,  Model model, HttpServletRequest request){
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        GymMember gymMember = gymMemberDao.getGymMemberById(gymMemberId);

        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + gymMember.getMemberIdentifier() + ".jpg");

        if (Files.exists(path)){
            try {
                Files.delete(path);
            }
            catch (Exception ex){
                throw new RuntimeException("Could not delete Image: " + ex.getMessage());
            }
        }

        gymMemberDao.deleteMember(gymMemberId);
        return "redirect:/gymMember";
    }

    @RequestMapping("/gymMember/GymMemberDetailed/{defaultId}")
    public String viewDetailGymMemberInfo(@PathVariable String defaultId,Model model){
        GymMember gymMember = gymMemberDao.getGymMemberById(defaultId);

        model.addAttribute("gymMember",gymMember);
        return "gym_member_detailed";
    }

    @RequestMapping("/gymMember/addNewGymMember")
    public String addNewGymMember(Model model){
        GymMember gymMember = new GymMember();

        model.addAttribute("gymMember",gymMember);

        return "gym_member_register";
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
