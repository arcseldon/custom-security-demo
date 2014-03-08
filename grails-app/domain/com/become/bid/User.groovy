package com.become.bid

import java.util.Date;

/**
 * Customized for compatibility with MAC 
 * 
 * Passwords are in plaintext in legacy DB..
 * 
 * @author arcseldon
 *
 */
class User {

	String username
	String password
	long merchantId;
	long roleId
	boolean enabled = true
	boolean accountExpired = false
	boolean accountLocked = false
	boolean passwordExpired = false


	static transients = ['enabled', 'accountLocked', 'passwordExpired']

	static constraints = {
		username(blank: false, unique: true)
		password(blank: false)
		roleId(range:1..6)
	}

	static mapping = {
		version false
		password column: '`password`'
		accountExpired column: "user_deleted", sqlType: "char", length: 3
	}

	Set<Role> getAuthorities() {
		RolesEnum roleType = RolesEnum.getRoleByRoleId(roleId)
		return [new Role(authority: roleType.getRoleName())] as Set
	}

	protected void encodePassword() {
		// do nothing
	}
	
}
