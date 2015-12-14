package com.epam.web.service;

import com.epam.web.servicewsimpl.UserServiceImpl;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Oleh_Maksymuk on 12/5/2015.
 */
@Path("/userService")
public class UserServiceRS {

    private static final Logger LOG = Logger.getLogger(UserServiceRS.class);

    private Gson gson;
    private UserServiceImpl userServiceWSImpl;

    public UserServiceRS() {

        gson = new Gson();
        userServiceWSImpl = new UserServiceImpl();
    }


    @GET
    @Path("/getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        return Response.ok(gson.toJson(userServiceWSImpl.getAllUsers())).build();
    }

    @POST
    @Path("/authorize")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorize(@FormParam("email") String email,
                              @FormParam("password") String password) {

        return Response.ok(gson.toJson(userServiceWSImpl.authorize(email, password))).build();
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@FormParam("email") String email,
                             @FormParam("password") String password,
                             @FormParam("firstName") String firstName,
                             @FormParam("lastName") String lastName) {


        return Response.ok(gson.toJson(userServiceWSImpl.register(email, password, firstName, lastName))).build();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("email") String email,
                          @FormParam("password") String password) {

        return Response.ok(gson.toJson(userServiceWSImpl.login(email, password))).build();
    }

    @POST
    @Path("/getUsersByRoleName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersByRoleName(@FormParam("roleName") String role) {

        return Response.ok(gson.toJson(userServiceWSImpl.getUsersByRoleName(role))).build();
    }


    @POST
    @Path("/deleteUserById")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserById(@FormParam("id") String id) {

        return Response.ok(gson.toJson(userServiceWSImpl.deleteUserById(id))).build();
    }


}
