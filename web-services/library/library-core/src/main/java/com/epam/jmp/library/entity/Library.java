package com.epam.jmp.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.util.function.Predicate;

public class Library {

	private long id;
	private String name;
	private String description;
	private List<Book> books;

	public Library() {
		this.books = new LinkedList<>();
	}

	public Library(String name) {
		this();
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addBook(Book book) {
		books.add(book);
	}
	
	public void removeBook(Book book) {
		books.remove(book);
	}

	@JsonIgnore
	public List<Book> getAllBooks() {
		return new ArrayList<>(books);
	}

	public Book getBook(String isbn) {
		return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().get();
	}

	public boolean hasBook(String isbn) {
		return books.stream().anyMatch(b -> b.getIsbn().equals(isbn));
	}

	public long count(Predicate<Book> condition) {
		return books.stream().filter(condition).count();
	}

	@Override
	public int hashCode() {
		return (int)id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Library library = (Library) o;
		return id == library.id;
	}
}
