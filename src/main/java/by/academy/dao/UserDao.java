package by.academy.dao;

import java.util.List;

import by.academy.entity.Role;

public interface UserDao {
	
	void addRole(int userId, int roleId);

	List<Role> findRoles(int userId);
}
