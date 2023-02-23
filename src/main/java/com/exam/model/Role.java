package com.exam.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

	
	public String getUrl() {
		return url;
	}
	
	@Id
	private Long roleId;
	private String roleName;
	private String url;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	private Set<UserRole> userRoles = new HashSet<>();

	public Role() {
		// super();
	}

	public Role(Long roleId, String roleName ,String url) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.url=url;
	}


	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
