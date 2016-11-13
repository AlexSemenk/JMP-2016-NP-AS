package com.epam.jmp.library.dao;

import com.epam.jmp.library.dao.exception.DaoException;
import com.epam.jmp.library.entity.Library;

import java.util.List;

public interface LibraryDao {

    List<Library> getAllLibraries() throws DaoException;

    Library getLibraryById(Long id) throws DaoException;

    Library getLibraryByName(String name) throws DaoException;

    void createLibrary(Library library) throws DaoException;

    void updateLibrary(Library library) throws DaoException;

    void deleteLibrary(Long id) throws DaoException;

}
