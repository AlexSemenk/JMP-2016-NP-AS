package com.epam.jmp.classloader;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class ClassUtilsTest {
	
	@Test
	public void testObjectIsStateless() {
		assertTrue(ClassUtils.isStateless(Object.class));
	}
	
	@Test
	public void testObjectArrayIsStateless() {
		assertTrue(ClassUtils.isStateless(Object[].class));
	}
	
	@Test
	public void testClassUtilsIsStateless() {
		assertTrue(ClassUtils.isStateless(ClassUtils.class));
	}
	
	@Test
	public void testIntegerIsStateless() {
		assertTrue(ClassUtils.isStateless(Integer.class));
	}
	
	@Test
	public void testAtomicIntegerIsNotStateless() {
		assertFalse(ClassUtils.isStateless(AtomicInteger.class));
	}
	
	@Test
	public void testLinkedListIsNotStateless() {
		assertFalse(ClassUtils.isStateless(LinkedList.class));
	}
	
}