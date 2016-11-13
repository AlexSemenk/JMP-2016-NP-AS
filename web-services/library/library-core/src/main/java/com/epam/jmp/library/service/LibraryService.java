package com.epam.jmp.library.service;

import com.epam.jmp.library.entity.Library;
import com.epam.jmp.library.service.exception.ServiceException;

import java.util.List;

public interface LibraryService {

    List<Library> getAllLibraries() throws ServiceException;

    Library getLibrary(Long id) throws ServiceException;

    void createLibrary(Library library) throws ServiceException;

    void updateLibrary(Library library) throws ServiceException;

    void deleteLibrary(Long libraryId) throws ServiceException;

}

