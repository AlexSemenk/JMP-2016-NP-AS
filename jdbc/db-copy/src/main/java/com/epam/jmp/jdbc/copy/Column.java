package com.epam.jmp.jdbc.copy;

public class Column implements Comparable<Column> {

	private String name;
	private String type;
	private int size;
	
	public Column() {
		
	}
	
	public Column(String name, String type, int size) {
		this.name = name;
		this.type = type;
		this.size=  size;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public int compareTo(Column other) {
		return this.name.compareTo(other.name);
	}
	
	@Override
	public String toString() {
		return name + " " + type + " " + size;
	}
	
}
