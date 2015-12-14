package com.epam.dao;

import com.epam.model.Role;

/**
 * Created by OLEG on 29.11.2015.
 */
public class RoleDao {

    public static final Role GUEST = new Role();
    public static final Role ADMIN = new Role();
    public static final Role USER = new Role();
    public static final Role MANAGER = new Role();

    public static final Role[] ROLES = {GUEST, ADMIN, USER, MANAGER};

    static {

        GUEST.setId(4);
        GUEST.setName("guest");

        ADMIN.setId(1);
        ADMIN.setName("admin");

        USER.setId(2);
        USER.setName("user");

        MANAGER.setId(3);
        MANAGER.setName("manager");
    }

    public Role getRoleByName(String name) {

        for (Role role : ROLES) {
            if (role.getName().equals(name))
                return role;
        }

        return null;
    }


    public Role[] getRoles() {
        return ROLES;
    }
}
