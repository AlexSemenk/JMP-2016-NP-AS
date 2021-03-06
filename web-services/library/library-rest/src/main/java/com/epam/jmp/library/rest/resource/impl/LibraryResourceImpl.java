package com.epam.jmp.library.rest.resource.impl;

import com.epam.jmp.library.entity.Library;
import com.epam.jmp.library.rest.resource.LibraryBookResource;
import com.epam.jmp.library.rest.resource.LibraryResource;
import com.epam.jmp.library.service.LibraryService;
import com.epam.jmp.library.service.exception.ServiceException;
import com.epam.jmp.library.service.impl.LibraryServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

public class LibraryResourceImpl implements LibraryResource {

    private LibraryService libraryService;

    public LibraryResourceImpl() {
        this(new LibraryServiceImpl());
    }

    public LibraryResourceImpl(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public List<Library> getLibraries() throws ServiceException {
        return libraryService.getAllLibraries();
    }

    @Override
    public Library getLibrary(Long id) throws ServiceException {
        Library library = libraryService.getLibrary(id);
        if (library == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return library;
    }

    @Override
    public Response createLibrary(Library library) throws ServiceException {
        libraryService.createLibrary(library);
        URI createdUri = URI.create("/libraries/" + library.getId());
        return Response.created(createdUri).build();
    }

    @Override
    public void updateLibrary(Long id, Library newLibrary) throws ServiceException {
        Library library = libraryService.getLibrary(id);
        if (library == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        newLibrary.setId(id);
        libraryService.updateLibrary(newLibrary);
    }

    @Override
    public void deleteLibrary(Long id) throws ServiceException {
        Library library = libraryService.getLibrary(id);
        if (library == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        libraryService.deleteLibrary(id);
    }

    @Override
    public LibraryBookResource getBookResource(Long id) throws ServiceException {
        Library library = libraryService.getLibrary(id);
        if (library == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return new LibraryBookResourceImpl(library);
    }

}