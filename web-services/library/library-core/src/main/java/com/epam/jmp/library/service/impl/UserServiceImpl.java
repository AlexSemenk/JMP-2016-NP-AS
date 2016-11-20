package com.epam.jmp.library.service.impl;

import com.epam.jmp.library.dao.UserDao;
import com.epam.jmp.library.dao.exception.DaoException;
import com.epam.jmp.library.dao.impl.UserDaoImpl;
import com.epam.jmp.library.entity.User;
import com.epam.jmp.library.service.UserService;
import com.epam.jmp.library.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDao.getAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserByLogin(String login) throws ServiceException {
        try {
            return userDao.getUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createUser(User user) throws ServiceException {
        try {
            userDao.createUser(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        try {
            userDao.updateUser(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUser(Long id) throws ServiceException {
        try {
            userDao.deleteUser(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
