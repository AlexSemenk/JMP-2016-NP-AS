package com.epam.jmp.jdbc.crud.generator;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Random;

import com.epam.jmp.jdbc.crud.entity.Post;

public class PostGenerator implements Iterator<Post> {
	
	private long i;
	
	private final long postsNumber;
	private final int usersNumber;
	private final Random random;
	private final LocalDateTimeGenerator timestampGenerator;
	
	public PostGenerator(long postsNumber, long usersNumber) {
		this.i = 0;
		this.postsNumber = postsNumber;
		this.usersNumber = (int)usersNumber;
		this.random = new Random();
		LocalDateTime min = LocalDateTime.of(2010, 9, 1, 0, 0);
		LocalDateTime max = LocalDateTime.of(2013, 4, 1, 0, 0);
		this.timestampGenerator = new LocalDateTimeGenerator(min, max);
	}
	
	@Override
	public boolean hasNext() {
		return i < postsNumber;
	}

	@Override
	public Post next() {
		i++;
		Post post = new Post();
		post.setId(i);
		post.setUserId(random.nextInt(usersNumber) + 1);
		post.setText("Text " + i);
		post.setTimestamp(timestampGenerator.next());
		return post;
	}
	
}
