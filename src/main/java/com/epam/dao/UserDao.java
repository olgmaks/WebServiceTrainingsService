package com.epam.dao;

import com.epam.model.Role;
import com.epam.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by OLEG on 29.11.2015.
 */
public class UserDao {

    private static final User USER_1 = new User(1, "John", "Dow", "dog@gmail.com", "qwerty", new Role[]{RoleDao.MANAGER, RoleDao.USER, RoleDao.ADMIN});
    private static final User USER_2 = new User(2, "Tom", "Smith", "fsdf@gmail.com", "qwerty", new Role[]{RoleDao.USER});
    private static final User USER_3 = new User(3, "test3", "testLast3", "dog3@gmail.com", "qwerty", new Role[]{RoleDao.USER, RoleDao.ADMIN});
    private static final User USER_4 = new User(4, "test4", "testLast4", "dog4@gmail.com", "qwerty", new Role[]{RoleDao.MANAGER, RoleDao.USER});
    private static final User USER_5 = new User(5, "test5", "testLast5", "dog5@gmail.com", "qwerty", new Role[]{RoleDao.MANAGER, RoleDao.USER, RoleDao.ADMIN});

    private static User[] USERS = {USER_1, USER_2, USER_3, USER_4, USER_5};

    public UserDao() {


    }
    
    public int getUsersQuantity () {
    	return USERS.length;
    }

    public void save(User user) {
        User[] updated = new User[USERS.length + 1];
        System.arraycopy(USERS, 0, updated, 0, USERS.length);
        updated[USERS.length] = user;
        USERS = updated;
    }


    public User getUserById(int id) {
        for (User user : USERS) {
            if (user.getId() == id)
                return user;
        }

        return null;
    }

    public Role[] getRolesByUserId(int id) {

        return getUserById(id).getRoles();
    }

    public User[] getAllUsers() {
        return USERS;
    }

    public User getUserByEmail(String email) {


        for (User user : USERS) {

            if (user.getEmail().equals(email)) {
                return user;
            }
        }

        return null;
    }

    public boolean deleteUserById(int id) {

        User user = getUserById(id);

        if (user == null) {

            return false;

        } else {

            List<User> list = new ArrayList<>(Arrays.asList(USERS));

            list.remove(user);

            USERS = list.toArray(new User[list.size()]);

            return true;
        }
    }

    public static void main(String[] args) {

        UserDao userDao = new UserDao();

        System.out.println("roles = " + userDao.getRolesByUserId(1));
        System.out.println("user = " + userDao.getUserById(1));
    }


}
