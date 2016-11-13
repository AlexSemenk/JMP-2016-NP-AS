package com.epam.jmp.library.dao.exception;

import com.epam.jmp.library.dao.exception.DaoException;

/**
 * Created by Alex on 13.11.2016.
 */
public class LibraryNotFoundByIdDaoException extends DaoException {

    public LibraryNotFoundByIdDaoException(Long id) {
        super(String.format("Library with id %d doesn't exist", id));
    }

}
