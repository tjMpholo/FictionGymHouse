package com.fictiongym.dao;

import com.fictiongym.model.GymMember;

import java.util.List;

public interface GymMemberDao {


    void addNewGymMember(GymMember member);

    void updateGymMember(GymMember member);

    List<GymMember> getAllGymMembers();

    GymMember getGymMemberById(String defaultId);

    void deleteMember(String memberId);
}
