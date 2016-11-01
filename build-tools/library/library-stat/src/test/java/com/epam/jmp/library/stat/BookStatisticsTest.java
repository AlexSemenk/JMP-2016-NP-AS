package com.epam.jmp.library.stat;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.epam.jmp.library.core.Author;
import com.epam.jmp.library.core.Book;
import com.epam.jmp.library.core.Library;

@RunWith(JUnit4.class)
public class BookStatisticsTest {

	private static Library library;
	private static BookStatistics statistics;

	@BeforeClass
	public static void init() {
		Book harryPotter1 = new Book();
		harryPotter1.setIsbn("9788700631625");
		harryPotter1.setName("Harry Potter 1");
		harryPotter1.setAuthor("J.K.Rowling");
		
		Book harryPotter2 = new Book();
		harryPotter2.setIsbn("9788700631654");
		harryPotter2.setName("Harry Potter 2");
		harryPotter2.setAuthor("J.K.Rowling");
		
		Book harryPotter3 = new Book();
		harryPotter3.setIsbn("9788700631353");
		harryPotter3.setName("Harry Potter 3");
		harryPotter3.setAuthor("J.K.Rowling");

		Book harryPotter4 = new Book();
		harryPotter4.setIsbn("9788700635456");
		harryPotter4.setName("Harry Potter 4");
		harryPotter4.setAuthor("J.K.Rowling");
		
		Book theLordOfTheRings = new Book();
		theLordOfTheRings.setIsbn("9780007117116");
		theLordOfTheRings.setName("The Lord of the Rings");
		theLordOfTheRings.setAuthor("J.R.R.Tolkien");
		char[] theLordOfTheRingsText = new char[2000];
		Arrays.fill(theLordOfTheRingsText, 'T');
		theLordOfTheRings.setText(String.valueOf(theLordOfTheRingsText));
		
		library = new Library("test1");
		library.addBook(harryPotter1);
		library.addBook(harryPotter2);
		library.addBook(harryPotter3);
		library.addBook(harryPotter4);
		library.addBook(theLordOfTheRings);
		
		statistics = new BookStatistics(library);
	}

	@Test
	public void testCountAllBooks() {
		long allBooksNumber = statistics.countAllBooks();
		assertEquals(5, allBooksNumber);
	}

	@Test
	public void testCountBooksOfAuthor() {
		Author jkRowling = new Author("J.K.Rowling");
		long allBooksNumber = statistics.countBooksOfAuthor(jkRowling);
		assertEquals(4, allBooksNumber);
	}

	@Test
	public void testCounLongBooks() {
		long allBooksNumber = statistics.counLongBooks();
		assertEquals(1, allBooksNumber);
	}

}
