package com.epam.jmp.library.entity;

public class Author {

	private String pseudonym;
	private String firstName;
	private String lastName;

	public Author(String pseudonym) {
		this.pseudonym = pseudonym;
	}
	
	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return pseudonym;
	}
	
}
