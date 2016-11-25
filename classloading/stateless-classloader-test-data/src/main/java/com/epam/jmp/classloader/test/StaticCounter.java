package com.epam.jmp.classloader.test;

public class StaticCounter {

	private static int count = 0;
	
	public static void inc() {
		count++;
	}
	
	public static int getCount() {
		return count;
	}
	
}
