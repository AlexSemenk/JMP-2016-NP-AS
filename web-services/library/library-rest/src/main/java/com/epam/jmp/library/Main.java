package com.epam.jmp.library;

import java.io.IOException;
import java.net.URI;

import com.epam.jmp.library.entity.BookBuilder;
import com.epam.jmp.library.entity.Library;
import com.epam.jmp.library.rest.LibraryApplication;
import com.epam.jmp.library.service.exception.ServiceException;
import com.epam.jmp.library.service.impl.LibraryServiceImpl;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {

    public static final String BASE_URI = "http://localhost:8080/jmp/";

    static {
        Library library1 = new Library("Alexander Library");
        library1.setDescription("Some Library");
        library1.addBook(new BookBuilder("9788700631625").withName("Harry Potter 1").withAuthor("J.K.Rowling").buld());
        library1.addBook(new BookBuilder("9788700631654").withName("Harry Potter 2").withAuthor("J.K.Rowling").buld());
        library1.addBook(new BookBuilder("9788700631353").withName("Harry Potter 3").withAuthor("J.K.Rowling").buld());
        library1.addBook(new BookBuilder("9788700635456").withName("Harry Potter 4").withAuthor("J.K.Rowling").buld());
        library1.addBook(new BookBuilder("9788700631333").withName("Harry Potter 5").withAuthor("J.K.Rowling").buld());
        library1.addBook(new BookBuilder("9788701532356").withName("The Lord of the Rings").withAuthor("J.R.R.Tolkien").buld());
        try {
            (new LibraryServiceImpl()).createLibrary(library1);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ResourceConfig resConfig = ResourceConfig.forApplicationClass(LibraryApplication.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resConfig);
        System.out.println("Jersey app started.");
        System.out.println(String.format("WADL available at %sapplication.wadl", BASE_URI));
        System.out.println("Hit enter to stop it...");
        System.in.read();
        server.stop();
    }
}

