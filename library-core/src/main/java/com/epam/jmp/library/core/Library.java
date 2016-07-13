package com.epam.jmp.library.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Library {

	private String name;
	private List<Book> books;
	
	public Library(String name) {
		this.name= name;
		this.books = new LinkedList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public void removeBook(Book book) {
		books.remove(book);
	}
	
	public List<Book> getAllBooks() {
		return new ArrayList<>(books);
	}
	
	public long count(Predicate<Book> condition) {
		return books.stream().filter(condition).count();
	}
	
}
