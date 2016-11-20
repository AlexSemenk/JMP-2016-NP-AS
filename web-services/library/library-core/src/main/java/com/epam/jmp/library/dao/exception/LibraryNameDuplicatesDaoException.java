package com.epam.jmp.library.dao.exception;

import com.epam.jmp.library.dao.exception.DaoException;

/**
 * Created by Alex on 13.11.2016.
 */
public class LibraryNameDuplicatesDaoException extends DaoException {

    public LibraryNameDuplicatesDaoException(String name) {
        super(String.format("Library with name '%s' already exist", name));
    }

}
