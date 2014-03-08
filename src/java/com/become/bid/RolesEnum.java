package com.become.bid;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author arcseldon
 *
 */
public enum RolesEnum {

	ROLE_Admin(1, "ROLE_Admin"),
	ROLE_FinanceManager(2, "ROLE_FinanceManager"),
	ROLE_AccountManager(3, "ROLE_AccountManager"), 
	ROLE_SalesManager(4, "ROLE_SalesManager"), 
	ROLE_Merchant(5, "ROLE_Merchant"), 
	ROLE_ReportUser(6,"ROLE_ReportUser");
	
	
	private static List<RolesEnum> roles = new ArrayList<RolesEnum>();
	
	static {
		roles.add(ROLE_Admin);
		roles.add(ROLE_FinanceManager);
		roles.add(ROLE_AccountManager);
		roles.add(ROLE_SalesManager);
		roles.add(ROLE_Merchant);
		roles.add(ROLE_ReportUser);
	}
	
	private long roleId;
	private String roleName;
	
	private RolesEnum(final long roleId, final String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	public long getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}
	
	public static RolesEnum getRoleByRoleId(final long roleId) {
		for(RolesEnum role : roles){
			if(roleId == role.getRoleId()) {
				return role;
			}
		}
		throw new IllegalArgumentException("Invalid RoleId: " + roleId); 
	}
	
	public static RolesEnum getRoleByRoleName(final String roleName) {
		for(RolesEnum role : roles){
			if(role.getRoleName().equals(roleName)) {
				return role;
			}
		}
		throw new IllegalArgumentException("Invalid RoleName: " + roleName); 
	}

};

