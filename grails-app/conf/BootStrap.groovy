import com.become.bid.Role
import com.become.bid.User
import com.become.bid.Merchant
import com.become.bid.RolesEnum



class BootStrap {

    def init = { servletContext ->

		environments {
			development {
				
				def adminRole = Role.findByAuthority(RolesEnum.ROLE_Admin.roleName) ?: new Role(authority:RolesEnum.ROLE_Admin.roleName).save(flush:true)
				def merchantRole = Role.findByAuthority(RolesEnum.ROLE_Merchant.roleName) ?: new Role(authority: RolesEnum.ROLE_Merchant.roleName).save(flush:true)
			
				def merchantBecome = new Merchant(
					parentId: new Long(0),
					aggregator: false,
					agency: false,
					name: "Become",
					displayName: "Become",
					billable: true,
					type: 0,
					accountDeleted: false)
				
				merchantBecome.save(flush: true, failOnError: true)
				
				RolesEnum becomeAdminRolesEnum = RolesEnum.getRoleByRoleName(adminRole.authority)
				
				def becomeAdmin = new User(username: 'become', password: 'Passw0rd', merchantId: merchantBecome.id, passwordEnabled: true, accountExpired: false, accountLocked: false, passwordExpired: false, roleId: becomeAdminRolesEnum.roleId).save(flush:true)
							
			}
			test{}
			production {}
		}

    }
    def destroy = {
    }
}
