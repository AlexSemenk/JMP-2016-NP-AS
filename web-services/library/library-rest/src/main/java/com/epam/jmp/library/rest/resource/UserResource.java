package com.epam.jmp.library.rest.resource;

import com.epam.jmp.library.entity.Library;
import com.epam.jmp.library.entity.User;
import com.epam.jmp.library.service.exception.ServiceException;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Api(value = "/users", description = "Operations about users")
public interface UserResource {

    @GET
    @ApiOperation(value = "Find all existing users", notes = "Returns a list of users", response = User.class, responseContainer="List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All users are returned")})
    List<User> getAllUsers() throws ServiceException;

    @GET
    @Path("/{login}")
    @ApiOperation(value = "Find user by login", notes = "Returns a single user", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "User not found")})
    User getUser(@ApiParam(value = "Login of the user to be fetched", example = "Alex1234", required = true) @PathParam("login") String login) throws ServiceException;

    @POST
    @ApiOperation(value = "Create new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User is created"),
            @ApiResponse(code = 405, message = "Invalid input")})
    Response createUser(@ApiParam(value = "User object to be created", required = true) User user) throws ServiceException;

    @PUT
    @Path("/{login}")
    @ApiOperation(value = "Update existing user")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "User is updated"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Validation exception")})
    void updateUser(@ApiParam(value = "Login of the user to be updated", example = "Alex1234", required = true) @PathParam("login") String login,
                    @ApiParam(value = "Updated user object", required = true) User user) throws ServiceException;

    @DELETE
    @Path("/{login}")
    @ApiOperation(value = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "User not found")})
    void deleteUser(@ApiParam(value = "Login of the user to be deleted", example = "Alex1234", required = true) @PathParam("login") String login) throws ServiceException;

}
