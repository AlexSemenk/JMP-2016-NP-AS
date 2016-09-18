package com.epam.jmp.jdbc.crud.generator;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Random;

public class LocalDateTimeGenerator implements Iterator<LocalDateTime> {

	private final LocalDateTime start;
	private final int maxDaysRange;
	private final Random random;
	
	LocalDateTimeGenerator(LocalDateTime min, LocalDateTime max) {
		this.start = min;
		this.maxDaysRange = (int)DAYS.between(min, max) + 1;
		this.random = new Random();
	}
	
	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public LocalDateTime next() {
		return start.plusDays(random.nextInt(maxDaysRange));
	}

}
