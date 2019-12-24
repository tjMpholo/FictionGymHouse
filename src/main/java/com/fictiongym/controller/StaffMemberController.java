package com.fictiongym.controller;


import com.fictiongym.model.StaffMember;
import com.fictiongym.service.StaffMemberService;
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
@RequestMapping("/gymStaff")
public class StaffMemberController {

    @Autowired
    private StaffMemberService staffMemberService;

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

    @RequestMapping(value = "/addNewStaffMember", method = RequestMethod.POST)
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

        staffMemberService.addStaffMember(staffMember);
        return "redirect:/gymStaff/All";
    }


    @RequestMapping("/editStaffMemberDetail/{staffMemberId}")
    public String editStaffMemberDetail(@PathVariable String staffMemberId, Model model){
        StaffMember staffMember = staffMemberService.getStaffMemberByStaffId(staffMemberId);

        model.addAttribute("staffMember",staffMember);

        return "edit_staff_member";
    }

    @RequestMapping(value = "/editStaffMemberDetail", method = RequestMethod.POST)
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

        staffMemberService.updateStaffMember(staffMember);

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
                staffMemberService.deleteStaffMember(staffMemberId);
            }
            catch (Exception ex){
                throw new RuntimeException("Could not delete Image: " + ex.getMessage());
            }
        }


        return "redirect:/gymStaff/All";
    }

}
