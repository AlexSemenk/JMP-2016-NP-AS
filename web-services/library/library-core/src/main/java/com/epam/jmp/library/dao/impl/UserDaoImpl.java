package com.epam.jmp.library.dao.impl;

import com.epam.jmp.library.dao.UserDao;
import com.epam.jmp.library.dao.exception.DaoException;
import com.epam.jmp.library.dao.exception.UserLoginDuplicatesDaoException;
import com.epam.jmp.library.dao.exception.UserNotFoundByIdDaoException;
import com.epam.jmp.library.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static long ID_SEQUENCE = 0;
    private static final HashMap<Long, User> indexById = new HashMap<>();
    private static final HashMap<String, User> indexByLogin = new HashMap<>();
    private static final HashSet<User> users = new HashSet<>();

    @Override
    public List<User> getAllUsers() throws DaoException {
        return new ArrayList<>(users);
    }

    @Override
    public User getUserById(Long id) throws DaoException {
        return indexById.get(id);
    }

    @Override
    public User getUserByLogin(String login) throws DaoException {
        return indexByLogin.get(login);
    }

    @Override
    public void createUser(User user) throws DaoException {
        if (indexByLogin.containsKey(user.getLogin())) {
            throw new UserLoginDuplicatesDaoException(user.getLogin());
        }
        Long id = ID_SEQUENCE++;
        user.setId(id);
        indexById.put(id, user);
        users.add(user);
    }

    @Override
    public void updateUser(User updatedUser) throws DaoException {
        if(!indexById.containsKey(updatedUser.getId())) {
            throw new UserNotFoundByIdDaoException(updatedUser.getId());
        }
        User user = indexById.get(updatedUser.getId());
        if (!user.getLogin().equals(updatedUser.getLogin()) && indexByLogin.containsKey(user.getLogin())) {
            throw new UserLoginDuplicatesDaoException(updatedUser.getLogin());
        }
        user.setLogin(updatedUser.getLogin());
        user.setEmail(updatedUser.getEmail());
    }

    @Override
    public void deleteUser(Long id) throws DaoException {
        if(!indexById.containsKey(id)) {
            throw new UserNotFoundByIdDaoException(id);
        }
        User user = indexById.remove(id);
        indexByLogin.remove(user.getLogin());
        users.remove(user);
    }
}
