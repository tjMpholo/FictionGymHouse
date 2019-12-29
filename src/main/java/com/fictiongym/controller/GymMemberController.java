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
import org.springframework.security.core.userdetails.User;
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
import java.util.List;

@Controller
@RequestMapping("/gymMember")
public class GymMemberController {

    @Autowired
    private GymMemberService gymMemberService;

    @Autowired
    private StaffMemberService staffMemberService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    private Path path;

    @RequestMapping(value = "/addNewGymMember", method = RequestMethod.POST)
    public String addNewGymMemberPost(@Valid @ModelAttribute("gymMember") GymMember gymMember, BindingResult result, HttpServletRequest request,
                                      @RequestParam(value="idError", required = false) String idError,
                                      @RequestParam(value="emailError", required = false) String emailError, Model model){
        idError = null;
        emailError = null;

        if (result.hasErrors()){
            return "gym_member_register";
        }

        if (gymMemberService.getGymMemberByRsaId(gymMember.getMemberIdentifier()) != null ||
                staffMemberService.getStaffMemberByRsaId(gymMember.getMemberIdentifier()) != null){
            idError = "ID number already taken";
        }

        model.addAttribute("idError", idError);

        if (gymMemberService.getGymMemberByEmailAddress(gymMember.getEmailAddress()) != null ||
            staffMemberService.getStaffMemberByEmailAddress(gymMember.getEmailAddress()) != null){
            emailError = "Email address already taken";
        }

        model.addAttribute("emailError", emailError);

        if (idError != null || emailError != null){
            return "gym_member_register";
        }

        String username = MemberHelper.generateTempUsername(gymMember.getFirstName(),gymMember.getLastName());
        String password = MemberHelper.generateTempRandomPassword();

        gymMember.setUsername(username);
        gymMember.setPassword(password);

        Users newUser = new Users();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setStaffMemberId(gymMember.getMemberIdentifier());
        newUser.setEnabled(true);

        Authorities newAuthority = new Authorities();
        newAuthority.setUsername(username);
        newAuthority.setAuthority("ROLE_USER");
        newAuthority.setMemberId(gymMember.getMemberIdentifier());

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

        gymMemberService.addNewGymMember(gymMember);
        userService.addNewUser(newUser);
        authorityService.addNewAuthority(newAuthority);

        return "redirect:/gymMember/All";
    }

    @RequestMapping("/All")
    public String gymMembersHome(Model model){
        List<GymMember> gymMemberList = gymMemberService.getAllGymMembers();

        model.addAttribute("memberList",gymMemberList);

        return "gym_member";
    }

    @RequestMapping("/editGymMemberDetail/{defaultId}")
    public String editGymMemberDetails(@PathVariable String defaultId, Model model){
        GymMember gymMember = gymMemberService.getGymMemberById(defaultId);

        model.addAttribute("gymMember",gymMember);

        return "edit_gym_member";
    }

    @RequestMapping(value = "/editStaffMemberDetail", method = RequestMethod.POST)
    public String updateGymMemberDetails(@Valid @ModelAttribute("gymMember") GymMember gymMember,BindingResult result,
                                         HttpServletRequest request,
                                         @RequestParam(value="idError", required = false) String idError,
                                         @RequestParam(value="emailError", required = false) String emailError,
                                         Model model){
        if (result.hasErrors()){
            return "edit_gym_member";
        }

        idError = null;
        emailError = null;

        StringSelection stringSelection = new StringSelection( " aa");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        GymMember gymMemberTest = gymMemberService.getGymMemberByRsaId(gymMember.getMemberIdentifier());
        StaffMember staffMemberTest = staffMemberService.getStaffMemberByRsaId(gymMember.getMemberIdentifier());

        if (gymMemberTest != null || staffMemberTest != null){
            if (gymMemberTest != null){
                if (!gymMemberTest.getMemberIdentifier().equals(gymMember.getMemberIdentifier()))
                    idError = "ID number already taken.";
            }

            if (staffMemberTest != null){
                if (!staffMemberTest.getRsaIdNumber().equals(gymMember.getMemberIdentifier())){

                }
                idError = "ID number already taken.";
            }
            model.addAttribute("idError", idError);
        }
        //model.addAttribute("idError", "jelly 2");



        gymMemberTest = gymMemberService.getGymMemberByEmailAddress(gymMember.getEmailAddress());
        staffMemberTest = staffMemberService.getStaffMemberByEmailAddress(gymMember.getEmailAddress());

        if (gymMemberTest != null || staffMemberTest != null){
            if (gymMemberTest != null){
                if (!gymMemberTest.getDefaultId().equals(gymMember.getDefaultId())){
                    emailError = "Email address already taken.1";
                }
            }

            if (staffMemberTest != null){
                if (!staffMemberTest.getRsaIdNumber().equals(gymMember.getMemberIdentifier())){
                    emailError = "Email address already taken.2b";
                }
            }
            model.addAttribute("emailError", emailError);
        }




        if (idError != null || emailError != null){
            return "edit_gym_member";
        }

        //Authorities oldAuth = authorityService.getAuthorityByUsername(gymMember.getUsername());

        Users newUser = userService.getUserByUsernameAndPassword(gymMember.getUsername(),gymMember.getPassword());
        newUser.setStaffMemberId(gymMember.getMemberIdentifier());
        newUser.setEnabled(true);

        Authorities newAuthority = authorityService.getAuthorityByUsername(gymMember.getUsername());
        newAuthority.setMemberId(gymMember.getMemberIdentifier());

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

        gymMemberService.updateGymMember(gymMember);
        userService.updateUser(newUser);
        authorityService.updateAuthority(newAuthority);

        return "redirect:/gymMember/All";
    }

    @RequestMapping("/deleteMemberDetail/{gymMemberId}")
    public String deleteGymMember(@PathVariable String gymMemberId,  Model model, HttpServletRequest request){
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        GymMember gymMember = gymMemberService.getGymMemberById(gymMemberId);

        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + gymMember.getMemberIdentifier() + ".jpg");

        if (Files.exists(path)){
            try {
                Files.delete(path);
            }
            catch (Exception ex){
                throw new RuntimeException("Could not delete Image: " + ex.getMessage());
            }
        }

        gymMemberService.deleteMember(gymMemberId);
        userService.deleteUserByMemberId(gymMember.getMemberIdentifier());
        authorityService.deleteAuthorityByMemberId(gymMember.getMemberIdentifier());
        return "redirect:/gymMember/All";
    }

    @RequestMapping("/GymMemberDetailed/{defaultId}")
    public String viewDetailGymMemberInfo(@PathVariable String defaultId,Model model){
        GymMember gymMember = gymMemberService.getGymMemberById(defaultId);

        model.addAttribute("gymMember",gymMember);
        return "gym_member_detailed";
    }

    @RequestMapping("/addNewGymMember")
    public String addNewGymMember(Model model){
        GymMember gymMember = new GymMember();

        model.addAttribute("gymMember",gymMember);

        return "gym_member_register";
    }


}
