package com.example.challenge.auth.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.challenge.auth.AppUserDetailsDaoService;
import com.example.challenge.auth.domain.AppUserDetails;
import com.google.common.collect.Lists;

@Profile("test")
@Repository("app-user-details")
public class MemoryAppUserDetailDaoServiceForMock implements AppUserDetailsDaoService {
	private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemoryAppUserDetailDaoServiceForMock(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<AppUserDetails> findUserDetailInfoByUserNane(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<AppUserDetails> getApplicationUsers() {
    	Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
    	SimpleGrantedAuthority permissoin = new SimpleGrantedAuthority("location:view");
    	authorities.add(permissoin);
    	
        List<AppUserDetails> applicationUsers = Lists.newArrayList(
                new AppUserDetails(
                        "juan.challenge.2",
                        passwordEncoder.encode("challenge"),
                        authorities,
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }
}
