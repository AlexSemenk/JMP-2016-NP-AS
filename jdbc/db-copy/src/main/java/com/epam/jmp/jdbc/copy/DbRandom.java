package com.epam.jmp.jdbc.copy;

import java.util.Random;

public class DbRandom {

	private Random random = new Random();
	
	public long nextBigint() {
		return random.nextLong();
	}
	
	public int nextInt() {
		return random.nextInt();
	}
	
	public int nextMediumInt() {
		return random.nextInt() >> 8;
	}
	
	public int nextSmallInt() {
		return random.nextInt() >> 16;
	}
	
	public int nextTinyInt() {
		return random.nextInt() >> 24;
	}
	
	public String nextVarchar(int size) {
		char[] data = new char[size];
		for(int i=0; i<size; i++) {
			int j = random.nextInt('Z' - 'A');
			data[i] = (char)('A' + (char)j);
		}
		return String.valueOf(data);
	}
	
	public static void main(String[] args) {
		
	}
	
}
