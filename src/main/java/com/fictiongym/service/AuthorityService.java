package com.fictiongym.service;

import com.fictiongym.model.Authorities;
import com.fictiongym.model.Users;

public interface AuthorityService {

    void deleteAuthorityByMemberId(String memberId);

    void addNewAuthority(Authorities authority);

    Authorities getAuthorityByUsername(String username);

    void updateAuthority(Authorities authority);
}
