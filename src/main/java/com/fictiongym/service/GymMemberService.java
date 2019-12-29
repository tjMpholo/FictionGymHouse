package com.fictiongym.service;

import com.fictiongym.model.GymMember;
import com.fictiongym.model.StaffMember;

import java.util.List;

public interface GymMemberService {
    void addNewGymMember(GymMember member);

    void updateGymMember(GymMember member);

    List<GymMember> getAllGymMembers();

    GymMember getGymMemberById(String defaultId);

    void deleteMember(String memberId);

    GymMember getGymMemberByUsernameAndPassword(String username, String password);

    GymMember getGymMemberByUsername(String username);

    GymMember getGymMemberByRsaId(String memberIdNo);

    GymMember getGymMemberByEmailAddress(String emailAddress);
}
