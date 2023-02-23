//package com.exam.service.impl;
//
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.exam.model.User;
//import com.exam.model.UserRole;
//import com.exam.repo.RoleRepository;
//import com.exam.repo.UserRegisterRepository;
//import com.exam.repo.UserRepository;
//
//@Service
//public class UserRegisterImpl {
//
//	@Autowired(required=false)
//	private UserRegisterRepository userRegisterRepository; 
//	
//	@Autowired(required=false)
//	private RoleRepository roleRepository;
//	
//		// creating user
//		public String createUser(User user, Set<UserRole> userRoles) {
//			try {
//				User local = this.userRegisterRepository.findByUsername(user.getUsername());
//				if (local != null) {
//					return "User already present !!";
//				} else {
//					// user create
//					for (UserRole ur : userRoles) {
//						roleRepository.save(ur.getRole());
//					}
//					user.getUserRoles().addAll(userRoles);
//					this.userRegisterRepository.save(user);
//					return "Data is Saved";
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return null;
//		}
//
//}
