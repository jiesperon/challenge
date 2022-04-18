package com.example.challenge.auth;

import java.util.Optional;

public interface AppUserDetailsDao {
	
	public Optional<AppUserDetails> findUserDetailInfoByUserNane(String username);
}
