package com.epam.jmp.library.rest.resource;

import com.epam.jmp.library.entity.Library;
import com.epam.jmp.library.service.exception.ServiceException;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/libraries")
@Api(value = "/libraries", description = "Operations about libraries")
public interface LibraryResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Find all existing libraries", notes = "Returns a list of libraries", response = Library.class, responseContainer="List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All libraries are returned")})
    List<Library> getLibraries() throws ServiceException;

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Find library by ID", notes = "Returns a single library", response = Library.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Library not found")})
    Library getLibrary(@ApiParam(value = "ID of the library to bee fetched", example = "396152", required = true) @PathParam("id") Long id) throws ServiceException;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Create new library")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Library is created"),
            @ApiResponse(code = 405, message = "Invalid input")})
    Response createLibrary(@ApiParam(value = "Library object to be created", required = true) Library library) throws ServiceException;

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Update existing library")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Library is updated"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Library not found"),
            @ApiResponse(code = 405, message = "Validation exception")})
    void updateLibrary(@ApiParam(value = "ID of the library to bee updated", example = "396152", required = true) @PathParam("id") Long id,
                       @ApiParam(value = "Updated library object", required = true) Library newLibrary) throws ServiceException;

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Delete library")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Library not found")})
    void deleteLibrary(@ApiParam(value = "ID of the library to delete", example = "396152", required = true) @PathParam("id") Long id) throws ServiceException;

    @Path("/{id}/books")
    @ApiOperation(value = "Gets library books", response = LibraryBookResource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Library not found")})
    LibraryBookResource getBookResource(@ApiParam(value = "ID of the library", example = "396152", required = true) @PathParam("id") Long id) throws ServiceException;

}