package com.epam.jmp.library.service.impl;

import com.epam.jmp.library.dao.LibraryDao;
import com.epam.jmp.library.dao.exception.DaoException;
import com.epam.jmp.library.dao.impl.LibraryDaoImpl;
import com.epam.jmp.library.entity.Library;
import com.epam.jmp.library.service.LibraryService;
import com.epam.jmp.library.service.exception.ServiceException;

import java.util.List;

public class LibraryServiceImpl implements LibraryService {

    private LibraryDao libraryDao = new LibraryDaoImpl();


    @Override
    public List<Library> getAllLibraries() throws ServiceException {
        try {
            return libraryDao.getAllLibraries();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Library getLibrary(Long id) throws ServiceException {
        try {
            return libraryDao.getLibraryById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createLibrary(Library library) throws ServiceException {
        try {
            libraryDao.createLibrary(library);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateLibrary(Library library) throws ServiceException {
        try {
            libraryDao.updateLibrary(library);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteLibrary(Long libraryId) throws ServiceException {
        try {
            libraryDao.deleteLibrary(libraryId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
