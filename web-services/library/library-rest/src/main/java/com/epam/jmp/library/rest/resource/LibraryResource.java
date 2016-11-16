package com.epam.jmp.library.rest.resource;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.epam.jmp.library.entity.Library;
import com.epam.jmp.library.service.exception.ServiceException;

@Path("/libraries")
public interface LibraryResource {

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Library> getLibraries() throws ServiceException;

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Library getLibray(@PathParam("id") Long id) throws ServiceException;

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createLibrary(Library library) throws ServiceException;

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void updateLibrary(@PathParam("id") Long id, Library newLibrary) throws ServiceException;

	@DELETE
	@Path("/{id}")
	public void deleteLibray(@PathParam("id") Long id) throws ServiceException;

	@Path("/{id}/books")
	public LibraryBookResource getBookResource(@PathParam("id") Long id) throws ServiceException;

}