package com.epam.jmp.library.service;

import com.epam.jmp.library.entity.User;
import com.epam.jmp.library.service.exception.ServiceException;

import java.util.List;

public interface UserService {

    List<User> getAllUsers() throws ServiceException;

    User getUserByLogin(String login) throws ServiceException;

    void createUser(User user) throws ServiceException;

    void updateUser(User user) throws ServiceException;

    void deleteUser(Long id) throws ServiceException;

}
