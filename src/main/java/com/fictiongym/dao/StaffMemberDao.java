package com.fictiongym.dao;

import com.fictiongym.model.StaffMember;

import java.util.List;

public interface StaffMemberDao {

    void addStaffMember(StaffMember staffMember);

    void updateStaffMember(StaffMember staffMember);

    StaffMember getStaffMemberByStaffId(String staffMemberId);

    List<StaffMember> getAllStaffMembers();

    void deleteStaffMember(String staffMemberId);
}
