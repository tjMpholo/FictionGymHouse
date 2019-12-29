package com.fictiongym.dao;

import com.fictiongym.model.Authorities;
import com.fictiongym.model.Users;

public interface AuthorityDao {


    void deleteAuthorityByMemberId(String memberId);

    Authorities getAuthorityByMemberId(String memberId);

    void addNewAuthority(Authorities authority);

    Authorities getAuthorityByUsername(String username);

    void updateAuthority(Authorities authority);
}
