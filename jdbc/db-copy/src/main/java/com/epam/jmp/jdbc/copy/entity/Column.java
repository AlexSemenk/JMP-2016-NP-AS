package com.epam.jmp.jdbc.copy.entity;

public class Column implements Comparable<Column> {

	private String name;
	private TypeInfo typeInfo;
	private int size;

	public Column() {

	}

	public Column(String name, TypeInfo typeInfo, int size) {
		super();
		this.name = name;
		this.typeInfo = typeInfo;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TypeInfo getTypeInfo() {
		return typeInfo;
	}

	public void setTypeInfo(TypeInfo typeInfo) {
		this.typeInfo = typeInfo;
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

}
