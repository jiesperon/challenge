package com.example.challenge.auth;

import java.util.Optional;

import com.example.challenge.auth.domain.AppUserDetails;

public interface AppUserDetailsDaoService {
	
	/**
	 * Finds user Detail Information by Username
	 * 
	 * @param username 
	 * @return AppUserDetails 
	 */
	public Optional<AppUserDetails> findUserDetailInfoByUserNane(String username);
}
