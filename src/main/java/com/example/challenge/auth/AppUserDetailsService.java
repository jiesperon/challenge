package com.example.challenge.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	private final AppUserDetailsDao appUserDetailsDao;
	
	@Autowired
	public AppUserDetailsService(@Qualifier("app-user-details") AppUserDetailsDao appUserDetailsDao) {
		this.appUserDetailsDao = appUserDetailsDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return appUserDetailsDao
				.findUserDetailInfoByUserNane(username)
				.orElseThrow(() -> 
						new UsernameNotFoundException(String.format("Username %s not found", username)));
	}

}
