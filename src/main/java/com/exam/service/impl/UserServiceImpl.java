package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	// creating user
	@Override
	public String createUser(User user, Set<UserRole> userRoles) {
		try {
			User local = this.userRepository.findByUsername(user.getUsername());
			if (local != null) {
				return "User already present !!";
			} else {
				// user create
				for (UserRole ur : userRoles) {
					roleRepository.save(ur.getRole());
				}
				user.getUserRoles().addAll(userRoles);
				this.userRepository.save(user);
				return "Data is Saved";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// getting user by username
	@Override
	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long userId) {
		try {
			System.out.println("userid" + userId);
			this.userRepository.deleteById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUserById(Long userId) {
		try {
			System.out.println("userid" + userId);
			User user = this.userRepository.getById(userId);
			if (user != null)
				return user;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public User loadUserByUsername(String username) {
	    User ud = (User) this.userRepository.findByUsername(username);
		return ud;
	}

	// update
	@Override
	public User updateUser(User user) {
		return this.userRepository.save(user);
	}

//	@Override
//	public User updateUser(User user,Long userId) {
//		User users=null;
//		users=(User)this.userRepository.findById(userId).get();//.orElseThrow(()-> new ResourceNotFoundException("User" ,"Id",userId));
//		users.setFirstName(user.getFirstName());
//		users.setLastName(user.getLastName());
//		users.setUsername(user.getUsername());
//		users.setPassword(user.getPassword());
//		users.setEmail(user.getEmail());
//		users.setEnabled(true);
//		users.setPhone(user.getPhone());
//		users.setProfile(user.getProfile());
//		this.userRepository.save(users);
//		//return "Data Updated SuccessFully";
//		return users;
////		User updateUser=this.userRepository.save(users);
////		return this.userToDto(updatedUser);
//	}

}
