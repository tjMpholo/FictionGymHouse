package com.fictiongym.controller;


import com.fictiongym.helper.MemberHelper;
import com.fictiongym.model.Authorities;
import com.fictiongym.model.GymMember;
import com.fictiongym.model.StaffMember;
import com.fictiongym.model.Users;
import com.fictiongym.service.AuthorityService;
import com.fictiongym.service.GymMemberService;
import com.fictiongym.service.StaffMemberService;
import com.fictiongym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gymStaff")
public class StaffMemberController {

    @Autowired
    private StaffMemberService staffMemberService;

    @Autowired
    private GymMemberService gymMemberService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    private Path path;

    @RequestMapping("/All")
    public String gymStaff(Model model){
        List<StaffMember> staffMembers = staffMemberService.getAllStaffMembers();

        model.addAttribute("staffMembers",staffMembers);
        return "gym_staff";
    }

    @RequestMapping("/staffDetailed/{staffMemberId}")
    public String viewIndividualStaffDetail(@PathVariable String staffMemberId, Model model ) throws Exception{
        StaffMember staffMember = staffMemberService.getStaffMemberByStaffId(staffMemberId);

        model.addAttribute("staffMember",staffMember);
        return "gym_staff_detailed";
    }

    @RequestMapping("/addNewStaffMember")
    public String addNewStaffMember(Model model){
        StaffMember staffMember = new StaffMember();
        model.addAttribute("staffMember",staffMember);

        return "add_new_staff_member";
    }

    @RequestMapping("/admin")
    public String addNewStaffMemberAdmin(Model model){
        StaffMember staffMember = new StaffMember();
        model.addAttribute("staffMember",staffMember);

        return "add_new_staff_member";
    }

    @RequestMapping(value = "/addNewStaffMember", method = RequestMethod.POST)
    public String addStaffMemberPost(@Valid @ModelAttribute("staffMember") StaffMember staffMember,
                                     BindingResult result, HttpServletRequest request,
                                     @RequestParam(value="idError", required = false) String idError,
                                     @RequestParam(value="emailError", required = false) String emailError, Model model){
        idError = null;
        emailError = null;

        if (result.hasErrors()){
            return "add_new_staff_member";
        }

        if (gymMemberService.getGymMemberByRsaId(staffMember.getRsaIdNumber()) != null ||
                staffMemberService.getStaffMemberByRsaId(staffMember.getRsaIdNumber()) != null){
            idError = "ID number already taken";
        }

        model.addAttribute("idError", idError);

        if (gymMemberService.getGymMemberByEmailAddress(staffMember.getEmailAddress()) != null ||
                staffMemberService.getStaffMemberByEmailAddress(staffMember.getEmailAddress()) != null){
            emailError = "Email address already taken";
        }

        model.addAttribute("emailError", emailError);

        if (idError != null || emailError != null){
            return "add_new_staff_member";
        }

        String username = MemberHelper.generateTempUsername(staffMember.getFirstName(),staffMember.getLastName());

        String password = MemberHelper.generateTempRandomPassword();

        String systemRole = staffMemberService.getStaffMemberRoleCode(staffMember.getWorkTitle().trim());

        StringSelection stringSelection = new StringSelection(staffMember.getWorkTitle() + " " + systemRole);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        staffMember.setUsername(username);
        staffMember.setPassword(password);

        Users newUser = new Users();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setStaffMemberId(staffMember.getRsaIdNumber());
        newUser.setEnabled(true);

        Authorities newAuthority = new Authorities();
        newAuthority.setUsername(username);
        newAuthority.setAuthority(systemRole);
        newAuthority.setMemberId(staffMember.getRsaIdNumber());

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

        staffMemberService.addStaffMember(staffMember);
        userService.addNewUser(newUser);
        authorityService.addNewAuthority(newAuthority);
        return "redirect:/gymStaff/All";
    }


