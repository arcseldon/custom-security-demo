package com.become.bid

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.plugin.springsecurity.userdetails.GrailsUserDetailsService

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

/**
 *  
 * @author arcseldon
 */
class CustomUserDetailsService implements GrailsUserDetailsService  {
	
	
	/**
	 * Some Spring Security classes (e.g. RoleHierarchyVoter) expect at least
	 * one role, so we give a user with no granted roles this one which gets
	 * past that restriction but doesn't grant anything.
	 */
//	static final List NO_ROLES = [new SimpleGrantedAuthority(SpringSecurityUtils.NO_ROLE)]
 
	UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
	   return loadUserByUsername(username)
	}
 
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
	   User.withTransaction { status ->
 
		  User user = User.findByUsername(username)
		  if (!user) throw new UsernameNotFoundException('User not found', username)
 
		  def authorities = user.authorities.collect {
			  new SimpleGrantedAuthority(it.authority)
		  }
		  
//		  def authorities = [ROLE_Admin.getRoleName()]
 
//		  return new grails.plugin.springsecurity.userdetails.GrailsUser(user.username, user.password, true, true, true, true, authorities ?: NO_ROLES, user.id)
		  return new grails.plugin.springsecurity.userdetails.GrailsUser(user.username, user.password, true, true, true, true, authorities, user.id)
	   }
	}
	
	
	/**
	 * Sets up granted authorities based up DB provisioned ROLES.
	 * Could setup Spring to use Groups at DB level but here simpler just to use a basic subsets approach
	 */
//	protected List<GrantedAuthority> getGrantedAuthoritiesForUser(final com.become.bid.User user) throws ServiceException {
//		final List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
//		if (isAdminLevel(user)) {
//			grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_Admin.getRoleName()));
//			grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_AccountManager.getRoleName()));
//			grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_FinanceManager.getRoleName()));
//			grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_SalesManager.getRoleName()));
//			grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_Merchant.getRoleName()));
//			grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_ReportUser.getRoleName()));
//		} else if (isManagerLevel(user)) {
//			grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_Merchant.getRoleName()));
//			grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_ReportUser.getRoleName()));
//			if(isFinanceManager(user)) {
//				grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_FinanceManager.getRoleName()));
//			} else if (isSalesManager(user)) {
//				grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_SalesManager .getRoleName()));
//			} else if (isAccountManager(user)) {
//				grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_AccountManager.getRoleName()));
//			}
//		} else if (isMerchantLevel(user)) {
//			grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_Merchant.getRoleName()));
//			grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_ReportUser.getRoleName()));
//		} else {
//			grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_ReportUser.getRoleName()));
//		}
//		return grantedAuthorities;
//	}
	
//	protected boolean isAdminLevel(final com.become.bid.User user) {
//		return user.getRole() == RolesEnum.ROLE_Admin;
//	}
//	
//	protected boolean isManagerLevel(final com.become.bid.User user)  {
//		return (user.getRole() == RolesEnum.ROLE_FinanceManager) || (user.getRole() == RolesEnum.ROLE_AccountManager) || (user.getRole() == RolesEnum.ROLE_SalesManager);
//	}
//	
//	protected boolean isMerchantLevel(final com.become.bid.User user)  {
//		return (user.getRole() == RolesEnum.ROLE_Merchant) || (user.getRole() == RolesEnum.ROLE_ReportUser);
//	}
//	
//	protected boolean isFinanceManager(final com.become.bid.User user) {
//		return (user.getRole() == RolesEnum.ROLE_FinanceManager);
//	}
//	
//	protected boolean isSalesManager(final com.become.bid.User  user)  {
//		return (user.getRole() == RolesEnum.ROLE_SalesManager);
//	}
//	
//	protected boolean isAccountManager(final com.become.bid.User  user)  {
//		return (user.getRole() == RolesEnum.ROLE_AccountManager);
//	}
	
	
}







