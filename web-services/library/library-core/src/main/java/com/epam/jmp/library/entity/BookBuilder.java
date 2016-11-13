package com.epam.jmp.library.entity;

public class BookBuilder {

	private String isbn;
	private String name;
	private String author;
	private String text;
	
	public BookBuilder(String isbn) {
		this.isbn = isbn;
	}
	
	public BookBuilder withName(String name) {
		this.name = name;
		return this;
	}
		
	public BookBuilder withAuthor(String author) {
		this.author = author;
		return this;
	}
	
	public BookBuilder withText(String text) {
		this.text = text;
		return this;
	}
	
	public Book buld() {
		Book book = new Book();
		book.setIsbn(isbn);
		book.setName(name);
		book.setAuthor(author);
		book.setText(text);
		return book;
	}
	
}
