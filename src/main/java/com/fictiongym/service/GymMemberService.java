package com.fictiongym.service;

import com.fictiongym.model.GymMember;

import java.util.List;

public interface GymMemberService {
    void addNewGymMember(GymMember member);

    void updateGymMember(GymMember member);

    List<GymMember> getAllGymMembers();

    GymMember getGymMemberById(String defaultId);

    void deleteMember(String memberId);
}
