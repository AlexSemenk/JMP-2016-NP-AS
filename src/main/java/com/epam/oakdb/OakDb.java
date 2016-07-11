package com.epam.oakdb;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class OakDb<T> implements OakDbView<T>, Iterable<T> {
	
	private String name;
	private Set<T> data;
	
	OakDb(String name, Class<T> type) {
		this.name = name;
		this.data = new HashSet<>();
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		return this == other;
	}

	@Override
	public Iterator<T> iterator() {
		return data.iterator();
	}
	
	@Override
	public Collection<T> selectAll() {
		return new LinkedList<>(data);
	}
	
	@Override
	public Collection<T> selectWhere(Predicate<T> condition) {
		List<T> ret = new LinkedList<>();
		for(T entity : data) {
			if (condition.test(entity)) {
				ret.add(entity);
			}
		}
		return ret;
	}

	public void insert(T value) {
		if (data.contains(value)) {
			throw new IllegalArgumentException("");
		} else {
			data.add(value);
		}
	}
	
	public void insert(Collection<T> collection) {
		data.addAll(collection);
	}
	
	@SuppressWarnings("unchecked")
	public void insert(T ... values) {
		this.insert(Arrays.asList(values));
	}
	
	public void trancate() {
		data.clear();
	}
		
}
