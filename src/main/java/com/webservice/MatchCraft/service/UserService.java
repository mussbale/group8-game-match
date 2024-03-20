package com.webservice.MatchCraft.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.webservice.MatchCraft.model.User;

public interface UserService {
	List<User> getAllMatchs();
	//Credential registerUser(Credential user);
	//Register findByUsername(String username);
	
	List<User> callStoreProced();
	UserDetailsService userDetailService();
	Optional<User> getUserProfileByUserId(Long id);
	User updateUserPreColumns(Map<String, List<String>> columnValues);
}
