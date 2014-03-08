package com.become.bid

/**
 * Customized for compatibility with MAC
 * 
 * @author arcseldon
 *
 */
class Role {

	String authority

	static mapping = {
		version false
		cache true
		authority column: "role_name" 
	}

	static constraints = {
		authority(blank: false, unique: true)
	}
	
}
