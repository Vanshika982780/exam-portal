package com.exam.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepository.findByUsername(username);
		if(user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("No user found !!");
		}
	
				
			return new org.springframework.security.core.userdetails.User(
				 user.getUsername(), user.getPassword(), getAuthority(user)
				);
	}
	
	private Set getAuthority(User user) {
		Set authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			System.out.println("role "+role.getRole().getRoleName());
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRole().getRoleName()));
		});
		
		return authorities;
	}

}
