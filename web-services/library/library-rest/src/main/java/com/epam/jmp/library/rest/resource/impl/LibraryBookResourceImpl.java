package com.epam.jmp.library.rest.resource.impl;

import com.epam.jmp.library.entity.Book;
import com.epam.jmp.library.entity.Library;
import com.epam.jmp.library.rest.resource.LibraryBookResource;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

public class LibraryBookResourceImpl implements LibraryBookResource {

    private Library library;

    public LibraryBookResourceImpl(Library library) {
        this.library = library;
    }

    @Override
    public List<Book> getAllBooks() {
        return library.getAllBooks();
    }

    @Override
    public Book getBook(String isbn) {
        Book book = library.getBook(isbn);
        if (book == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return book;
    }

    @Override
    public void createBook(Book book) {
        if (!library.hasBook(book.getIsbn())) {
            library.addBook(book);
        }
    }

    @Override
    public void updateBook(Book newBook) {
        Book book = library.getBook(newBook.getIsbn());
        if (book == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        book.setName(newBook.getName());
        book.setAuthor(newBook.getAuthor());
        book.setText(newBook.getText());
    }

    @Override
    public void deleteBook(String isbn) {
        Book book = library.getBook(isbn);
        if (book == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        library.removeBook(book);
    }

}
