package com.epam.web.servicewsimpl;

import com.epam.bo.UserBO;
import com.epam.dao.RoleDao;
import com.epam.model.Role;
import com.epam.model.User;
import com.epam.modelweb.ResponseWrapper;
import com.epam.modelweb.SummaryFault;
import com.epam.modelweb.SummarySuccess;
import com.epam.web.service.UserService;
import org.apache.log4j.Logger;

import javax.jws.WebService;
import java.util.Arrays;
import java.util.regex.Pattern;

@WebService(endpointInterface = "com.epam.web.service.UserService")
public class UserServiceImpl implements UserService {

    private static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private static final int MIN_PASSWORD_LENGTH = 6;

    private static final Logger LOG = Logger.getLogger(UserService.class);

    private UserBO userBO;

    public UserServiceImpl() {
        userBO = new UserBO();
    }


    public ResponseWrapper register(String email, String password, String firstName, String lastName) {

        LOG.info("method params : { " + "email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + " }");
//System.out.println("method params : { " + "email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + " }");
        ResponseWrapper response = validateEmailAndPassword(email, password);

        if (response != null) {
            return response;
        }

        if (firstName == null || firstName.isEmpty() || firstName.contains(" ")) {
            LOG.info("firstName == null || firstName.isEmpty() || firstName does not much REGEX");
            return ResponseWrapper.fault(SummaryFault.INVALID_FIRST_NAME_FAULT_MESSAGE);
        }

        if (lastName == null || lastName.isEmpty() || lastName.contains(" ")) {

            LOG.info("lastName == null || lastName.isEmpty() || lastName does not much REGEX)");
            return ResponseWrapper.fault(SummaryFault.INVALID_LAST_NAME_FAULT_MESSAGE);
        }

        if (userBO.alrearyRegister(email)) {

            LOG.info(SummaryFault.SUCH_USER_EXISTS_FAULT_MESSAGE + email);
            return ResponseWrapper.fault(SummaryFault.SUCH_USER_EXISTS_FAULT_MESSAGE);
        }

        User user = new User(userBO.getNewId(), firstName, lastName, email, password, new Role[]{RoleDao.USER});

        if (userBO.register(user) != null) {

            LOG.info(SummarySuccess.REGISTRATION_SUCCESS_MESSAGE + user);
            return ResponseWrapper.success(SummarySuccess.REGISTRATION_SUCCESS_MESSAGE, user);
        }

        LOG.info(SummaryFault.REGISTRATION_FAULT_MESSAGE);
        return ResponseWrapper.fault(SummaryFault.REGISTRATION_FAULT_MESSAGE);
    }

    public ResponseWrapper login(String email, String password) {

        LOG.info("method params : { " + "email=" + email + ", password=" + password + " }");
       //System.out.println("method params : { " + "email=" + email + ", password=" + password + " }");
        ResponseWrapper response = validateEmailAndPassword(email, password);

        if (response != null) {
            return response;
        }

        User user = userBO.login(email, password);

        if (user != null) {
            LOG.info("user login success : " + user);
            return new ResponseWrapper(new SummarySuccess(SummarySuccess.LOGIN_SUCCESS_MESSAGE), user);
        }

        LOG.info("invalid credentials : " + "email=" + email + ", password=" + password);
        return new ResponseWrapper(new SummaryFault(SummaryFault.INVALID_CREDENTIALS_FAULT_MESSAGE));
    }

    public ResponseWrapper authorize(String email, String password) {

        LOG.info("method params : { " + "email=" + email + ", password=" + password + " }");

        ResponseWrapper response = validateEmailAndPassword(email, password);

        if (response != null) {
            return response;
        }

        Role[] roles = userBO.authorize(email, password);

        if (Arrays.asList(roles).contains(RoleDao.GUEST)) {

            LOG.info("authorization success guest");
            return ResponseWrapper.success(SummarySuccess.AUTHORISATION_GUEST_MESSAGE, roles);
        }

        LOG.info(SummarySuccess.AUTHORISATION_SUCCESS_MESSAGE + Arrays.toString(roles));
        return ResponseWrapper.success(SummarySuccess.AUTHORISATION_SUCCESS_MESSAGE, roles);
    }

