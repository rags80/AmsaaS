/**
 * 
 */
package com.ams.users.application.api;

import java.util.List;

import com.ams.users.domain.model.Person;
import com.ams.users.domain.model.UserRole;

/**
 * @author Raghavendra Badiger
 * @param <Role>
 * 
 */
public interface ManageUserRole
{
	boolean assignRolesToUser(Person user, List<UserRole> roles);

	boolean createNewRoles(List<UserRole> roles);

	boolean deleteRoles(List<UserRole> roles);

	List<UserRole> getAllRoles();

	List<UserRole> getAllUserRoles(Person user);

	boolean revokeRolesOfUser(Person user, List<UserRole> roles);

}
