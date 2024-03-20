package com.webservice.MatchCraft.webservice;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.MatchCraft.model.User;
import com.webservice.MatchCraft.serviceimp.UserServiceImp;

@RestController
@RequestMapping("/Api")
public class PlayerWebService {
	private final UserServiceImp userServiceImp;

	public PlayerWebService(UserServiceImp userServiceImp) {
		this.userServiceImp = userServiceImp;
	}
	
	@GetMapping("/user/{userId}")
    public Optional<User> getUserProfile(@PathVariable Long userId) {
        return userServiceImp.getUserProfileByUserId(userId);
    }
	
	@PutMapping("/updatePreference")
    public User updateUserProfileColumns(
            @RequestBody Map<String, List<String>> columnValues
    ) {
        return userServiceImp.updateUserPreColumns(columnValues);
    }
}
