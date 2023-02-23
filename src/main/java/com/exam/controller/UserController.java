package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

import RequestDTO.UserDTO;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// creating user
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user) // data as a json that's why @RB
	{

		System.out.println("user " + user);
		Set<UserRole> roles = new HashSet<>();

		com.exam.model.Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		roles.add(userRole);

		return ResponseEntity.ok(this.userService.createUser(user, roles));
	}
//get user
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("username") String username) {
		System.out.println("testing");
		User user  = userService.getUser(username);
		System.out.println(user.getEmail());
		UserDTO userDto = new UserDTO();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setPassword(user.getPassword());
		userDto.setUsername(user.getUsername());
		return ResponseEntity.ok(userDto);
	}

	// delete the user by id
	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable("userId") Long userId) {

		try {
			System.out.println("userId " + userId);
			User user = this.userService.getUserById(userId);
			System.out.println("user " + user);
			if (user != null) {
				this.userService.deleteUser(user.getId());
				return "Delete User With ID " + userId;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Wrong Id";

	}

	// update user details
	@PutMapping("/")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		System.out.println("Your record updated Successfully ");
		return ResponseEntity.ok(this.userService.updateUser(user));
		
	}
}
