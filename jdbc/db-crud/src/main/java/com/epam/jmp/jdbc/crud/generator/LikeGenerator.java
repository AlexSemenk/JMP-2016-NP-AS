package com.epam.jmp.jdbc.crud.generator;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Random;

import com.epam.jmp.jdbc.crud.entity.Like;

public class LikeGenerator implements Iterator<Like> {

	private long i;
	private int userid;
	private int postid;
	
	private final long likesNumber;
	private final int  usersNumber;
	private final int  postsNumber;
	
	private final Random random;
	private final LocalDateTimeGenerator timestampGenerator;
	
	public LikeGenerator(long likesNumber, long usersNumber, long postsNumber) {
		this.i = 0L;
		this.userid = 1;
		this.postid = 1;
		this.likesNumber = likesNumber;
		this.usersNumber = (int)usersNumber;
		this.postsNumber = (int)postsNumber;
		this.random = new Random();
		LocalDateTime min = LocalDateTime.of(2014, 5, 1, 0, 0);
		LocalDateTime max = LocalDateTime.of(2016, 5, 1, 0, 0);
		this.timestampGenerator = new LocalDateTimeGenerator(min, max);
	}
	
	@Override
	public boolean hasNext() {
		return i < likesNumber && (userid < usersNumber || postid < postsNumber);
	}

	@Override
	public Like next() {
		do {
			incUserPostPair();
		} while (random.nextDouble() < 0.2);
		i++;
		Like like = new Like();
		like.setPostId(postid);
		like.setUserId(userid);
		like.setTimestamp(timestampGenerator.next());
		return like;
	}

	private void incUserPostPair() {
		postid++;
		if (postid > postsNumber) {
			postid = 1;
			userid++;
		}
	}
	
}
