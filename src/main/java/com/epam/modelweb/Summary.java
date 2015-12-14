package com.epam.modelweb;

/**
 * Created by OLEG on 01.12.2015.
 */

public class Summary {

	public static final String LOGIN_SUCCESS_MESSAGE = "Login successful";
	public static final String REGISTRATION_SUCCESS_MESSAGE = "Registration successful";
	public static final String GET_ALL_USERS_SUCCESS_MESSAGE = "Get all users successful";
	public static final String AUTHORISATION_SUCCESS_MESSAGE = "User has been authorised successful";
	public static final String NO_USER_WITH_INDICATED_ROLE_MESSAGE = "There are no users with indicated role, Role = [%s]";
	public static final String GET_USERS_BY_ROLE_MESSAGE = "Users for role %s successfully found";
	public static final String AUTHORISATION_GUEST_MESSAGE = "Guest has been authorised successful";;
	public static final String USER_DELETED_SUCCESS_MESSAGE = "User has been deleted successfully, User_ID = %s";

	public static final String INVALID_CREDENTIALS_FAULT_MESSAGE = "User email and/or password are/is incorrect, please try again";
	public static final String REGISTRATION_FAULT_MESSAGE = "Registration failed, please fill all fields and try again";
	public static final String INVALID_ROLE_NAME_FAULT_MESSAGE = "Invalid role name, Role = [%s]";
	public static final String INVALID_PASSWORD_FAULT_MESSAGE = "Password is invalid or empty";
	public static final String USER_NOT_DELETED_FAULT_MESSAGE = "User cannot be deleted";
	public static final String INVALID_PASSWORD__FAULT_MESSAGE = "Invalid password. Password should contain at least 6 letters.";
	public static final String INVALID_EMAIL_FAULT_MESSAGE = "Invalid email";
	public static final String SUCH_USER_EXISTS_FAULT_MESSAGE = "Such user already exists in a system. Use another email";
	public static final String INVALID_LAST_NAME_FAULT_MESSAGE = "Invalid last Name";
	public static final String INVALID_FIRST_NAME_FAULT_MESSAGE = "Invalid first Name";
	public static final String NO_USERS_FOUND_IN_SYSTEM_FAULT_MESSAGE = "No users found in system";
	public static final String INVALID_INPUT_DATA_TYPE_FOR_ID = "Invalid input data type for id=";

	public Summary() {
	}

}
