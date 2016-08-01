package com.epam.jmp.library.app;

import com.epam.jmp.library.core.Author;
import com.epam.jmp.library.core.BookBuilder;

public class Main {
	
	public static void main(String[] args) {
		LibraryApp app = new LibraryApp();
		app.addBook(new BookBuilder("9788700631625").withName("Harry Potter 1").withAuthor("J.K.Rowling").buld());
		app.addBook(new BookBuilder("9788700631654").withName("Harry Potter 2").withAuthor("J.K.Rowling").buld());
		app.addBook(new BookBuilder("9788700631353").withName("Harry Potter 3").withAuthor("J.K.Rowling").buld());
		app.addBook(new BookBuilder("9788700635456").withName("Harry Potter 4").withAuthor("J.K.Rowling").buld());
		app.addBook(new BookBuilder("9788700631333").withName("Harry Potter 5").withAuthor("J.K.Rowling").buld());
		app.printAuthorBooksNumber(new Author("J.K.Rowling"));
		app.printAllBooks();
	}
	
}