    public ResponseWrapper getAllUsers() {

        LOG.info("method params : no params");

        User[] users = userBO.getAllUsers();

        if (users == null || users.length == 0) {

            LOG.info("users == null || users is empty");
            return ResponseWrapper.fault(SummaryFault.NO_USERS_FOUND_IN_SYSTEM_FAULT_MESSAGE);
        }

        LOG.info(SummarySuccess.GET_ALL_USERS_SUCCESS_MESSAGE + Arrays.toString(users));
        return ResponseWrapper.success(SummarySuccess.GET_ALL_USERS_SUCCESS_MESSAGE, users);
    }

    public ResponseWrapper getUsersByRoleName(String role) {

        LOG.info("method params : { role" + role + " }");

        ResponseWrapper response = ResponseWrapper.fault(String.format(SummaryFault.INVALID_ROLE_NAME_FAULT_MESSAGE, role));

        if (role == null || role.isEmpty() || !userBO.isValidRoleName(role)) {

            LOG.info("role == null || role.isEmpty() || not valid role name");
            return response;
        }

        User[] users = userBO.getUsersByRoleName(role);

        if (users == null) {

            LOG.info("users result == null");
            return response;
        }

        if (users.length == 0) {

            LOG.info("users result is empty");
            return ResponseWrapper.fault(String.format(SummarySuccess.NO_USER_WITH_INDICATED_ROLE_MESSAGE, role));
        }

        LOG.info(String.format(SummarySuccess.GET_USERS_BY_ROLE_MESSAGE, role) + ", users=" + Arrays.toString(users));
        return ResponseWrapper.success(String.format(SummarySuccess.GET_USERS_BY_ROLE_MESSAGE, role), users);
    }

    public ResponseWrapper deleteUserById(String stringID) {

        LOG.info("method params : { id=" + stringID + "}");

        Integer intID = null;

        try {

            intID = Integer.parseInt(stringID);

            if (intID <= 0) {
                return ResponseWrapper.fault(SummaryFault.INVALID_INPUT_DATA_TYPE_FOR_ID + stringID);
            }

        } catch (Exception e) {

            LOG.info(e);
            LOG.error(e);
            return ResponseWrapper.fault(SummaryFault.INVALID_INPUT_DATA_TYPE_FOR_ID + stringID);
        }

        boolean isDeleted = userBO.deleteUserById(intID);
        ResponseWrapper response = new ResponseWrapper();

        if (isDeleted) {

            LOG.info(String.format(SummarySuccess.USER_DELETED_SUCCESS_MESSAGE, intID));
            response.setSummary(new SummarySuccess(String.format(SummarySuccess.USER_DELETED_SUCCESS_MESSAGE, intID)));

        } else {

            LOG.info(SummaryFault.USER_NOT_DELETED_FAULT_MESSAGE);
            response.setSummary(new SummaryFault(SummaryFault.USER_NOT_DELETED_FAULT_MESSAGE));
        }

        return response;
    }

    private ResponseWrapper validateEmailAndPassword(String email, String password) {

        if (email == null || email.isEmpty() || !Pattern.compile(EMAIL_VALIDATION_REGEX).matcher(email).matches()) {

            LOG.info("validateEmail : " + "email == null || email.isEmpty() || does not much REGEX");
            return ResponseWrapper.fault(SummaryFault.INVALID_EMAIL_FAULT_MESSAGE);
        }

        if (password == null || password.isEmpty() || password.length() < MIN_PASSWORD_LENGTH) {

            LOG.info("validateEmail : " + "password == null || password.isEmpty() || does not much REGEX");
            return ResponseWrapper.fault(SummaryFault.INVALID_PASSWORD__FAULT_MESSAGE);
        }

        return null;
    }

}