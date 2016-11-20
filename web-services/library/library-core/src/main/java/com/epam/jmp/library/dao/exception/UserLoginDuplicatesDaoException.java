package com.epam.jmp.library.dao.exception;

public class UserLoginDuplicatesDaoException extends DaoException {

    public UserLoginDuplicatesDaoException(String login) {
        super(String.format("User with login '%s' already exist", login));
    }

}
