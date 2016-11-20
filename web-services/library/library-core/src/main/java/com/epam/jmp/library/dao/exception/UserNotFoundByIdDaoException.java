package com.epam.jmp.library.dao.exception;

public class UserNotFoundByIdDaoException extends DaoException {

    public UserNotFoundByIdDaoException(Long id) {
        super(String.format("User with id %d doesn't exist", id));
    }

}
