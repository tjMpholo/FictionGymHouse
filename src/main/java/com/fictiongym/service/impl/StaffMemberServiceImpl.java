package com.fictiongym.service.impl;

import com.fictiongym.dao.StaffMemberDao;
import com.fictiongym.model.StaffMember;
import com.fictiongym.service.StaffMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StaffMemberServiceImpl implements StaffMemberService {

    @Autowired
    private StaffMemberDao staffMemberDao;

    @Override
    public void addStaffMember(StaffMember staffMember) {
        staffMemberDao.addStaffMember(staffMember);
    }

    @Override
    public void updateStaffMember(StaffMember staffMember) {
        staffMemberDao.updateStaffMember(staffMember);
    }

    @Override
    public StaffMember getStaffMemberByStaffId(String staffMemberId) {
        return staffMemberDao.getStaffMemberByStaffId(staffMemberId);
    }

    @Override
    public List<StaffMember> getAllStaffMembers() {
        return staffMemberDao.getAllStaffMembers();
    }

    @Override
    public void deleteStaffMember(String staffMemberId) {
        staffMemberDao.deleteStaffMember(staffMemberId);
    }
}
