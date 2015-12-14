package com.epam.web.service;

import com.epam.modelweb.ResponseWrapper;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by OLEG on 29.11.2015.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserService {


    @WebMethod(operationName = "register")
    ResponseWrapper register(
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password,
            @WebParam(name = "firstName") String firstName,
            @WebParam(name = "lastName") String lastName
    );


    @WebMethod(operationName = "login")
    ResponseWrapper login(
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password
    );


    @WebMethod(operationName = "authorize")
    ResponseWrapper authorize(
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password
    );


    @WebMethod(operationName = "getAll")
    ResponseWrapper getAllUsers();


    @WebMethod(operationName = "getByRoleName")
    ResponseWrapper getUsersByRoleName(
            @WebParam(name = "role") String role
    );


    @WebMethod(operationName = "delete")
    ResponseWrapper deleteUserById(
            @WebParam(name = "id") String id
    );


}
