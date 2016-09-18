package com.epam.jmp.jdbc.crud.generator;

import java.time.LocalDate;
import java.util.Iterator;

import com.epam.jmp.jdbc.crud.entity.User;

public class UserGenerator implements Iterator<User> {

	private static final String[] NAMES = { "Alex", "Bill", "Dennis", "George", "Harry", "John", "Larry", "Leonard",
			"Mark", "Oliver", "Peter", "Ashley", "Emily", "Angelina", "Emma", "Jennifer", "Jessica", "Julia" };

	private long i;
	private final long number;
	private LocalDate birthdate;
	
	public UserGenerator(long number) {
		this.i = 0;
		this.birthdate = LocalDate.of(1970, 1, 1).minusDays(1);
		this.number = number;
	}
	
	@Override
	public boolean hasNext() {
		return i < number;
	}

	@Override
	public User next() {
		i = i + 1;
		birthdate = birthdate.plusDays(5);
		User user = new User();
		user.setId(i);
		user.setName(NAMES[(int)(i%NAMES.length)]);
		user.setSurname("Surname" + i);
		user.setBirthdate(birthdate);
		return user;
	}
	
}
