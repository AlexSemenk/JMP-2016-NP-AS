package com.epam.jmp.library.dao;

import com.epam.jmp.library.dao.exception.DaoException;
import com.epam.jmp.library.entity.Library;
import com.epam.jmp.library.entity.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers() throws DaoException;

    User getUserById(Long id) throws DaoException;

    User getUserByLogin(String login) throws DaoException;

    void createUser(User user) throws DaoException;

    void updateUser(User user) throws DaoException;

    void deleteUser(Long id) throws DaoException;

}
