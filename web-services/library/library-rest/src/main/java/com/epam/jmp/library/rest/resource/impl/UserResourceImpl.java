package com.epam.jmp.library.rest.resource.impl;

import com.epam.jmp.library.entity.User;
import com.epam.jmp.library.rest.resource.UserResource;
import com.epam.jmp.library.service.LibraryService;
import com.epam.jmp.library.service.UserService;
import com.epam.jmp.library.service.exception.ServiceException;
import com.epam.jmp.library.service.impl.LibraryServiceImpl;
import com.epam.jmp.library.service.impl.UserServiceImpl;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

public class UserResourceImpl implements UserResource {

    private UserService userService = new UserServiceImpl();

    @Override
    public List<User> getAllUsers() throws ServiceException {
        return userService.getAllUsers();
    }

    @Override
    public User getUser(String login) throws ServiceException {
        User user = userService.getUserByLogin(login);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return user;
    }

    @Override
    public Response createUser(User user) throws ServiceException {
        userService.createUser(user);
        URI createdUri = URI.create("/users/" + user.getId());
        return Response.created(createdUri).build();
    }

    @Override
    public void updateUser(String login, User updatedUser) throws ServiceException {
        User user = userService.getUserByLogin(login);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        updatedUser.setId(user.getId());
        userService.updateUser(updatedUser);
    }

    @Override
    public void deleteUser(String login) throws ServiceException {
        User user = userService.getUserByLogin(login);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        userService.deleteUser(user.getId());
    }

}
