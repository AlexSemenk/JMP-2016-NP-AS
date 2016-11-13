package com.epam.jmp.library.rest.resource;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.epam.jmp.library.entity.Library;
import com.epam.jmp.library.rest.dto.LibraryDto;
import com.epam.jmp.library.service.LibraryService;
import com.epam.jmp.library.service.exception.ServiceException;
import com.epam.jmp.library.service.impl.LibraryServiceImpl;

@Path("/libraries")
public class LibrariesResource {

	private LibraryService libraryService = new LibraryServiceImpl();

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Library> getLibraries() throws ServiceException {
		return libraryService.getAllLibraries();
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Library getLibray(@PathParam("id") Long id) throws ServiceException {
		return libraryService.getLibrary(id);
	}

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createLibrary(Library library) throws ServiceException {
		libraryService.createLibrary(library);
		URI createdUri = URI.create("http://localhost:8080/jmp/library/" + library.getId());
		return Response.created(createdUri).build();
	}

	@DELETE
	@Path("/{id}")
	public void deleteLibray(@PathParam("id") Long id) throws ServiceException {
		libraryService.deleteLibrary(id);
	}

	@Path("/{id}/books")
	public LibrariesBookResource getBookResource(@PathParam("id") Long id) throws ServiceException {
		Library library = libraryService.getLibrary(id);
		if (library != null) {
			return new LibrariesBookResource(library);
		} else {
			return null;
		}
	}

}