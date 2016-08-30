package com.epam.jmp.library.stat;

import java.util.Objects;

import com.epam.jmp.library.core.Author;
import com.epam.jmp.library.core.Library;

public class BookStatistics {

	private Library library;
	
	public BookStatistics(Library library) {
		Objects.requireNonNull(library);
		this.library = library;
	}
	
	public long countAllBooks() {
		return library.count(book -> true);
	}
	
	public long countBooksOfAuthor(Author author) {
		return library.count(book -> author.getPseudonym().equals(book.getAuthor()));
	}
	
	public long counLongBooks() {
		return library.count(book -> book.getText().length() > 1000);
	}
	
}
