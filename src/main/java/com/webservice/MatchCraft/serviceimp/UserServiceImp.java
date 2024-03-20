package com.webservice.MatchCraft.serviceimp;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.webservice.MatchCraft.model.User;
import com.webservice.MatchCraft.repo.UserRepo;
import com.webservice.MatchCraft.service.UserService;

import io.jsonwebtoken.lang.Arrays;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImp implements UserService {
	private final UserRepo userRepo;

	public UserServiceImp(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public List<User> getAllMatchs() {
		return userRepo.findAll();
	}

	@Transactional
	@Override
	public List<User> callStoreProced() {
		// TODO Auto-generated method stub
		return userRepo.executeStoredProcedure();
	}
	
	@Override
	@Cacheable(value = "matchDataCache", key = "#id")
	public Optional<User> getUserProfileByUserId(Long id) {
        return userRepo.findById(id);
    }

	@Override
	public UserDetailsService userDetailService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) {
				return userRepo.findByUserEmail(username);
						//.orElseThrow(() -> new UsernameNotFoundException("User not found"));
			}
		};
	}
	
	//This is to update the user preference
	@Transactional
	@Override
	public User updateUserPreColumns(Map<String, List<String>> columnValues) {
		Long userId = getCurrentUserId();
        Optional<User> userDetailsOptional = userRepo.findById(userId);
        if (userDetailsOptional.isPresent()) {
        	User userDetails = userDetailsOptional.get();
            for (Map.Entry<String, List<String>> entry : columnValues.entrySet()) {
                String columnName = entry.getKey();
                List<String> columnValue = entry.getValue();
                switch (columnName) {
                    case "playStyle":
                    	userDetails.setPlayStyle(columnValue);
                        break;
                    case "attitude":
                    	userDetails.setAttitude(columnValue);
                        break;
                    case "communicationStyle":
                    	userDetails.setCommunicationStyle(columnValue);
                        break;
                    case "preferredGameModes":
                    	userDetails.setPreferredGameModes(columnValue);
                        break;
                    case "preferredTeammates":
                    	userDetails.setPreferredTeammates(columnValue);
                        break;
                    case "languagePreference":
                    	userDetails.setLanguagePreference(columnValue);
                        break;
                    default:
                        // Handle unknown column names or return an error
                        throw new IllegalArgumentException("Invalid column name: " + columnName);
                }
            }
            return userRepo.save(userDetails);
        } else {
            throw new RuntimeException("User not found with userId: " + userId);
        }
    }
	
	public Long getCurrentUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String currentUserName = authentication.getName();
			User user = userRepo.findByUserEmail(currentUserName);
			if (user != null) {
				return user.getId();
			}
		}
		return null;
	}
}