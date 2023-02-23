package com.exam.service;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {
	
	//creating user
	public String createUser(User user, Set<UserRole> userRoles);
    
	//get user by username
	public User getUser(String username);

	//get user by userById
	public User getUserById(Long userId);
	
	//get delete user by id
	public void deleteUser(Long userId);
	
	//update user value
	//public User updateUser(User user,Long userId);
	public User updateUser(User user);

	public UserDetails loadUserByUsername(String username);
}
