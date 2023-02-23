package com.exam.model;

import javax.persistence.*;

@Entity
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userRoleid;

	// user
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@ManyToOne
	private Role role;

	public UserRole() {
		super();
	}

	public Long getUserRoleid() {
		return userRoleid;
	}

	public void setUserRoleid(Long userRoleid) {
		this.userRoleid = userRoleid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
