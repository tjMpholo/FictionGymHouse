package com.fictiongym.service.impl;


import com.fictiongym.dao.GymMemberDao;
import com.fictiongym.model.GymMember;
import com.fictiongym.service.GymMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymMemberServiceImpl implements GymMemberService {

    @Autowired
    private GymMemberDao gymMemberDao;

    @Override
    public void addNewGymMember(GymMember member) {
        gymMemberDao.addNewGymMember(member);
    }

    @Override
    public void updateGymMember(GymMember member) {
        gymMemberDao.updateGymMember(member);
    }

    @Override
    public List<GymMember> getAllGymMembers() {
        return gymMemberDao.getAllGymMembers();
    }

    @Override
    public GymMember getGymMemberById(String defaultId) {
        return gymMemberDao.getGymMemberById(defaultId);
    }

    @Override
    public void deleteMember(String memberId) {
        gymMemberDao.deleteMember(memberId);
    }
}
