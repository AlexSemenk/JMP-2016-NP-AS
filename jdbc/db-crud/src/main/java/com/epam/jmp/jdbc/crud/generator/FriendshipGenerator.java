package com.epam.jmp.jdbc.crud.generator;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Random;

import com.epam.jmp.jdbc.crud.entity.Friendship;

public class FriendshipGenerator implements Iterator<Friendship> {
	
	private long i;
	private long userid1;
	private long userid2;
	
	private final long friendshipsNumber;
	private final long usersNumber;
	private final LocalDateTimeGenerator timestampGenerator;
	private final Random random;
	
	public FriendshipGenerator(long friendshipsNumber, long usersNumber) {
		this.i = 0L;
		this.userid1 = 1L;
		this.userid2 = 1L;
		this.friendshipsNumber = friendshipsNumber;
		this.usersNumber = usersNumber;
		LocalDateTime min = LocalDateTime.of(2014, 5, 1, 0, 0);
		LocalDateTime max = LocalDateTime.of(2016, 5, 1, 0, 0);
		this.timestampGenerator = new LocalDateTimeGenerator(min, max);
		this.random = new Random();
	}
	
	@Override
	public boolean hasNext() {
		return i < friendshipsNumber && (userid1 < usersNumber || userid2 < usersNumber);
	}

	@Override
	public Friendship next() {
		do {
			incFriendsPair();
		} while (random.nextDouble() < 0.7);
		i++;
		Friendship friendship = new Friendship();
		friendship.setUserId1(userid1);
		friendship.setUserId2(userid2);
		friendship.setTimestamp(timestampGenerator.next());
		return friendship;
	}

	private void incFriendsPair() {
		userid2++;
		if (userid1 == userid2) {
			userid2++;
		}
		if (userid2 > usersNumber) {
			userid2 = 1;
			userid1++;
		}
	}
	
}
