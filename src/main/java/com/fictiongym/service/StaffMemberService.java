package com.fictiongym.service;

import com.fictiongym.model.StaffMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StaffMemberService {

    void addStaffMember(StaffMember staffMember);

    void updateStaffMember(StaffMember staffMember);

    StaffMember getStaffMemberByStaffId(String staffMemberId);

    List<StaffMember> getAllStaffMembers();

    void deleteStaffMember(String staffMemberId);
}
