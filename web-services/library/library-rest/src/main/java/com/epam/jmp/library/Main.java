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

    public static HttpServer startServer() {
        ResourceConfig resCofig = ResourceConfig.forApplicationClass(LibraryApplication.class);
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resCofig);
    }

    static {
        Library library1 = new Library("J.K.Rowling Library");
        library1.setDescription("J.K.Rowling Library is full of Harry Potter books");
        library1.addBook(new BookBuilder("9788700631625").withName("Harry Potter 1").withAuthor("J.K.Rowling").buld());
        library1.addBook(new BookBuilder("9788700631654").withName("Harry Potter 2").withAuthor("J.K.Rowling").buld());
        library1.addBook(new BookBuilder("9788700631353").withName("Harry Potter 3").withAuthor("J.K.Rowling").buld());
        library1.addBook(new BookBuilder("9788700635456").withName("Harry Potter 4").withAuthor("J.K.Rowling").buld());
        library1.addBook(new BookBuilder("9788700631333").withName("Harry Potter 5").withAuthor("J.K.Rowling").buld());
        try {
            (new LibraryServiceImpl()).createLibrary(library1);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

