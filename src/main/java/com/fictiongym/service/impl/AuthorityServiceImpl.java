package com.fictiongym.service.impl;

import com.fictiongym.dao.AuthorityDao;
import com.fictiongym.model.Authorities;
import com.fictiongym.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public void deleteAuthorityByMemberId(String memberId) {
        authorityDao.deleteAuthorityByMemberId(memberId);
    }

    @Override
    public void addNewAuthority(Authorities authority) {
        authorityDao.addNewAuthority(authority);
    }

    @Override
    public Authorities getAuthorityByUsername(String username) {
        return authorityDao.getAuthorityByUsername(username);
    }

    @Override
    public void updateAuthority(Authorities authority) {
        authorityDao.updateAuthority(authority);
    }
}
