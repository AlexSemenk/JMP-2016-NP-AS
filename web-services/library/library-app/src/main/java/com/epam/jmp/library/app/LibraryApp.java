package com.epam.jmp.library.app;

import java.util.List;

import com.epam.jmp.library.entity.Author;
import com.epam.jmp.library.entity.Book;
import com.epam.jmp.library.entity.Library;
import com.epam.jmp.library.stat.BookStatistics;

public class LibraryApp {

	private Library library;
	private BookStatistics statistic;
	
	public LibraryApp() {
		this.library = new Library("AppLibrary");
		this.statistic = new BookStatistics(library);
	}
	
	public void addBook(Book book) {
		library.addBook(book);
	}
	
	public void removeBook(Book book) {
		library.removeBook(book);
	}
	
	public void printAllBooks() {
		List<Book> books = library.getAllBooks();
		int idx = 1;
		for(Book book : books) {
			System.out.print(String.format("%d) %s\n", idx++, book.toString()));
		}
	}
	
	public void printAuthorBooksNumber(Author author) {
		long booksNumber = statistic.countBooksOfAuthor(author);
		System.out.println("Author: " + author.toString() + "; books number: " + booksNumber); 
	}
	
}
