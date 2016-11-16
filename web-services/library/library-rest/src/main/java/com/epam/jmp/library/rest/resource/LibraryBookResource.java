package com.epam.jmp.library.rest.resource;

import com.epam.jmp.library.entity.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public interface LibraryBookResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Book> getAllBooks();

    @GET
    @Path("/{isbn}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
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
