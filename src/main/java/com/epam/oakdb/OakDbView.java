package com.epam.oakdb;

import java.util.Collection;
import java.util.function.Predicate;

public interface OakDbView<T> {

	String getName();
	Collection<T> selectAll();
	Collection<T> selectWhere(Predicate<T> condition);
	
}
