package com.epam.jmp.jdbc.copy.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Random;

public class SqlRandom {

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
	
	public String nextVarchar(int maxsize) {
		return nextChar(random.nextInt(maxsize + 1));
	}
	
	public String nextChar(int size) {
		char[] data = new char[size];
		for(int i=0; i<size; i++) {
			data[i] = nextChar();
		}
		return String.valueOf(data);
	}
	
	private char nextChar() {
		switch(random.nextInt(3)) {
		case 0:
			return (char)('0' + random.nextInt('9' - '0'));
		case 1:
			return (char)('a' + random.nextInt('z' - 'a'));
		case 2:
		default:
			return (char)('A' + random.nextInt('Z' - 'A'));
		}
	}
	
	public Timestamp nextTimestamp() {
		long millis = Math.abs(random.nextLong()) % System.currentTimeMillis();
		return Timestamp.from(Instant.ofEpochMilli(millis));
	}
	
	public Date nextDate() {
		LocalDate localDate = LocalDate.of(1970, 1, 1).plusDays(random.nextInt(365 * 35));
		return Date.valueOf(localDate);
	}
	
}
