package com.webservice.MatchCraft.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.webservice.MatchCraft.model.Role;
import com.webservice.MatchCraft.model.User;

public interface UserRepo extends JpaRepository<User, Long>{
	@Procedure(value = "GetResults")
    List<User> executeStoredProcedure();
	
	User findByUserEmail(String userEmail);
	Boolean existsByUserEmail(String userEmail);
	User findByRole(Role role);
	Optional<User> findById(Long id);
}