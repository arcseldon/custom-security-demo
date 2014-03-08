package com.become.bid

import grails.plugin.spock.IntegrationSpec

class UserIntegrationSpec extends IntegrationSpec {

	def setup() {
	}

	def cleanup() {
	}

	def "Saving our first User to the database"() {
		
		given: "A brand new user"
		Role adminRole = Role.findByAuthority(RolesEnum.ROLE_Admin.roleName) ?: new Role(authority:RolesEnum.ROLE_Admin.roleName).save(flush:true)
		RolesEnum becomeAdminRolesEnum = RolesEnum.getRoleByRoleName(adminRole.authority)
		def becomeAdmin = new User(username: 'testuser', password: 'Passw0rd', merchantId: new Long(5), passwordEnabled: true, accountExpired: false, accountLocked: false, passwordExpired: false, roleId: becomeAdminRolesEnum.roleId).save(flush:true)
				
		when: "the user is saved"
		becomeAdmin.save(flush: true, failOnError: true)

		then: "it saved successfully and can be found in the database"
		becomeAdmin.errors.errorCount == 0
		becomeAdmin.id != null
		User.get(becomeAdmin.id).password == becomeAdmin.password
	}

}
