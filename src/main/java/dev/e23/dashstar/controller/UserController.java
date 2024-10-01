package dev.e23.dashstar.controller;

import jakarta.ws.rs.*;

@Path("/users")
public class UserController {

    @GET
    @Path("/")
    public String getAllUsers() {
        return "/";
    }

    @GET
    @Path("/{id}")
    public String getUserById(@PathParam("id") int id) {
        return String.valueOf(id);
    }

    @POST
    @Path("/")
    public String createUser() {
        return "/";
    }

    @PUT
    @Path("/{id}")
    public String updateUser(@PathParam("id") int id) {
        return String.valueOf(id);
    }

    @DELETE
    @Path("/{id}")
    public String deleteUser(@PathParam("id") int id) {
        return "DELETE /{id}";
    }

    @POST
    @Path("/login")
    public String login() {
        return "/login";
    }

    @POST
    @Path("/register")
    public String register() {
        return "/register";
    }
}
