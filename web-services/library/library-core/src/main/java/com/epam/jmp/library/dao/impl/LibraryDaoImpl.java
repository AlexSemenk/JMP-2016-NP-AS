package com.epam.jmp.library.dao.impl;

import com.epam.jmp.library.dao.LibraryDao;
import com.epam.jmp.library.dao.exception.DaoException;
import com.epam.jmp.library.dao.exception.LibraryNameDuplicatesDaoException;
import com.epam.jmp.library.dao.exception.LibraryNotFoundByIdDaoException;
import com.epam.jmp.library.entity.Library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LibraryDaoImpl implements LibraryDao {

    private static long ID_SEQUENCE = 0;
    private static final HashMap<Long, Library> indexById = new HashMap<>();
    private static final HashMap<String, Library> indexByName = new HashMap<>();
    private static final HashSet<Library> libraries = new HashSet<>();

    @Override
    public List<Library> getAllLibraries() throws DaoException {
        return new ArrayList<>(libraries);
    }

    @Override
    public Library getLibraryById(Long id) throws DaoException {
        return indexById.get(id);
    }

    @Override
    public Library getLibraryByName(String name) throws DaoException {
        return indexByName.get(name);
    }

    @Override
    public void createLibrary(Library library) throws DaoException {
        if (indexByName.containsKey(library.getName())) {
            throw new LibraryNameDuplicatesDaoException(library.getName());
        }
        Long id = ID_SEQUENCE++;
        library.setId(id);
        indexById.put(id, library);
        libraries.add(library);
    }

    @Override
    public void updateLibrary(Library newLibrary) throws DaoException {
        if(!indexById.containsKey(newLibrary.getId())) {
            throw new LibraryNotFoundByIdDaoException(newLibrary.getId());
        }
        Library oldLibrary = indexById.get(newLibrary.getId());
        if (!newLibrary.getName().equals(oldLibrary.getName()) && indexByName.containsKey(newLibrary.getName())) {
            throw new LibraryNameDuplicatesDaoException(newLibrary.getName());
        }
        oldLibrary.setName(newLibrary.getName());
        oldLibrary.setDescription(newLibrary.getDescription());
    }

    @Override
    public void deleteLibrary(Long id) throws DaoException {
        if(!indexById.containsKey(id)) {
            throw new LibraryNotFoundByIdDaoException(id);
        }
        Library library = indexById.remove(id);
        indexByName.remove(library.getName());
        libraries.remove(library);
    }

}
