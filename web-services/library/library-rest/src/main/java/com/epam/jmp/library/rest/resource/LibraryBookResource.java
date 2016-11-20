package com.epam.jmp.library.rest.resource;

import com.epam.jmp.library.entity.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Api(hidden=true, value = "/books", description = "Operations about the books", position = 1)
public interface LibraryBookResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Book> getAllBooks(@QueryParam("author") String author);

    @GET
    @Path("/{isbn}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Gets library book by ISBN number", response = Book.class)
    public Book getBook(@PathParam("isbn") String isbn);

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createBook(Book book);

    @PUT
    @Path("/{isbn}")
    public void updateBook(Book newBook);

    @DELETE
    @Path("/{isbn}")
    public void deleteBook(@PathParam("isbn") String isbn);

}
