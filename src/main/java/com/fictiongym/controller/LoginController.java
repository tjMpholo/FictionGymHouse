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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/MemberLogin")
public class LoginController {

    @Autowired
    private StaffMemberService staffMemberService;

    @Autowired
    private GymMemberService gymMemberService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserService userService;

    @RequestMapping("/Login")
    public String Login(@RequestParam(value="error", required = false) String error,Model model) {

        if (error != null){
            model.addAttribute("error","Invalid username or password please try again!");
        }


        return "login";
    }

    @RequestMapping("/ForgotPassword")
    public String ChangePassword() {
        return "forgot_password";
    }

    @RequestMapping("/LoginSuccess")
    public String LoginSuccess(HttpServletRequest request, SecurityContextHolder auth){
        String loggedUser = request.getRemoteUser();
        boolean isStaffMember = false;


        Collection<?extends GrantedAuthority> granted = auth.getContext().getAuthentication().getAuthorities();
        String role;

        for(int i=0;i<granted.size();i++){
            role = granted.toArray()[i] + "";

            if(MemberHelper.isUserStaffMember(role)){
                isStaffMember = true;
            }
        }

        if (isStaffMember){
            StaffMember staffMember = staffMemberService.getStaffMemberByUsername(loggedUser);

            staffMember.setLastLoginDate(new Date());
            staffMemberService.updateStaffMember(staffMember);
        }
        else{
            GymMember gymMember = gymMemberService.getGymMemberByUsername(loggedUser);

            gymMember.setLastLoginDate(new Date());
            gymMemberService.updateGymMember(gymMember);
        }


        if (loggedUser.contains("@"))
            return "redirect:/";
        else
            return "redirect:/MemberLogin/ForgotPassword";
    }

    @RequestMapping(value = "/ForgotPassword", method = RequestMethod.POST)
    public String SaveLoginDetailChange(@RequestParam("currentUsername") String currentUsername,
                                        @RequestParam("newUsername") String newUsername,
                                        @RequestParam("currentPassword") String currentPassword,
                                        @RequestParam("newPassword") String newPassword) {

        StaffMember staffMember = staffMemberService.getStaffMemberByUsernameAndPassword(currentUsername.trim(),currentPassword.trim());
        GymMember gymMember = gymMemberService.getGymMemberByUsernameAndPassword(currentUsername.trim(),currentPassword.trim());
        Users user = userService.getUserByUsernameAndPassword(currentUsername.trim(),currentPassword.trim());
        Authorities authority = authorityService.getAuthorityByUsername(currentUsername.trim());

        if (MemberHelper.isUserStaffMember(authority.getAuthority())){
            if (staffMember.getRsaIdNumber().equals(user.getStaffMemberId()) &&
                    staffMember.getRsaIdNumber().equals(authority.getMemberId())){
                staffMember.setUsername(newUsername);
                staffMember.setPassword(newPassword);

                user.setUsername(newUsername);
                user.setPassword(newPassword);

                authority.setUsername(newUsername);

                staffMemberService.updateStaffMember(staffMember);
                userService.updateUser(user);
                authorityService.updateAuthority(authority);
            }
        }
        else{
            if (gymMember.getMemberIdentifier().equals(user.getStaffMemberId()) &&
                    gymMember.getMemberIdentifier().equals(authority.getMemberId())){
                gymMember.setUsername(newUsername);
                gymMember.setPassword(newPassword);

                user.setUsername(newUsername);
                user.setPassword(newPassword);

                authority.setUsername(newUsername);

                gymMemberService.updateGymMember(gymMember);
                userService.updateUser(user);
                authorityService.updateAuthority(authority);
            }
        }

        /*
        try {
            if (staffMember != null){

            }

            if (gymMember != null){

            }
        }
        catch (Exception ex){
            throw new RuntimeException("One of the objects is null");
        }
        */


        return "login";
    }

    /*
    @RequestMapping("/LoginSuccess")
    public String LoginSuccess(HttpServletRequest request, SecurityContextHolder auth){
        String loggedUser = request.getRemoteUser();
        boolean isStaffMember = false;


        Collection<?extends GrantedAuthority> granted = auth.getContext().getAuthentication().getAuthorities();
        String role;

        for(int i=0;i<granted.size();i++){
            role = granted.toArray()[i] + "";


            if(isUserStaffMember(role)){
                isStaffMember = true;
            }
        }

        if (isStaffMember){
            StaffMember staffMember = staffMemberService.getStaffMemberByUsername(loggedUser);


        }
        //

        return "";
    }
    * */
}