    @RequestMapping("/editStaffMemberDetail/{staffMemberId}")
    public String editStaffMemberDetail(@PathVariable String staffMemberId, Model model){
        StaffMember staffMember = staffMemberService.getStaffMemberByStaffId(staffMemberId);

        model.addAttribute("staffMember",staffMember);

        return "edit_staff_member";
    }

    @RequestMapping(value = "/editStaffMemberDetail", method = RequestMethod.POST)
    public String editStaffMemberDetail(@Valid @ModelAttribute("staffMember") StaffMember staffMember, BindingResult result,
                                        HttpServletRequest request,
                                        @RequestParam(value="idError", required = false) String idError,
                                        @RequestParam(value="emailError", required = false) String emailError,
                                        Model model){
        Path defaultPath = null;

        if (result.hasErrors()){
            return "edit_staff_member";
        }

        idError = null;
        emailError = null;

        GymMember gymMemberTest = gymMemberService.getGymMemberByRsaId(staffMember.getRsaIdNumber());
        StaffMember staffMemberTest = staffMemberService.getStaffMemberByRsaId(staffMember.getRsaIdNumber());

        if (gymMemberTest != null || staffMemberTest != null){
            if (gymMemberTest != null){
                if (!gymMemberTest.getMemberIdentifier().equals(staffMember.getRsaIdNumber()))
                    idError = "ID number already taken.";
            }

            if (staffMemberTest != null){
                if (!staffMemberTest.getRsaIdNumber().equals(staffMember.getRsaIdNumber())){

                }
                idError = "ID number already taken.";
            }
            model.addAttribute("idError", idError);
        }
        //model.addAttribute("idError", "jelly 2");

        gymMemberTest = gymMemberService.getGymMemberByEmailAddress(staffMember.getEmailAddress());
        staffMemberTest = staffMemberService.getStaffMemberByEmailAddress(staffMember.getEmailAddress());

        if (gymMemberTest != null || staffMemberTest != null){
            if (gymMemberTest != null){
                if (!gymMemberTest.getDefaultId().equals(staffMember.getStaffMemberId())){
                    emailError = "Email address already taken.1";
                }
            }

            if (staffMemberTest != null){
                if (!staffMemberTest.getEmailAddress().equals(staffMember.getEmailAddress())){
                    emailError = "Email address already taken.2b " + staffMemberTest.getRsaIdNumber()
                    + " " + staffMember.getRsaIdNumber() + " " + staffMemberTest.getEmailAddress() +
                    " " + staffMember.getEmailAddress();
                }
            }
            model.addAttribute("emailError", emailError);
        }

        if (idError != null || emailError != null){
            return "edit_staff_member";
        }

        //Authorities oldAuth = authorityService.getAuthorityByUsername(gymMember.getUsername());

        Users newUser = userService.getUserByUsernameAndPassword(staffMember.getUsername(),staffMember.getPassword());
        newUser.setStaffMemberId(staffMember.getRsaIdNumber());
        newUser.setEnabled(true);

        Authorities newAuthority = authorityService.getAuthorityByUsername(staffMember.getUsername());
        newAuthority.setMemberId(staffMember.getRsaIdNumber());

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

        staffMemberService.updateStaffMember(staffMember);
        authorityService.updateAuthority(newAuthority);
        userService.updateUser(newUser);

        return "redirect:/gymStaff/All";
    }

    @RequestMapping("/deleteStaffDetail/{staffMemberId}")
    public String deleteStaffMember(@PathVariable String staffMemberId,  Model model, HttpServletRequest request){
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        StaffMember staffMember = staffMemberService.getStaffMemberByStaffId(staffMemberId);

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


                //authorityService.deleteAuthority(staffMember.getUsername());
            }
            catch (Exception ex){
                throw new RuntimeException("Could not delete Image: " + ex.getMessage() + " ID: " + staffMember.getRsaIdNumber());
            }
        }

        staffMemberService.deleteStaffMember(staffMemberId);
        userService.deleteUserByMemberId(staffMember.getRsaIdNumber());
        authorityService.deleteAuthorityByMemberId(staffMember.getRsaIdNumber());

        return "redirect:/gymStaff/All";
    }



}
