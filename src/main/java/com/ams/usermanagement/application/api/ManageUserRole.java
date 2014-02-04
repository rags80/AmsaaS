package com.ams.usermanagement.application.api;

import java.util.List;

public interface ManageUserRole
{
	public void createUserRole(String userRoleName);

	public void updateUserRole(String userRoleName);

	public void deleteUserRole(String userRoleName);

	public List<String> getUserRoles();

	public List<String> getMyRoles(long userId);
}
