package com.fictiongym.controller;

import com.fictiongym.model.GymMember;
import com.fictiongym.service.GymMemberService;
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
import java.util.List;

@Controller
@RequestMapping("/gymMember")
public class GymMemberController {

    @Autowired
    private GymMemberService gymMemberService;

    private Path path;

    @RequestMapping(value = "/gymMember/addNewGymMember", method = RequestMethod.POST)
    public String addNewGymMemberPost(@Valid @ModelAttribute("gymMember") GymMember gymMember, BindingResult result, HttpServletRequest request ){
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

        gymMemberService.addNewGymMember(gymMember);

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

        gymMemberService.updateGymMember(gymMember);

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
