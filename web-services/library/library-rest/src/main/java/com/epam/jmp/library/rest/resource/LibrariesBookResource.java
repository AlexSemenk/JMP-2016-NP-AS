package com.epam.jmp.library.rest.resource;

import com.epam.jmp.library.entity.Book;
import com.epam.jmp.library.entity.Library;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class LibrariesBookResource {

    private Library library;

    public LibrariesBookResource(Library library) {
        this.library = library;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Book> getBooks() {
        return library.getAllBooks();
    }

    @GET
    @Path("/{isbn}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Book getBook(@PathParam("isbn") String isbn) {
        return library.getBook(isbn);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createBook(Book book) {
        if (!library.hasBook(book.getIsbn())) {
            library.addBook(book);
        }
    }

    @PUT
    @Path("/{isbn}")
    public void updateBook(Book newBook) {
        Book oldBook = library.getBook(newBook.getIsbn());
        if (oldBook != null) {
            oldBook.setName(newBook.getName());
            oldBook.setAuthor(newBook.getAuthor());
            oldBook.setText(newBook.getText());
        }
    }

}
