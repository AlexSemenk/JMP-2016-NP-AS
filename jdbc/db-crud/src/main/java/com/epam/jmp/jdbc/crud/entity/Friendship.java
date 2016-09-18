package com.epam.jmp.jdbc.crud.entity;

import java.time.LocalDateTime;

public class Friendship {

	private long userId1;
	private long userId2;
	private LocalDateTime timestamp;

	public long getUserId1() {
		return userId1;
	}

	public void setUserId1(long userid1) {
		this.userId1 = userid1;
	}

	public long getUserId2() {
		return userId2;
	}

	public void setUserId2(long userid2) {
		this.userId2 = userid2;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
