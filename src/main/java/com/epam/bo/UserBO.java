package com.epam.bo;

import com.epam.dao.RoleDao;
import com.epam.dao.UserDao;
import com.epam.model.Role;
import com.epam.model.User;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by OLEG on 29.11.2015.
 */
public class UserBO {

	private UserDao userDao;
	private RoleDao roleDao;

	public UserBO() {
		userDao = new UserDao();
		roleDao = new RoleDao();
	}

	public User register(User user) {

		if (userDao.getUserByEmail(user.getEmail()) == null) {

			userDao.save(user);

			return user;

		} else {

			return null;
		}

	}

	public Role[] getAllRoles() {
		return roleDao.getRoles();
	}

	public User login(String email, String password) {

		User user = userDao.getUserByEmail(email);

		if (user == null) {
			return null;
		} else {
			if (user.getPassword().equals(password)) {
				return user;
			} else {
				return null;
			}
		}
	}

	public Role[] authorize(String email, String password) {

		if (email == null || password == null || login(email, password) == null) {
			return new Role[] { RoleDao.GUEST };
		}

		User user = userDao.getUserByEmail(email);

		if (user != null)
			return user.getRoles();

		return new Role[] { RoleDao.GUEST };
	}

	public User[] getAllUsers() {
		return userDao.getAllUsers();
	}

	public User[] getUsersByRoleName(String role) {

		ArrayList<User> result = new ArrayList<>();

		for (User u : getAllUsers()) {
			if (Arrays.asList(u.getRoles()).contains(roleDao.getRoleByName(role)))
				result.add(u);
		}

		return result.toArray(new User[result.size()]);
	}

	public boolean deleteUserById(int id) {
		return userDao.deleteUserById(id);
	}

	public static void main(String[] args) {
		UserBO userBO = new UserBO();

		userBO.register(new User(7, "adsff", "sdfa", "afsaf", "ertre", new Role[] { RoleDao.USER }));

		System.out.println(Arrays.toString(userBO.authorize("dog@gmail.com", "qwerty")));

		System.out.println(userBO.alrearyRegister("dog@gmail.com"));
	}

	public boolean isValidRoleName(String roleName) {

		boolean res = false;

		for (Role role : roleDao.getRoles()) {

			if (role.getName().equals(roleName)) {

				res = true;

				return res;
			}
		}

		return res;
	}

	public boolean alrearyRegister(String email) {
		return userDao.getUserByEmail(email) != null;
	}

	public int getNewId() {
		return userDao.getUsersQuantity();
	}
}
